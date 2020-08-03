package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class PositionRelativeTransition extends PositionTransition {
	@Override
	public String getLuaScript(int imageIndex) {
		String luaScript="x=x+("+parameters[0]+"*resX)\n"
		+ "y=y+("+parameters[1]+"*resY)\n";
		return luaScript;
	}
}
