package pos.test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import pos.conf.ConfInit;

public class Demo01 {
	public static void main(String[] args) {
		JFrame jPanel = new JFrame("01");
		jPanel.setSize(600, 600);
		jPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel jLabel = new JLabel("���ڹر�ʱִ�в�����");
//		jLabel.setBounds(20, 50, 150, 30);
		final ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton button1 = new JRadioButton("ѯ��");
//		button1.setBounds(200, 10, 130, 30);
		button1.setName("default");
		JRadioButton button2 = new JRadioButton("ֱ���˳�");
//		button2.setBounds(200, 50, 130, 30);
		button2.setName("exit");
		JRadioButton button3 = new JRadioButton("��С��������");
//		button3.setBounds(200, 90, 130, 30);
		button3.setName("minisize");
		buttonGroup.add(button1);
		buttonGroup.add(button2);
		buttonGroup.add(button3);
		String select = null;
		try {
			select = ConfInit.getSet();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		Enumeration<AbstractButton> enumeration = buttonGroup.getElements();
		while (enumeration.hasMoreElements()) {
			JRadioButton button = (JRadioButton) enumeration.nextElement();
			if (select.equals(button.getName())) {
				button.setSelected(true);
			}
		}
		JButton jButton1 = new JButton("Ӧ��");
		jButton1.setBounds(110, 130, 80, 25);
		JButton jButton2 = new JButton("�ָ�Ĭ��");
		jButton2.setBounds(210, 130, 80, 25);
		GridBagLayout layout = new GridBagLayout();
		jPanel.setLayout(layout);
		jPanel.add(button1);
		jPanel.add(button2);
		jPanel.add(button3);
		jPanel.add(jButton1);
		jPanel.add(jButton2);
		jPanel.add(jLabel);
		GridBagConstraints s = new GridBagConstraints();// ����һ��GridBagConstraints��
		// ������������ӽ����������ʾλ��
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridy = 0;
		s.gridx = 0;
		layout.setConstraints(jLabel, s);// �������
		s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 1;
		s.gridy = 0;
		layout.setConstraints(button1, s);// �������
		s.gridy = 1;
		layout.setConstraints(button2, s);// �������
		s.gridy = 2;
		layout.setConstraints(button3, s);// �������
		s.insets = new Insets(80, 20, 20, 20);
		s.gridwidth = 0;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 2;
		s.gridy = 3;
		layout.setConstraints(jButton2, s);// �������
		s.gridwidth = 0;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 3;
		s.gridy = 3;
		layout.setConstraints(jButton1, s);// �������
		jPanel.setVisible(true);
	
	}
}
