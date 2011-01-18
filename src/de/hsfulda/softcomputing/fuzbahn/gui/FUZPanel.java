package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hsfulda.softcomputing.fuzbahn.*;
import de.hsfulda.softcomputing.fuzbahn.test.AbstractTest;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.JPanelFis;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FUZPanel extends JPanel implements TrackPostitionListener {
	FuzzyController controller;
	Track track;

	JList tList;
	VarPanel[] vps;
	AbstractTest test;

	public FUZPanel(AbstractTest test, FuzzyController c, Track t) {
		this.track = t;
		this.controller = c;
		this.test = test;

		setLayout(new BorderLayout());

		JPanel varsp = new JPanel();
		varsp.setLayout(new GridLayout(2, 3));

		Collection<FuzzyValue> vars = controller.getValues().values();
		vps = new VarPanel[vars.size()];
		int i = 0;
		for (FuzzyValue v : vars) {
			vps[i] = new VarPanel(v);
			varsp.add(vps[i]);
			i++;
		}
		t.addListener(this);
		tList = new JList(t.getElementsArray());
		tList.setCellRenderer(new TrackElementCellRenderer());
		varsp.add(tList);

		add(varsp, BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.add(new JButton(new StartAction()));
		buttons.add(new JButton(new StopAction()));

		JSlider slider = new JSlider(0, 10, 5);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int i = ((JSlider) (e.getSource())).getValue();
				double d = i - 4;
				if (d < 1) {
					d = -1 / (d - 1);
				}
				getTest().setSimulationScale(d);
			}
		});
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		Dictionary<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(5, new JLabel("Normal"));
		labels.put(10, new JLabel("Schneller"));
		labels.put(0, new JLabel("Langsamer"));
		slider.setLabelTable(labels);
		slider.setPaintLabels(true);

		buttons.add(slider);

		add(buttons, BorderLayout.NORTH);
	}

	AbstractTest getTest() {
		return test;
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

	class StartAction extends AbstractAction {
		public StartAction() {
			super("Simulation starten");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			test.startDemo();
		}
	};

	class StopAction extends AbstractAction {
		public StopAction() {
			super("Simulation anhalten");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			test.stopDemo();
		}
	};
}
