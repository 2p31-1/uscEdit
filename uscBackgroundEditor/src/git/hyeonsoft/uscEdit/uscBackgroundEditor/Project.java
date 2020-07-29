package git.hyeonsoft.uscEdit.uscBackgroundEditor;
// Save / Load files. Contains informations of background information.

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
			l.add(x.getInfo());
		}
		return l;
	}
	public BackgroundEffect getEffect() {
		try {
			return backgroundEffect.get(selectedEffect);
		}catch (Exception e) {
			return null;
		}
	}
	public void addEffect(UscBackgroundEditor sup) {
		JFrame dialogControl = new JFrame();
		dialogControl.setLayout( null );
		dialogControl.setVisible(false);
		JDialog dialog = new JDialog(dialogControl);
		dialog.setLayout(new GridLayout(10, 2));
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
		String sizeReferenceText[] = {"longer axis", "shorter axis"};
		JComboBox <String> sizeReference = new JComboBox<String>(sizeReferenceText);
		sizeReference.setSelectedIndex(modifyBackgroundEffect.sizeReference.ordinal());
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
		dialog.add(new JLabel("size 1.0 based on"));
		dialog.add(sizeReference);
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
			modifyBackgroundEffect.sizeReference=BackgroundEffect.SizeReference.values()[sizeReference.getSelectedIndex()];
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
		if(clipboard==null)return;
		if(selectedEffect!=-1)backgroundEffect.add(selectedEffect, clipboard);
		else backgroundEffect.add(clipboard);
		sup.makeScreen();
	}
	public void editEffect(UscBackgroundEditor sup) {
		if(selectedEffect==-1)return;
		JFrame dialogControl = new JFrame();
		dialogControl.setLayout( null );
		dialogControl.setVisible(false);
		JDialog dialog = new JDialog(dialogControl);
		dialog.setLayout(new GridLayout(10, 2));
		dialog.setSize(600, 300);
		dialog.setTitle("Edit Effects");
		int selectedEffect = this.selectedEffect;
		
		try {
		BackgroundEffect modifyBackgroundEffect = backgroundEffect.get(selectedEffect);
		JTextField effectName = new JTextField(modifyBackgroundEffect.effectName);
		JTextField startTime = new JTextField(modifyBackgroundEffect.startTime.toString());
		JTextField endTime = new JTextField(modifyBackgroundEffect.endTime.toString());
		JTextField fadeIn = new JTextField(modifyBackgroundEffect.fadeIn.toString());
		JTextField fadeOut = new JTextField(modifyBackgroundEffect.fadeOut.toString());
		JTextField size = new JTextField(modifyBackgroundEffect.size.toString());
		JTextField imagePath = new JTextField(modifyBackgroundEffect.imagePath);
		String sizeReferenceText[] = {"longer axis", "shorter axis"};
		JComboBox <String> sizeReference = new JComboBox<String>(sizeReferenceText);
		sizeReference.setSelectedIndex(modifyBackgroundEffect.sizeReference.ordinal());
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
		dialog.add(new JLabel("size 1.0 based on"));
		dialog.add(sizeReference);
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
			modifyBackgroundEffect.sizeReference=BackgroundEffect.SizeReference.values()[sizeReference.getSelectedIndex()];
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
		}catch(Exception e) {
			return;
		}
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
		JFrame dialog = new JFrame();
	    dialog.setVisible(false);
	    JFileChooser folderChooser = new JFileChooser(new java.io.File("."));
	    folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    folderChooser.setDialogTitle("Select folder to export");
	    folderChooser.setSize(800, 600);
		int result = folderChooser.showSaveDialog(dialog);
		Vector<String> images = new Vector<String>();
		Vector<Integer> imagesIndex = new Vector<Integer>();
		for(BackgroundEffect x : backgroundEffect) {
			if(!images.contains(x.imagePath)) {
				images.add(x.imagePath);
			}
			imagesIndex.add(images.indexOf(x.imagePath));
		}
		
		if (result==JFileChooser.CANCEL_OPTION)return;
		try{
			OutputStream fos = new FileOutputStream(folderChooser.getSelectedFile()+"/fg.lua", false);
			fos.write("".getBytes());
			fos.close();
			fos = new DataOutputStream(new FileOutputStream(folderChooser.getSelectedFile()+"/fg.fs", false));
			fos.write("void main(){}".getBytes());
			fos.close();
			fos = new DataOutputStream(new FileOutputStream(folderChooser.getSelectedFile()+"/bg.lua", false));
			
			for(int k=0;k<images.size();k++) {
				fos.write(("local image"+k+"=gfx.CreateImage(background.GetPath() .. \""+images.get(k)+"\", 1);\n").getBytes());
			}
			fos.write(("local resX,resY = game.GetResolution();\n"
					+ "local centerX = resX/2\n" + 
					"local centerY = resY/2\n" + 
					"local small = 0\n"
					+ "local large = 0\n" + 
					"if resX<resY then\n" + 
					"	small=resX\n"
					+ "	large = resY\n" + 
					"else\r\n" + 
					"	small=resY\n"
					+ "	large=resX\n" + 
					"end\n"
					+ "function render_bg(deltaTime)\n"
					+ "local start, end_\n" + 
					"if resX<resY then\n" + 
					"	small=resX\n" + 
					"	large = resY\n" + 
					"else\n" + 
					"	small=resY\n" + 
					"	large=resX\n" + 
					"end\n"
					+ "background.DrawShader()\n"
					+ "local bartime, offsync, real = background.GetTiming()\n").getBytes());
			for(int i=0;i<backgroundEffect.size();i++) {
				fos.write(backgroundEffect.get(i).getLuaScript(imagesIndex.get(i)).getBytes());
				fos.write("\n".getBytes());
			}
			fos.write("end\n".getBytes());
			fos.close();
			fos = new DataOutputStream(new FileOutputStream(folderChooser.getSelectedFile()+"/bg.fs", false));
			fos.write("void main(){}".getBytes());
			fos.close();
		}catch(IOException exception) {
			JFrame alert = new JFrame();
		    alert.setSize(350,250);
		    alert.add(new JLabel("Failed to save"));
		    alert.setVisible(true);
		}
	}
}
