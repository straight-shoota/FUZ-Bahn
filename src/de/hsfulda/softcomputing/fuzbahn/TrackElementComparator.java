package de.hsfulda.softcomputing.fuzbahn;

import java.util.Comparator;

/**
 * Compares TrackElements by their position on track.
 */
public class TrackElementComparator<T extends TrackElement>
implements Comparator<T> {
	@Override
	public int compare(T arg0, T arg1) {
		return (int) Math.round(arg0.getDistance(arg1));
	}
}