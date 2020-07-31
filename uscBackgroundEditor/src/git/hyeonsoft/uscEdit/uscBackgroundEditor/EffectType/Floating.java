package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class Floating implements EffectType {

	private String[] parameters;
	private final String[] parameterNames= {"speed", "max height delta"};
	
	public Floating(){
		parameters = new String[2];
		parameters[0]="1.0";
		parameters[1]="0.5";
	}
	
	@Override
	public String getLuaScript() {
		// TODO Auto-generated method stub
		return "y=y+math.sin(real*"+parameters[0]+")*"+parameters[1]+";";
	}

	@Override
	public void setParameter(int n, String parameter) {
		parameters[n]=parameter;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "Floating (Speed="+parameters[0]+", amplitude="+parameters[1]+")";
	}

	@Override
	public String getLuaInitializeScript() {
		// TODO Auto-generated method stub
		return "";
	}
	

}
