package git.hyeonsoft.uscEdit.uscBackgroundEditor;
// Save / Load files. Contains informations of background information.

import java.awt.FileDialog;
import java.util.Vector;

import javax.swing.JFrame;

public class Project {
	public Vector<BackgroundEffect> backgroundEffect;
	public String path="";
	public String filename="";
	public String songpath="";
	
	public void loadProject() {
		JFrame f = new JFrame();
	    f.setSize(350,250);     
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Load", FileDialog.LOAD);
		dialog.setFilenameFilter(new USCBGPROJFileFilter());
		dialog.setVisible(true);
		path = dialog.getDirectory();
		filename = dialog.getFile();
	}
	public void saveProject() {
		JFrame f = new JFrame();
	    f.setSize(350,250);     
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Save", FileDialog.SAVE);
		dialog.setFilenameFilter(new USCBGPROJFileFilter());
		dialog.setVisible(true);
	}
	public void exportProject() {
		JFrame f = new JFrame();
	    f.setSize(350,250);     
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Save", FileDialog.SAVE);
		dialog.setFilenameFilter(new LUAFileFilter());
		dialog.setVisible(true);
	}
}
