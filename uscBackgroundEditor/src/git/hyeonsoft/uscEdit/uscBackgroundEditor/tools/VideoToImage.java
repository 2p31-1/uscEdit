package git.hyeonsoft.uscEdit.uscBackgroundEditor.tools;

import java.awt.FileDialog;

import javax.swing.JFrame;

public class VideoToImage {
	public VideoToImage(){
		JFrame f = new JFrame();
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Load Video", FileDialog.LOAD);
		dialog.setVisible(true);
		String videoPath = dialog.getDirectory()+'\\'+dialog.getFile();
	}
}
