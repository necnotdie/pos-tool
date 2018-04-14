package pos.test;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Demo02 {
	public static void main(String[] args) {
		Demo02 demo02 = new Demo02();
		JFrame frame = demo02.frame;
		frame.setSize(400, 200);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button1 = new JButton("1");
		final JButton button2 = new JButton("2");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button2.setVisible(false);
			}
		});
		final JButton button3 = new JButton("3");
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button3.setVisible(false);
			}
		});
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		demo02.autoSize();
		frame.setVisible(true);
	}
	JFrame frame = new JFrame();
	public void autoSize(){

		GridBagLayout gridBagLayout = (GridBagLayout) frame.getRootPane().getContentPane().getLayout();
		GridBagConstraints s = new GridBagConstraints();// 定义一个GridBagConstraints，
		// 是用来控制添加进的组件的显示位置
//		Dimension size = new Dimension(jFrame.getWidth()-6,jFrame.getHeight()-53);
		Component[] com = frame.getRootPane().getContentPane().getComponents();
//		List<Component> components = new ArrayList<Component>();
//		for (Component component : com) {
//			if (component.isVisible()&&component instanceof JPanel) {
//				components.add(component);
//			}
//		}
//		int x = 0;
//		int y = 0;
//		int height = 0;
//		int width = 0;
		s.fill = GridBagConstraints.BOTH;
		s.insets = new Insets(10, 10, 10, 10);
		for (int i = 0; i < com.length; i++) {
			if (com.length <= 2) {
//				width = (size.width - 10 * (components.size() - 1))
//						/ components.size();
//				height = size.height;
//				components.get(i).setBounds(x, y, width, height);
//				x = x + width + 10;
				s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
				s.gridheight = 1;
				s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
				s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
				s.gridy = 0;
				s.gridx = i;
				if(i==(com.length-1)){s.gridwidth = 0;}
				gridBagLayout.setConstraints(com[i], s);
			}
			if (com.length > 2 && (com.length % 2 == 1)) {
//				width = (size.width - 10 * ((components.size() + 1) / 2 - 1))
//						/ ((components.size() + 1) / 2);
				if (i == ((com.length - 1) / 2)) {
//					height = size.height;
//					components.get(i).setBounds(x, y, width, height);
//					x = 0;
//					y = y + (size.height - 10) / 2 + 10;
					s.gridwidth = 0;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
					s.gridheight = 2;
					s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.gridy = 0;
					s.gridx = i;
					gridBagLayout.setConstraints(com[i], s);
				} else if (i < ((com.length - 1) / 2)) {
					s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
					s.gridheight = 1;
					s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.gridy = 0;
					s.gridx = i;
					gridBagLayout.setConstraints(com[i], s);
				} else {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
					s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
					s.gridheight = 1;
					s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.gridy = 1;
					s.gridx = i-(com.length+1)/2;
					gridBagLayout.setConstraints(com[i], s);
				}
			}
			if (com.length > 2 && (com.length % 2 == 0)) {
//				width = (size.width - 10 * (components.size() / 2 - 1))
//						/ ((components.size()) / 2);
				if (i < ((com.length) / 2)) {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
//					if (i == ((components.size()) / 2 - 1)) {
//						x = 0;
//						y = y + height + 10;
//					}
					s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
					s.gridheight = 1;
					s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.gridy = 0;
					s.gridx = i;
					if(i==(com.length-1)/2){s.gridwidth = 0;}
					gridBagLayout.setConstraints(com[i], s);
				} else {
//					height = (size.height - 10) / 2;
//					components.get(i).setBounds(x, y, width, height);
//					x = x + width + 10;
					s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
					s.gridheight = 1;
					s.weightx = 1;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.weighty = 1;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
					s.gridy = 1;
					s.gridx = i;
					if(i==(com.length-1)){s.gridwidth = 0;}
					gridBagLayout.setConstraints(com[i], s);
				}
			}
		}
	}
}
