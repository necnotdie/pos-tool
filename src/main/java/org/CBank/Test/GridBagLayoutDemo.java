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
        setTitle("网格组布局");// 设置窗体的标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体退出时操作
        setBounds(100, 100, 450, 200);// 设置窗体位置和大小
        contentPane = new JPanel();// 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板的边框
        setContentPane(contentPane);// 应用内容面板
        GridBagLayout gbl_contentPane = new GridBagLayout();// 创建网格组布局
        contentPane.setLayout(gbl_contentPane);// 使用网格组布局
         
        JButton button1 = new JButton("A");// 创建按钮
        GridBagConstraints gbc_button1 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_button1.insets = new Insets(0, 0, 5, 5);// 设置控件的空白
        gbc_button1.fill = GridBagConstraints.HORIZONTAL;// 设置填充方式
        gbc_button1.weightx = 10.0;// 第一列的分布方式为10%
        gbc_button1.gridx = 0;// 起始点为第1列
        gbc_button1.gridy = 0;// 起始点为第1行
        contentPane.add(button1, gbc_button1);// 增加按钮及其约束条件
         
        JButton button2 = new JButton("B");// 创建按钮
        GridBagConstraints gbc_button2 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_button2.insets = new Insets(0, 5, 5, 5);// 设置控件的空白
        gbc_button2.fill = GridBagConstraints.HORIZONTAL;// 设置填充方式
        gbc_button2.weightx = 20.0;// 第一列的分布方式为20%
        gbc_button2.gridx = 1;// 起始点为第2列
        gbc_button2.gridy = 0;// 起始点为第1行
        contentPane.add(button2, gbc_button2);// 增加按钮及其约束条件
         
        JButton button3 = new JButton("C");// 创建按钮
        GridBagConstraints gbc_button3 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_button3.gridheight = 2;// 占用2列
        gbc_button3.fill = GridBagConstraints.BOTH;// 设置填充方式
        gbc_button3.weightx = 30.0;// 第一列的分布方式为30%
        gbc_button3.insets = new Insets(0, 0, 5, 5);// 设置控件的空白
        gbc_button3.gridx = 2;// 起始点为第3列
        gbc_button3.gridy = 0;// 起始点为第1行
        contentPane.add(button3, gbc_button3);// 增加按钮及其约束条件
         
        JButton button4 = new JButton("D");// 创建按钮
        GridBagConstraints gbc_button4 = new GridBagConstraints();// 创建网格组布局约束条件
        gbc_button4.weightx = 40.0;// 第一列的分布方式为40%
        gbc_button4.fill = GridBagConstraints.BOTH;// 设置填充方式
        gbc_button4.gridheight = 4;// 占用4列
        gbc_button4.insets = new Insets(0, 5, 0, 0);// 设置控件的空白
        gbc_button4.gridx = 3;// 起始点为第4列
        gbc_button4.gridy = 0;// 起始点为第1行
        contentPane.add(button4, gbc_button4);// 增加按钮及其约束条件
        button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        EventQueue.invokeLater(run);
//				System.exit(0);
			}
		});
    }
     
}
