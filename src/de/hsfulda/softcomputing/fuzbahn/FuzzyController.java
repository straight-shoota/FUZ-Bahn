package de.hsfulda.softcomputing.fuzbahn;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyController {
	
	private FIS fis;
	private Train train;
	private Variable speed;
	private Variable targetDistance;
	private Variable targetSpeed;

	public FuzzyController(Train train)
	throws FuzzyUnavailableException {
		this(train, "/fis/fuz-bahn.fis");
	}
	public FuzzyController(Train train, String fisFile)
	throws FuzzyUnavailableException {
		fis = FIS.load(getClass().getResourceAsStream(fisFile), true);
		if(fis == null){
			throw new FuzzyUnavailableException();
		}
		
		speed = fis.getVariable(Train.SPEED);
		targetDistance = fis.getVariable(Train.TARGET_DISTANCE);
		targetSpeed = fis.getVariable(Train.TARGET_SPEED);
		
		this.train = train;
	}
	
	public void update() {
		speed.setValue(train.getSpeed());
		TrackElement target = null;//train.getTarget();
		targetDistance.setValue(train.getDistance(target));
		targetSpeed.setValue(train.getSpeed() - target.getSpeed());
		
		fis.evaluate();
	}
}