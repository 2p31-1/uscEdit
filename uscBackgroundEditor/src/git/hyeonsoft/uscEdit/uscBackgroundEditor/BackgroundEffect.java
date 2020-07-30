package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
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
	public int imagesIndex = 0;
	private transient JFrame subFrame;
	private transient JPanel mainPanel;
	private void editEffectUpdate() {
		mainPanel = new JPanel(new BorderLayout());
		Vector <String> effectNames = new Vector<String>();
		for(EffectType x : effect) {
			effectNames.add(x.getInfo());
		}
		JList<String> effectList = new JList<String>(effectNames);
		effectList.setSelectionMode(1);
		effectList.setLayoutOrientation(JList.VERTICAL);
		JScrollPane effectListScroller = new JScrollPane(effectList);
		effectListScroller.setPreferredSize(new Dimension(900,500));
		mainPanel.add(effectListScroller, BorderLayout.CENTER);
		JButton ok = new JButton("ok");
		ok.addActionListener(e -> {
			effect.add(new Floating());
			editEffectUpdate();
		});
		mainPanel.add(ok, BorderLayout.SOUTH);
		subFrame.remove(mainPanel);
		subFrame.add(mainPanel);
		subFrame.revalidate();
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
		BASED_ON_SHORTER_AXIS //When making Characters
	}
	public String getLuaScript() {
		String script="--"+effectName+"starts\nstart="+startTime.toString()+"\nend_="+endTime.toString()+"\n";
		return script;
	}
	public String getInfo() {
		return effectName+", Starts at "+startTime.toString()+", ends at "+endTime.toString()+". Image at \""+imagePath+"\"";
	}
	public String imagePathDependencies(int k) {
		return "local image"+k+"=gfx.CreateImage(background.GetPath() .. \""+imagePath+"\", 1);\n";
	}
}