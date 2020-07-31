package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class ImagePrint extends EffectType {
	@Override
	public String getLuaScript(int imageIndex) {
		return "gfx.ImageRect(x, y, width, height, image"+imageIndex+", alpha, 0);";
	}
	
}
