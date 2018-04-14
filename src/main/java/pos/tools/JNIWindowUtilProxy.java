package pos.tools;

import javax.swing.JFrame;

public class JNIWindowUtilProxy {
	// 显示JFrame窗口
	public static native void showWindow(int HWND);
	// 隐藏JFrame窗口
	public static native void hideWindow(int HWND);
	// 得到这个Java窗体的窗口句柄HWND
	public static native int getWindowHWND(String jawtpath, JFrame target);
}
