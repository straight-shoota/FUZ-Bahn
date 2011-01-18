package de.hsfulda.softcomputing.fuzbahn.test;

import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;

public class TestMain extends AbstractTest {
	private double power = 1;

	protected void init() {
		track = new Track(10000);
		Train t = getDefaultPrototype().createTrain();
		t.setPowerRatio(power);
		t.setSpeed(0);
		t.update(0);
		track.add(t);
		t.update(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestMain().startDemo();
	}
}
