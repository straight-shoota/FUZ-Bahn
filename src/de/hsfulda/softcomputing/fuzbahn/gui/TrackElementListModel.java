package de.hsfulda.softcomputing.fuzbahn.gui;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import de.hsfulda.softcomputing.fuzbahn.Track;

public class TrackElementListModel extends AbstractListModel {
	Track track;
	public TrackElementListModel(Track track){
		this.track = track;
	}
	@Override
	public Object getElementAt(int index) {return null;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
