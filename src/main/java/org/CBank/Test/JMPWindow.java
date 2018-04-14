/*
 * Name:JMPWindow.java
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:20
 * Function:Use class MainUI to implement a JMPWindow GUI
 * */
package org.CBank.Test;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class JMPWindow extends JFrame implements ActionListener
{
	//�������
	JPanel contentpane;
	//�������������
	JPanel textpanel=new JPanel();
	JPanel buttonpanel=new JPanel();
	//�����ı�����������ʾԴ����
	JTextArea textarea=new JTextArea();
	JScrollPane scrollpane=new JScrollPane();
	//����͹رհ�ť
	JButton closebutton=new JButton("�ر�");
	JButton savebutton=new JButton("����");
	//Դ�ļ�
	String sourcecode="";
	//ҳ������
	String pageName="";
	public JMPWindow()
	{
		//���ô�������
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.setSize(new Dimension(800,600));
		this.setTitle("����");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//������
		contentpane=(JPanel)getContentPane();
		contentpane.setLayout(new BorderLayout());
		scrollpane.getViewport().add(textarea);
		textpanel.setLayout(new BorderLayout());
		textpanel.add(scrollpane,BorderLayout.CENTER);
		contentpane.add(textpanel,BorderLayout.CENTER);
		//���ð�ť����
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.add(closebutton);
		closebutton.setFocusable(true);
		contentpane.add(buttonpanel,BorderLayout.SOUTH);
		String linesep="";
		linesep=System.getProperty("line.separator");
		String readme="";
		String temp="";
		try {
			//��ȡreadme�ļ�
			File readmefile=new File("readme.txt");
			FileReader filereader;
			filereader = new FileReader(readmefile);
			BufferedReader bufferedreader=new BufferedReader(filereader);
			while((temp=bufferedreader.readLine())!=null)
			{
				readme=readme+temp+linesep;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JMPWindow.this,"��ȡ�����ļ�����","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
		}
		//readme���ɱ༭
		this.textarea.setEditable(false);
		this.textarea.setText(readme);
		closebutton.addActionListener(this);
	}
	public JMPWindow(String title,String urladdress)
	{
		//���ô�������
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.sourcecode=urladdress;
		this.setSize(new Dimension(800,600));
		this.setTitle("Դ����");
		this.pageName=title;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//������
		contentpane=(JPanel)getContentPane();
		contentpane.setLayout(new BorderLayout());
		scrollpane.getViewport().add(textarea);
		textpanel.setLayout(new BorderLayout());
		textpanel.add(scrollpane,BorderLayout.CENTER);
		contentpane.add(textpanel,BorderLayout.CENTER);
		//���ð�ť����
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.add(savebutton);
		savebutton.setFocusable(true);
		buttonpanel.add(closebutton);
		closebutton.setFocusable(false);
		contentpane.add(buttonpanel,BorderLayout.SOUTH);
		this.textarea.setEditable(false);
		this.textarea.setText(sourcecode);
		savebutton.addActionListener(this);
		closebutton.addActionListener(this);
	}
	//��Ӧ��ť�¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		String url="";
		if(e.getSource()==closebutton)
		{
			dispose();
		}
		else if(e.getSource()==savebutton)
		{
			//���������水ť���򵯳�����Ի���
			JFileChooser filechooser=new JFileChooser();
			filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
			int returnvalue=filechooser.showSaveDialog(JMPWindow.this);
			File savefile=filechooser.getSelectedFile();
			String savefilename=savefile.getPath();
			savefilename=savefilename+".html";
			try{
				FileWriter filewriter=new FileWriter(savefilename);
				BufferedWriter bufferedwriter=new BufferedWriter(filewriter);
				bufferedwriter.write(textarea.getText());
				bufferedwriter.close();
				filewriter.close();
			}catch(IOException event){
				JOptionPane.showMessageDialog(JMPWindow.this, "����ʧ��!","Think ��ʾ��:",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}