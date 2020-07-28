package git.hyeonsoft.uscEdit.uscBackgroundEditor;
// Save / Load files. Contains informations of background information.

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Project {
	public Vector<BackgroundEffect> backgroundEffect = new Vector<BackgroundEffect>();
	public String path="";
	public String filename="";
	public String songpath="";
	public int selectedEffect=0; //Selected Effect number
	JDialog dialog;
	public Vector<String> getLists(){
		Vector<String> l = new Vector<String>();
		for(BackgroundEffect x : backgroundEffect) {
			String lv;
			lv=x.effectName+", Starts at "+x.startTime.toString()+", ends at "+x.endTime.toString()+". Image at \""+x.imagePath+"\"";
			l.add(lv);
		}
		return l;
	}
	public BackgroundEffect getEffect() {
		return backgroundEffect.get(selectedEffect);
	}
	public void addEffect(UscBackgroundEditor sup) {
		JFrame f = new JFrame();
	    f.setLayout( null );
	    f.setVisible(false);
		dialog = new JDialog(f);
		dialog.setLayout(new GridLayout(2, 0));
		dialog.setSize(600, 300);
		dialog.setTitle("Add Effects");
		
		BackgroundEffect modifyBackgroundEffect = new BackgroundEffect();
		JTextField effectName = new JTextField(modifyBackgroundEffect.effectName);
		JTextField startTime = new JTextField(modifyBackgroundEffect.startTime.toString());
		JTextField endTime = new JTextField(modifyBackgroundEffect.endTime.toString());
		JTextField fadeIn = new JTextField(modifyBackgroundEffect.fadeIn.toString());
		JTextField fadeOut = new JTextField(modifyBackgroundEffect.fadeOut.toString());
		JTextField size = new JTextField(modifyBackgroundEffect.size.toString());
		JTextField imagePath = new JTextField(modifyBackgroundEffect.imagePath);
		
		JButton ok = new JButton("ok");
		ok.addActionListener(e -> {
			modifyBackgroundEffect.startTime=Double.parseDouble(startTime.getText());
			modifyBackgroundEffect.endTime=Double.parseDouble(endTime.getText());
			modifyBackgroundEffect.fadeIn=Double.parseDouble(fadeIn.getText());
			modifyBackgroundEffect.fadeOut=Double.parseDouble(fadeOut.getText());
			modifyBackgroundEffect.size=Double.parseDouble(size.getText());
			modifyBackgroundEffect.imagePath=imagePath.getText();
			modifyBackgroundEffect.effectName=(effectName.getText());
			backgroundEffect.add(modifyBackgroundEffect);
			sup.makeScreen();
		});
		dialog.add(ok, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
	public void addEffect(UscBackgroundEditor sup, BackgroundEffect clipboard) {
		
	}
	public void editEffect(UscBackgroundEditor sup) {
		
	}
	public void deleteEffect(UscBackgroundEditor sup) {
		
	}
	public void loadProject() {
		JFrame f = new JFrame();
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
