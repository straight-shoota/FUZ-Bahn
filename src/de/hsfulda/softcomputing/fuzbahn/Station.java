package de.hsfulda.softcomputing.fuzbahn;

public class Station extends StaticTrackElement {

	public Station(String name, double length, double position) {
		super(name, 0, position);
	}

	public double getDistancePosition() {
		return getPosition();
	}
}