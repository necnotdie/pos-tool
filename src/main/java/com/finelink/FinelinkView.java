package com.finelink;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

import pos.annotation.Resource;
import pos.tools.Table;

//@Resource(name = "dfsfds")
public class FinelinkView implements Table{
	@Override
	public Component view() {
		JPanel jPanel = new JPanel();
		jPanel.add(new JButton("fdasfsdagsdafsdf"),BorderLayout.CENTER);
		return jPanel;
	}
}
