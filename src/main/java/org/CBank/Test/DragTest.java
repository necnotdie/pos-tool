package org.CBank.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * ��򵥵�Java��ק����ʾ��
 * @author ���԰�
 * 2013��1��24��
 */
public class DragTest extends JFrame
{

    JPanel panel;//Ҫ������ק�����
    public DragTest()
    {
        panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("��򵥵���קʾ������ק�ļ������棨20130124��");
        drag();//������ק
    }
    public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//����Ƥ��
        new DragTest().setVisible(true);;
    }
    public void drag()//�������ק����
    {
        //panel��ʾҪ������ק�Ŀؼ�
        new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)//��д��������drop����
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//���������ļ���ʽ��֧��
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//������ק��������
                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        String temp="";
                        for(File file:list)
                            temp+=file.getAbsolutePath()+";\n";
                        JOptionPane.showMessageDialog(null, temp);
                        dtde.dropComplete(true);//ָʾ��ק���������
                    }
                    else
                    {
                        dtde.rejectDrop();//����ܾ���ק��������
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
