package de.hsfulda.softcomputing.fuzbahn.gui.train.objects;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Daniel Buth
 * 
 */
public class TrainObject extends WorldObject {

	private static final long serialVersionUID = 3813471325837296472L;

	Icon icon;

	public TrainObject(Double positionStartX,
			Double positionStartY, Double stepX, Double stepY) {
		super( positionStartX, positionStartY, stepX, stepY);
	}

	protected void initiateObjects() {
		icon = new ImageIcon("images/train.jpg");
	}

	protected void paintObject(Graphics g) {
		icon.paintIcon(this, g, positionX.intValue(), positionY.intValue());
	}

}
