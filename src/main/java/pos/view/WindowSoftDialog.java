package pos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pos.windows.Window;

public class WindowSoftDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288503705669581654L;
	public WindowSoftDialog() {
		super();
		this.setTitle("安装或卸载插件");
//		super(null, , true);
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		JPanel centerJPanel = new JPanel();
//		topJPanel = new JPanel();
//		topJPanel.setBackground(Color.WHITE);
//		BoxLayout boxLayout = new BoxLayout(topJPanel, BoxLayout.Y_AXIS);
//		topJPanel.setLayout(boxLayout);
//		topJPanel.add(new JLabel("POS机运维工具"));
//		topJPanel.add(new JLabel("版本：Lemon 1.0"));
//		downJPanel = new JPanel();
//		downJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		JButton jButton = new JButton("确定");
//		jButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				WindowAboutDialog.this.dispose();
//			}
//		});
//		downJPanel.add(jButton);
//		JScrollPane jScrollPane = new JScrollPane(topJPanel);
//		this.add(jScrollPane,BorderLayout.CENTER);
//		this.add(downJPanel,BorderLayout.SOUTH);
	}
}
