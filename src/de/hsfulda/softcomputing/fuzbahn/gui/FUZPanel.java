package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import de.hsfulda.softcomputing.fuzbahn.*;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.JPanelFis;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FUZPanel extends JPanel {
	FuzzyController controller;
	Track track;

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
}
