package de.hsfulda.softcomputing.fuzbahn;

import java.util.*;

public class Track 
implements Iterable<TrackElement>{

	/**
	 * real length of track (in meters)
	 */
	private double length;

	private NavigableSet<TrackElement> elements;
	private List<Train> trains;
	
	private TrackElement fin;
	
	public Track(double length){
		elements = new TreeSet<TrackElement>();
		trains = new ArrayList<Train>();
		
		setLength(length);
		
		fin = new Fin();
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


	public void add(Train t) {
		//System.out.println("adding train " + t);
		trains.add(t);
		add((TrackElement) t);
	}
	
	public void add(TrackElement e) {
		//System.out.println("adding element " + e);
		elements.add(e);
		e.setTrack(this);
	}

	public boolean remove(Train t) {
		throw new RuntimeException("Removing train " + t);
		/*trains.remove(t);
		return remove((TrackElement) t);*/
	}
	public boolean remove(TrackElement e) {
		e.setTrack(null);
		return elements.remove(e);
	}
	
	public Iterable<Train> getTrains(){
		return trains;
	}
	public Iterator<TrackElement> iterator(){
		return elements.iterator();
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
	
	public TrackElement nextElement(TrackElement t){
		return elements.higher(t);
	}
	
	public String toString(){
		return "Track [length=" + getLength() + ",elements=" + elements.size() + ",trains=" + trains.size() + "]";
	}
	
	public class Fin
	extends TrackElement {
		public Fin(){
			super(0, length);
			add(this);
		}
		public double getPosition() {
			return Track.this.getLength();
		}
	}
}