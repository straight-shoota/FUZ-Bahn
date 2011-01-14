package de.hsfulda.softcomputing.fuzbahn.test;

import de.hsfulda.softcomputing.fuzbahn.*;

public class TestMain
extends AbstractTest {
	private Train t;
	private double power = 1;
	
	protected void init() {
		t = getDefaultPrototype().createTrain();
		
		t.setPowerRatio(power);
		t.setSpeed(3);

		t.update(0);
		
		trains = new Train[]{t};
	}
	protected void doStep(double deltaT) {
		for(Train t : trains) {
			t.update(deltaT);
			System.out.println(t);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestMain().start();
	}
}
