/*
 * Name:ThinkFrame.java
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:20
 * Function:Use class MainUI to implement a GUI
 * */
package org.CBank.Test;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross;
public class ThinkFrame {
	public ThinkFrame() 
	{
		//设置窗口外观
		try {
//			UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
			UIManager.setLookAndFeel(new BeautyEyeLookAndFeelCross());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(new MainUI(), "GUI初始化失败！","Think 提示您:",JOptionPane.ERROR_MESSAGE);
		}
		
		MainUI ui=new MainUI();
		ui.pack();
		ui.setVisible(true);
	}
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine se = sem.getEngineByName("javascript"); 
		setProxy("proxy.piccnet.com.cn","3128");
		ThinkFrame core=new ThinkFrame();
	}
	public static void setProxy(String host, String port) {  
		  System.setProperty("proxySet", "true");  
		  System.setProperty("proxyHost", host);  
		  System.setProperty("proxyPort", port);  
	}  
}
