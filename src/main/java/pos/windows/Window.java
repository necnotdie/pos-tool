package pos.windows;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross;
import org.jb2011.lnf.windows2.Windows2LookAndFeel;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;

import pos.annotation.Close;
import pos.annotation.Init;
import pos.annotation.Resource;
import pos.conf.ConfInit;
import pos.summer.Summer;
import pos.tools.FileLockManager;
import pos.tools.JNIWindowUtilProxy;
import pos.tools.Table;
import pos.view.WindowAboutDialog;
import pos.view.WindowJMenuBar;
import pos.view.WindowSetDialog;
import pos.view.WindowSoftDialog;

/**
 * @���� �Ŷ�
 * @���� pos������ά�����
 * @version 1.0
 */
public class Window {

	// private static ConfInit confInit = new ConfInit();
//	private static Component component;
	private static List<Map<Object, Object>> ZDS = new ArrayList<Map<Object,Object>>();
	private static Map<String, List<String>> LIBS = new HashMap<String, List<String>>();
	private static JWindowFrame jFrame;
	public static JPanel rootPanel;
	private static JProgressBar jWindowBar;
	private static JLabel jWindowLabel;
	private static int value = 0;
	private static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
//	private static FileLockManager fileLockManager = new FileLockManager(
//			System.getProperty("user.dir") + "\\conf\\Lock.key");
	private static List<File> jars = new ArrayList<File>();
	private static Summer summer;
	@Resource(name = "Window.Dialog")
	private static WindowSetDialog setDialog;
	@Resource(name = "Window.JMenuBar")
	private static WindowJMenuBar jMenuBar;
	@SuppressWarnings("unused")
	@Resource(name = "Window.AboutDialog")
	private static WindowAboutDialog aboutDialog;
	@SuppressWarnings("unused")
	@Resource(name = "Window.SoftDialog")
	private static WindowSoftDialog softDialog;
	public static void main(String[] args) {
//		try {
//			if (!fileLockManager.Lock()) {
//				File file = new File(System.getProperty("user.dir")
//						+ "\\conf\\week.UP");
//				file.createNewFile();
//				System.exit(0);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.load(System.getProperty("user.dir")+System.getProperty("file.separator")+"lib"+System.getProperty("file.separator")+("JNIWindowUtilProxy.dll"));
		addJar();
		try {
			UIManager.setLookAndFeel(new LunaLookAndFeel());
			Class clazz = Class.forName("org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross");
			System.out.println(LookAndFeel.class.isAssignableFrom(clazz));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String outPath = System.getProperty("out.path");
			if(outPath!=null){
				File outfile = new File(outPath);
				if(outfile.exists()){
					PrintStream out = new PrintStream(outfile);
				    System.setOut(out);
				}
			}
			String errPath = System.getProperty("err.path");
			if(errPath!=null){
				File errfile = new File(errPath);
				if(errfile.exists()){
					PrintStream err = new PrintStream(errfile);
				    System.setErr(err);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFrame jWindow = new JFrame("pos������ά�����");
		jWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jWindow.addWindowListener(new WindowAdapter() {
//			// ��д���ڹر��¼�
//			@Override
//			public void windowClosing(WindowEvent arg0) {
//				System.out.println("�˳���");
//				try {
//					fileLockManager.unLock();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				System.exit(0);
//			}
//		});
		jWindow.setUndecorated(true);
		jWindow.setResizable(false);
		jWindowBar = new JProgressBar();
		jWindowLabel = new JLabel();
		JLayeredPane jLayeredPane = new JLayeredPane();
		Dimension dimension = new Dimension(400, 240);  
		jLayeredPane.setPreferredSize(dimension);
		ImageIcon icon = ConfInit.getImage(ConfInit.class, "window.jpg");
		icon.setImage(icon.getImage().getScaledInstance(dimension.width,
				dimension.height, Image.SCALE_DEFAULT));
		JLabel bg = new JLabel(icon);
		bg.setBounds(0, 0, dimension.width, dimension.height);
		jWindowBar.setStringPainted(false);
		jWindowBar.setBounds(0, 190, dimension.width, 20);
		jWindowBar.setVisible(false);
		jWindowLabel.setBounds(0, 215, dimension.width, 30);
		jLayeredPane.add(bg, new Integer(0));
		jLayeredPane.add(jWindowBar, new Integer(1));
		jLayeredPane.add(jWindowLabel, new Integer(2));
		jWindow.add(jLayeredPane);
		ImageIcon loading = ConfInit.getImage(ConfInit.class, "LOADING.png");
		jWindow.setIconImage(loading.getImage());
		jWindow.pack();
		jWindow.setLocationRelativeTo(null);
		jWindow.setVisible(true);
		ConfInit.init(ZDS,LIBS);
		jWindowLabel.setText("��ʼ��...");
		summer = Summer.newinstance();
		jWindowLabel.setText("��ʼ����ϣ�");
		if (init()) {
			jWindowBar.setValue(jWindowBar.getMaximum());
			jWindow.dispose();
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			jFrame.setVisible(true);
			System.out.println(jFrame.getRootPane().getContentPane().getSize());
//			autoSize();
		} else {
			System.exit(0);
		}
	}

	private static boolean init() {
		try {
			if(jFrame==null){
				jFrame = new JWindowFrame();
			}
			jFrame.setTitle("pos������ά�����");
			ImageIcon pos = ConfInit.getImage(ConfInit.class, "POS.png");
			jFrame.setIconImage(pos.getImage());
			jFrame.setSize(800, 480);
			GridBagLayout bagLayout = new GridBagLayout();
			jFrame.setLayout(bagLayout);
//			jFrame.setResizable(false);
			jFrame.setLocationRelativeTo(null);
			if(rootPanel==null){
				rootPanel=new JPanel();
			}
			addTable();
			jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			jFrame.addWindowListener(new WindowAdapter() {
				// ��д���ڹر��¼�
				@Override
				public void windowClosing(WindowEvent arg0) {
					String action = null;
					try {
						action = ConfInit.getSet();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if ("default".equals(action)) {
						Object[] selectOption = { "ֱ���˳�", "��С��������" };
						JCheckBox checkBox = new JCheckBox("��סѡ���´β���ѯ��");
						JPanel downPanel = new JPanel();
						downPanel.add(checkBox);
						int select = JOptionPane.showOptionDialog(
								Window.jFrame, downPanel, "�˳�",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null,
								selectOption, selectOption[0]);
						if (0 == select) {
							boolean exitFlag = true;
							List<Object> objects = closeAction();
							for (Object object : objects) {
								boolean flag = (Boolean) object;
								if (!flag) {
									exitFlag = false;
								}
							}
							if (exitFlag) {
								System.out.println("�˳���");
//								try {
//									fileLockManager.unLock();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
								if (checkBox.isSelected()) {
									try {
										ConfInit.setSet("exit");
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								System.exit(0);
							} else {
								int choose = JOptionPane.showConfirmDialog(
										Window.jFrame, "�в����ֹ����������Ƿ�ǿ�ƹرգ�",
										"�ر�", JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE);// ��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
								if (JOptionPane.YES_OPTION == choose) {
									System.out.println("�˳���");
									tray.remove(trayIcon);
//									try {
//										fileLockManager.unLock();
//									} catch (IOException e1) {
//										e1.printStackTrace();
//									}
									System.exit(0);
								}
							}
						} else if (1 == select) {
							Window.jFrame.hideWindow();
							if (checkBox.isSelected()) {
								try {
									ConfInit.setSet("minisize");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					} else if ("exit".equals(action)) {
						boolean exitFlag = true;
						List<Object> objects = closeAction();
						for (Object object : objects) {
							boolean flag = (Boolean) object;
							if (!flag) {
								exitFlag = false;
							}
						}
						if (exitFlag) {
							System.out.println("�˳���");
							tray.remove(trayIcon);
//							try {
//								fileLockManager.unLock();
//							} catch (IOException e1) {
//								e1.printStackTrace();
//							}
							System.exit(0);
						} else {
							int select = JOptionPane.showConfirmDialog(
									Window.jFrame, "�в����ֹ����������Ƿ�ǿ�ƹرգ�", "�ر�",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.WARNING_MESSAGE);// ��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
							if (JOptionPane.YES_OPTION == select) {
								System.out.println("�˳���");
								tray.remove(trayIcon);
//								try {
//									fileLockManager.unLock();
//								} catch (IOException e1) {
//									e1.printStackTrace();
//								}
								System.exit(0);
							}
						}
					} else if ("minisize".equals(action)) {
						jFrame.hideWindow();
					}
				}
			});
			ImageIcon trayImg = ConfInit.getImage(ConfInit.class, "POS.png");// ����ͼ��
			PopupMenu pop = new PopupMenu(); // ���������һ��˵�
			MenuItem set = new MenuItem("����");
			MenuItem show = new MenuItem("��ʾ������");
			MenuItem exit = new MenuItem("�˳�");
			set.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					setDialog.setVisible(true);
				}
			});
			show.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// tray.remove(trayIcon);
					jFrame.showWindow();
					jFrame.setExtendedState(JFrame.NORMAL);
					jFrame.toFront();
				}
			});
			exit.addActionListener(new ActionListener() { // �����˳���
				public void actionPerformed(ActionEvent e) {
					boolean exitFlag = true;
					List<Object> objects = closeAction();
					for (Object object : objects) {
						boolean flag = (Boolean) object;
						if (!flag) {
							exitFlag = false;
						}
					}
					if (exitFlag) {
						System.out.println("�˳���");
						tray.remove(trayIcon);
//						try {
//							fileLockManager.unLock();
//						} catch (IOException e1) {
//							e1.printStackTrace();
//						}
						System.exit(0);
					} else {
						int select = JOptionPane.showConfirmDialog(
								Window.jFrame, "�в����ֹ����������Ƿ�ǿ�ƹرգ�", "�ر�",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE);// ��ʾһ���Ի�����ʵ���Ƿ񸲸�Դ�ļ�
						if (JOptionPane.YES_OPTION == select) {
							System.out.println("�˳���");
							tray.remove(trayIcon);
//							try {
//								fileLockManager.unLock();
//							} catch (IOException e1) {
//								e1.printStackTrace();
//							}
							System.exit(0);
						}
					}
				}
			});
			pop.add(set);
			pop.add(show);
			pop.add(exit);
			trayIcon = new TrayIcon(trayImg.getImage(), jFrame.getTitle(), pop);
			trayIcon.setImageAutoSize(true);
			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) { // �����˫���¼�
					if (e.getClickCount() == 2) {
						// tray.remove(trayIcon); // ��ȥ����ͼ��
						jFrame.showWindow();
						jFrame.setExtendedState(JFrame.NORMAL); // ��ԭ����
						jFrame.toFront();
					}
				}
			});
			jFrame.setJMenuBar(jMenuBar);
			autoSize(rootPanel);
//			Component[] plugz = jFrame.getRootPane().getContentPane()
//					.getComponents();
//			for (Component plug : plugz) {
//				if(plug instanceof JPanel){
//					JPanel plugs = (JPanel) plug;
//					autoSize(plugs);
//				}
//			}
			jFrame.add(rootPanel);
			GridBagConstraints s = new GridBagConstraints();// ����һ��GridBagConstraints��
			s.fill = GridBagConstraints.BOTH;
//			s.insets = new Insets(10, 10, 10, 10);
	    	s.gridwidth = 0;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
			s.gridheight = 1;
			s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
			s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
			s.gridy = 0;
			s.gridx = 0;
			bagLayout.setConstraints(rootPanel, s);
			jWindowLabel.setText("���ز������");
//			weekUp();
			initAction();
			value++;
			jWindowBar.setValue(value);
			jWindowLabel.setText("������ɣ�");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"������������\r\nԭ��"+e.toString());
			return false;
		}
	}

	private static void addJar() {
		File libDir = new File("lib");
		File plugsDir = new File("plugs");
		if (libDir.exists()) {
			if (libDir.isDirectory()) {
				getJars(libDir);
			}
		}
		if (plugsDir.exists()) {
			if (plugsDir.isDirectory()) {
				getJars(plugsDir);
			}
		}
		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL",
					URL.class);
			boolean accessible = method.isAccessible(); // ��ȡ�����ķ���Ȩ��
			if (accessible == false) {
				method.setAccessible(true); // ���÷����ķ���Ȩ��
			}
			// ��ȡϵͳ�������
			URLClassLoader classLoader = (URLClassLoader) ClassLoader
					.getSystemClassLoader();
			for (File jar : jars) {
//				jWindowLabel.setText("����" + jar.getName());
				System.out.println("����" + jar.getName());
				URL url = jar.toURI().toURL();
				method.invoke(classLoader, url);
			}
		} catch (Throwable e){
			e.printStackTrace();
		}
	}

	private static void getJars(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getJars(file);
			} else if (file.isFile()) {
				if (file.getName().endsWith(".jar")) {
					jars.add(file);
				}
			}
		}
	}

	public static void addTable() {
		jWindowBar.setMinimum(0);
		Set<Class<?>> classes = summer.getSummerObject().keySet();
		jWindowBar.setMaximum(classes.size());
		jWindowBar.setVisible(true);
		try {
			for (Class<?> clazz : classes) {
				Set<String> keys = summer.getSummerObject().get(clazz).keySet();
				for (String key : keys) {
					Object object = summer.getSummerObject().get(clazz).get(key);
					if(object instanceof Table){
						Table table = (Table) object;
						Component component = table.view();
						component.setName(object.getClass().getName());
						component.setVisible(true);
						rootPanel.add(component);
						jWindowLabel.setText("����" + clazz.getName() + "��ɣ�");
						value++;
						jWindowBar.setValue(value);
					}
				}
			}
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(Window.jFrame,
					"���س���\r\nԭ��" + e.toString());
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void clearTable(String className) {
		Component[] components = rootPanel.getComponents();
		for (Component component : components) {
			String classPath = component.getName();
			if (!className.equals(classPath)) {
				component.setVisible(false);
//				Window.component = component;
			}
		}
	}

	public static void reload(Component component) {
		System.out.println(component.getName());
		jFrame.add(component);
	}

//	public static void reload() {
//		Component component = Window.component;
//		System.out.println(component.getName());
//		jFrame.add(component);
//		jFrame.setVisible(false);
//		jFrame.setVisible(true);
//	}
//
//	public static Component getComponent() {
//		return component;
//	}
//
//	public static void setComponent(Component component) {
//		Window.component = component;
//	}

	public static void autoSize(JPanel jPanel) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		jPanel.setLayout(gridBagLayout);
		GridBagConstraints s = new GridBagConstraints();// ����һ��GridBagConstraints��
		// ������������ӽ����������ʾλ��
//		s.fill = GridBagConstraints.BOTH;
//		s.insets = new Insets(10, 10, 10, 10);
//		Dimension size = new Dimension(jFrame.getWidth()-6,jFrame.getHeight()-53);
		Component[] com = jPanel.getComponents();
//		List<Component> components = new ArrayList<Component>();
//		for (Component component : com) {
//			if (component.isVisible()&&component instanceof JPanel) {
//				components.add(component);
//			}
//		}
//		int x = 0;
//		int y = 0;
//		int height = 0;
//		int width = 0;
		s.fill = GridBagConstraints.BOTH;
		s.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < com.length; i++) {
			if (com.length <= 2) {
//				width = (size.width - 10 * (components.size() - 1))
//						/ components.size();
//				height = size.height;
//				components.get(i).setBounds(x, y, width, height);
//				x = x + width + 10;
				s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
				s.gridheight = 1;
				s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
				s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
				s.gridy = 0;
				s.gridx = i;
				if(i==(com.length-1)){s.gridwidth = 0;}
				gridBagLayout.setConstraints(com[i], s);
			}
			if (com.length > 2 && (com.length % 2 == 1)) {
//				width = (size.width - 10 * ((components.size() + 1) / 2 - 1))
//				/ ((components.size() + 1) / 2);
				if (i == ((com.length - 1) / 2)) {
		//			height = size.height;
		//			components.get(i).setBounds(x, y, width, height);
		//			x = 0;
		//			y = y + (size.height - 10) / 2 + 10;
					s.gridwidth = 0;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
					s.gridheight = 2;
					s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.gridy = 0;
					s.gridx = i;
					gridBagLayout.setConstraints(com[i], s);
				} else if (i < ((com.length - 1) / 2)) {
					s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
					s.gridheight = 1;
					s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.gridy = 0;
					s.gridx = i;
					gridBagLayout.setConstraints(com[i], s);
				} else {
		//			height = (size.height - 10) / 2;
		//			components.get(i).setBounds(x, y, width, height);
		//			x = x + width + 10;
					s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
					s.gridheight = 1;
					s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.gridy = 1;
					s.gridx = i-(com.length+1)/2;
					gridBagLayout.setConstraints(com[i], s);
				}
			}
			if (com.length > 2 && (com.length % 2 == 0)) {
//				width = (size.width - 10 * (components.size() / 2 - 1))
//						/ ((components.size()) / 2);
				if (i < ((com.length) / 2)) {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//					if (i == ((components.size()) / 2 - 1)) {
//						x = 0;
//						y = y + height + 10;
//					}
					s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
					s.gridheight = 1;
					s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.gridy = 0;
					s.gridx = i;
					if(i==(com.length-1)/2){s.gridwidth = 0;}
					gridBagLayout.setConstraints(com[i], s);
				} else {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
					s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
					s.gridheight = 1;
					s.weightx = 1;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.weighty = 1;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
					s.gridy = 1;
					s.gridx = i-com.length/2;
					if(i==(com.length-1)){s.gridwidth = 0;}
					gridBagLayout.setConstraints(com[i], s);
				}
			}
		}
	}

	public static void reSet() {
		try {
			Component[] coms = rootPanel.getComponents();
			for (Component component : coms) {
				component.setVisible(true);
			}
		} catch (Exception e) {
		}
	}

//	public static void autoSize(JPanel panel) {
//		// Point contentPos = contentPane.getLocationOnScreen();// ����Ļ������
//		Dimension size = panel.getSize(); // ��������Ĵ�С
//		// Component[] components = panel.getComponents();
//		Component[] com = panel.getComponents();
//		List<Component> components = new ArrayList<Component>();
//		for (Component component : com) {
//			if (component.isVisible()) {
//				components.add(component);
//			}
//		}
//		int x = 0;
//		int y = 0;
//		int height = size.height;
//		int width = size.width;
//		for (int i = 0; i < components.size(); i++) {
//			if (components.size() <= 2) {
//				width = (size.width - 10 * (components.size() - 1))
//						/ components.size();
//				height = size.height;
//				components.get(i).setBounds(x, y, width, height);
//				x = x + width + 10;
//			}
//			if (components.size() > 2 && (components.size() % 2 == 1)) {
//				width = (size.width - 10 * ((components.size() + 1) / 2 - 1))
//						/ ((components.size() + 1) / 2);
//				if (i == ((components.size() - 1) / 2)) {
//					height = size.height;
//					components.get(i).setBounds(x, y, width, height);
//					x = 0;
//					y = y + (size.height - 10) / 2 + 10;
//				} else if (i < ((components.size() - 1) / 2)) {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//				} else {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//				}
//			}
//			if (components.size() > 2 && (components.size() % 2 == 0)) {
//				width = (size.width - 10 * (components.size() / 2 - 1))
//						/ ((components.size()) / 2);
//				if (i < ((components.size()) / 2)) {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//					if (i == ((components.size()) / 2 - 1)) {
//						x = 0;
//						y = y + height + 10;
//					}
//				} else {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//				}
//			}
//		}
//		panel.setVisible(false);
//		panel.setVisible(true);
//	}

	private static List<Object> closeAction() {
		List<Object> list = new ArrayList<Object>();
		for (Class<?> clazz : summer.getSummerObject().keySet()) {
			for (String key : summer.getSummerObject().get(clazz).keySet()) {
				Object object = summer.getSummerObject().get(clazz).get(key);
				for (Method m : object.getClass().getDeclaredMethods()) {
					if(!m.isAccessible()){
						m.setAccessible(true);
					}
					if (m.isAnnotationPresent(Close.class)) {
						try {
							list.add(m.invoke(object));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return list;
	}

	private static void initAction() {
		for (Class<?> clazz : summer.getSummerObject().keySet()) {
			for (String key : summer.getSummerObject().get(clazz).keySet()) {
				Object object = summer.getSummerObject().get(clazz).get(key);
				for (Method m : object.getClass().getDeclaredMethods()) {
					if(!m.isAccessible()){
						m.setAccessible(true);
					}
					if (m.isAnnotationPresent(Init.class)) {
						try {
							m.invoke(object);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * �˷���������ϲ������ע��ʹ��
	 */
	public static List<Object> plugAction(Class<? extends Annotation> clazz) {
		List<Object> list = new ArrayList<Object>();
		for (Class<?> cls : summer.getSummerObject().keySet()) {
			for (String key : summer.getSummerObject().get(cls).keySet()) {
				Object object = summer.getSummerObject().get(cls).get(key);
				for (Method m : object.getClass().getMethods()) {
					if (m.isAnnotationPresent(clazz)) {
						try {
							System.out.println("=============" + m.getName());
							list.add(m.invoke(object));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * �����ѹ��� �˷�������c++����
	 */
//	public static void weekUp() {
//		new Thread() {
//			@Override
//			public void run() {
//				File file = new File(System.getProperty("user.dir")
//						+ "\\conf\\week.UP");
//				while (true) {
//					try {
//						if (file != null) {
//							if (file.exists() && file.isFile()) {
//								jFrame.setVisible(true);
//								file.delete();
//							}
//						}
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}.start();
//	}

	public static void setTitle(String string) {
		jFrame.setTitle(string);
	}
}