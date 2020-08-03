package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class VideoPrint extends EffectType {
	@Override
	public String getLuaInitializeScript(int imageIndex) {
		String initializeLuaScript="local video"+imageIndex+"=gfx.LoadAnimation(background.GetPath()..\""+parameters[2]+"\", "
				+1.0/Double.parseDouble(parameters[1])+")\n"
				+ "gfx.TickAnimation(video"+imageIndex+", "+parameters[0]+")";
		return initializeLuaScript;
	}
	public VideoPrint(){
		parameters = new String[3];
		parameters[0]="0";
		parameters[1]="23.98";
		parameters[2]="";
		parameterNames = new String[3];
		parameterNames[0]="startTime";
		parameterNames[1]="frame per seconds";
		parameterNames[2]="video frame folder";
	}
	@Override
	public String getLuaScript(int imageIndex) {
		return "gfx.TickAnimation(video"+imageIndex+", deltaTime)"
				+ "\ngfx.ImageRect(x, y, width, height, video"+imageIndex+", alpha, 0);";
	}
	
}
