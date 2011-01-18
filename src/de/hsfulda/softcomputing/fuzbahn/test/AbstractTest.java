package de.hsfulda.softcomputing.fuzbahn.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;

import net.sourceforge.jFuzzyLogic.rule.Variable;

import org.jfree.chart.JFreeChart;

import de.hsfulda.softcomputing.fuzbahn.FuzzyController;
import de.hsfulda.softcomputing.fuzbahn.FuzzyValue;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.Train;
import de.hsfulda.softcomputing.fuzbahn.TrainPrototype;

public abstract class AbstractTest implements Runnable {
	static final double DELTA_T = .1D;

	private boolean running = true;
	private double deltaT = DELTA_T;
	private double simulationScale = .5D;
	private List<SimulationStateListener> listeners = new Vector<SimulationStateListener>();

	private Thread thread;

	protected Track track;
	FuzzyController controller;

	public AbstractTest() {
		initModel();
		initUI();
	}

	protected void initModel() {
	}

	protected void initUI() {
	}

	@Override
	public void run() {
		long sleep = Math.round(deltaT * 1000);

		while (running) {
			doStep(deltaT * simulationScale);
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException exc) {
				setRunning(false);
				break;
			}
		}
	}

	/**
	 * @param running the running to set
	 */
	private void setRunning(boolean running) {
		this.running = running;
		
		for(SimulationStateListener l : listeners){
			l.simulationStateChanged(isRunning());
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void startDemo() {
		setRunning(true);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stopDemo() {
		setRunning(false);
		thread = null;
	}
	public void reset(){
		stopDemo();
		setInitValues();
	}
	protected void setInitValues(){
		for(Train e : track.getTrains()){
			e.setPosition(0);
			e.setSpeed(0);
			e.setPowerRatio(0);
			e.setBrakeForce(0);
		}
	}

	/**
	 * @return the simulationScale
	 */
	public double getSimulationScale() {
		return simulationScale;
	}

	/**
	 * @param simulationScale
	 *            the simulationScale to set
	 */
	public void setSimulationScale(double simulationScale) {
		this.simulationScale = simulationScale;
	}

	public FuzzyController getController() {
		return controller;
	}

	public Track getTrack() {
		return track;
	}

	protected void doStep(double deltaT) {
		controller.update();
		for (Train t : track.getTrains()) {
			t.update(deltaT);
		}
		track.updateElements();
	}

	protected TrainPrototype getDefaultPrototype() {
		TrainPrototype prototype = new TrainPrototype();
		/*
		 * prototype.setName("FUZ"); prototype.setBrakeAccelerationMax(-1.0);
		 * prototype.setLength(100); prototype.setPowerMax(2000000);
		 * prototype.setPowerMin(-2000000); prototype.setMass(140000);
		 * prototype.setSpeedMax(70/3.6);
		 */

		prototype.setName("B");
		prototype.setBrakeForceMax(400000);
		prototype.setLength(40);
		prototype.setPowerMax(780000);
		prototype.setPowerMin(-780000);
		prototype.setMass(80000);
		prototype.setSpeedMax(80 / 3.6);
		return prototype;
	}

	public void showCharts(FuzzyController fis) {
		JFrame frame = new JFrame("Fuzzy Test");
		frame.setSize(1000, 750);
		JComponent cp = (JComponent) frame.getContentPane();
		cp.setLayout(new GridLayout(3, 2));

		for (FuzzyValue v : fis.getValues().values()) {
			cp.add(new ChartPanel(fis, v.getVariable()));
		}

		frame.setVisible(true);
	}

	public class ChartPanel extends JComponent {
		private JFreeChart chart;
		FuzzyController controller;

		public ChartPanel(FuzzyController controller, Variable v) {
			this.chart = v.chart(false);
			this.controller = controller;
			// controller.addListener(this);
		}

		public JFreeChart getChart() {
			return chart;
		}

		@Override
		public void paintComponent(Graphics g) {
			if (chart != null) {
				Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(),
						getHeight());
				chart.draw((Graphics2D) g, rect);
			}
		}

		public void setChart(JFreeChart chart) {
			this.chart = chart;
		}
	}
	public static interface SimulationStateListener
	extends EventListener {
		public void simulationStateChanged(boolean running);
	}
	public void addSimulationStateListener(SimulationStateListener l){
		listeners.add(l);
	}
	public void removeSimulationStateListener(SimulationStateListener l){
		listeners.remove(l);
	}
}
