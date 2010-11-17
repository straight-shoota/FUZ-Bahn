package de.hsfulda.softcomputing.fuzbahn;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DecimalFormat;
import java.text.Format;

import javax.swing.event.SwingPropertyChangeSupport;

public class Train extends TrackElement {
	public static final double G = 9.80665;
	
	public static final String SPEED = "speed";
	public static final String TARGET_DISTANCE = "target_distance";
	public static final String TARGET_SPEED = "target_speed";
	public static final String BRAKE_FORCE = "brakeForce";
	public static final String POWER_RATIO = "powerRatio";

	private static final String ACCELERATION = null;
	
	private double brakeForce;

	protected PropertyChangeSupport pcs = new SwingPropertyChangeSupport(this, true);

	/**
	 * Current power of train engine. Valid values are in percent ranging from
	 * -1.0 to 1.0 and representing values between acclerationMin and
	 * accelerationMax.
	 */
	private double powerRatio;
	
	private TrainPrototype prototype;
	
	/**
	 * Current speed of the train, is influenced by acceleration and braking
	 * power. Values in meters per second (positive only). Defaults to 0.0.
	 */
	private double speed = 0;
	
	private double acceleration;
	
	public Train(TrainPrototype prototype) {
		this.prototype = prototype;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}

	/**
	 * Computes current acceleration by adding getEngineAcceleration() and
	 * getBrakeAcceleration()
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * Returns current acceleration (actually it's deceleration) provided by the
	 * actual brake force. Values range from 0 to brakeAccelerationMax
	 */
	public double getBrakeAcceleration() {
		return 0.0;
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getBrakeAccelerationMax()
	 */
	public double getBrakeAccelerationMax() {
		return prototype.getBrakeAccelerationMax();
	}

	/**
	 * @return the brakeForce
	 */
	public double getBrakeForce() {
		return brakeForce;
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getEnergyEfficiency()
	 */
	public double getEnergyEfficiency() {
		return prototype.getEnergyEfficiency();
	}

	/**
	 * Returns current acceleration provided by the trains main engine depending
	 * on current engine power. Values range from acclerationMin to
	 * accelerationMax
	 */
	public double getEngineAcceleration() {
		return 0.0;
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getLength()
	 */
	public double getLength() {
		return prototype.getLength();
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getMass()
	 */
	public double getMass() {
		return prototype.getMass();
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getPowerMax()
	 */
	public double getPowerMax() {
		return prototype.getPowerMax();
	}
	
	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getPowerMin()
	 */
	public double getPowerMin() {
		return prototype.getPowerMin();
	}
	/**
	 * @return the power
	 */
	public double getPowerRatio() {
		return powerRatio;
	}
	
	/**
	 * @return the prototype
	 */
	public TrainPrototype getPrototype() {
		return prototype;
	}

	/**
	 * @see de.hsfulda.softcomputing.fuzbahn.TrackElement#getSpeed()
	 * 
	 */
	@Override
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getSpeedMax()
	 */
	public double getSpeedMax() {
		return prototype.getSpeedMax();
	}
	
	public void setAcceleration(double a){
		double oldValue = this.acceleration;
		this.acceleration = a;
		
		pcs.firePropertyChange(ACCELERATION, oldValue, a);
	}

	public void setBrakeForce(double brakeForce) {
		if(brakeForce < 0 || brakeForce > 1){
			throw new IllegalArgumentException("brake force must be between 0% and 100%");
		}
		double oldValue = this.brakeForce;
		this.brakeForce = brakeForce;
		
		pcs.firePropertyChange(BRAKE_FORCE, oldValue, this.brakeForce);
	}

	public void setPowerRatio(double power) {
		if(power < -1 || power > 1){
			throw new IllegalArgumentException("power ratio must be between -100% and +100%");
		}
		
		double oldValue = this.powerRatio;
		this.powerRatio = power;
		
		pcs.firePropertyChange(POWER_RATIO, oldValue, powerRatio);
	}
	
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		double oldVal = this.speed;
		this.speed = speed;
		
		pcs.firePropertyChange(SPEED, oldVal, speed);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Format f = new DecimalFormat("#.#");
		return "Train (" + prototype.getName() + ") [speed=" + Math.round(speed * 3.6) + " km/h, "
			+ "acceleration=" + f.format(acceleration) + " m/s² "
			+ "brakeForce=" + f.format(brakeForce) 
			+ ", power=" + Math.round(powerRatio * 100) + "%]";
	}

	/**
	 * Updates current speed according to current acceleration during one time
	 * unit.
	 * @param deltaT length of time unit in seconds
	 */
	public void update(double deltaT) {
		double accelerationPower;
		Format f = new DecimalFormat("#.#");
		
		if(getPowerRatio() > 0) {
			accelerationPower = getPowerMax() * getPowerRatio();
		}else{
			accelerationPower = getPowerMin() * -getPowerRatio();
		}
		accelerationPower *= getPrototype().getEnergyEfficiency();
		
		double totalForce = getRollingResistance() + getDragForce();
		System.out.println("force = " + f.format(totalForce) + " N");
		double totalPower = accelerationPower - totalForce * getSpeed();

		System.out.println("power = " + f.format(totalPower / 1000) + " kW");
		double kineticEnergy = getMass() * getSpeed() * getSpeed() / 2;
		
		double newEnergy = kineticEnergy + deltaT * totalPower;
		
		double newSpeed = Math.sqrt(2 * newEnergy / getMass());
		setAcceleration((newSpeed - getSpeed()) / deltaT);
		setSpeed(newSpeed);
	}

	protected double getDragForce() {
		double pLuft = 1.2;
		double A = 10;
		double cw = 0.8;
		return pLuft / 2 * cw * A * getSpeed() * getSpeed();
	}
	protected double getRollingResistance() {
		double cr = 0.0015;
		return cr * getMass() * G;
	}
}