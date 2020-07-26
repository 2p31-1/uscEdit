package git.hyeonsoft.uscEdit.uscBackgroundEditor;

import java.util.Vector;

public class BackgroundEffect {
	public int imageIndex;
	public double startTime = 0;
	public double endTime = 0;
	public double fadeIn = 0;
	public double fadeOut = 0;
	public double size = 1;
	public Vector<EffectType> effect;
	public Vector<Double> effectParameter;
	public String ImagePath;
}