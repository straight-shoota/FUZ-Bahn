package de.hsfulda.softcomputing.fuzbahn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Map<String, FuzzyValue> values;
	private List<ActionListener> listeners = new ArrayList<ActionListener>();

	public FuzzyController(Train train)
	throws FuzzyUnavailableException {
		//this(train, "/fcl/Fuzzy-Project.fcl");
		this(train, "/fuz-controller.fcl");
	}
	
	public FuzzyController(Train train, String fisFile)
	throws FuzzyUnavailableException {
		this.train = train;
		
		fis = FIS.load(getClass().getResourceAsStream(fisFile), true);
		if(fis == null){
			throw new FuzzyUnavailableException();
		}
		
		values = new HashMap<String, FuzzyValue>();
		addValue(new FuzzyValue.Speed());
		addValue(new FuzzyValue.TargetSpeed());
		addValue(new FuzzyValue.TargetDistance());
		addValue(new FuzzyValue.Brake());
		addValue(new FuzzyValue.Power());
		}
	public void addValue(FuzzyValue f){
		f.setVariable(getFis().getVariable(f.getName()));
		f.setTrain(train);
		this.values.put(f.getName(), f);
	}
	/*public Variable[] getVariables() {
		return variables;
	}*/
	public Map<String,FuzzyValue> getValues() {
		return values;
	}
	public FIS getFis(){
		return fis;
	}
	public synchronized void update() {
		for(FuzzyValue v : getValues().values()){
			if(v.isInput()){
				v.update();
			}
		}
		
		fis.evaluate();

		for(FuzzyValue v : getValues().values()){
			if(v.isOutput()){
				v.update();
			}
		}
		
		//System.out.println("speed=" + speed.getValue() + ",distance=" + targetDistance.getValue() + ",tSpeed=" + targetSpeed.getValue());
		//System.out.println("power=" + powerRatio.getValue() + ",brake=" + brakeForce.getValue());
		
		/*ActionEvent e = new ActionEvent(this, 0, "fuzzy");
		for(ActionListener l : this.listeners){
			l.actionPerformed(e);
		}*/
	}
	/*public void addListener(ActionListener l){
		this.listeners.add(l);
	}
	public void removeListener(ActionListener l){
		this.listeners.remove(l);
	}*/
}