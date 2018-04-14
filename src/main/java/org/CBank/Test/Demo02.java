//package org.CBank.Test;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//
//import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross;
//import org.mozilla.browser.MozillaPanel;
//
//public class Demo02 {
//	public static void main(String[] args) {
//		System.out.println(UIManager.getInstalledLookAndFeels());
////		UIManager.installLookAndFeel("BeautyEyeLookAndFeelCross", "org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross");
////		try {
////			UIManager.setLookAndFeel(new BeautyEyeLookAndFeelCross());
////		} catch (UnsupportedLookAndFeelException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
////		JFrame.setDefaultLookAndFeelDecorated(true);
////		JDialog.setDefaultLookAndFeelDecorated(true);
////		try {
////			UIManager.setLookAndFeel(new BeautyEyeLookAndFeelCross());
////		} catch (UnsupportedLookAndFeelException e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
//		final JFrame frame = new JFrame();
//		frame.setUndecorated(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(800, 400);
//		frame.add(new MozillaPanel(),BorderLayout.CENTER);
//		final JButton button = new JButton("��Ƥ����");
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					System.out.println("����");
////					frame.getRootPane().updateUI();
////					button.updateUI();
////
////					UIManager.getDefaults();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		frame.add(button,BorderLayout.SOUTH);
//		frame.setVisible(true);
////		String reg = "^(?![A-Za-z]+$)(?![^a-z\\d\\s]+$)(?![A-Z\\d]+$)(?![a-z\\d]+$)(?![^A-Z\\d\\s]+$)(?![^A-Za-z\\s]+$)[^\\s]{6,}$";
////		System.out.println("@qwesdasda".matches(reg));
//	}
//}
