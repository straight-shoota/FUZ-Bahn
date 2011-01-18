package de.hsfulda.softcomputing.fuzbahn;

public class SpeedLimit extends StaticTrackElement {

	private double speedLimit;

	public SpeedLimit(double position, double speed) {
		super("Speed Limit: " + speed * Train.MS_KMH + " km/h", 0, position);
		setSpeed(speed);
	}

	public void setSpeed(double speed) {
		this.speedLimit = speed;
	}

	public double getSpeed() {
		return this.speedLimit;
	}
}