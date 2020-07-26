package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class UscBackgroundEditor {
	
	Project project;
	static JFrame mainWindow;
	
	public static void main(String[] args) {
		//initialize
		
		//window initialize
		mainWindow = new JFrame();
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
	}

}
