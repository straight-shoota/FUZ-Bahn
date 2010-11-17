package de.hsfulda.softcomputing.fuzbahn;

import java.text.DecimalFormat;
import java.text.Format;

public class Train extends TrackElement {
	public static final double G = 9.80665;
	/**
	 * Current speed of the train, is influenced by acceleration and braking
	 * power. Values in meters per second (positive only). Defaults to 0.0.
	 */
	private double speed;

	/**
	 * Current power of train engine. Valid values are in percent ranging from
	 * -1.0 to 1.0 and representing values between acclerationMin and
	 * accelerationMax.
	 */
	private double powerRatio;

	private double brakeForce;
	
	private TrainPrototype prototype;

	public Train(TrainPrototype prototype) {
		this.prototype = prototype;
	}

	/**
	 * @return the power
	 */
	public double getPowerRatio() {
		return powerRatio;
	}

	/**
	 * @return the brakeForce
	 */
	public double getBrakeForce() {
		return brakeForce;
	}

	/**
	 * @return the prototype
	 */
	public TrainPrototype getPrototype() {
		return prototype;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Computes current acceleration by adding getEngineAcceleration() and
	 * getBrakeAcceleration()
	 */
	public double getAcceleration() {
		return 0.0;
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
	 * @see de.hsfulda.softcomputing.fuzbahn.TrackElement#getSpeed()
	 * 
	 */
	@Override
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Returns current acceleration (actually it's deceleration) provided by the
	 * actual brake force. Values range from 0 to brakeAccelerationMax
	 */
	public double getBrakeAcceleration() {
		return 0.0;
	}

	public void setPowerRatio(double power) {
		this.powerRatio = power;
	}

	public void setBrakeForce(double brakeForce) {
		this.brakeForce = brakeForce;
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
		
		double totalForce = getRollingResistance() + getDragForce();
		System.out.println("force = " + f.format(totalForce) + " N");
		double totalPower = accelerationPower - totalForce * getSpeed();

		System.out.println("power = " + f.format(totalPower) + " kW");
		double kineticEnergy = getMass() * getSpeed() * getSpeed() / 2;
		
		double newEnergy = kineticEnergy + deltaT * totalPower;
		
		setSpeed(Math.sqrt(2 * newEnergy / getMass()));
	}
	
	public double getRollingResistance() {
		double cr = 0.0015;
		return cr * getMass() * G;
	}
	public double getDragForce() {
		double pLuft = 1.2;
		double A = 10;
		double cw = 0.8;
		return pLuft / 2 * cw * A * getSpeed() * getSpeed();
	}
	
	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getBrakeAccelerationMax()
	 */
	public double getBrakeAccelerationMax() {
		return prototype.getBrakeAccelerationMax();
	}

	/**
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getEnergyEfficiency()
	 */
	public double getEnergyEfficiency() {
		return prototype.getEnergyEfficiency();
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
	 * @return
	 * @see de.hsfulda.softcomputing.fuzbahn.TrainPrototype#getSpeedMax()
	 */
	public double getSpeedMax() {
		return prototype.getSpeedMax();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Format f = new DecimalFormat("#.#");
		return "Train (" + prototype.getName() + ") [speed=" + Math.round(speed * 3.6) + " km/h, "
		+ "brakeForce=" + f.format(brakeForce) 
		+ ", power=" + Math.round(powerRatio * 100) + "%]";
	}
	
}