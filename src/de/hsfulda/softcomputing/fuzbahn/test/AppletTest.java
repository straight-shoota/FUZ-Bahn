package de.hsfulda.softcomputing.fuzbahn.test;

import javax.swing.JApplet;

import de.hsfulda.softcomputing.fuzbahn.gui.FUZPanel;

public class AppletTest extends SwingTest {
	public void initUI() {
		panel = new FUZPanel(this, getController(), getTrack());
	}
	public FUZPanel getPanel(){
		return panel;
	}
}
