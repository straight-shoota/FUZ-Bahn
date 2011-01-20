/**
 * 
 */
package de.hsfulda.softcomputing.fuzbahn.gui.train.objects;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Daniel Buth
 * 
 */
public class Background extends WorldObject {

	private static final long serialVersionUID = 1189829771177638431L;

	Icon iconWall, iconGround;
	Double positionX_VG, positionY_VG;


	public Background(Double positionStartX,
			Double positionStartY, Double stepX, Double stepY) {
		super(positionStartX, positionStartY, stepX, stepY);
		positionX_VG = positionStartX;
		positionY_VG = positionStartY;
	}


	protected void initiateObjects() {
		iconWall = new ImageIcon("images/background.jpg");
		
		iconGround = new ImageIcon("images/background2.jpg");
	}

	protected void paintObject(Graphics g) {
		iconWall.paintIcon(this, g, positionX.intValue(), positionY.intValue());
		iconWall.paintIcon(this, g, positionX.intValue() + iconWall.getIconWidth(),
				positionY.intValue());
		iconWall.paintIcon(this, g, positionX.intValue() + iconWall.getIconWidth()
				+ iconWall.getIconWidth(), positionY.intValue());

		iconGround.paintIcon(this, g, positionX_VG.intValue(), positionY_VG.intValue() + 300);

		iconGround.paintIcon(this, g, positionX_VG.intValue() + iconGround.getIconWidth(),
				positionY_VG.intValue() + 300);

		iconGround.paintIcon(this, g, positionX_VG.intValue() + iconGround.getIconWidth()
				+ iconGround.getIconWidth(), positionY_VG.intValue() + 300);

		if (positionX < (0 - iconGround.getIconWidth()))
		{
			positionX = positionStartX;
			positionX_VG = positionStartX;
		}
	}
	
	@Override
	public void drawObjects(Graphics g) {
		positionX = positionX + stepX;
		positionY = positionY + stepY;
		// Foreground is slower in X
		positionX_VG = positionX_VG + stepX / 2;
		positionY_VG = positionY;
		paintObject(g);

	}

}
