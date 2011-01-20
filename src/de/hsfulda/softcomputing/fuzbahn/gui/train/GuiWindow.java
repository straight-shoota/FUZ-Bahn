package de.hsfulda.softcomputing.fuzbahn.gui.train;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

/**
 * @author Daniel Buth
 *
 */
public class GuiWindow {

	private static final long serialVersionUID = 7180164956704916919L;

	private JFrame frame;

	public GuiWindow() {
		// super("Train Simulator");

		frame = new JFrame("Train Simulator");
		// Swing anweisen, das Programm zu beenden, wenn das Fenster
		// geschlossen wird
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void init() {
		GUIWorldPanel world;

		frame.setSize(800, 500);
		frame.setVisible(true);

		RepaintManager.currentManager(frame).setDoubleBufferingEnabled(true);

		world = new GUIWorldPanel();
		frame.add(world);

		world.startAnimation();

		// world.stopAnimation();
	}

}
