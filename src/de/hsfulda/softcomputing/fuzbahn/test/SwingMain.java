package de.hsfulda.softcomputing.fuzbahn.test;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyUnavailableException;
import de.hsfulda.softcomputing.fuzbahn.SpeedLimit;
import de.hsfulda.softcomputing.fuzbahn.Station;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;

public class SwingMain extends SwingTest {
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

	public static void main(String[] args) {
		new SwingMain();
	}
}
