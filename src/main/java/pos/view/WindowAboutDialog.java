package pos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;

import pos.annotation.Close;

public class WindowAboutDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4273432170241255483L;
	private JTextPane topJPanel;
	private JPanel downJPanel;
	private File file;
	public WindowAboutDialog() {
//		super(null, "关于", true);
		this.setTitle("关于");
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		topJPanel = new JTextPane();
		topJPanel.setEditable(false);
		topJPanel.setBackground(Color.WHITE);
//		topJPanel.setContentType("text/html");
		addAbout("<h1 style = 'font-size:10px;'>POS机运维工具</h1><span style = 'font-size:10px;'>版本：Lemon 2.0<br/>官网：<a href=\"http://www.baidu.com\">baidu</a>");
		addAbout("<p style = 'font-size:10px;'>Lemon 2.0版本 更新内容：<br/>1.优化界面UI<br/>2.增加换肤功能并优化兼容性，兼容所有皮肤<br/>3.增加添加和删除插件功能<br/>4.优化启动速度，减少不必要资源浪费</p>");
		addAbout("<p style = 'font-size:10px;'>Lemon 1.0版本 更新内容：<br/>1.优化软件结构，降低耦合度<br/>2.新增启动参数，可对jvm进行调节<br/>3.优化设置视图，更加人性化</p>");
		downJPanel = new JPanel();
		downJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton jButton = new JButton("确定");
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowAboutDialog.this.dispose();
			}
		});
		downJPanel.add(jButton);
		JScrollPane jScrollPane = new JScrollPane(topJPanel);
		this.add(jScrollPane,BorderLayout.CENTER);
		this.add(downJPanel,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		WindowAboutDialog aboutDialog = new WindowAboutDialog();
		aboutDialog.setVisible(true);
	}
	public void addAbout(String text){
//		Document localDocument = topJPanel.getDocument();
//	    EditorKit localEditorKit = topJPanel.getEditorKit();
//	    try {
//			localEditorKit.read(new StringReader(text), localDocument, localDocument.getLength());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			if(file==null){
				file = File.createTempFile("AboutPage", ".html");
			}
			FileWriter fileWriter = new FileWriter(file,true);
			fileWriter.write(text);
			fileWriter.close();
			System.out.println(file.getPath());
			Document doc = topJPanel.getDocument();
			doc.putProperty(Document.StreamDescriptionProperty, null);
			topJPanel.setPage(file.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		topJPanel.validate();
		topJPanel.repaint();
//	    if (paramURL == null)
//	      throw new IOException("invalid url");
//	    URL localURL = getPage();
//	    if ((!paramURL.equals(localURL)) && (paramURL.getRef() == null))
//	    	topJPanel.scrollRectToVisible(new Rectangle(0, 0, 1, 1));
//	    int i = 0;
//	    Object localObject1 = topJPanel.getPostData();
//	    if ((localURL == null) || (!localURL.sameFile(paramURL)) || (localObject1 != null))
//	    {
//	      int j = getAsynchronousLoadPriority(getDocument());
//	      Object localObject2;
//	      if ((localObject1 == null) || (j < 0))
//	      {
//	        localObject2 = getStream(paramURL);
//	        if (topJPanel.kit != null)
//	        {
//	          Document localDocument = topJPanel.initializeModel(topJPanel.getEditorKit(), paramURL);
//	          synchronized (topJPanel)
//	          {
//	            if (topJPanel.loading != null)
//	            {
//	            	topJPanel.loading.cancel();
//	            	topJPanel.loading = null;
//	            }
//	          }
//	          j = getAsynchronousLoadPriority(localDocument);
//	          if (j >= 0)
//	          {
//	            setDocument(localDocument);
//	            synchronized (this)
//	            {
//	              this.loading = new PageStream((InputStream)localObject2);
//	              PageLoader localPageLoader = new PageLoader(localDocument, this.loading, j, localURL, paramURL);
//	              localPageLoader.start();
//	            }
//	            return;
//	          }
//	          read((InputStream)localObject2, localDocument);
//	          setDocument(localDocument);
//	          i = 1;
//	        }
//	      }
//	      else
//	      {
//	        localObject2 = new PageLoader(null, null, j, localURL, paramURL);
//	        ((Thread)localObject2).start();
//	        return;
//	      }
//	    }
//	    String str = paramURL.getRef();
//	    if (str != null)
//	    {
//	      if (i == 0)
//	        scrollToReference(str);
//	      else
//	        SwingUtilities.invokeLater(new Runnable(str)
//	        {
//	          public void run()
//	          {
//	            JEditorPane.this.scrollToReference(this.val$reference);
//	          }
//	        });
//	      getDocument().putProperty("stream", paramURL);
//	    }
//	    firePropertyChange("page", localURL, paramURL);
	}
	@SuppressWarnings("unused")
	@Close
	private boolean clear(){
		File path = file.getParentFile();
		System.out.println(file.getName());
		System.out.println("删除临时文件");
		file.delete();
		if(path.isDirectory()){
			for (File item : path.listFiles()) {
				if(item.getName().contains("AboutPage")&&item.getName().endsWith(".html")){
					item.delete();
				}
			}
		}
		return true;
	}
}
