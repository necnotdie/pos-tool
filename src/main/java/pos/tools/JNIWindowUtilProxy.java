package pos.tools;

import javax.swing.JFrame;

public class JNIWindowUtilProxy {
	// ��ʾJFrame����
	public static native void showWindow(int HWND);
	// ����JFrame����
	public static native void hideWindow(int HWND);
	// �õ����Java����Ĵ��ھ��HWND
	public static native int getWindowHWND(String jawtpath, JFrame target);
}
