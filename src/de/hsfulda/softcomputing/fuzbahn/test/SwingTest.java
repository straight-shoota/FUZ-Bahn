package de.hsfulda.softcomputing.fuzbahn.test;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyUnavailableException;
import de.hsfulda.softcomputing.fuzbahn.SpeedLimit;
import de.hsfulda.softcomputing.fuzbahn.Station;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;
import de.hsfulda.softcomputing.fuzbahn.gui.FUZPanel;
import de.hsfulda.softcomputing.fuzbahn.gui.TrackPanel;

import de.hsfulda.softcomputing.fuzbahn.gui.train.*;

public abstract class SwingTest extends AbstractTest {
	JFrame frame;
	FUZPanel panel;
	Train t;
	Train t2;
	
	public void initModel() {
		track = new Track(10000);
		t = getDefaultPrototype().createTrain();
		t.setName("Train No. 1");
		track.add(t);
		
		t2 = getDefaultPrototype().createTrain();
		t2.setName("Train No. 2");
		t2.setPosition(100);
		
		track.add(t2);
		track.add(new Station("Station A", 120, 100));
		track.add(new SpeedLimit(800, 50 / Train.MS_KMH));
		track.add(new Station("Station B", 120, 2000));
		track.add(new Station("Station C", 120, 2800));
		track.add(new Station("Station D", 120, 4000));
		setSimulationScale(1D);
		initControllers();
	}
	@Override
	protected void doStep(double deltaT) {
		//controller2.update();
		super.doStep(deltaT);
	}

	public void initUI() {
		frame = new JFrame("FUZ-Bahn");
		frame.setSize(1200, 650);
		
		JPanel cp = new JPanel();
		cp.setLayout(new BorderLayout());
		
		panel = new FUZPanel(this, getControllers()[0], getTrack());
		cp.add(panel, BorderLayout.CENTER);
		
		TrackPanel gui = new TrackPanel(t);
		cp.add(gui, BorderLayout.NORTH);
		
		frame.setContentPane(cp);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
