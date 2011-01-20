package de.hsfulda.softcomputing.fuzbahn;

import java.text.DecimalFormat;
import java.text.Format;

public class Train extends TrackElement {
	public static final double G = 9.80665D;
	public static final double MS_KMH = 3.6D;

	public static final String SPEED = "speed";
	public static final String TARGET_DISTANCE = "target_distance";
	public static final String TARGET_SPEED = "target_speed";
	public static final String BRAKE_FORCE = "brake";
	public static final String POWER_RATIO = "power";

	private double brakeForce;
	private String name;

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

	private double acceleration = 0;

	public Train(TrainPrototype prototype) {
		super(prototype.getLength());
		this.prototype = prototype;
		this.setName("Train (" + prototype.getName() + ")");
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
	public double getBrakeForceMax() {
		return prototype.getBrakeForceMax();
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

	public void setAcceleration(double a) {
		this.acceleration = a;
	}

	public void setBrakeForce(double brakeForce) {
		if (brakeForce < 0 || brakeForce > 1) {
			throw new IllegalArgumentException(
					"brake force must be between 0% and 100%: " + brakeForce);
		}
		this.brakeForce = brakeForce;
	}

	public void setPowerRatio(double power) {
		if (power < -1 || power > 1) {
			throw new IllegalArgumentException(
					"power ratio must be between -100% and +100%: " + power);
		}

		this.powerRatio = power;
	}

	public void setPosition(double pos) {
		this.position = pos;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public TrackElement getTarget() {
		return getTrack().nextElement(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Format f = new DecimalFormat("#.#");
		return "Train (" + prototype.getName() + ") [" + "position="
				+ f.format(getPosition()) + " m,\t" + "speed="
				+ Math.round(speed * 3.6) + " km/h,\t" + "acceleration="
				+ f.format(acceleration) + " m/s²,\t" + "brakeForce="
				+ f.format(brakeForce) + "power="
				+ Math.round(powerRatio * 100) + "%]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Updates current speed according to current acceleration during one time
	 * unit.
	 * 
	 * @param deltaT
	 *            length of time unit in seconds
	 */
	public void update(double deltaT) {
		if (deltaT < 0) {
			throw new IllegalArgumentException("time period must be positive");
		}

		double accelerationPower;
		Format f = new DecimalFormat("#.#");

		if (getPowerRatio() > 0) {
			accelerationPower = getPowerMax() * getPowerRatio();
		} else {
			accelerationPower = getPowerMin() * getPowerRatio() * -1D;
		}
		accelerationPower *= getPrototype().getEnergyEfficiency();

		double totalForce = getRollingResistance() + getDragForce();
		// System.out.println("force = " + f.format(totalForce) + " N");

		double brakePower = getBrakeForce() * getBrakeForceMax() * getSpeed();
		//System.out.println("brakePower = " + f.format(brakePower) + " W");
		double totalPower = accelerationPower - brakePower - totalForce
				* getSpeed();

		// System.out.println(f.format(accelerationPower) + " W " + getSpeed() +
		// " m/s");

		// System.out.println("power = " + f.format(totalPower / 1000) + " kW");
		double kineticEnergy = getMass() * getSpeed() * getSpeed() / 2;

		double newEnergy = kineticEnergy + deltaT * totalPower;

		double newSpeed = Math.sqrt(2 * newEnergy / getMass());

		/*
		 * if(Double.isNaN(newSpeed)) { newSpeed = getSpeed(); }
		 */

		// System.out.println(kineticEnergy +" :: " + newSpeed + " :: " +
		// totalPower);
		if (Double.isNaN(newSpeed)) {
			newSpeed = getSpeed() + 1;
		}
		if (deltaT != 0) {			
			setPosition(getPosition() + (getSpeed() + newSpeed) / 2 * deltaT);

			setAcceleration((newSpeed - getSpeed()) / deltaT);
		}
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