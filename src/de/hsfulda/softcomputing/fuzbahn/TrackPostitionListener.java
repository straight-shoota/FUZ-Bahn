package de.hsfulda.softcomputing.fuzbahn;

import java.util.EventListener;

public interface TrackPostitionListener
extends EventListener{
	public void trackPostitionsUpdated(Track track);
}
