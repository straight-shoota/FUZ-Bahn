package de.hsfulda.softcomputing.fuzbahn.test;

import javax.swing.JFrame;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyUnavailableException;
import de.hsfulda.softcomputing.fuzbahn.SpeedLimit;
import de.hsfulda.softcomputing.fuzbahn.Station;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;
import de.hsfulda.softcomputing.fuzbahn.gui.FUZPanel;

public abstract class SwingTest extends AbstractTest {
	JFrame frame;
	FUZPanel panel;
	Train t;
	
	public void initModel() {
		track = new Track(10000);
		t = getDefaultPrototype().createTrain();
		t.update(0);
		track.add(t);
		track.add(new Station("TestHalt1", 120, 100));
		track.add(new SpeedLimit(600, 50 / Train.MS_KMH));
		try {
			controller = new FuzzyController(t);
		} catch (FuzzyUnavailableException exc) {
			exc.printStackTrace();
		}
		setSimulationScale(1D);
	}

	public void initUI() {
		frame = new JFrame(getClass().getCanonicalName());
		frame.setSize(1200, 500);

		panel = new FUZPanel(this, getController(), getTrack());
		frame.setContentPane(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
