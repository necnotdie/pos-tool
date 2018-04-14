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
		JLabel jLabel = new JLabel("窗口关闭时执行操作：");
//		jLabel.setBounds(20, 50, 150, 30);
		final ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton button1 = new JRadioButton("询问");
//		button1.setBounds(200, 10, 130, 30);
		button1.setName("default");
		JRadioButton button2 = new JRadioButton("直接退出");
//		button2.setBounds(200, 50, 130, 30);
		button2.setName("exit");
		JRadioButton button3 = new JRadioButton("最小化至托盘");
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
		JButton jButton1 = new JButton("应用");
		jButton1.setBounds(110, 130, 80, 25);
		JButton jButton2 = new JButton("恢复默认");
		jButton2.setBounds(210, 130, 80, 25);
		GridBagLayout layout = new GridBagLayout();
		jPanel.setLayout(layout);
		jPanel.add(button1);
		jPanel.add(button2);
		jPanel.add(button3);
		jPanel.add(jButton1);
		jPanel.add(jButton2);
		jPanel.add(jLabel);
		GridBagConstraints s = new GridBagConstraints();// 定义一个GridBagConstraints，
		// 是用来控制添加进的组件的显示位置
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
		s.gridheight = 1;
		s.weightx = 0;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.gridy = 0;
		s.gridx = 0;
		layout.setConstraints(jLabel, s);// 设置组件
		s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
		s.gridheight = 1;
		s.weightx = 0;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.gridx = 1;
		s.gridy = 0;
		layout.setConstraints(button1, s);// 设置组件
		s.gridy = 1;
		layout.setConstraints(button2, s);// 设置组件
		s.gridy = 2;
		layout.setConstraints(button3, s);// 设置组件
		s.insets = new Insets(80, 20, 20, 20);
		s.gridwidth = 0;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
		s.gridheight = 1;
		s.weightx = 0;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.gridx = 2;
		s.gridy = 3;
		layout.setConstraints(jButton2, s);// 设置组件
		s.gridwidth = 0;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
		s.gridheight = 1;
		s.weightx = 0;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		s.gridx = 3;
		s.gridy = 3;
		layout.setConstraints(jButton1, s);// 设置组件
		jPanel.setVisible(true);
	
	}
}
