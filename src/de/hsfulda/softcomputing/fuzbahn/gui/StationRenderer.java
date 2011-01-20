package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import de.hsfulda.softcomputing.fuzbahn.TrackElement;

public class StationRenderer extends TrackElementRenderer {

	public void renderElement(TrackPanel p, TrackElement e, Graphics2D g, Color c){
		int x = (int) e.getPosition();
		int l = 40;
		int h = 3;
		int y = TrackPanel.GROUND - h;
		g.setColor(c);
		g.fillRect(x, y, l, h);
		g.drawString(e.getName(), x, y + STRING_SPACING);
	}
}
