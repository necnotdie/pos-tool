package pos.windows;

import javax.swing.JFrame;

import pos.tools.JNIWindowUtilProxy;

public class JWindowFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6937910846296761880L;
	private final String jawtpath = System.getProperty("java.home")+ System.getProperty("file.separator")+ "bin"+ System.getProperty("file.separator") + "jawt.dll";
	public void showWindow(){
		JNIWindowUtilProxy.showWindow(JNIWindowUtilProxy.getWindowHWND(jawtpath, this));
	}
	public void hideWindow(){
		JNIWindowUtilProxy.hideWindow(JNIWindowUtilProxy.getWindowHWND(jawtpath, this));
	}
}
