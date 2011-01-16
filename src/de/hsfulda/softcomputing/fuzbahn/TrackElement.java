package de.hsfulda.softcomputing.fuzbahn;

public class TrackElement
implements Comparable<TrackElement> {

	/**
	 * Position of element on its track, value in meters (<= track length). May
	 * be dynamic altered (e.g. moving Trains).
	 */
	protected double position = 0;

	/**
	 * Length of TrackElement. Defaults to 0.
	 */
	private double length;
	
	private Track track;
	
	private String name;

	public TrackElement(double length) {
		this("", length);
		this.name = getClass().getSimpleName();
	}
	public TrackElement(String name, double length){
		this.name = name;
		this.length = length;
	}
	public TrackElement(String name, double length, double position) {
		this(name, length);
		this.position = position;
	}
	public String getName(){
		return name;
	}
	public double getPosition() {
		return position;
	}
	public double getLastPosition() {
		return getPosition() - getLength(); 
	}
	public double getDistancePosition() {
		return getLastPosition();
	}

	public double getLength() {
		return length;
	}

	public double getSpeed() {
		return 0.0;
	}
	public void setTrack(Track t){
		track = t;
	}
	public Track getTrack() {
		return track;
	}
	
	public String toString(){
		return getClass() + "[pos=" + getPosition() + ";length=" + getLength() + "speed=" + getSpeed() + "]";
	}

	/**
	 * Calculates distance from current TrackElement to e (including e's
	 * length).
	 */
	public double getDistance(TrackElement e) {
		return e.getDistancePosition() - this.getPosition(); 
	}
	@Override
	public int compareTo(TrackElement o) {
		return (int) (o.getPosition() - getPosition());
	}
}