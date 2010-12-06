package de.hsfulda.softcomputing.fuzbahn;

import java.util.*;

public class Track {

	/**
	 * real length of track (in meters)
	 */
	private double length;

	private SortedSet<TrackElement> elements;
	private List<Train> trains;
	
	public Track(double length){
		setLength(length);
		
		elements = new TreeSet<TrackElement>();
		trains = new ArrayList<Train>();
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		if(length < 0){
			throw new IllegalArgumentException("length cannot be negative.");
		}
		this.length = length;
	}

	public void addElement(TrackElement e) {
		elements.add(e);
	}

	public boolean removeElement(TrackElement e) {
		return elements.remove(e);
	}
	
	public Train[] getTrains(){
		Train[] t = new Train[trains.size()];
		trains.toArray(t);
		return t;
	}

	/**
	 * This method must be called when the position a movable TrackElement (i.e.
	 * Train) changes. It recalculates the order of elements on track if
	 * necessary.
	 */
	public void updateElements() {
		for(Train t : trains){
			elements.remove(t);
			elements.add(t);
		}
	}

}