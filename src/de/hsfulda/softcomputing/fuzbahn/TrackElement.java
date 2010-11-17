package de.hsfulda.softcomputing.fuzbahn;

public class TrackElement {

	/**
	 * Position of element on its track, value in meters (<= track length). May
	 * be dynamic altered (e.g. moving Trains).
	 */
	public double position;

	/**
	 * Length of TrackElement. Defaults to 0.
	 */
	public double length;

	public double getPosition() {
		return position;
	}

	public double getLength() {
		return length;
	}

	public void TrackElement(double position) {
		this.position = position;
	}

	public double getSpeed() {
		return 0.0;
	}

	/**
	 * Calculates distance from current TrackElement to e (including e's
	 * length).
	 */
	public double getDistance(TrackElement e) {
		return 0.0;
	}

}