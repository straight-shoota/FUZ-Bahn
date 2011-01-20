/**
 * 
 */
package de.hsfulda.softcomputing.fuzbahn.gui.train;

import javax.swing.JSlider;

/**
 * @author fdai3076
 *
 */
public class FrontTrainSlider extends JSlider {
	private static final long serialVersionUID = 5804486745037808668L;

	/**
	 * 
	 */
	public FrontTrainSlider() {
		super();
		this.setValue(0);
		this.setMajorTickSpacing(20);
		this.setMinorTickSpacing(5);
		this.setPaintTicks(true);
		this.setPaintLabels(true);

	}



}
