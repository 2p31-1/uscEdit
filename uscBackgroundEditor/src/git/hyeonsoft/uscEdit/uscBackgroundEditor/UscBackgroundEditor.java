package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;

public class UscBackgroundEditor {
	
	Project project;
	JFrame mainWindow;
	JPanel mainPanel;
	BackgroundEffect clipboard;
	
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
		//panel, project initialize
		project = new Project();
		mainPanel = new JPanel(new BorderLayout());
		mainWindow.add(mainPanel);
		//right panel initialize
		JPanel rightPanel = new JPanel(new GridLayout(5, 1));
		JButton addEffect = new JButton("add effect");
		JButton editEffect = new JButton("edit effect");
		JButton deleteEffect = new JButton("delete effect");
		JButton copyEffect = new JButton("copy effect");
		JButton pasteEffect = new JButton("paste effect");

		addEffect.addActionListener(e -> {
			project.addEffect(this);
		});
		editEffect.addActionListener(e -> {
			//project.editEffect(this);
		});
		deleteEffect.addActionListener(e -> {
			//project.deleteEffect(this);
		});
		copyEffect.addActionListener(e -> {
			//clipboard = project.getEffect(this);
		});
		pasteEffect.addActionListener(e -> {
			//project.addEffect(this, clipboard);
		});

		rightPanel.add(addEffect);
		rightPanel.add(editEffect);
		rightPanel.add(deleteEffect);
		rightPanel.add(copyEffect);
		rightPanel.add(pasteEffect);
		mainWindow.add(rightPanel, BorderLayout.EAST);
		
		makeScreen();
	}
	
	void makeScreen() {
		mainWindow.remove(mainPanel);
		mainPanel = new JPanel(new BorderLayout());
		//Making Panel Screen
		JList<String> backgroundEffect = new JList<String>(project.getLists());
		
		
		backgroundEffect.setSelectionMode(1);
		backgroundEffect.setLayoutOrientation(JList.VERTICAL);
		backgroundEffect.addListSelectionListener((ListSelectionEvent e) -> {
			project.selectedEffect = backgroundEffect.getSelectedIndex();
		});
		JScrollPane backgroundEffectScroller = new JScrollPane(backgroundEffect);
		backgroundEffectScroller.setPreferredSize(new Dimension(1100, 600));
		
		mainPanel.add(backgroundEffectScroller);
		
		
		mainWindow.add(mainPanel, BorderLayout.CENTER);
		mainWindow.revalidate();
	}
	
	@SuppressWarnings("deprecation")
	void makeMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemNew = new JMenuItem("New");
		JMenuItem itemLoad = new JMenuItem("Open");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemExport = new JMenuItem("Export Project");
		
		menuFile.setMnemonic(KeyEvent.VK_F);
		itemNew.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemLoad.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
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
