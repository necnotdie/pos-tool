/*
 * Name:AddBookMark.java
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:20
 * Function:Use class MainUI to implement a AddBookMark GUI
 * */
package org.CBank.Test;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class AddBookMark extends JFrame implements ActionListener {
	//�������
	JPanel contentpane;
	//����ı���������
	JPanel textpanel=new JPanel();
	//��Ӱ�ť�����
	JPanel buttonpanel=new JPanel();
	//���ơ���ַ
	JLabel namelabel=new JLabel("����");
	JLabel addresslabel=new JLabel("��ַ");
	//��ʾ���ƺ͵�ַ���ı���
	JTextField nametext=new JTextField(20);
	JTextField addresstext=new JTextField(20);
	//ȷ����ȡ����ť
	JButton OKbuuton=new JButton("ȷ��");
	JButton Cancelbuuton=new JButton("ȡ��");
	//isExist�����ж��Ƿ��Ѿ�������ǩ��
	boolean isExist=false;
	public AddBookMark(String title,String urladdress,boolean isExist)
	{
		//���ô�������
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		this.setSize(new Dimension(300,150));
		this.setTitle(" �ղؼ�");
		this.isExist=isExist;
		//���ô�������
		contentpane=(JPanel)this.getContentPane();
		contentpane.setLayout(new BorderLayout());
		textpanel.setLayout(new FlowLayout());
		textpanel.add(namelabel);
		textpanel.add(nametext);
		nametext.setEnabled(true);
		nametext.setText(title);
		nametext.setFocusable(true);
		textpanel.add(addresslabel);
		textpanel.add(addresstext);
		addresstext.setEditable(true);
		addresstext.setText(urladdress);
		buttonpanel.setLayout(new FlowLayout());
		buttonpanel.add(OKbuuton);
		OKbuuton.setFocusable(false);
		buttonpanel.add(Cancelbuuton);
		Cancelbuuton.setFocusable(false);
		contentpane.add(textpanel,BorderLayout.CENTER);
		contentpane.add(buttonpanel,BorderLayout.SOUTH);
		OKbuuton.addActionListener(this);
		Cancelbuuton.addActionListener(this);
	}
	//��Ӧ��ť�¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Cancelbuuton)
		{
			dispose();
		}
		if(e.getSource()==OKbuuton)
		{
			//�����������ǩ��
			if(this.isExist==true)
			{
				if (JOptionPane.showConfirmDialog(AddBookMark.this, "��Ŀ�Ѵ��ڣ��Ƿ񸲸�?",
						"Think ��ʾ��:", JOptionPane.OK_CANCEL_OPTION)==0) {
				}
			}
			dispose();
		}
	}
}