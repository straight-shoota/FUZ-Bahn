package de.hsfulda.softcomputing.fuzbahn.test;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyUnavailableException;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;

public class FuzzyMain extends AbstractTest {

	FuzzyMain() {
		track = new Track(10000);
	}

	protected void init() {
		Train t = getDefaultPrototype().createTrain();
		t.update(0);
		track.add(t);
		try {
			controller = new FuzzyController(t);
		} catch (FuzzyUnavailableException exc) {
			exc.printStackTrace();
		}
	}

	protected void doStep(double deltaT) {
		for (Train t : track.getTrains()) {
			controller.update();
			t.update(deltaT);
			System.out.println(t);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FuzzyMain().startDemo();
	}

}
