package pos.test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class Test extends JFrame {
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	JTextField jtf = new JTextField(5);

	Test() {
		jp1.setBackground(Color.RED);
		jp2.setBackground(Color.YELLOW);
		jp3.setBackground(Color.GREEN);
		jp4.setBackground(Color.BLUE);
		jp1.add(jtf);
		jtf.setMargin(new Insets(4, 4, 4, 4));
		Container container = getContentPane();
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				jp1, jp2);
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				jp3, jp4);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				splitPane1, splitPane2);
		container.add(splitPane, BorderLayout.CENTER);
		pack();
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		splitPane1.setDividerLocation(0.3);
		splitPane2.setDividerLocation(0.5);
		splitPane.setDividerLocation(0.5);
	}

	public static void main(String[] args) {
		System.out.println("======="+System.getProperty("java.library.path"));
		new Test();
		System.out.println(" ".getBytes()[1]);
	}
}