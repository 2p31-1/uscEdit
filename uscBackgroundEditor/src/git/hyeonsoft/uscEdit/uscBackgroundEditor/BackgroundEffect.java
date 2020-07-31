package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType.*;

public class BackgroundEffect {
	public String effectName = "effect";
	public Double startTime = 0.0;
	public Double endTime = 10.0;
	public Double fadeIn = 0.5;
	public Double fadeOut = 0.5;
	public Double width = 1.0;
	public Double height = 1.0;
	public Vector<EffectType> effect = new Vector<EffectType>();
	public SizeReference sizeReference = SizeReference.BASED_ON_LONGER_AXIS;
	public Vector<String> effectParameter = new Vector<String>();
	public String imagePath = new String();
	private transient int selected = -1;
	private transient int imagesIndex = 0;
	private transient JFrame subFrame;
	private transient JPanel mainPanel;
	public BackgroundEffect() {}
	@SuppressWarnings("unchecked")
	public BackgroundEffect(BackgroundEffect a) {
		this.effectName = a.effectName;
		this.startTime = a.startTime;
		this.endTime = a.endTime;
		this.fadeIn = a.fadeIn;
		this.fadeOut = a.fadeOut;
		this.width = a.width;
		this.height = a.height;
		this.effect = (Vector<EffectType>)(a.effect.clone());
		this.sizeReference = a.sizeReference;
		this.effectParameter = (Vector<String>)(a.effectParameter.clone());
		this.imagePath = a.imagePath;
	}
	private void editEffectUpdate() {
		selected =-1;
		try {subFrame.remove(mainPanel);}catch(NullPointerException e){}
		mainPanel = new JPanel(new BorderLayout());
		Vector <String> effectNames = new Vector<String>();
		for(EffectType x : effect) {
			effectNames.add(x.getInfo());
		}
		JList<String> effectList = new JList<String>(effectNames);
		effectList.setSelectionMode(1);
		effectList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane effectListScroller = new JScrollPane(effectList);
		mainPanel.add(effectListScroller, BorderLayout.CENTER);
		JButton newEffect = new JButton("new");
		JButton editEffect = new JButton("edit");
		JButton deleteEffect = new JButton("delete");
		effectList.addListSelectionListener(e -> {
			selected = effectList.getSelectedIndex();
		});
		newEffect.addActionListener(e -> {
			if(selected!=-1) {
				effect.add(selected,new EffectType());
			}else {
				effect.add(new EffectType());
			}
			editEffectUpdate();
		});
		editEffect.addActionListener(e -> {
			editEffectType(selected);
		});
		deleteEffect.addActionListener(e -> {
			if(selected!=-1) effect.remove(selected);
			editEffectUpdate();
		});
		JPanel buttonPanel = new JPanel(new GridLayout(0, 3));
		buttonPanel.add(newEffect);
		buttonPanel.add(editEffect);
		buttonPanel.add(deleteEffect);
		subFrame.add(buttonPanel, BorderLayout.SOUTH);
		subFrame.add(mainPanel);
		subFrame.revalidate();
	}
	@SuppressWarnings("unchecked")
	public void editEffectType(int selected) {
		JFrame f = new JFrame();
		f.setSize(500, 700);
		f.setTitle("edit effect type");
		JPanel p = new JPanel();
		String[] effectTypeClasses = {"EffectType", "Floating"};
		JComboBox<String> effectType = new JComboBox<String>(effectTypeClasses);
		effectType.addActionListener(e->{
			int s = effectType.getSelectedIndex();
			try {
				Class<EffectType> c = (Class<EffectType>)Class.forName("git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType."+effectTypeClasses[s]);
				effect.set(selected, c.getConstructor().newInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			editEffectUpdate();
		});
		p.add(effectType);
		f.add(p);
		f.setVisible(true);
	}
	public void editEffect() {
		subFrame = new JFrame();
		subFrame.setTitle("edit effects");
		subFrame.setSize(1000, 600);
		editEffectUpdate();
		subFrame.setVisible(true);
	}
	public enum SizeReference {
		BASED_ON_LONGER_AXIS, //When making Backgrounds
		BASED_ON_SHORTER_AXIS, //When making Characters
		ABSTRACT,
		RELATIVE
	}
	public void setImagesIndex(int k) {
		imagesIndex = k;
	}
	public String getLuaScript() {
		String script="--"+effectName+"starts\nstart="+startTime.toString()+"\nend_="+endTime.toString()+"\n"
				+ "if real>start and real<end_ then\n"
				+ "local perc = (end_-real)/(end_-start)\n"
				+ "local width = "+width+"\n"
				+ "local height = "+height+"\n"
				+ "local alpha = 1.0\n"
				+ "local x=resX/2;\n"
				+ "local y=resY/2;\n";
		switch(sizeReference) {
		case BASED_ON_LONGER_AXIS:
			script+="width=width*large;\n"
					+ "height=height*large\n";
			break;
		case BASED_ON_SHORTER_AXIS:
			script+="width=width*small;\n"
					+ "height=height*small\n";
			break;
		case RELATIVE:
			script+="width=width*resX;\n"
					+ "height=height*resY\n";
			break;
		default:
			break;
		}
		for(EffectType x : effect) {
			script+=x.getLuaScript()+"\n";
		}
		script+="end\n";
		return script;
	}
	public String getLuaInitializeScript() {
		if(!imagePath.equals("")) {
			return "local image"+imagesIndex+"=gfx.CreateImage(background.GetPath() .. \""+imagePath+"\", 1);\n";
		}else {
			return "";
		}
	}
	public String getInfo() {
		return effectName+", Starts at "+startTime.toString()+", ends at "+endTime.toString()+". Image at \""+imagePath+"\"";
	}
}