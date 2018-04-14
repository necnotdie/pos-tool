package pos.test;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
public class Tree extends JFrame implements TreeSelectionListener  
{  
    private JLabel label;  
  
    public Tree()  
    {  
        super("���β˵�");  setSize(400,400);  
        Container container = getContentPane();  
  
        //�������ڵ���ӽڵ�  
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("�ı��༭��");  
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�ļ�");  
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("�༭");  
        //���ø��ڵ㴴��TreeModel  
        DefaultTreeModel treeModel = new DefaultTreeModel(root);  
        //�����ӽڵ�node1,node2  
        treeModel.insertNodeInto(node1,root,root.getChildCount());  
        treeModel.insertNodeInto(node2,root,root.getChildCount());  
  
        //�����ڵ�node1���ӽڵ㲢����  
        DefaultMutableTreeNode leafnode = new DefaultMutableTreeNode("��");  
        treeModel.insertNodeInto(leafnode,node1,node1.getChildCount());  
        leafnode = new DefaultMutableTreeNode("����");  
        treeModel.insertNodeInto(leafnode,node1,node1.getChildCount());  
        leafnode = new DefaultMutableTreeNode("���Ϊ");  
        treeModel.insertNodeInto(leafnode,node1,node1.getChildCount());  
        leafnode = new DefaultMutableTreeNode("�ر�");  
        treeModel.insertNodeInto(leafnode,node1,node1.getChildCount());  
  
        //�����ڵ�node2���ӽڵ㲢����  
        leafnode = new DefaultMutableTreeNode("����");  
        treeModel.insertNodeInto(leafnode,node2,node2.getChildCount());  
        leafnode = new DefaultMutableTreeNode("����");  
        treeModel.insertNodeInto(leafnode,node2,node2.getChildCount());  
        leafnode = new DefaultMutableTreeNode("ճ��");  
        treeModel.insertNodeInto(leafnode,node2,node2.getChildCount());  
  
        //����������  
        JTree tree = new JTree(treeModel);  
        //����Tree��ѡ��Ϊһ��ֻ��ѡ��һ���ڵ�  
        tree.getSelectionModel().setSelectionMode(  
                            TreeSelectionModel.SINGLE_TREE_SELECTION);  
        //ע�������  
        tree.addTreeSelectionListener(this);  
  
        tree.setRowHeight(20);  
  
        //�����ڵ���ƶ���  
        DefaultTreeCellRenderer cellRenderer =  
                            (DefaultTreeCellRenderer)tree.getCellRenderer();  
  
        //��������  
        cellRenderer.setFont(new Font("Serif",Font.PLAIN,14));  
        cellRenderer.setBackgroundNonSelectionColor(Color.white);  
        cellRenderer.setBackgroundSelectionColor(Color.yellow);  
        cellRenderer.setBorderSelectionColor(Color.red);  
  
        //����ѡ��ѡʱ�����ֵı仯��ɫ  
        cellRenderer.setTextNonSelectionColor(Color.black);  
        cellRenderer.setTextSelectionColor(Color.blue);  
          
        //����������ӵ��������  
        container.add(new JScrollPane(tree));  
  
        //������ǩ  
        label = new JLabel("�㵱ǰѡ��Ľڵ�Ϊ��",JLabel.CENTER);  
        label.setFont(new Font("Serif",Font.PLAIN,14));  
        container.add(label,BorderLayout.SOUTH);  
  
        setVisible(true);   //���ÿɼ�  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ô��ڹرն���  
    }  
  
    //����TreeSelectionEvent�¼�  
    public void valueChanged(TreeSelectionEvent event)  
    {  
        JTree tree = (JTree)event.getSource();  
        //��ȡĿǰѡȡ�Ľڵ�  
        DefaultMutableTreeNode selectionNode =  
            (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();  
        String nodeName = selectionNode.toString();  
        label.setText("�㵱ǰѡȡ�Ľڵ�Ϊ�� "+nodeName);  
    }  
  
    public static void main(String args[])  
    {  
        Tree d = new Tree();  
    }  
  
}  