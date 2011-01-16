package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import de.hsfulda.softcomputing.fuzbahn.*;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.JPanelFis;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FUZPanel extends JPanel
implements TrackPostitionListener {
	FuzzyController controller;
	Track track;
	
	JList tList;
	VarPanel[] vps;
	
	public FUZPanel(FuzzyController c, Track t) {
		this.track = t;
		this.controller = c;
		
		setLayout(new BorderLayout());
		
		JPanel varsp = new JPanel();
		varsp.setLayout(new GridLayout(2,3));
		
		Collection<FuzzyValue> vars = controller.getValues().values();
		vps = new VarPanel[vars.size()];
		int i = 0;
		for(FuzzyValue v : vars){
			vps[i] = new VarPanel(v);
			varsp.add(vps[i]);
			i++;
		}
		t.addListener(this);
		tList = new JList(t.getElementsArray());
		tList.setCellRenderer(new TrackElementCellRenderer());
		varsp.add(tList);
		
		add(varsp, BorderLayout.CENTER);
	}
	
	/**
	 * @return the fis
	 */
	public FuzzyController getController() {
		return controller;
	}


	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}

	@Override
	public void trackPostitionsUpdated(Track track) {
		tList.setListData(track.getElementsArray());
	}
}
