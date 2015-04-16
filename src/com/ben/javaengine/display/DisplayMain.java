package com.ben.javaengine.display;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.ben.javaengine.Controller;
import com.ben.javaengine.configuration.Configuration;
import com.ben.javaengine.configuration.ConfigurationDescriptor;
import com.ben.javaengine.display.converter.AbstractStateConverter;
import com.ben.javaengine.game.state.AbstractState;

public class DisplayMain extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	private KeyListener currListener;
	
	public DisplayMain() {
		super();
		currListener = null;
		initDisplay();
		setVisible(true);
	}
	
	public void initDisplay() {
		Configuration config = Controller.getInstance().getConfiguration();
		Integer height = config.getProperty(ConfigurationDescriptor.SCREEN_HEIGHT).getValueAsInteger();
		Integer width = config.getProperty(ConfigurationDescriptor.SCREEN_WIDTH).getValueAsInteger();
		setSize(width, height);
		setTitle("Renderer");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setVisible(false);
		addWindowListener(this);
	}
	
	public void registerInputMapInputs(KeyStroke keyStroke, String actionMapKey) {

	}
	
	public void registerActionMapActions(String key, AbstractAction action) {
		
	}
	
	public void setState(AbstractState state) {
		AbstractStateConverter.convert(state, (JPanel) getContentPane());
	}

	public void windowClosed(WindowEvent e) {
		Controller.getInstance().prepareToExit(0);
	}
	
	public void windowClosing(WindowEvent e) {
		Controller.getInstance().prepareToExit(0);
	}
	
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
