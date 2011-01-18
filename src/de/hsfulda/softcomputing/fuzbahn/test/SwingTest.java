package de.hsfulda.softcomputing.fuzbahn.test;

import javax.swing.JFrame;

import de.hsfulda.softcomputing.fuzbahn.gui.FUZPanel;

public abstract class SwingTest extends AbstractTest {
	JFrame frame;
	FUZPanel panel;

	public void initUI() {
		frame = new JFrame(getClass().getCanonicalName());
		frame.setSize(1200, 500);

		panel = new FUZPanel(this, getController(), getTrack());
		frame.setContentPane(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
