package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.hsfulda.softcomputing.fuzbahn.TrackElement;

public class TrackElementCellRenderer extends JLabel implements
		ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		TrackElement t = (TrackElement) value;
		DecimalFormat f = new DecimalFormat("####0");

		setText(index + ". " + f.format(t.getPosition()) + " m  " + t.getName());

		return this;
	}

}
