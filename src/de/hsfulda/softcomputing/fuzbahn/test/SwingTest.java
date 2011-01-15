package de.hsfulda.softcomputing.fuzbahn.test;

import javax.swing.JFrame;

import de.hsfulda.softcomputing.fuzbahn.gui.*;

public abstract class SwingTest extends AbstractTest {
	JFrame frame;
	FUZPanel panel;
	
	public void initUI(){
		frame = new JFrame(getClass().getName());
		frame.setSize(1200, 400);

		panel = new FUZPanel(getController(), getTrack());
		frame.setContentPane(panel);
		
		frame.setVisible(true);
	}
}
