package org.CBank.Test;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GridBagLayoutDemo extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4481121176026056530L;
    private JPanel contentPane;
    private static Runnable run;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if(run == null){
        	run = new Runnable() {
                public void run() {
                    try {
                        GridBagLayoutDemo frame = new GridBagLayoutDemo();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        EventQueue.invokeLater(run);
    }
     public void reStart(){
         EventQueue.invokeLater(run);
     }
    /**
     * Create the frame.
     */
    public GridBagLayoutDemo() {
        setTitle("�����鲼��");// ���ô���ı���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô����˳�ʱ����
        setBounds(100, 100, 450, 200);// ���ô���λ�úʹ�С
        contentPane = new JPanel();// �����������
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������ı߿�
        setContentPane(contentPane);// Ӧ���������
        GridBagLayout gbl_contentPane = new GridBagLayout();// ���������鲼��
        contentPane.setLayout(gbl_contentPane);// ʹ�������鲼��
         
        JButton button1 = new JButton("A");// ������ť
        GridBagConstraints gbc_button1 = new GridBagConstraints();// ���������鲼��Լ������
        gbc_button1.insets = new Insets(0, 0, 5, 5);// ���ÿؼ��Ŀհ�
        gbc_button1.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
        gbc_button1.weightx = 10.0;// ��һ�еķֲ���ʽΪ10%
        gbc_button1.gridx = 0;// ��ʼ��Ϊ��1��
        gbc_button1.gridy = 0;// ��ʼ��Ϊ��1��
        contentPane.add(button1, gbc_button1);// ���Ӱ�ť����Լ������
         
        JButton button2 = new JButton("B");// ������ť
        GridBagConstraints gbc_button2 = new GridBagConstraints();// ���������鲼��Լ������
        gbc_button2.insets = new Insets(0, 5, 5, 5);// ���ÿؼ��Ŀհ�
        gbc_button2.fill = GridBagConstraints.HORIZONTAL;// ������䷽ʽ
        gbc_button2.weightx = 20.0;// ��һ�еķֲ���ʽΪ20%
        gbc_button2.gridx = 1;// ��ʼ��Ϊ��2��
        gbc_button2.gridy = 0;// ��ʼ��Ϊ��1��
        contentPane.add(button2, gbc_button2);// ���Ӱ�ť����Լ������
         
        JButton button3 = new JButton("C");// ������ť
        GridBagConstraints gbc_button3 = new GridBagConstraints();// ���������鲼��Լ������
        gbc_button3.gridheight = 2;// ռ��2��
        gbc_button3.fill = GridBagConstraints.BOTH;// ������䷽ʽ
        gbc_button3.weightx = 30.0;// ��һ�еķֲ���ʽΪ30%
        gbc_button3.insets = new Insets(0, 0, 5, 5);// ���ÿؼ��Ŀհ�
        gbc_button3.gridx = 2;// ��ʼ��Ϊ��3��
        gbc_button3.gridy = 0;// ��ʼ��Ϊ��1��
        contentPane.add(button3, gbc_button3);// ���Ӱ�ť����Լ������
         
        JButton button4 = new JButton("D");// ������ť
        GridBagConstraints gbc_button4 = new GridBagConstraints();// ���������鲼��Լ������
        gbc_button4.weightx = 40.0;// ��һ�еķֲ���ʽΪ40%
        gbc_button4.fill = GridBagConstraints.BOTH;// ������䷽ʽ
        gbc_button4.gridheight = 4;// ռ��4��
        gbc_button4.insets = new Insets(0, 5, 0, 0);// ���ÿؼ��Ŀհ�
        gbc_button4.gridx = 3;// ��ʼ��Ϊ��4��
        gbc_button4.gridy = 0;// ��ʼ��Ϊ��1��
        contentPane.add(button4, gbc_button4);// ���Ӱ�ť����Լ������
        button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        EventQueue.invokeLater(run);
//				System.exit(0);
			}
		});
    }
     
}
