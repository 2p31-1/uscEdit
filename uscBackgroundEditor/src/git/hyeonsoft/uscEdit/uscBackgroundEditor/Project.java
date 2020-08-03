package git.hyeonsoft.uscEdit.uscBackgroundEditor;
// Save / Load files. Contains informations of background information.

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType.*;

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Scanner;
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
		dialog.setLayout(new GridLayout(11, 2));
		dialog.setSize(600, 400);
		dialog.setTitle("Add Effects");
		
		BackgroundEffect modifyBackgroundEffect = new BackgroundEffect();
		JTextField effectName = new JTextField(modifyBackgroundEffect.effectName);
		JTextField startTime = new JTextField(modifyBackgroundEffect.startTime.toString());
		JTextField endTime = new JTextField(modifyBackgroundEffect.endTime.toString());
		JTextField fadeIn = new JTextField(modifyBackgroundEffect.fadeIn.toString());
		JTextField fadeOut = new JTextField(modifyBackgroundEffect.fadeOut.toString());
		JTextField width = new JTextField(modifyBackgroundEffect.width.toString());
		JTextField height = new JTextField(modifyBackgroundEffect.height.toString());
		JTextField imagePath = new JTextField(modifyBackgroundEffect.imagePath);
		String sizeReferenceText[] = {"longer axis", "shorter axis", "abstract", "relative"};
		JComboBox <String> sizeReference = new JComboBox<String>(sizeReferenceText);
		sizeReference.setSelectedIndex(modifyBackgroundEffect.sizeReference.ordinal());
		String fileTypeText[] = {"Image", "Video", "Else"};
		JComboBox<String> fileType  =  new JComboBox<String>(fileTypeText);
		fileType.setSelectedIndex(modifyBackgroundEffect.fileType.ordinal());
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
		dialog.add(new JLabel("size width"));
		dialog.add(width);
		dialog.add(new JLabel("size height"));
		dialog.add(height);
		dialog.add(new JLabel("image file path"));
		dialog.add(imagePath);
		dialog.add(new JLabel("size 1.0 based on"));
		dialog.add(sizeReference);
		dialog.add(new JLabel("file Type"));
		dialog.add(fileType);
		JButton ok = new JButton("ok");
		JButton editEffect = new JButton("Edit Effects");
		ok.addActionListener(e -> {
			modifyBackgroundEffect.startTime=Double.parseDouble(startTime.getText());
			modifyBackgroundEffect.endTime=Double.parseDouble(endTime.getText());
			modifyBackgroundEffect.fadeIn=Double.parseDouble(fadeIn.getText());
			modifyBackgroundEffect.fadeOut=Double.parseDouble(fadeOut.getText());
			modifyBackgroundEffect.width=Double.parseDouble(width.getText());
			modifyBackgroundEffect.height=Double.parseDouble(height.getText());
			modifyBackgroundEffect.imagePath=imagePath.getText();
			modifyBackgroundEffect.effectName=(effectName.getText());
			modifyBackgroundEffect.sizeReference=BackgroundEffect.SizeReference.values()[sizeReference.getSelectedIndex()];
			modifyBackgroundEffect.fileType=BackgroundEffect.FileType.values()[fileType.getSelectedIndex()];
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
		dialog.setLayout(new GridLayout(11, 2));
		dialog.setSize(600, 400);
		dialog.setTitle("Edit Effects");
		int selectedEffect = this.selectedEffect;
		
		try {
		BackgroundEffect modifyBackgroundEffect = new BackgroundEffect(backgroundEffect.get(selectedEffect));
		JTextField effectName = new JTextField(modifyBackgroundEffect.effectName);
		JTextField startTime = new JTextField(modifyBackgroundEffect.startTime.toString());
		JTextField endTime = new JTextField(modifyBackgroundEffect.endTime.toString());
		JTextField fadeIn = new JTextField(modifyBackgroundEffect.fadeIn.toString());
		JTextField fadeOut = new JTextField(modifyBackgroundEffect.fadeOut.toString());
		JTextField width = new JTextField(modifyBackgroundEffect.width.toString());
		JTextField height = new JTextField(modifyBackgroundEffect.height.toString());
		JTextField imagePath = new JTextField(modifyBackgroundEffect.imagePath);
		String sizeReferenceText[] = {"longer axis", "shorter axis", "abstract", "relative"};
		JComboBox <String> sizeReference = new JComboBox<String>(sizeReferenceText);
		sizeReference.setSelectedIndex(modifyBackgroundEffect.sizeReference.ordinal());
		String fileTypeText[] = {"Image", "Video", "Else"};
		JComboBox<String> fileType  =  new JComboBox<String>(fileTypeText);
		fileType.setSelectedIndex(modifyBackgroundEffect.fileType.ordinal());
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
		dialog.add(new JLabel("size width"));
		dialog.add(width);
		dialog.add(new JLabel("size height"));
		dialog.add(height);
		dialog.add(new JLabel("image file path"));
		dialog.add(imagePath);
		dialog.add(new JLabel("size 1.0 based on"));
		dialog.add(sizeReference);
		dialog.add(new JLabel("file Type"));
		dialog.add(fileType);
		JButton ok = new JButton("ok");
		JButton editEffect = new JButton("Edit Effects");
		ok.addActionListener(e -> {
			modifyBackgroundEffect.startTime=Double.parseDouble(startTime.getText());
			modifyBackgroundEffect.endTime=Double.parseDouble(endTime.getText());
			modifyBackgroundEffect.fadeIn=Double.parseDouble(fadeIn.getText());
			modifyBackgroundEffect.fadeOut=Double.parseDouble(fadeOut.getText());
			modifyBackgroundEffect.width=Double.parseDouble(width.getText());
			modifyBackgroundEffect.height=Double.parseDouble(height.getText());
			modifyBackgroundEffect.imagePath=imagePath.getText();
			modifyBackgroundEffect.effectName=(effectName.getText());
			modifyBackgroundEffect.sizeReference=BackgroundEffect.SizeReference.values()[sizeReference.getSelectedIndex()];
			modifyBackgroundEffect.fileType=BackgroundEffect.FileType.values()[fileType.getSelectedIndex()];
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
	
	public class JsonDeserializerWithInheritance<T> implements JsonDeserializer<T> {
		 
		 @Override
		 public T deserialize(
		     JsonElement json, Type typeOfT, JsonDeserializationContext context)
		     throws JsonParseException {
		     JsonObject jsonObject = json.getAsJsonObject();
		     JsonPrimitive classNamePrimitive = (JsonPrimitive) jsonObject.get("type");
		 
		     String className = classNamePrimitive.getAsString();
		 
		     Class<?> clazz;
		     try {
		     clazz = Class.forName(className);
		     } catch (ClassNotFoundException e) {
		     throw new JsonParseException(e.getMessage());
		     }
		     return context.deserialize(jsonObject, clazz);
		 }
		}
	//https://riptutorial.com/gson/example/22968/using-gson-with-inheritance
	public void loadProject() {
		JFrame f = new JFrame();
	    f.setLayout( null );
	    f.setVisible(false);
		FileDialog dialog = new FileDialog(f, "Load", FileDialog.LOAD);
		dialog.setFilenameFilter(new USCBGPROJFileFilter());
		dialog.setVisible(true);
		projectfilename = dialog.getDirectory()+'\\'+dialog.getFile();
		try{
			Scanner fis = new Scanner(new File(projectfilename));
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(EffectType.class, new JsonDeserializerWithInheritance<EffectType>());
			Gson gson = builder.create();
			String json = "";
			while(fis.hasNextLine()) {
				json+=fis.nextLine();
			}
			BackgroundEffect[] backgroundEffectArray = gson.fromJson(json, BackgroundEffect[].class);
			backgroundEffect = new Vector<BackgroundEffect>(Arrays.asList(backgroundEffectArray));
			fis.close();
		}
		catch(IOException e) {
			
		}
	}
	public void saveProject() {
		if(projectfilename==null) {
			JFrame f = new JFrame();
		    f.setLayout( null );
		    f.setVisible(false);
			FileDialog dialog = new FileDialog(f, "Save", FileDialog.SAVE);
			dialog.setFilenameFilter(new USCBGPROJFileFilter());
			dialog.setVisible(true);
			projectfilename = dialog.getDirectory()+'\\'+dialog.getFile();
		}
		Gson gson = new Gson();
		String json = gson.toJson(backgroundEffect);
		try{
			OutputStream fos = new FileOutputStream(projectfilename, false);
			fos.write(json.getBytes());
			fos.close();
		}
		catch(Exception e) {
			
		}
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
		for(int i=0;i<backgroundEffect.size();i++) {
			backgroundEffect.get(i).setImagesIndex(i);
			images.add(backgroundEffect.get(i).getLuaInitializeScript());
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
				fos.write((images.get(k)+"\n").getBytes());
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
					+ "local btn = {false, false, false, false, false, false};\n"
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
					+ "local bartime, offsync, real = background.GetTiming()\n"
					+ "	btn_p = {false, false, false, false, false, false};\n" + 
					"	for i=0,5 do\n" + 
					"			if game.GetButton(i) then\n" + 
					"			if btn[i] == false then\n" + 
					"				btn_p[i] = true\n" + 
					"				btn[i]=true\n" + 
					"			end\n" + 
					"		else\n" + 
					"			btn[i]=false\n" + 
					"		end\n" + 
					"	end\n").getBytes());
			for(int i=0;i<backgroundEffect.size();i++) {
				fos.write("\ngfx.BeginPath()\n".getBytes());
				fos.write(backgroundEffect.get(i).getLuaScript().getBytes());
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
