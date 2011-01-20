package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.Format;

import de.hsfulda.softcomputing.fuzbahn.TrackElement;
import de.hsfulda.softcomputing.fuzbahn.Train;

public class TrainRenderer
extends TrackElementRenderer{
	int HEIGHT = 10;
	public void renderElement(TrackPanel p, TrackElement e, Graphics2D g, Color c){
		int x = (int) e.getPosition();
		int l = (int) e.getLength();
		int h = HEIGHT;
		int y = TrackPanel.GROUND;
		g.setColor(c);
		int k = 3;
		/*g.fillRect(x, y, l, h);
		g.fillPolygon(new int[]{x+l, x+l+3, x+l}, new int[]{y, y+h, y+h}, 3);
		g.fillPolygon(new int[]{x, x-3, x}, new int[]{y, y+h, y+h}, 3);*/
		g.fillPolygon(new int[]{x, x + l, x + l - k, x + k}, new int[]{y, y, y - h, y - h}, 4);
		
		g.drawString(e.getName(), x, y -h - 4);
		String speed = (int) (e.getSpeed() * Train.MS_KMH) + " km/h";
		g.drawString(speed, x, y - h - 16);
	}
}
