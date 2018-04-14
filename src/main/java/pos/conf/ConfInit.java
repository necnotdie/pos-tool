package pos.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pos.windows.Window;

public class ConfInit {
	public static void init(List<Map<Object, Object>> ZDS,Map<String, List<String>> LIBS){
		File file = new File(System.getProperty("user.dir") + "\\pos.xml");
		try {
			if(file.exists()){
				DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
				DocumentBuilder dombuilder = domfac.newDocumentBuilder();
				InputStream is = new FileInputStream(file);
				Document doc = dombuilder.parse(is);
				Element root = doc.getDocumentElement();
				NodeList items = root.getChildNodes();
				if (items != null) {
					for (int i = 0; i < items.getLength(); i++) {
						Node item = items.item(i);
						if ("tables".equals(item.getNodeName())) {
							NodeList tables = item.getChildNodes();
							if (tables != null) {
								for (int j = 0; j < tables.getLength(); j++) {
									Node table = tables.item(j);
									if ("table".equals(table.getNodeName())) {
										Map<Object, Object> ZD = new HashMap<Object, Object>();
										String classPath = table.getAttributes().getNamedItem("class").getNodeValue();
										String name = table.getAttributes().getNamedItem("name").getNodeValue();
										ZD.put("classPath", classPath);
										ZD.put("name", name);
										NodeList nodes = table.getChildNodes();
										if(nodes != null){
											List<String> registerInfo = new ArrayList<String>();
											for (int k = 0; k < nodes.getLength(); k++) {
												Node node = nodes.item(k);
												if ("data".equals(node.getNodeName())) {
													Map<String, String> data = new HashMap<String, String>();
													data.put("name", node.getAttributes().getNamedItem("name").getNodeValue());
													data.put("type", node.getAttributes().getNamedItem("type").getNodeValue());
													ZD.put("data", data);
												}else if("register-info".equals(node.getNodeName())){
													registerInfo.add(node.getNodeValue());
												}
											}
											ZD.put("register-info", registerInfo);
										}
										ZDS.add(ZD);
									}
								}
							}
						}else if("public-libs".equals(item.getNodeName())){
							NodeList infos = item.getChildNodes();
							if(infos!=null){
								List<String> registerInfo = new ArrayList<String>();
								for (int j = 0; j < infos.getLength(); j++) {
									Node info = infos.item(j);
									registerInfo.add(info.getNodeValue());
								}
								LIBS.put("register-info", registerInfo);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @描述 调用此静态方法获取img包内的图片
	 * @param flieName
	 * @param imagePath
	 * @return ImageIcon
	 */
	public static ImageIcon getImage(String flieName, String imagePath) {
		File imageFile = null;
		ZipFile zipFile = null;
		InputStream inputStream = null;
		ImageIcon icon = null;
		try {
			// byte[] b = null;
			File dir = new File(System.getProperty("user.dir") + "\\image");
			File[] files = dir.listFiles();
			for (File file : files) {
				if (flieName.toLowerCase().trim()
						.equals(file.getName().toLowerCase().trim())) {
					imageFile = file;
				}
			}
			zipFile = new ZipFile(imageFile);
			Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
			ZipEntry zipFileEntry = null;
			while (enumeration.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
				if ((imagePath.replaceAll("\\\\", "/").toLowerCase().trim())
						.equals(zipEntry.getName().toLowerCase().trim())) {
					zipFileEntry = zipEntry;
				}
			}
			inputStream = zipFile.getInputStream(zipFileEntry);
			// int length = 0;
			// while(inputStream.read()!=-1){
			// length++;
			// }
			// b = new byte[length];
			// zipFile.getInputStream(zipFile.getEntry(imageName)).read(b);
			icon = new ImageIcon(ImageIO.read(inputStream));
			zipFile.close();
			return icon;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Window.rootPanel,
					"加载图片出错！\r\n原因：" + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
		return icon;
	}

	public static ImageIcon getImage(Class<?> clazz, String imagePath) {
		ImageIcon icon = null;
		try {
			System.out.println(clazz.getResourceAsStream(imagePath));
			icon = new ImageIcon(ImageIO.read(clazz
					.getResourceAsStream(imagePath)));
			return icon;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(Window.rootPanel,
					"加载图片出错！\r\n原因：" + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
		return icon;
	}

	public static String getSet() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\conf\\model-file.properties"));
		return properties.getProperty("model");
	}

	public static void setSet(String action) throws Exception {
		// File file = new
		// File(ConfInit.class.getResource("model-file.properties").toURI());
		FileOutputStream oFile = new FileOutputStream(
				System.getProperty("user.dir")
						+ "\\conf\\model-file.properties", false);
		Properties properties = new Properties();
		properties.setProperty("model", action);
		properties.store(oFile, "程序设置");
	}

	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir")
				+ "\\image\\window.jpg");
		ImageIcon icon2 = ConfInit
				.getImage("System.IMG", "LOADING\\window.JPG");
		System.out.println("========================="
				+ icon.getImage().toString());
		System.out.println("========================="
				+ icon2.getImage().getSource());
	}
}
