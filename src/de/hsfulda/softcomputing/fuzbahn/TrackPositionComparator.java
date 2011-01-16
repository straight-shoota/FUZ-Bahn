package de.hsfulda.softcomputing.fuzbahn;

import java.util.Comparator;

/**
 * Compares TrackElements by their position on track.
 */
public class TrackPositionComparator<T extends TrackElement>
implements Comparator<T> {
	@Override
	public int compare(T arg0, T arg1) {
		if(arg0 == arg1) return 0;
		int c = (int) (arg0.getPosition() - arg1.getPosition());
		//System.out.println("Comparing " + arg0.getPosition() + " and " + arg1.getPosition() + " ::: " + c);
		return c;
	}
}