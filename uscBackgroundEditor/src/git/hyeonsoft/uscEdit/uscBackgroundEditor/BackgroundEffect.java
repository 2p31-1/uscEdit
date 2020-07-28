package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.util.Vector;

public class BackgroundEffect {
	public String effectName = "effect";
	public Double startTime = 0.0;
	public Double endTime = 0.0;
	public Double fadeIn = 0.0;
	public Double fadeOut = 0.0;
	public Double size = 1.0;
	public Vector<EffectType> effect = new Vector<EffectType>();
	public Vector<Double> effectParameter = new Vector<Double>();
	public String imagePath = new String();
	
}