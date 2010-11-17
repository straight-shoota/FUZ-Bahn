package de.hsfulda.softcomputing.fuzbahn.test;

import de.hsfulda.softcomputing.fuzbahn.*;

public class TestMain implements Runnable {
	static final double DELTA_T = 1;
	
	private boolean running = true;
	private Train t;
	
	TestMain() {
		TrainPrototype prototype = new TrainPrototype();
		prototype.setName("FUZ");
		prototype.setBrakeAccelerationMax(-1.0);
		prototype.setLength(100);
		prototype.setPowerMax(2000000);
		prototype.setPowerMin(-2000000);
		prototype.setMass(140000);
		prototype.setSpeedMax(70/3.6);
		
		
		prototype.setName("BrB");
		prototype.setBrakeAccelerationMax(-1.0);
		prototype.setLength(40);
		prototype.setPowerMax(780000);
		prototype.setPowerMin(-780000);
		prototype.setMass(80000);
		prototype.setSpeedMax(80/3.6);
		
		t = prototype.createTrain();
		
		System.out.println(prototype);
		
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		long sleep = Math.round(DELTA_T * 1000);
		
		t.setPowerRatio(.8);
		t.setSpeed(3);

		t.update(0);
		System.out.println(t);
		while(running) {
			t.update(DELTA_T);
			System.out.println(t);
			try {
				Thread.sleep(sleep);
			}catch(InterruptedException exc){
				
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestMain();
	}
}
