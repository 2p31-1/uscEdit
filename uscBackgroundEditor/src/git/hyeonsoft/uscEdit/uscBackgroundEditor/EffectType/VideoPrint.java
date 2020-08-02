package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class VideoPrint extends EffectType {
	VideoPrint(){
		parameters = new String[3];
		parameters[0]="0";
		parameters[1]="1000";
		parameters[2]="23.98";
		parameterNames = new String[3];
		parameterNames[0]="startFrame";
		parameterNames[1]="endFrame";
		parameterNames[2]="frame per seconds";
		
	}
	@Override
	public String getLuaScript(int imageIndex) {
		return "gfx.ImageRect(x, y, width, height, image"+imageIndex+", alpha, 0);";
	}
	
}
