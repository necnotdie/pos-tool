package pos.test;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 400);
		frame.setLayout(new BoxLayout(frame, BoxLayout.X_AXIS )); 
		frame.add(new JScrollPane(new JTextArea()));
		frame.add(new JButton("sadas"));
		frame.setVisible(true);
	}
}
