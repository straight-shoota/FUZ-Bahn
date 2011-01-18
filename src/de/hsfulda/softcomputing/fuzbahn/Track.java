package de.hsfulda.softcomputing.fuzbahn;

import java.util.*;

public class Track 
implements Iterable<TrackElement>{
	public static double DISTANCE_MAX = 1500D;
	public static double SPEED_MAX = 22.2D;
	/**
	 * real length of track (in meters)
	 */
	private double length;

	private NavigableSet<TrackElement> elements;
	private List<Train> trains;
	
	private TrackElement fin;
	
	private List<TrackPostitionListener> listeners = new Vector<TrackPostitionListener>();
	
	public Track(double length){
		elements = new TreeSet<TrackElement>(new TrackPositionComparator());
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
	
	public void add(TrackElement e) {
		/*if(e instanceof Train){
			trains.add((Train) e);
		}*/
		elements.add(e);
		e.setTrack(this);
	}

	public boolean remove(TrackElement e) {
		e.setTrack(null);
		/*if(e instanceof Train){
			trains.remove((Train) e);
		}*/
		return elements.remove(e);
	}
	
	public Iterable<Train> getTrains(){
		List<Train> trains = new Vector<Train>();
		for(TrackElement e : elements){
			if(e instanceof Train){
				trains.add((Train) e);
			}
		}
		return trains;
	}
	public Iterable<TrackElement> getElements(){
		return elements;
	}
	public TrackElement[] getElementsArray(){
		TrackElement[] a = new TrackElement[elements.size()];
		a = elements.toArray(a);
		return a;
	}
	public Iterator<TrackElement> iterator(){
		return elements.iterator();
	}

	/**
	 * This method must be called when the position a movable TrackElement (i.e.
	 * Train) changes. It recalculates the order of elements on track if
	 * necessary.
	 */
	public synchronized void updateElements() {
		TrackElement[] es = getElementsArray();
		for(TrackElement t : es){
			//System.out.println(t);
			elements.remove(t);
			elements.add(t);
		}
		for(TrackElement t : es){
		}
		for(TrackPostitionListener l : listeners){
			l.trackPostitionsUpdated(this);
		}
	}
	
	public void addListener(TrackPostitionListener l){
		listeners.add(l);
	}
	public void removeListener(TrackPostitionListener l){
		listeners.remove(l);
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
			super("FIN", 0, length);
			add(this);
		}
		public double getPosition() {
			return Track.this.getLength();
		}
	}
}