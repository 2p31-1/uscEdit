package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class VideoPrint extends EffectType {
	@Override
	public String getLuaInitializeScript(int imageIndex) {
		String initializeLuaScript="local video"+imageIndex+"=gfx.LoadAnimation(background.GetPath()..\""+parameters[2]+"\", "
				+1.0/Double.parseDouble(parameters[1])+")\n"
				+ "gfx.TickAnimation(video"+imageIndex+", "+parameters[0]+", true)";
		return initializeLuaScript;
	}
	public VideoPrint(){
		parameters = new String[4];
		parameters[0]="0";
		parameters[1]="23.98";
		parameters[2]="";
		parameters[3]="1";
		parameterNames = new String[4];
		parameterNames[0]="startTime";
		parameterNames[1]="frame per seconds";
		parameterNames[2]="video frame folder";
		parameterNames[3]="loop count";
	}
	@Override
	public String getLuaScript(int imageIndex) {
		return "gfx.TickAnimation(video"+imageIndex+", deltaTime, "+parameters[3]+")"
				+ "\ngfx.ImageRect(x, y, width, height, video"+imageIndex+", alpha, 0);";
	}
	
}
