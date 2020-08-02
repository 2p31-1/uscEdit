package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public class KeySound extends EffectType {
	
	public KeySound(){
		parameterNames = new String[6];
		parameterNames[0] = "button A sound file path : ";
		parameterNames[1] = "button B sound file path : ";
		parameterNames[2] = "button C sound file path : ";
		parameterNames[3] = "button D sound file path : ";
		parameterNames[4] = "button FX-L sound file path : ";
		parameterNames[5] = "button FX-R sound file path : ";
		parameters = new String[6];
		for(int i=0;i<6;i++)parameters[i]="remove all texts here to ignore";
	}
	
	@Override
	public String getLuaScript(int imageIndex) {
		String lScript = "";
		for(int i=0;i<6;i++) {
			if(parameters[i].equals(""))continue;
			lScript+="if btn_p["+i+"] then\n" + 
					"game.PlaySample(background.GetPath()..\""+parameters[i]+"\")\n"
					+ "end\n";
		}
		return lScript;
	}

	@Override
	public String getLuaInitializeScript(int imageIndex) {
		String initialize = "";
		for(int i=0;i<6;i++) {
			if(!parameters[i].equals("")) {
				initialize+="game.LoadSample(background.GetPath()..\""+parameters[i]+"\")\n";
			}
		}
		return initialize;
	}
	

}
