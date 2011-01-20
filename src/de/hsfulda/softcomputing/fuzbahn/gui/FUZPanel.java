package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyValue;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.TrackPostitionListener;
import de.hsfulda.softcomputing.fuzbahn.test.AbstractTest;
import de.hsfulda.softcomputing.fuzbahn.test.AbstractTest.SimulationStateListener;

public class FUZPanel
extends JPanel
implements TrackPostitionListener, SimulationStateListener {
	FuzzyController controller;
	Track track;

	VarPanel[] vps;
	AbstractTest test;
	JList tList;
	JButton startButton, stopButton;

	public FUZPanel(AbstractTest test, FuzzyController c, Track t) {
		this.track = t;
		this.controller = c;
		this.test = test;
		test.addSimulationStateListener(this);

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
		
		JPanel trackPanel = new JPanel();
		trackPanel.setLayout(new BorderLayout());
		
		tList = new JList(t.getElementsArray());
		tList.setCellRenderer(new TrackElementCellRenderer());
		tList.setBorder(BorderFactory.createEtchedBorder());
		trackPanel.add(tList, BorderLayout.CENTER);
		
		trackPanel.add(new JLabel("Positions on track"), BorderLayout.NORTH);
		
		varsp.add(trackPanel);

		add(varsp, BorderLayout.CENTER);
		JPanel top = new JPanel();
		JPanel buttons = new JPanel();
		
		JLabel label = new JLabel("<html>Fuzzy controller for " + c.getTrain().getName() + "</html>");
		label.setForeground(Color.RED);
		
		buttons.add(label);
		
		startButton = new JButton(new StartAction());
		stopButton = new JButton(new StopAction());
		stopButton.setEnabled(false);
		
		buttons.add(startButton);
		buttons.add(stopButton);

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
		buttons.add(new JButton(new ResetAction()));

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
	class ResetAction extends AbstractAction {
		public ResetAction() {
			super("Simulation zurücksetzen");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			test.reset();
		}
	}
	@Override
	public void simulationStateChanged(boolean running) {
		startButton.setEnabled(! running);
		stopButton.setEnabled(running);
	};
}
