package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class EffectType {
	public String[] parameters;
	public String[] parameterNames= {};
	
	public String getLuaScript() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getInfo() {
		String className = this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length-1];
		String info = className +" (";
		for(int i=0;i<parameterNames.length;i++) {
			if(i!=0)info+=", ";
			info+=parameterNames[i]+"="+parameters[i];
		}
		info+=")";
		return info;
	}

	public String getLuaInitializeScript() {
		// TODO Auto-generated method stub
		return "";
	}
}
