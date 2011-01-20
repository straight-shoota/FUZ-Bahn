package de.hsfulda.softcomputing.fuzbahn;

public class TrainPrototype {
	private double powerMax;
	private double powerMin;

	private double energyEfficiency = .8;

	private double brakeForceMax;

	private double speedMax;

	private double mass;
	private double length;

	private String name;

	public Train createTrain() {
		Train t = new Train(this);
		t.update(0);
		return t;
	}

	/**
	 * @return the powerMax
	 */
	public double getPowerMax() {
		return powerMax;
	}

	/**
	 * @param powerMax
	 *            the powerMax to set
	 */
	public void setPowerMax(double powerMax) {
		this.powerMax = powerMax;
	}

	/**
	 * @return the powerMin
	 */
	public double getPowerMin() {
		return powerMin;
	}

	/**
	 * @param powerMin
	 *            the powerMin to set
	 */
	public void setPowerMin(double powerMin) {
		this.powerMin = powerMin;
	}

	/**
	 * @return the speedMax
	 */
	public double getSpeedMax() {
		return speedMax;
	}

	/**
	 * @param speedMax
	 *            the speedMax to set
	 */
	public void setSpeedMax(double speedMax) {
		this.speedMax = speedMax;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * @param mass
	 *            the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @return the energyEfficiency
	 */
	public double getEnergyEfficiency() {
		return energyEfficiency;
	}

	/**
	 * @param energyEfficiency
	 *            the energyEfficiency to set
	 */
	public void setEnergyEfficiency(double energyEfficiency) {
		this.energyEfficiency = energyEfficiency;
	}

	/**
	 * @return the brakeAccelerationMax
	 */
	public double getBrakeForceMax() {
		return brakeForceMax;
	}

	/**
	 * @param brakeAccelerationMax
	 *            the brakeAccelerationMax to set
	 */
	public void setBrakeForceMax(double brakeAccelerationMax) {
		this.brakeForceMax = brakeAccelerationMax;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrainPrototype:" + name + " [brakeAccelerationMax="
				+ brakeForceMax + ", energyEfficiency=" + energyEfficiency
				+ ", length=" + length + ", mass=" + mass + ", powerMax="
				+ powerMax + ", powerMin=" + powerMin + ", speedMax="
				+ speedMax + "]";
	}

}
