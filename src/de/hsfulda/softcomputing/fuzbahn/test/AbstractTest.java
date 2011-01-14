package de.hsfulda.softcomputing.fuzbahn.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jfree.chart.JFreeChart;

import net.sourceforge.jFuzzyLogic.rule.Variable;

import de.hsfulda.softcomputing.fuzbahn.*;

public abstract class AbstractTest
extends Thread {
	static final double DELTA_T = 1;
	
	private boolean running = true;
	private double deltaT = DELTA_T;
	
	protected Track track;
	
	protected void init(){}
	
	@Override
	public void run() {
		init();

		long sleep = Math.round(deltaT * 1000);
		
		while(running) {
			doStep(deltaT);
			try {
				Thread.sleep(sleep);
			}catch(InterruptedException exc){
				running = false;
			}
		}
	}
	
	protected abstract void doStep(double deltaT);
	
	protected TrainPrototype getDefaultPrototype() {
		TrainPrototype prototype = new TrainPrototype();
		/*prototype.setName("FUZ");
		prototype.setBrakeAccelerationMax(-1.0);
		prototype.setLength(100);
		prototype.setPowerMax(2000000);
		prototype.setPowerMin(-2000000);
		prototype.setMass(140000);
		prototype.setSpeedMax(70/3.6);*/
		
		prototype.setName("B");
		prototype.setBrakeAccelerationMax(-1.0);
		prototype.setLength(40);
		prototype.setPowerMax(780000);
		prototype.setPowerMin(-780000);
		prototype.setMass(80000);
		prototype.setSpeedMax(80/3.6);
		return prototype;
	}
	
	public void showCharts(FuzzyController fis) {
		JFrame frame = new JFrame("Fuzzy Test");
		frame.setSize(1000, 400);
		JComponent cp = (JComponent) frame.getContentPane();
		cp.setLayout(new GridLayout(3,2));
		
		for(Variable v : fis.getVariables()){
			cp.add(new ChartPanel(fis, v));
		}
		
		frame.setVisible(true);
	}
	public class ChartPanel
	extends JComponent 
	implements ActionListener {
		private JFreeChart chart;
		FuzzyController controller;
		
		public ChartPanel(FuzzyController controller, Variable v) {
			this.chart = v.chart(false);
			this.controller = controller;
			controller.addListener(this);
		}
		public JFreeChart getChart() {
			return chart;
		}

		@Override
		public void paintComponent(Graphics g) {
			if( chart != null ) {
				Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
				chart.draw((Graphics2D) g, rect);
			}
		}

		public void setChart(JFreeChart chart) {
			this.chart = chart;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
