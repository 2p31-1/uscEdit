package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class PositionTransition extends EffectType {
	public PositionTransition() {
		parameters=new String[2];
		parameterNames=new String[2];
		parameters[0]="0.0";
		parameters[1]="0.0";
		parameterNames[0]="X axis transition";
		parameterNames[1]="Y axis transition";
	}

	@Override
	public String getLuaScript(int imageIndex) {
		String luaScript="x=x+("+parameters[0]+")\n"
				+ "y=y+("+parameters[1]+")\n";
		return luaScript;
	}
}
