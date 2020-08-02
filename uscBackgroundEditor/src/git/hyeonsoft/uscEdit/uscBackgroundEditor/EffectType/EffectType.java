package git.hyeonsoft.uscEdit.uscBackgroundEditor.EffectType;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EffectType {
	public String[] parameters = {};
	public String[] parameterNames= {};
	
	public JPanel getSettingPanel() {
		JPanel settingPanelWrap = new JPanel();
		settingPanelWrap.setSize(500, (parameters.length+1)*40);
		JPanel settingPanel = new JPanel(new GridLayout(parameters.length+1, 2));
		settingPanel.setSize(500, (parameters.length+1)*40);
		JLabel[] parametersLabel = new JLabel[parameters.length];
		JTextField[] parametersTextField = new JTextField[parameters.length];
		for(int i=0;i<parameters.length;i++){
			parametersLabel[i] = new JLabel(parameterNames[i]);
			parametersTextField[i] = new JTextField(parameters[i]);
			settingPanel.add(parametersLabel[i]);
			settingPanel.add(parametersTextField[i]);
		}
		JButton ok = new JButton("ok");
		ok.addActionListener(e->{
			for(int i=0;i<parameters.length;i++){
				parameters[i] = parametersTextField[i].getText();
			}
		});
		settingPanel.add(ok);
		settingPanelWrap.add(settingPanel);
		return settingPanelWrap;
	}
	
	public String getLuaScript(int imageIndex) {
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

	public String getLuaInitializeScript(int imageIndex) {
		return "";
	}
}
