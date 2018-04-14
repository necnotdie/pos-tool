package org.CBank.codegenerate;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class CodeGenerateTable {
	public static void main(String[] args) {
		JEditorPane editPane = null;
		try {
			URL address = new URL("http://www.baidu.com");
			InetSocketAddress socketAddress=new InetSocketAddress(InetAddress.getByName("proxy.piccnet.com.cn"),3128); 
//			setProxy("proxy.piccnet.com.cn","3128");
			URLConnection connection = address.openConnection(new Proxy(Type.HTTP, socketAddress));
			editPane = new JEditorPane(connection.getURL());
			editPane.setContentType("text/html");
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL:" + e);
		} catch (IOException e) {
			System.out.println("IOException:" + e);
		}
		editPane.setEditable(false);
		JFrame f = new JFrame("JEditorPane3");
		f.setContentPane(new JScrollPane(editPane));
		f.setSize(200, 250);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
//	public static void setProxy(String host, String port) {  
//        System.setProperty("proxySet", "true");  
//        System.setProperty("proxyHost", host);  
//        System.setProperty("proxyPort", port);  
//    }  
}
