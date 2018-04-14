package pos.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import pos.conf.ConfInit;
import pos.windows.Window;

public class WindowSetDialog extends JDialog implements TreeSelectionListener{
	
	/**
	 * 
	 */
	private JTree tree;
	private JScrollPane rightScrollPane;
	private DefaultMutableTreeNode root;
	private static final long serialVersionUID = -1538089951651260539L;
	private List<Component> componentList;
	public WindowSetDialog(){
		super();
//		this(null, "����", true);
		this.setTitle("����");
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		componentList = new ArrayList<Component>();
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		root = new DefaultMutableTreeNode("Root");   
        DefaultMutableTreeNode systemSet = new DefaultMutableTreeNode("ϵͳ����");
        DefaultMutableTreeNode baseSet = new DefaultMutableTreeNode("��������");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);  
        root.add(systemSet);
        systemSet.add(baseSet);
        tree = new JTree(treeModel);  
        //����Tree��ѡ��Ϊһ��ֻ��ѡ��һ���ڵ�  
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRowHeight(20);  
        tree.addTreeSelectionListener(this);
        //�����ڵ���ƶ���  
        DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer)tree.getCellRenderer();  
        //��������  
        cellRenderer.setFont(new Font("Serif",Font.PLAIN,14));  
        cellRenderer.setBackgroundNonSelectionColor(Color.white);  
        cellRenderer.setBackgroundSelectionColor(Color.yellow);  
        cellRenderer.setBorderSelectionColor(Color.red);
        //����ѡ��ѡʱ�����ֵı仯��ɫ  
        cellRenderer.setTextNonSelectionColor(Color.black);  
        cellRenderer.setTextSelectionColor(Color.blue); 
        tree.setRootVisible(false);
        JScrollPane leftScrollPane = new JScrollPane(tree);
        rightScrollPane = new JScrollPane();
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftScrollPane,rightScrollPane);
		JPanel jPanel = new JPanel(new BorderLayout(30,200));
		jPanel.setName(Arrays.toString(baseSet.getUserObjectPath()));
		SetWSD(jPanel);
		componentList.add(jPanel);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT,20,10);
		JPanel downJPanel = new JPanel(flowLayout);
		JButton jButton1 = new JButton("ȷ��");
		JButton jButton2 = new JButton("ȡ��");
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowSetDialog.this.setVisible(false);
			}
		});
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowSetDialog.this.setVisible(false);
			}
		});
		downJPanel.add(jButton1);
		downJPanel.add(jButton2);
		this.add(jSplitPane,BorderLayout.CENTER);
		this.add(downJPanel,BorderLayout.SOUTH);
	}
	public void addComponent(Component component){
		componentList.add(component);
	}
	public void addTree(DefaultMutableTreeNode node){
		((DefaultTreeModel) tree.getModel()).insertNodeInto(node,root,root.getChildCount());
	}
	private WindowSetDialog(JFrame jFrame, String title, boolean modal) {
		super(jFrame, title, modal);
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
//		System.out.println(rightJPanel.getSize());
		for (Component component : componentList) {
			if(e.getPath().toString().equals(component.getName())){
				component.setVisible(true);
				rightScrollPane.getViewport().add(component);
			}else{
				component.setVisible(false);
				rightScrollPane.getViewport().remove(component);
			}
		}
	}
	private void SetWSD(JPanel jPanel){
		JLabel jLabel = new JLabel("���ڹر�ʱִ�в�����");
		final ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton button1 = new JRadioButton("ѯ��");
		button1.setName("default");
		JRadioButton button2 = new JRadioButton("ֱ���˳�");
		button2.setName("exit");
		JRadioButton button3 = new JRadioButton("��С��������");
		button3.setName("minisize");
		buttonGroup.add(button1);
		buttonGroup.add(button2);
		buttonGroup.add(button3);
		String select = null;
		try {
			select = ConfInit.getSet();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		Enumeration<AbstractButton> enumeration = buttonGroup.getElements();
		while (enumeration.hasMoreElements()) {
			JRadioButton button = (JRadioButton) enumeration.nextElement();
			if (select.equals(button.getName())) {
				button.setSelected(true);
			}
		}
		final JButton jButton1 = new JButton("Ӧ��");
//		jButton1.setBounds(110, 130, 80, 25);
		jButton1.setEnabled(false);
		jButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Enumeration<AbstractButton> enumeration = buttonGroup
						.getElements();
				while (enumeration.hasMoreElements()) {
					JRadioButton button = (JRadioButton) enumeration
							.nextElement();
					if (button.isSelected()) {
						try {
							ConfInit.setSet(button.getName());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						jButton1.setEnabled(false);
					}
				}
			}
		});
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(true);
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(true);
			}
		});
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(true);
			}
		});
		JButton jButton2 = new JButton("�ָ�Ĭ��");
		jButton2.setBounds(210, 130, 80, 25);
		jButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Enumeration<AbstractButton> enumeration = buttonGroup
						.getElements();
				while (enumeration.hasMoreElements()) {
					JRadioButton button = (JRadioButton) enumeration
							.nextElement();
					if ("ѯ��".equals(button.getText())) {
						button.setSelected(true);
					}
				}
				jButton1.setEnabled(true);
			}
		});
		GridBagLayout layout = new GridBagLayout();
		JPanel jPanel1 = new JPanel(layout);
		jPanel1.add(button1);
		jPanel1.add(button2);
		jPanel1.add(button3);
		jPanel1.add(jLabel);
		GridBagConstraints s = new GridBagConstraints();// ����һ��GridBagConstraints��
		// ������������ӽ����������ʾλ��
		s.fill = GridBagConstraints.BOTH;
		s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridy = 0;
		s.gridx = 0;
		s.insets = new Insets(50, 10, 10, 10);
		layout.setConstraints(jLabel, s);// �������
		s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 1;
		s.gridy = 1;
		s.insets = new Insets(10, 10, 10, 10);
		layout.setConstraints(button1, s);// �������
		s.gridy = 2;
		layout.setConstraints(button2, s);// �������
		s.gridy = 3;
		layout.setConstraints(button3, s);// �������
		s.gridwidth = 1;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 2;
		s.gridy = 4;
		layout.setConstraints(jButton2, s);// �������
		s.gridwidth = 0;// �÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
		s.gridheight = 1;
		s.weightx = 0;// �÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.weighty = 0;// �÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
		s.gridx = 3;
		s.gridy = 4;
		layout.setConstraints(jButton1, s);// �������
		JPanel jPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jPanel2.add(jButton2);
		jPanel2.add(jButton1);
		jPanel.add(jPanel1,BorderLayout.WEST);
		jPanel.add(jPanel2,BorderLayout.SOUTH);
		jPanel.setVisible(false);
	}
}
