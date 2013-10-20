package com.pinktriangle.hussain.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateISOGUI extends JFrame {
	private static final Logger LOG = LoggerFactory.getLogger(CreateISOGUI.class);
	private static final long serialVersionUID = 1L;
	private static final String USER_HOME = System.getProperty("user.home");
	
	private JPanel panelNorth;
	private JButton addFile;
	private JButton createISO;
	private JButton removeFile;
	
	private DefaultListModel listModel;
	private JList filesAndFolders;
	
	private JFileChooser chooser;

	public CreateISOGUI(){
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
		chooser.setCurrentDirectory(new File(USER_HOME));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogTitle("Choose files and folders to add to the ISO image");
		chooser.setAcceptAllFileFilterUsed(true);
		
		panelNorth = new JPanel();
		addFile = new JButton("Add File/Folder");
		removeFile = new JButton("Remove File(s)/Folder(s)");
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
				int listSize = listModel.getSize();
				File [] filesToBeAdded = new File[listSize];
				if(listSize == 0)
				{
					//TODO Show error to the user
				}
				else
				{
					for(int i = 0; i < listSize; i++)
					{
						filesToBeAdded[i] = (File) listModel.get(i);
					}
					LOG.trace("Creating the ISOs with the following files and folders: "+Arrays.toString(filesToBeAdded));
				}
			}
		});
		
		removeFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				int [] selectedItems = filesAndFolders.getSelectedIndices();
				if(selectedItems.length == 0)
				{
					LOG.error("There are no items to remove");
				}
				else
				{
					LOG.trace("Removing items from the JList");
					for(int i = selectedItems.length - 1; i >= 0; i--)
					{
						listModel.removeElementAt(selectedItems[i]);
					}
				}
			}
		});
	}
	
}
