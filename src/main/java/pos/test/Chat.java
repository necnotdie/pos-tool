package pos.test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import pos.tools.JNIWindowUtilProxy;
import pos.windows.JWindowFrame;

public class Chat{
//	static{
//		System.loadLibrary("JNIWindowUtilProxy");
//	}
	public static void main(String[] args) {
//		System.getProperties().list(System.out);
//		System.getProperty("file.encoding");
		System.out.println(System.getProperty("java.home")+ System.getProperty("file.separator")+ "bin"+ System.getProperty("file.separator") + "jawt.dll");
		System.load(JNIWindowUtilProxy.class.getResource("JNIWindowUtilProxy.dll").getPath());
		System.out.println(JNIWindowUtilProxy.class.getResource("JNIWindowUtilProxy.dll").getPath());
		System.out.println("======="+System.getProperty("java.library.path"));
		final JWindowFrame frame = new JWindowFrame();
		frame.setTitle("111");
		frame.setSize(800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button1 = new JButton("Òþ²Ø£¡£¡£¡");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.hideWindow();
			}
		});
		JButton button2 = new JButton("ÏÔÊ¾£¡£¡£¡");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.showWindow();
			}
		});
		frame.setLayout(new FlowLayout());
		frame.add(button1);
		frame.add(button2);
		frame.setVisible(true);
	}
}