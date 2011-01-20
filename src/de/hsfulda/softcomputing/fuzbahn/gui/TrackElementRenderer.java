package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import de.hsfulda.softcomputing.fuzbahn.TrackElement;


public class TrackElementRenderer
extends JComponent {
	public static int STRING_SPACING = 30;
	public void renderElement(TrackPanel p, TrackElement e, Graphics2D g, Color c){
		int x = (int) e.getPosition();
		int l = (int) e.getLength();
		int h = 5;
		int y = TrackPanel.GROUND - h;
		g.setColor(c);
		g.fillRect(x, y, l, h);
	}
}
