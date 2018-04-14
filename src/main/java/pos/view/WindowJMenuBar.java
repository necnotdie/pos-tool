package pos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pos.summer.Summer;

public class WindowJMenuBar extends JMenuBar implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287101942744121616L;
	public WindowJMenuBar() {
		super();
		this.setName("Window.JMenuBar");
		JMenu file1 = new JMenu("ϵͳ");
	    JMenuItem file1mi1 = new JMenuItem("����");
	    JMenuItem file1mi2 = new JMenuItem("��װ��ж�ز��");
	    file1mi1.addActionListener(this);
	    file1mi2.addActionListener(this);
	    JMenu file2 = new JMenu("����");
	    JMenuItem file2mi1 = new JMenuItem("����");
	    file2mi1.addActionListener(this);
	    file1.add(file1mi1);
	    file1.add(file1mi2);
	    file2.add(file2mi1);
	    this.add(file1);
	    this.add(file2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if("����".equals(e.getActionCommand())){
			WindowSetDialog dialog = (WindowSetDialog) Summer.newinstance().getSummerObject().get(WindowSetDialog.class).get("Window.Dialog");
			dialog.setVisible(true);
		}else if("����".equals(e.getActionCommand())){
			WindowAboutDialog aboutDialog = (WindowAboutDialog) Summer.newinstance().getSummerObject().get(WindowAboutDialog.class).get("Window.AboutDialog");
			aboutDialog.setVisible(true);
		}else if("��װ��ж�ز��".equals(e.getActionCommand())){
			WindowSoftDialog softDialog = (WindowSoftDialog) Summer.newinstance().getSummerObject().get(WindowSoftDialog.class).get("Window.SoftDialog");
			softDialog.setVisible(true);
		}
	}
}
