package de.hsfulda.softcomputing.fuzbahn;

import java.util.SortedSet;

public class Track {

	/**
	 * real length of track (in meters)
	 */
	public double length;

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
		this.length = length;
	}

	public SortedSet<TrackElement> elements;

	public void getPrecedingElements(TrackElement e) {
	}

	public void addElement(TrackElement e) {
	}

	public TrackElement removeElement(TrackElement e) {
		return null;
	}

	/**
	 * This method must be called when the position a movable TrackElement (i.e.
	 * Train) changes. It recalculates the order of elements on track if
	 * necessary.
	 */
	public void updateElement(TrackElement e) {
	}

}