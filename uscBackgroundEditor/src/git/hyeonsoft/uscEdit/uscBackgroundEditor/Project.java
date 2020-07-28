package git.hyeonsoft.uscEdit.uscBackgroundEditor;
// Save / Load files. Contains informations of background information.

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Project {
	public Vector<BackgroundEffect> backgroundEffect = new Vector<BackgroundEffect>();
	public String projectfilename;
	public int selectedEffect=-1; //Selected Effect number
	public ProjectSetting projectSetting;
	
	
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
		if(backgroundEffect.get(selectedEffect)==null) {
			return null;
		}
		return backgroundEffect.get(selectedEffect);
	}
	public void addEffect(UscBackgroundEditor sup) {
		JFrame dialogControl = new JFrame();
		dialogControl.setLayout( null );
		dialogControl.setVisible(false);
		JDialog dialog = new JDialog(dialogControl);
		dialog.setLayout(new GridLayout(8, 3));
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
		dialog.add(new JLabel("effect name"));
		dialog.add(effectName);
		dialog.add(new JLabel("start time"));
		dialog.add(startTime);
		dialog.add(new JLabel("end time"));
		dialog.add(endTime);
		dialog.add(new JLabel("fade in time"));
		dialog.add(fadeIn);
		dialog.add(new JLabel("fade out time"));
		dialog.add(fadeOut);
		dialog.add(new JLabel("size"));
		dialog.add(size);
		dialog.add(new JLabel("image path"));
		dialog.add(imagePath);
		JButton ok = new JButton("ok");
		JButton editEffect = new JButton("Edit Effects");
		ok.addActionListener(e -> {
			modifyBackgroundEffect.startTime=Double.parseDouble(startTime.getText());
			modifyBackgroundEffect.endTime=Double.parseDouble(endTime.getText());
			modifyBackgroundEffect.fadeIn=Double.parseDouble(fadeIn.getText());
			modifyBackgroundEffect.fadeOut=Double.parseDouble(fadeOut.getText());
			modifyBackgroundEffect.size=Double.parseDouble(size.getText());
			modifyBackgroundEffect.imagePath=imagePath.getText();
			modifyBackgroundEffect.effectName=(effectName.getText());
			if(selectedEffect!=-1)backgroundEffect.add(selectedEffect, modifyBackgroundEffect);
			else backgroundEffect.add(modifyBackgroundEffect);
			sup.makeScreen();
			dialogControl.setVisible(false);
		});
		editEffect.addActionListener(e -> {
			modifyBackgroundEffect.editEffect();
		});
		dialog.add(ok);
		dialog.add(editEffect);
		dialog.setVisible(true);
	}
	public void addEffect(UscBackgroundEditor sup, BackgroundEffect clipboard) {
		if(selectedEffect!=-1)backgroundEffect.add(selectedEffect, clipboard);
		else backgroundEffect.add(clipboard);
		sup.makeScreen();
	}
	public void editEffect(UscBackgroundEditor sup) {
		JFrame dialogControl = new JFrame();
		dialogControl.setLayout( null );
		dialogControl.setVisible(false);
		JDialog dialog = new JDialog(dialogControl);
		dialog.setLayout(new GridLayout(8, 3));
		dialog.setSize(600, 300);
		dialog.setTitle("Add Effects");
		int selectedEffect = this.selectedEffect;
		
		BackgroundEffect modifyBackgroundEffect = backgroundEffect.get(selectedEffect);
		JTextField effectName = new JTextField(modifyBackgroundEffect.effectName);
		JTextField startTime = new JTextField(modifyBackgroundEffect.startTime.toString());
		JTextField endTime = new JTextField(modifyBackgroundEffect.endTime.toString());
		JTextField fadeIn = new JTextField(modifyBackgroundEffect.fadeIn.toString());
		JTextField fadeOut = new JTextField(modifyBackgroundEffect.fadeOut.toString());
		JTextField size = new JTextField(modifyBackgroundEffect.size.toString());
		JTextField imagePath = new JTextField(modifyBackgroundEffect.imagePath);
		dialog.add(new JLabel("effect name"));
		dialog.add(effectName);
		dialog.add(new JLabel("start time"));
		dialog.add(startTime);
		dialog.add(new JLabel("end time"));
		dialog.add(endTime);
		dialog.add(new JLabel("fade in time"));
		dialog.add(fadeIn);
		dialog.add(new JLabel("fade out time"));
		dialog.add(fadeOut);
		dialog.add(new JLabel("size"));
		dialog.add(size);
		dialog.add(new JLabel("image path"));
		dialog.add(imagePath);
		JButton ok = new JButton("ok");
		JButton editEffect = new JButton("Edit Effects");
		ok.addActionListener(e -> {
			modifyBackgroundEffect.startTime=Double.parseDouble(startTime.getText());
			modifyBackgroundEffect.endTime=Double.parseDouble(endTime.getText());
			modifyBackgroundEffect.fadeIn=Double.parseDouble(fadeIn.getText());
			modifyBackgroundEffect.fadeOut=Double.parseDouble(fadeOut.getText());
			modifyBackgroundEffect.size=Double.parseDouble(size.getText());
			modifyBackgroundEffect.imagePath=imagePath.getText();
			modifyBackgroundEffect.effectName=(effectName.getText());
			backgroundEffect.set(selectedEffect, modifyBackgroundEffect);
			sup.makeScreen();
			dialogControl.setVisible(false);
		});
		editEffect.addActionListener(e -> {
			modifyBackgroundEffect.editEffect();
		});
		dialog.add(ok);
		dialog.add(editEffect);
		dialog.setVisible(true);
	}
	public void deleteEffect(UscBackgroundEditor sup) {
		if(selectedEffect!=-1) {
			backgroundEffect.removeElementAt(selectedEffect);
		}
		selectedEffect=-1;
		sup.makeScreen();
	}
	public void loadProject() {
		JFrame f = new JFrame();
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Load", FileDialog.LOAD);
		dialog.setFilenameFilter(new USCBGPROJFileFilter());
		dialog.setVisible(true);
		projectfilename = dialog.getDirectory()+dialog.getFile();
		
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
