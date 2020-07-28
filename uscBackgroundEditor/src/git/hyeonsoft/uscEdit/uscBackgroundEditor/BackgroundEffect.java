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
	public Vector<Double> effectParameter = new Vector<Double>();
	public String imagePath = new String();
	public void editEffect() {
		
	}
	public enum EffectType {
		FLOATING,
		ROTATING
	}
	public enum SizeReference {
		BASED_ON_LONGER_AXIS, //When making Backgrounds
		BASED_ON_SHORTER_AXIS //When making Characters
	}
}