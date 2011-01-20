/**
 * 
 */
package de.hsfulda.softcomputing.fuzbahn.gui.train.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.JComponent;

/**
 * @author Daniel Buth
 * 
 */
abstract class WorldObject extends JComponent {
	Panel panel;

	Double stepX, stepY;

	Double positionX, positionY, positionStartX, positionStartY;

	protected WorldObject() {
		super();
	}

	/**
	 * @param window
	 * @param positionStartX
	 * @param positionStartY
	 * @param stepX
	 * @param stepY
	 */
	public WorldObject(Double positionStartX,
			Double positionStartY, Double stepX, Double stepY) {
		this();
		this.stepX = stepX;
		this.stepY = stepY;
		this.positionX = positionStartX;
		this.positionStartX = positionX;
		this.positionY = positionStartY;
		this.positionStartY = positionY;
		initiateObjects();
	}

	abstract protected void initiateObjects();

	abstract protected void paintObject(Graphics g);

	public void drawObjects(Graphics g) {
		if (positionX > getWidth())
			positionX = positionStartX;
		if (positionY > getHeight())
			positionY = positionStartY;
		if (positionX < 0)
			positionX = positionStartX;
		if (positionY < 0)
			positionY = positionStartY;

		positionX = positionX + stepX;
		positionY = positionY + stepY;
		paintObject(g);
	}

	public void setStepX(Double stepX) {
		this.stepX = stepX;
	}

	public Double getStepX() {
		return this.stepX;
	}
}
