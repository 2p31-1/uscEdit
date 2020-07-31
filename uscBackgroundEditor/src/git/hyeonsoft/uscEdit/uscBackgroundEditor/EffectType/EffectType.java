package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

public interface EffectType {
	public String getLuaInitializeScript();
	public void setParameter(int n, String parameter) ;
	public String getInfo();
	public String getLuaScript();
}
