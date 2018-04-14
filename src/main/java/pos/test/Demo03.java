package pos.test;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import pos.conf.ConfInit;

public class Demo03 {

	public static void main(String[] args) {
		JFrame jPanel = new JFrame("01");
		jPanel.setSize(600, 600);
		jPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame jFrame = new JFrame();
		jFrame.add(new JButton("122"),BorderLayout.CENTER);
		jPanel.add(jFrame.getRootPane(),BorderLayout.CENTER);
		jPanel.setVisible(true);
	}
}
