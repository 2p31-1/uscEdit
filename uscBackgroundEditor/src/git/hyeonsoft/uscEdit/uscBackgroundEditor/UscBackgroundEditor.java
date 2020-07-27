package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class UscBackgroundEditor {
	
	Project project;
	JFrame mainWindow;
	JPanel mainPanel;
	
	public UscBackgroundEditor(){
		//window initialize
		mainWindow = new JFrame();
		makeMenu();
		mainWindow.setTitle("USC Background Editor");
		mainWindow.setSize(1280, 720);
		mainWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

			}
			
		});
		mainWindow.setLayout(new BorderLayout(0, 0));
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		//initialize
		project = new Project();
		mainPanel = new JPanel();
		mainWindow.add(mainPanel);
		makeScreen();
	}
	
	void makeScreen() {
		mainWindow.remove(mainPanel);
		mainPanel = new JPanel();
		//Making Panel Screen
		
		mainWindow.add(mainPanel);
		mainWindow.revalidate();
	}
	
	void makeMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemNew = new JMenuItem("New");
		JMenuItem itemLoad = new JMenuItem("Load");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemExport = new JMenuItem("Export Project");
		
		itemNew.addActionListener((ActionEvent e) -> {
			project = new Project();
			makeScreen();
		});
		itemSave.addActionListener((ActionEvent e) -> {
			project.saveProject();
			makeScreen();
		});
		itemLoad.addActionListener((ActionEvent e) -> {
			project.loadProject();
			makeScreen();
		});
		itemExport.addActionListener((ActionEvent e) -> {
			project.exportProject();
			makeScreen();
		});
		menuFile.add(itemNew);
		menuFile.add(itemLoad);
		menuFile.add(itemSave);
		menuFile.add(itemExport);
		menuBar.add(menuFile);
		mainWindow.setJMenuBar(menuBar);
	}
	
	public static void main(String[] args) {
		new UscBackgroundEditor();
	}

}
