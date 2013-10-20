package com.pinktriangle.hussain.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateISO extends JFrame {
	private static final Logger LOG = LoggerFactory.getLogger(CreateISO.class);
	private static final long serialVersionUID = 1L;
	
	private JPanel panelNorth;
	private JButton addFile;
	private JButton createISO;
	private JButton removeFile;
	
	private DefaultListModel listModel;
	private JList filesAndFolders;
	
	private JFileChooser chooser;

	public CreateISO(){
		super("Create ISO Disk Image");
		setSize(new Dimension(550, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initWidgets();
		setLayout();
		addWidgets();
		addActionListeners();
	}
	
	private void initWidgets(){
		listModel = new DefaultListModel();
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogTitle("Choose files and folders to add to the ISO image");
		chooser.setAcceptAllFileFilterUsed(true);
		
		panelNorth = new JPanel();
		addFile = new JButton("Add File/Folder");
		removeFile = new JButton("Remove file");
		createISO = new JButton("Create ISO");
		
		filesAndFolders = new JList();
		filesAndFolders.setModel(listModel);
	}
	
	private void setLayout(){
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	private void addWidgets(){
		panelNorth.add(addFile);
		panelNorth.add(createISO);
		panelNorth.add(removeFile);
		
		this.add(panelNorth,BorderLayout.NORTH);
		this.add(new JScrollPane(filesAndFolders), BorderLayout.CENTER);
	}
	
	private void addActionListeners(){
		addFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					listModel.addElement(chooser.getSelectedFile());
				}
			}
		});
		
		createISO.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		removeFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
