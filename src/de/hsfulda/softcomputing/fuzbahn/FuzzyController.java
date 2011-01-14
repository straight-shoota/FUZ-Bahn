package de.hsfulda.softcomputing.fuzbahn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyController {
	
	private FIS fis;
	private Train train;
	private Variable speed;
	private Variable targetDistance;
	private Variable targetSpeed;
	
	/*
	 * Output variables
	 */
	private Variable powerRatio;
	private Variable brakeForce;
	
	private Variable[] variables;
	
	private List<ActionListener> listeners = new ArrayList<ActionListener>();

	public FuzzyController(Train train)
	throws FuzzyUnavailableException {
		this(train, "/Fuzzy-Project.fcl");
	}
	
	public FuzzyController(Train train, String fisFile)
	throws FuzzyUnavailableException {
		this.train = train;
		
		fis = FIS.load(getClass().getResourceAsStream(fisFile), true);
		if(fis == null){
			throw new FuzzyUnavailableException();
		}
		
		speed = fis.getVariable(Train.SPEED);
		targetDistance = fis.getVariable(Train.TARGET_DISTANCE);
		targetSpeed = fis.getVariable(Train.TARGET_SPEED);
		
		powerRatio = fis.getVariable(Train.POWER_RATIO);
		brakeForce = fis.getVariable(Train.BRAKE_FORCE);
		
		this.variables = new Variable[]{speed, targetDistance, targetSpeed, powerRatio, brakeForce};
	}
	public Variable[] getVariables() {
		return variables;
	}
	public FIS getFis(){
		return fis;
	}
	public synchronized void update() {
		speed.setValue(train.getSpeed());
		TrackElement target = train.getTarget();

		if(target != null) {
			targetDistance.setValue(train.getDistance(target));
			targetSpeed.setValue(train.getSpeed() - target.getSpeed());
		}else{
			targetDistance.setValue(targetDistance.getUniverseMax());
			targetSpeed.setValue(80);
		}
		
		fis.evaluate();
		
		train.setPowerRatio(powerRatio.getValue() / 100);
		train.setBrakeForce(brakeForce.getValue() / 100);
		
		System.out.println("speed=" + speed.getValue() + ",distance=" + targetDistance.getValue() + ",tSpeed=" + targetSpeed.getValue());
		System.out.println("power=" + powerRatio.getValue() + ",brake=" + brakeForce.getValue());
		
		ActionEvent e = new ActionEvent(this, 0, "fuzzy");
		for(ActionListener l : this.listeners){
			l.actionPerformed(e);
		}
	}
	public void addListener(ActionListener l){
		this.listeners.add(l);
	}
	public void removeListener(ActionListener l){
		this.listeners.remove(l);
	}
}