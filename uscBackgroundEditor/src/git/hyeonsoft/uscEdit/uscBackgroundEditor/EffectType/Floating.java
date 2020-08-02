package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class Floating extends EffectType {
	
	public Floating(){
		parameterNames = new String[2];
		parameterNames[0] = "speed";
		parameterNames[1] = "max height delta";
		parameters = new String[2];
		parameters[0]="1.0";
		parameters[1]="0.5";
	}
	
	@Override
	public String getLuaScript(int imageIndex) {
		return "y=y+math.sin(real*"+parameters[0]+")*"+parameters[1]+";";
	}

	

}
