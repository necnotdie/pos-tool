/*
 * Name:MainUI
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:20
 * Function:the MainUI class which implement the basic GUI function
 * */
package org.CBank.Test;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.tree.DefaultMutableTreeNode;
public class MainUI extends JFrame {
	// ���������
//	JFrame think = new JFrame("think"); 
	// �������˵���
	JMenuBar menubar = new JMenuBar(); 
	// �ļ��˵�
	JMenu filemenu = new JMenu("�ļ�(F)"); 
	JMenuItem newwindow = new JMenuItem("�´���(N)");
	JMenuItem openfile = new JMenuItem("��(O)");
	JMenuItem saveas = new JMenuItem("���Ϊ(S)");
	JMenuItem quit = new JMenuItem("�˳�(Q)");
	// �鿴�˵�
	JMenu watchmenu = new JMenu("�鿴(V)"); 
	JMenuItem sourcecode = new JMenuItem("Դ����(O)");
	// ��ǩ�˵�
	static JMenu bookmarkmenu = new JMenu("��ǩ(B)"); 
	JMenuItem addbookmark = new JMenuItem("�����ǩ(B)");
	// �����˵�
	JMenu helpmenu = new JMenu("����(H)"); 
	JMenuItem help = new JMenuItem("think ����");
	JMenuItem about = new JMenuItem("���� think");
	// �򿪱���Ի���
	JFileChooser openfilechooser = new JFileChooser("D://");
	JFileChooser saveasfilechooser = new JFileChooser("D://");
	// �������ִ������
	public final static String Windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public final static String Metal = "javax.swing.plaf.metal.MetalLookAndFeel";
	public final static String Motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	//���尴ť��ͼ��
//	ImageIcon bookmarkicon=new ImageIcon(getClass().getResource("/icons/back.png"));
//	ImageIcon forwardicon=new ImageIcon(getClass().getResource("/icons/forward.png"));
//	ImageIcon backicon=new ImageIcon(getClass().getResource("/icons/back.png"));
//	ImageIcon refreshicon=new ImageIcon(getClass().getResource("/icons/refresh.png")); 
//	ImageIcon homeicon=new ImageIcon(getClass().getResource("/icons/home.png"));
//	ImageIcon goicon=new ImageIcon(getClass().getResource("/icons/go.png"));
//	ImageIcon stopicon=new ImageIcon(getClass().getResource("/icons/stop.png"));
	//���������� 
	JToolBar toolbar=new JToolBar(); 
//	JButton bookmark=new JButton("��ǩ",bookmarkicon);
	JButton bookmark=new JButton("��ǩ");
//	JButton back=new JButton("����",backicon); 
	JButton back=new JButton("����"); 
//	JButton forward=new JButton("ǰ��",forwardicon); 
	JButton forward=new JButton("ǰ��"); 
//	JButton stop=new JButton("ֹͣ",stopicon);
	JButton stop=new JButton("ֹͣ");
//	JButton refresh=new JButton("ˢ��",refreshicon);
	JButton refresh=new JButton("ˢ��"); 
//	JButton home=new JButton("��ҳ",homeicon);
	JButton home=new JButton("��ҳ");
	//��ַ��
	JLabel addresslabel=new JLabel("��ַ:");
	JTextField urlfield = new JTextField(50);
//	JButton go = new JButton("ת��",goicon);
	JButton go = new JButton("ת��");
	Box addressbox = new Box(BoxLayout.LINE_AXIS);
	JToolBar addresstoolbar=new JToolBar();
	// ������ʾ��ҳ��ҳ��
	JEditorPane webpagepane = new JEditorPane();
	JScrollPane scrollpane = new JScrollPane(webpagepane);
	JScrollPane treescollpane=new JScrollPane();
	// Ĭ����ҳURL��ַ
	String urladdress = "http://www.baidu.com";
	//�����ָ���
	JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	//���������ڵ�
	DefaultMutableTreeNode root=new DefaultMutableTreeNode("�ղؼ�");
	//����һ���ļ��ڵ�
	DefaultMutableTreeNode homepage=new DefaultMutableTreeNode("��ҳ");
	JTree jtree=new JTree(root);
	// ����һ��������������ʷ��¼
	PageList historylist = new PageList();
	// ����һ��������������ǩ��¼
	PageList bookmarklist = new PageList();
	public MainUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		think.setPreferredSize(new Dimension(800,600));           
		int thinkWidth=this.getPreferredSize().width;
		int thinkHeight=this.getPreferredSize().height;
//		think.setSize(thinkWidth,thinkHeight);
//		think.setLocation((screenSize.width-thinkWidth)/2,(screenSize.height-thinkHeight)/2);
		// ���ô�������
		//think.setLocationRelativeTo(null);
//		think.setLayout(new BorderLayout());
//		think.setTitle("Think");
//		think.setResizable(true);
//		// ��Ӳ˵���
//		think.setJMenuBar(menubar);
		menubar.add(filemenu);
		filemenu.setMnemonic('F');
		menubar.add(watchmenu);
		watchmenu.setMnemonic('V');
		menubar.add(bookmarkmenu);
		bookmarkmenu.setMnemonic('B');
		menubar.add(helpmenu);
		helpmenu.setMnemonic('H');
		// ��Ӳ˵���
		filemenu.add(newwindow);
		// ��Ӽ��̿�ݷ�ʽ
		newwindow.setMnemonic('N');
		newwindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		newwindow.addActionListener(new Action());
		filemenu.addSeparator();
		filemenu.add(openfile);
		openfile.setMnemonic('O');
		openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		openfile.addActionListener(new Action());
		filemenu.add(saveas);
		saveas.setMnemonic('S');
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_MASK));
		saveas.addActionListener(new Action());
		filemenu.addSeparator();
		filemenu.add(quit);
		quit.setMnemonic('Q');
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		quit.addActionListener(new Action());
		watchmenu.add(sourcecode);
		sourcecode.setMnemonic('U');
		sourcecode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
				InputEvent.CTRL_MASK));
		sourcecode.addActionListener(new Action());
		bookmarkmenu.add(addbookmark);
		addbookmark.setMnemonic('D');
		addbookmark.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_MASK));
		addbookmark.addActionListener(new Action());
		helpmenu.add(help);
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,
				InputEvent.BUTTON1_MASK));
		help.addActionListener(new Action());
		helpmenu.addSeparator();
		helpmenu.add(about);
		about.addActionListener(new Action());
		// ��ӹ��߰�ť
		toolbar.add(bookmark);
		bookmark.setFocusable(false);
		bookmark.setEnabled(true);
		bookmark.setHorizontalTextPosition(SwingConstants.RIGHT);
		bookmark.setVerticalTextPosition(SwingConstants.CENTER);
		bookmark.addActionListener(new Action());
		toolbar.add(back);
		back.setFocusable(false);
		back.setEnabled(false);
		back.setHorizontalTextPosition(SwingConstants.RIGHT);
		back.setVerticalTextPosition(SwingConstants.CENTER);
		back.addActionListener(new Action());
		toolbar.add(forward);
		forward.setFocusable(false);
		forward.setEnabled(false);
		forward.setHorizontalTextPosition(SwingConstants.RIGHT);
		forward.setVerticalTextPosition(SwingConstants.CENTER);
		forward.addActionListener(new Action());
		toolbar.add(stop);
		stop.setFocusable(false);
		stop.setHorizontalTextPosition(SwingConstants.RIGHT);
		stop.setVerticalTextPosition(SwingConstants.CENTER);
		stop.addActionListener(new Action());
		toolbar.add(refresh);
		refresh.setFocusable(false);
		refresh.setHorizontalTextPosition(SwingConstants.RIGHT);
		refresh.setVerticalTextPosition(SwingConstants.CENTER);
		refresh.addActionListener(new Action());
		toolbar.add(home);
		home.setFocusable(false);
		home.setHorizontalTextPosition(SwingConstants.RIGHT);
		home.setVerticalTextPosition(SwingConstants.CENTER);
		home.addActionListener(new Action());
		toolbar.addSeparator();
		addressbox.add(addresslabel,BorderLayout.WEST);
		addressbox.add(urlfield,BorderLayout.CENTER);
		urlfield.setFocusable(true);
		urlfield.addActionListener(new Action());
		urlfield.addKeyListener(new KeyAction());
		addressbox.add(go,BorderLayout.EAST);
		go.setFocusable(false);
		go.setHorizontalTextPosition(SwingConstants.RIGHT);
		go.setVerticalTextPosition(SwingConstants.CENTER);
		go.addActionListener(new Action());
		toolbar.add(addressbox);
		scrollpane.setPreferredSize(new Dimension(800, 600));
		root.add(homepage);
		jtree.updateUI();
		jtree.addTreeSelectionListener(new TreeNodeChange());
		treescollpane.setViewportView(jtree);
		splitPane.setDividerLocation(0);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerSize(0);
		splitPane.add(treescollpane);
		splitPane.add(scrollpane);
		// ����������,���˵��͹�������ӵ������������
		Container contentpane = getContentPane();
		// ��menubar���ڿ�ܵĶ���
		setJMenuBar(menubar);
		contentpane.add(toolbar, BorderLayout.NORTH);
		contentpane.add(splitPane);
		//�������ʱ������ǩ�ļ� 
		try{
			File loadbookmarklist=new File("BookMarkList.txt");
			FileReader filereader=new FileReader(loadbookmarklist);
			BufferedReader bufferedreader=new BufferedReader(filereader);
			//��ʱ�����ҳ����
			String temp="";
			//��ʱ�����ҳURL
			String content="";
			String linesep=System.getProperty("line.separator");
			while((temp=bufferedreader.readLine())!=null)
			{
				//����ÿ�������Ӧһ����ַ������һ�ο��Զ�ȡ����
				content=bufferedreader.readLine();
				//����ǩ������ӵ����ڵ���
				DefaultMutableTreeNode treenode=new DefaultMutableTreeNode(temp);
				root.add(treenode);
				jtree.updateUI();
				bookmarklist.addURL(temp, content);
			}
			bufferedreader.close();
			filereader.close();
		}catch(IOException event){
			event.printStackTrace();
			JOptionPane.showMessageDialog(MainUI.this, "��ǩ�ļ���ʧ��,�������ļ��Ѷ�ʧ!","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
		}
		//���ô��ڹرռ����¼�
		this.addWindowListener(new WindowState());
		this.addWindowStateListener(new WindowState());
		setNewPage(0, urladdress);
	}
	//���url��ַ������
	public String getContent(String urladdress)
	{
		String linesep;
		String line="";
		String tempsource="";
		linesep=System.getProperty("line.separator");
		try {
			//�������������ȡURL����
			URL source=new URL(urladdress);
			URLConnection urlconnection=source.openConnection();
			String encoding=urlconnection.getContentEncoding();
			System.out.println(encoding);
			InputStream inputstream=urlconnection.getInputStream();
			//�����е���ҳʹ�õ��ַ�����ͬ�������е���ҳԴ����򿪵�ʱ���������
			//��ʱutf-8����ʹ�ã����ǻᵼ�±���֮�����ҳ
			//�ڱ��������д򿪵�ʱ����Ȼ������
			InputStreamReader inputstreamreader=new InputStreamReader(inputstream,"utf-8");
			BufferedReader bufferedreader=new BufferedReader(inputstreamreader);
			while((line=bufferedreader.readLine())!=null)
			{
				tempsource=tempsource+line+linesep;	
			}
			//�ر���
			inputstream.close();
			bufferedreader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempsource;
	}
	//����ҳ��
	public void setNewPage(int flag,String urladdress)
	{
		String temp=getContent(urladdress);
		String title=getTitle(temp);
		try {
			urlfield.setText(urladdress);
			webpagepane.setEditable(false);
			webpagepane.setPage(urladdress);
			if(historylist.isPrePageNameExist(title))
			{
				back.setEnabled(true);
			}
			if(historylist.isNextPageNameExist(title))
			{
				forward.setEnabled(true);
			}
			urlfield.repaint();
			webpagepane.repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ӽ���ʷ����
		if(flag==0)
		{
			historylist.addURL(title,urladdress);
		}
		webpagepane.addHyperlinkListener(new HTMLView());
	}
	//��ȡ��ҳ����
	public String getTitle(String htmlcontent)
	{
		String temp="";
		String regex="<[Tt][Ii][Tt][Ll][Ee]>([^</[Tt][Ii][Tt][Ll][Ee]>]*)";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(htmlcontent);
		while (match.find()) {
			int start = match.start();
			int end = match.end();
			temp = htmlcontent.substring(start+7, end-1);
		}
		return temp;
	}
	//�˳�ʱ������ǩ�ļ��ı���
	public void savebookmarklist()
	{
		File savefile=new File("BookMarkList.txt");
		String linesep=System.getProperty("line.separator");
		try{
			FileWriter filewriter=new FileWriter(savefile);
			BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
			//��bookmarklist�������汣��ļ�¼һ��д���ļ�
			String temppagename="";
			String tempurladdress="";
			PageNode head=bookmarklist.getPageNode().getNext();
			while(head!=null)
			{
				//��ȡ�������������
				temppagename=head.getPagename();
				temppagename=temppagename+linesep;
				tempurladdress=head.getUrl();
				tempurladdress=tempurladdress+linesep;
				bufferedwriter.write(temppagename);
				bufferedwriter.write(tempurladdress);
				head=head.getNext();
			}
			bufferedwriter.close();
			filewriter.close();
		}catch(IOException event){
			JOptionPane.showMessageDialog(MainUI.this, "��ǩ�б���ʧ��!","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
		}
	}
	// �����¼�����
	private class Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// ����˵��¼�
			if (e.getSource() == newwindow) {
				// �����´���
				ThinkFrame core=new ThinkFrame();
			}
			// ���ļ�,���ڲ������HTML�ļ���ֻ����Դ�������ʽ��������
			if (e.getSource() == openfile) {
				openfilechooser.setDialogTitle("��");
				openfilechooser.setDialogType(JFileChooser.OPEN_DIALOG);
				int result = openfilechooser.showOpenDialog(MainUI.this);
				File fileopen=openfilechooser.getSelectedFile();
				String openfilename=fileopen.getPath();
				System.out.println(openfilename);
				if (result == JFileChooser.APPROVE_OPTION) {
					// ��Ӳ��Ŀ¼�¶�ȡ�ļ�
					try{
						FileReader filereader=new FileReader(openfilename);
						BufferedReader bufferedreader=new BufferedReader(filereader);
						String temp="";
						String content="";
						String linesep=System.getProperty("line.separator");
						while((temp=bufferedreader.readLine())!=null)
						{
							content=content+temp+linesep;
						}
						bufferedreader.close();
						filereader.close();
						urlfield.setText(openfilename);
						urlfield.revalidate();
						webpagepane.setText(content);
						webpagepane.revalidate();
					}catch(IOException event){
						event.printStackTrace();
						JOptionPane.showMessageDialog(MainUI.this, "��ͼ��Դ�����ļ�ʧ��!","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// ҳ�����Ϊ
			if (e.getSource() == saveas) {
				urladdress = urlfield.getText().toString().trim();
				//���õ����Ի�������
				saveasfilechooser.setDialogTitle("���Ϊ...");
				saveasfilechooser.setDialogType(JFileChooser.SAVE_DIALOG);
				int result = saveasfilechooser.showSaveDialog(MainUI.this);
				//��ȡ�ļ���
				File filesave=saveasfilechooser.getSelectedFile();
				String savefilename=filesave.getPath();
				savefilename=savefilename+".html";
				if (result == JFileChooser.APPROVE_OPTION) {
					try{
						//���ļ�����
						String temp=getContent(urladdress);
						FileWriter filewriter=new FileWriter(savefilename);
						BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
						bufferedwriter.write(temp);
						bufferedwriter.close();
						filewriter.close();
					}catch(IOException event){
						JOptionPane.showMessageDialog(MainUI.this, "Webҳ�汣��ʧ��!","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// �˳������
			if (e.getSource() == quit) {
				if (JOptionPane.showConfirmDialog(MainUI.this, "ȷ��Ҫ�˳���",
						"Think ��ʾ��:", JOptionPane.OK_CANCEL_OPTION) == 0) {
					//�˳�ʱ����ǩ��¼д���ļ�
					savebookmarklist();
					System.exit(0);
				} else {
					// Do Nothing
				}
			}
			// �鿴Դ����
			if (e.getSource() == sourcecode) {
				urladdress = urlfield.getText().toString().trim();
				if (urladdress.length() > 0
						&& !urladdress.startsWith("http://")) {
					urladdress = "http://"+urladdress;
				}
				if(!urladdress.equals(""))
				{
					String temp=getContent(urladdress);
					String title=getTitle(temp);
					temp=getContent(urladdress);
					//����Դ���봰�����
					JMPWindow viewsourcecode=new JMPWindow(title,temp);
					viewsourcecode.setVisible(true);
				}
				else if (urladdress.length() == 0) {
					JOptionPane.showMessageDialog(MainUI.this, "�Բ���,�������˿յ�ַ,���ǷǷ�������","Think ��ʾ��:",JOptionPane.WARNING_MESSAGE);
				}
			}
			// ���ҳ�浽��ǩ
			if (e.getSource() == addbookmark) 
			{
				urladdress = urlfield.getText().toString().trim();
				String temp=getContent(urladdress);
				String title=getTitle(temp);
				if(title!=""&&urladdress!="")
				{
					if (bookmarklist.isPageNameExist(title) == false) 
					{
						bookmarklist.addURL(title,urladdress);
						System.out.println("��ǩ");
						System.out.println(title);
						System.out.println(urladdress);
						//�����ղؼд��ڶ���
						AddBookMark addbookmarkdone=new AddBookMark(title,urladdress,false);
						addbookmarkdone.setVisible(true);
						DefaultMutableTreeNode node=new DefaultMutableTreeNode(title);
						root.add(node);
						jtree.updateUI();
					} 
					else if(bookmarklist.isPageNameExist(title) == true)
					{
						AddBookMark addbookmarkdone=new AddBookMark(title,urladdress,true);
						addbookmarkdone.setVisible(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(MainUI.this, "�����ǩ���������ַ����Ϊ�գ�","Think ��ʾ��:",JOptionPane.WARNING_MESSAGE);
				}
			}
			// �򿪰����ĵ�
			if (e.getSource() == help) {
				// ��дһ��chm��ʽ���ĵ�
				JMPWindow viewhelp=new JMPWindow();
				viewhelp.setVisible(true);
			}
			// �򿪹��ڴ���
			if (e.getSource() == about) {
				JOptionPane.showMessageDialog(MainUI.this, "Think version 0.1"
						+ "/n" + "лл����ʹ��!", "���� Think",
						JOptionPane.INFORMATION_MESSAGE);
			}
			// ������ǩ��ť�¼�����ʾ/����
			if(e.getSource()==bookmark)
			{
				if(splitPane.getDividerLocation()==250)
				{
					splitPane.setDividerLocation(0);
				}
				else
				{
					splitPane.setDividerLocation(250);
				}
			}
			// �����¼�
			if (e.getSource() == back) {
				urladdress = urlfield.getText().toString().trim();
				try {
					// �����ʷ�����е�ǰһ��URL��ַ
					String pagename="";
					if(historylist.isPrePageNameExist(urladdress))
					{
						pagename= historylist.getPrePageName(pagename);
						urladdress=historylist.getURL(pagename);
						MainUI.this.setNewPage(1,urladdress);
					}
				}catch (Exception event) {
					event.printStackTrace();
					JOptionPane.showMessageDialog(MainUI.this, "��������ʧ�ܣ�","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
				}
			}
			// ǰ���¼�
			if (e.getSource() == forward) {
				urladdress = urlfield.getText().toString().trim();
				try {
					String pagename="";
					if(historylist.isNextPageNameExist(urladdress))
					{
						// �����ʷ�����е���һ��URL��ַ
						pagename= historylist.getNextPageName(pagename);
						System.out.println(pagename);
						urladdress=historylist.getURL(pagename);
						MainUI.this.setNewPage(1,urladdress);
					}
				} catch (Exception event) {
					event.printStackTrace();
					JOptionPane.showMessageDialog(MainUI.this, "��������ʧ�ܣ�","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
				}
			}
			//ֹͣ�¼�
			if (e.getSource() == stop) {
				//��ҳ���ÿ�
				urladdress = "";
				MainUI.this.setNewPage(1,urladdress);
			}
			// ˢ���¼�
			if (e.getSource() == refresh) {
				urladdress = urlfield.getText().toString().trim();
				MainUI.this.setNewPage(1,urladdress);
			}
			// �ص���ҳ
			if (e.getSource() == home) {
				urladdress =  "http://www.baidu.com";
				MainUI.this.setNewPage(0,urladdress);
			}
			// ����go���߻س��¼�
			if ((e.getSource() == go) || (e.getSource() == urlfield)) {
				urladdress = urlfield.getText().toString().trim();
				if (urladdress.length() > 0 && urladdress.startsWith("http://")) {
					MainUI.this.setNewPage(0,urladdress);
				} else if (urladdress.length() > 0
						&& !urladdress.startsWith("http://")) {
					urladdress = "http://"+urladdress;
					MainUI.this.setNewPage(0,urladdress);
				} else if (urladdress.length() == 0) {
					JOptionPane.showMessageDialog(MainUI.this, "�Բ���,�������˿յ�ַ,���ǷǷ�������","Think ��ʾ��:",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	// ʵ��HyperlinkListener����,ʵ��ҳ���ڵĳ�����
	private class HTMLView implements HyperlinkListener {
		@Override
		public void hyperlinkUpdate(HyperlinkEvent event) {
			if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				JEditorPane pane = (JEditorPane) event.getSource();
				if (event instanceof HTMLFrameHyperlinkEvent) {
					//��������
					HTMLFrameHyperlinkEvent htmlframehyperlinkevent = (HTMLFrameHyperlinkEvent) event;
					HTMLDocument htmldocument = (HTMLDocument) pane.getDocument();
					htmldocument.processHTMLFrameHyperlinkEvent(htmlframehyperlinkevent);
				} else {
					try {
						String linkurl = event.getURL().toString();
						String temp=getContent(linkurl);
						String title=getTitle(temp);
						pane.setPage(linkurl);
						pane.setEditable(false);
						urlfield.setText(linkurl);
						urlfield.revalidate();
						pane.invalidate();
						webpagepane.addHyperlinkListener(new HTMLView());
						historylist.addURL(title,linkurl);
					} catch (IOException t) {
						t.printStackTrace();
						JOptionPane.showMessageDialog(MainUI.this, "��������ʧ�ܣ�","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	//���̼����¼���
	private class KeyAction extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) {
			char key=e.getKeyChar();
			//���»س���
			if(key=='\n')
			{
				urladdress = urlfield.getText().toString().trim();
				if (urladdress.length() > 0 && urladdress.startsWith("http://")) {
					MainUI.this.setNewPage(0,urladdress);
				} else if (urladdress.length() > 0
						&& !urladdress.startsWith("http://")) {
					urladdress = "http://"+urladdress;
					MainUI.this.setNewPage(0,urladdress);
				} else if (urladdress.length() == 0) {
					JOptionPane.showMessageDialog(MainUI.this, "�Բ���,�������˿յ�ַ,���ǷǷ�������","Think ��ʾ��:",JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	//�������ڵ�ı���¼�
	private class TreeNodeChange implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree=(JTree)e.getSource();
			DefaultMutableTreeNode selectednode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			String nodename=selectednode.toString();
			if(selectednode.isLeaf())
			{
				if(bookmarklist.isPageNameExist(nodename))
				{	
					//��ȡ�ڵ�����
					String tempurl=bookmarklist.getURL(nodename);
					setNewPage(0,tempurl);
				}
				else if(nodename.equalsIgnoreCase("��ҳ"))
				{
					String tempurl="http://www.baidu.com";
					setNewPage(0,tempurl);
				}
			}
		}
	}
	//�������ڴ�С�ı����
	private class WindowState extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			if (JOptionPane.showConfirmDialog(MainUI.this, "ȷ��Ҫ�˳���",
					"Think ��ʾ��:", JOptionPane.OK_CANCEL_OPTION) == 0) {
				//�˳�ʱ����ǩ��¼д���ļ�
				savebookmarklist();
				System.exit(0);
			} else {
				// Do Nothing
			}
		}
		public void windowStateChanged(WindowEvent e)
		{
			splitPane.setDividerSize(0);
		}
	}
}
