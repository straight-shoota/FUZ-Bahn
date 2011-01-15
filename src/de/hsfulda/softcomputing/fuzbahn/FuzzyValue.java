package de.hsfulda.softcomputing.fuzbahn;

import java.util.List;
import java.util.Vector;

import net.sourceforge.jFuzzyLogic.rule.Variable;

public abstract class FuzzyValue {
	Variable variable;
	Train train;
	double min = 0, max = 100;
	private List<FuzzyValueListener> listeners = new Vector<FuzzyValueListener>();
	
	public void setBounds(double min, double max){
		setMin(min);
		setMax(max);
	}
	
	
	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}


	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}


	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}


	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}


	/**
	 * @return the t
	 */
	public Train getTrain() {
		return train;
	}

	/**
	 * @param t the t to set
	 */
	public void setTrain(Train t) {
		this.train = t;
	}

	public void setVariable(Variable v){
		variable = v;
	}
	
	public abstract String getName();
	public boolean isInput(){
		return false;
	}
	public boolean isOutput(){
		return false;
	}
	
	public Variable getVariable(){
		return variable;
	}
	
	public double getStepSize(){
		return 10D;
	}
	
	public void update(){
		_update();
		
		notifyListeners();
	}
	protected abstract void _update();
	protected void notifyListeners() {
		for(FuzzyValueListener l : listeners){
			l.valueChanged(this);
		}
	}
	
	public void addListener(FuzzyValueListener l){
		listeners.add(l);
	}
	public void removeListener(FuzzyValueListener l){
		listeners.remove(l);
	}
	
	public static abstract class Input
	extends FuzzyValue {
		protected void _update(){
			variable.setValue(getValue());
		}
		public abstract double getValue();
		public boolean isInput(){
			return true;
		}
	}
	public static abstract class Output
	extends FuzzyValue {
		protected void _update(){
			setTrainValue(getValue(variable.getValue()));
		}
		public abstract double getValue(double d);
		public abstract void setTrainValue(double d);
		public boolean isOutput(){
			return true;
		}
	}
	
	public static class Speed
	extends FuzzyValue.Input {
		public Speed(){
			setBounds(0, 120);
		}

		@Override
		public String getName() {
			return Train.SPEED;
		}

		@Override
		public double getValue() {
			return getTrain().getSpeed() * Train.MS_KMH;
		}
	}
	public static class TargetSpeed
	extends FuzzyValue.Input {
		public TargetSpeed(){
			setBounds(-120, 120);
		}

		@Override
		public String getName() {
			return Train.TARGET_SPEED;
		}

		@Override
		public double getValue() {
			TrackElement target = getTrain().getTarget();
			if(target == null){
				return 80 - getTrain().getSpeed() * Train.MS_KMH;
			}
			return (target.getSpeed() - getTrain().getSpeed()) * Train.MS_KMH;
		}
		public double getStepSize(){
			return 20D;
		}
	}
	public static class TargetDistance
	extends FuzzyValue.Input {
		public TargetDistance(){
			setBounds(0, 1500);
		}

		@Override
		public String getName() {
			return Train.TARGET_DISTANCE;
		}

		@Override
		public double getValue() {
			TrackElement target = getTrain().getTarget();
			double tDistance = 1500;
			if(target != null){
				tDistance = train.getDistance(target);
			}
			return tDistance;
		}
		public double getStepSize(){
			return 100D;
		}
	}
	public static class Power
	extends FuzzyValue.Output {
		public Power(){
			setBounds(-100, 100);
		}

		@Override
		public String getName() {
			return Train.POWER_RATIO;
		}

		@Override
		public double getValue(double d) {
			return d / 100;
		}
		@Override
		public void setTrainValue(double d){
			train.setPowerRatio(d);
		}
		public double getStepSize(){
			return 20D;
		}
	}
	public static class Brake
	extends FuzzyValue.Output {
		public Brake(){
			setBounds(0, 100);
		}

		@Override
		public String getName() {
			return Train.BRAKE_FORCE;
		}

		@Override
		public double getValue(double d) {
			return d / 100;
		}
		@Override
		public void setTrainValue(double d){
			train.setBrakeForce(d);
		}
	}
}
