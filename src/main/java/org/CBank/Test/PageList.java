/*
 * Name:PageList.java
 * Writer:bitsjx
 * Date:2009-12-07
 * Time:00:26
 * Function:Construct a LinkList to save History and BookMarks Nodes
 * */
package org.CBank.Test;
public class PageList {
	//ͷ�ڵ�
	private PageNode head;
	//���ɽڵ�
	private PageNode link;
	//��¼��ǰ�Ľڵ�����λ��
	private PageNode linkpointer;
	public PageList()
	{
		head=new PageNode();
		link=head;
		linkpointer=head;
	}
	//����µ�ҳ�浽����
	public void addURL(String pagename,String urladdress)
	{
		PageNode node=new PageNode();
		link=head;
		while(link.getNext()!=null)
		{
			link=link.getNext();
		}
		node.setPagename(pagename);
		node.setUrl(urladdress);
		link.setNext(node);
		node.setPre(link);
		node.setNext(null);
		linkpointer=node;
	}
	//�������ͷ
	public PageNode getPageNode()
	{
		return this.head;
	}
	
	//����ĳ��pagename��Ӧ��URL
	public String getURL(String pagename)
	{
		link=head;
		String url="";
		while(!((link.getNext()).getPagename()).equalsIgnoreCase(pagename))
		{
			link=link.getNext();
		}
		url=(link.getNext()).getUrl();
		linkpointer=link;
		return url;
	}
	
	//��ȡĳ��URL��Ӧ��pagename
	public String getPageName(String urladdress)
	{
		link=head;
		String pagename="";
		while(!(link.getNext().getUrl()).equalsIgnoreCase(urladdress))
		{
			link=link.getNext();
		}
		pagename=link.getNext().getPagename();
		linkpointer=link;
		return pagename;
	}
	//��ȡ��ǰpagename��ǰһ��pagename
	public String getPrePageName(String pagename)
	{
		link=linkpointer.getPre();
		linkpointer=link;
		return link.getPagename();
	}
	//��ȡ��ǰpagename����һ��pagename
	public String getNextPageName(String pagename)
	{
		link=linkpointer.getNext();
		linkpointer=link;
		return link.getPagename();
	}
	//����ĳ��pagename��ַ�Ƿ����
	public boolean isPageNameExist(String pagename)
	{
		link=this.head.getNext();
		//����Ƿ��ҵ�
		boolean isfind=false;
		String pageName="";
		while(link!=null)
		{
			pageName=link.getPagename();
			if(pageName.equalsIgnoreCase(pagename))
			{
				isfind=true;
				break;
			}
			link=link.getNext();
		}
		System.out.println("���ұ�ҳ���:"+isfind);
		return isfind;
	}
	//�ж�ĳ��pagename��ǰһ��pagename�Ƿ����
	public boolean isPrePageNameExist(String pagename)
	{
		boolean isfind=false;
		link=head;
		link=linkpointer.getPre();
		if(link!=null&&link!=head)
		{
			isfind=true;
		}
		System.out.println("����ǰҳ���:"+isfind);
		return isfind;
	}
	//�ж�ĳ��pagename�ĺ�һ��pagename�Ƿ����
	public boolean isNextPageNameExist(String pagename)
	{
		boolean isfind=false;
		link=head;
		link=linkpointer.getNext();
		if(link!=null)
		{
			isfind=true;
		}
		System.out.println("���Һ�ҳ���:"+isfind);
		return isfind;
	}
}
