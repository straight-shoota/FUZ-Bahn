package de.hsfulda.softcomputing.fuzbahn.gui.train;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.hsfulda.softcomputing.fuzbahn.gui.train.objects.*;

/**
 * @author Daniel Buth
 * 
 */
public class GUIWorldPanel extends JPanel implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 6765101564751442239L;

	protected int animationDelay = 20; // millisecond delay (25fps)

	protected int scrollComplete = 2; // to scroll from left to

	// right complete

	protected Timer animationTimer; // Timer drives animation

	TrainObject trainFollower;

	TrainObject trainFront;

	Background background;

	FrontTrainSlider sliderTrainFront;

	Double pxPerDelay;

	public GUIWorldPanel() {
		super();
		/*sliderTrainFront = new FrontTrainSlider();
		sliderTrainFront.addChangeListener(this);
		this.add(sliderTrainFront);*/
		setSize(1000, 400);
		// Farbe
		setBackground(new Color(0, 153, 255));
		pxPerDelay = (double) animationDelay / (double) (scrollComplete);
		createWorld();
		// sliderTrainFront.setValue(background.getSpeed().intValue() * 100);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		animate(g);
	}

	public void startAnimation() {
		if (animationTimer == null) {
			animationTimer = new Timer(animationDelay, this);
			animationTimer.start();
		} else // continue from last image displayed
		if (!animationTimer.isRunning()) {
			animationTimer.restart();
		}

	}

	public void stopAnimation() {
		animationTimer.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		repaint(); // will call paintComponent()
	}

	public void stateChanged(ChangeEvent e) {
		FrontTrainSlider source = (FrontTrainSlider) e.getSource();
		Double value;
		if (source.getValueIsAdjusting()) {
			value = (double) source.getValue();
			setTrainFrontSpeed(value / 100);
			setTrainFollowerSpeed(0.5);
		}
	}

	public void setTrainFrontSpeed(Double speedInPercent) {
		background.setStepX(calculatePXInStep(-speedInPercent));
	}

	public void setTrainFollowerSpeed(Double speedInPercent) {
		//+ wegen umgekehrtes scrolling des backgrounds
		trainFollower.setStepX(calculatePXInStep(speedInPercent) + background.getStepX()); 

	}

	private Double calculatePXInStep(Double speedInPercent) {
		return pxPerDelay * speedInPercent;
	}

	private void animate(Graphics g) {
		background.drawObjects(g);
		trainFollower.drawObjects(g);
		trainFront.drawObjects(g);
	}

	private void createWorld() {
		// initiate state
		background = new Background(0.0, 0.0,
				calculatePXInStep(0.0), 0.0);
		background.setSize(1000, 400);
		trainFollower = new TrainObject(100.0, 220.0,
				calculatePXInStep(0.5), 0.0);
		trainFollower.setSize(100, 100);
		trainFront = new TrainObject(650.0, 220.0, 0.0, 0.0);
		trainFront.setSize(100, 100);
	}

}
