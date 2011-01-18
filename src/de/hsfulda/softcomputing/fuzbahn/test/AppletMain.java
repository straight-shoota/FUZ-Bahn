package de.hsfulda.softcomputing.fuzbahn.test;

import javax.swing.JApplet;

public class AppletMain extends JApplet {

	/* (non-Javadoc)
	 * @see java.applet.Applet#init()
	 */
	@Override
	public void init() {
		AppletTest test = new AppletTest();
		setContentPane(test.getPanel());
	}
}
