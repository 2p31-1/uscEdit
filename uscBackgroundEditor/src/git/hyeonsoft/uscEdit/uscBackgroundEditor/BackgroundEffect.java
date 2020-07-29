package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.util.Vector;

public class BackgroundEffect {
	public String effectName = "effect";
	public Double startTime = 0.0;
	public Double endTime = 10.0;
	public Double fadeIn = 0.5;
	public Double fadeOut = 0.5;
	public Double size = 1.0;
	public Vector<EffectType> effect = new Vector<EffectType>();
	public SizeReference sizeReference = SizeReference.BASED_ON_LONGER_AXIS;
	public Vector<String> effectParameter = new Vector<String>();
	public String imagePath = new String();
	public void editEffect() {
		
	}
	public enum EffectType {
		FLOATING,
		ROTATING,
		VIDEO
	}
	public enum SizeReference {
		BASED_ON_LONGER_AXIS, //When making Backgrounds
		BASED_ON_SHORTER_AXIS //When making Characters
	}
	public String getLuaScript(int imageIndex) {
		String script="--"+effectName+"starts\nstart="+startTime.toString()+"\nend_="+endTime.toString()+"\n";
		return script;
	}
	public String getInfo() {
		return effectName+", Starts at "+startTime.toString()+", ends at "+endTime.toString()+". Image at \""+imagePath+"\"";
	}
}