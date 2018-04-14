/*
 * Name:PageNode.java
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:20
 * Function:implement a Node of LinkList
 * */
package org.CBank.Test;
public class PageNode {
	//ҳ�������
	private String pagename="";
	//urlΪ��ַʵ�� 
	private String url="";
	//nextΪ��һ���ڵ�
	private PageNode next=null;
	//preΪǰһ���ڵ�
	private PageNode pre=null;
	
	//get��set����
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public PageNode getNext() {
		return next;
	}
	public void setNext(PageNode next) {
		this.next = next;
	}
	public PageNode getPre() {
		return pre;
	}
	public void setPre(PageNode pre) {
		this.pre = pre;
	}
	
	//��дĬ�Ϲ��캯��
	public PageNode()
	{
		this.pagename="";
		this.url="";
		this.next=null;
		this.pre=null;
	}
	//�Զ��幹�캯��
	public PageNode(String pagename,String urladdress)
	{
		this.pagename=pagename;
		this.url=urladdress;
		this.next=null;
		this.pre=null;
	}
}
