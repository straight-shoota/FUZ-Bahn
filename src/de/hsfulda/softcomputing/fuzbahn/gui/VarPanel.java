package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.dial.*;
import org.jfree.chart.plot.dial.DialPointer.Pointer;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import de.hsfulda.softcomputing.fuzbahn.FuzzyValue;
import de.hsfulda.softcomputing.fuzbahn.FuzzyValueListener;

import net.sourceforge.jFuzzyLogic.rule.Variable;

public class VarPanel extends JPanel
implements FuzzyValueListener
{
	FuzzyValue value;
	DefaultValueDataset data;
	ChartPanel chartPanel;

	public VarPanel(FuzzyValue v){
		this.value = v;
		value.addListener(this);
		
		setLayout(new BorderLayout());

		chartPanel = new ChartPanel(getVariable().chart(false));
		add(chartPanel, BorderLayout.CENTER);
		
		data = new DefaultValueDataset(getVariable().getValue());
		
		ChartPanel var = new ChartPanel(createDialChart());
		var.setPreferredSize(new Dimension(160, 160));
		JPanel p = new JPanel();
		p.add(var);
		add(p, BorderLayout.EAST);
	}
	public Variable getVariable(){
		return getValue().getVariable();
	}
	public FuzzyValue getValue() {
		return value;
	}
	
	public JFreeChart createDialChart() {
		JFreeChart chart;
		
		DialPlot dialplot = new DialPlot(data);
        //plot.setView(0.0D, 0.0D, 1.0D, 1.0D);
		
		StandardDialFrame standarddialframe = new StandardDialFrame();
        standarddialframe.setBackgroundPaint(Color.lightGray);
        standarddialframe.setForegroundPaint(Color.darkGray);
        dialplot.setDialFrame(standarddialframe);
        
        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
        DialBackground dialbackground = new DialBackground(gradientpaint);
        dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
        dialplot.setBackground(dialbackground);
        
        DialTextAnnotation dialtextannotation = new DialTextAnnotation(getValue().getName());
        //dialtextannotation.setFont(new Font("Dialog", 1, 14));
        dialtextannotation.setRadius(0.69999999999999996D);
        dialplot.addLayer(dialtextannotation);
        
        DialValueIndicator dialvalueindicator = new DialValueIndicator();
        //dialvalueindicator.setFont(new Font("Dialog", 0, 10));
        dialvalueindicator.setOutlinePaint(Color.darkGray);
        dialvalueindicator.setRadius(0.59999999999999998D);
        dialvalueindicator.setAngle(-103D);
        dialplot.addLayer(dialvalueindicator);

        StandardDialScale standarddialscale = new StandardDialScale(getValue().getMin(), getValue().getMax(), -120D, -300D, getValue().getStepSize(), 4);
        standarddialscale.setTickRadius(0.88D);
        standarddialscale.setTickLabelOffset(0.14999999999999999D);
        //standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
        dialplot.addScale(0, standarddialscale);


        dialplot.mapDatasetToScale(1, 1);
        
        /*StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
        standarddialrange.setScaleIndex(1);
        standarddialrange.setInnerRadius(0.58999999999999997D);
        standarddialrange.setOuterRadius(0.58999999999999997D);
        dialplot.addLayer(standarddialrange);*/
        
        /*org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
        pin.setRadius(0.55000000000000004D);
        dialplot.addPointer(pin);*/
        Pointer pointer = new Pointer(0);
        dialplot.addPointer(pointer);
        /*DialCap dialcap = new DialCap();
        dialcap.setRadius(0.10000000000000001D);
        dialplot.setCap(dialcap);*/


        chart = new JFreeChart(dialplot);
		
		return chart;
	}
	@Override
	public void valueChanged(FuzzyValue f) {
		data.setValue(value.getVariable().getValue());
		
		chartPanel.setChart(getVariable().chart(false));
		
		repaint();
	}
}
