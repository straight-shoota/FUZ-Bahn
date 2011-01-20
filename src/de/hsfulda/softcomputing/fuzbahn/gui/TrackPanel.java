package de.hsfulda.softcomputing.fuzbahn.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import de.hsfulda.softcomputing.fuzbahn.SpeedLimit;
import de.hsfulda.softcomputing.fuzbahn.StaticTrackElement;
import de.hsfulda.softcomputing.fuzbahn.Station;
import de.hsfulda.softcomputing.fuzbahn.Track;
import de.hsfulda.softcomputing.fuzbahn.TrackElement;
import de.hsfulda.softcomputing.fuzbahn.TrackPostitionListener;
import de.hsfulda.softcomputing.fuzbahn.Train;

public class TrackPanel extends JPanel
implements TrackPostitionListener{
	private Train train;
	private Track track;
	
	private Map<TrackElement, Color> trainColors = new HashMap<TrackElement, Color>();
	private Map<Class<? extends TrackElement>, TrackElementRenderer> renderers = new HashMap<Class<? extends TrackElement>, TrackElementRenderer>();
	
	public static int GROUND = 50;
	public static int TICK_SPACING = 100;
	public static int TICK_SIZE = 5;
	
	List<Train> trains = new ArrayList<Train>();
	List<StaticTrackElement> statics = new ArrayList<StaticTrackElement>();
	
	public TrackPanel(Train train){
		this.train = train;
		this.track = train.getTrack();
		track.addListener(this);
		
		setBackground(Color.DARK_GRAY);
		
		renderers.put(Station.class, new StationRenderer());
		renderers.put(TrackElement.class, new TrackElementRenderer());
		renderers.put(Train.class, new TrainRenderer());
		renderers.put(SpeedLimit.class, new SpeedLimitRenderer());

		Color main = Color.RED;
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLUE);
		colors.add(Color.GREEN);
		colors.add(Color.ORANGE);
		colors.add(Color.CYAN);
		colors.add(Color.PINK);
		colors.add(Color.YELLOW);
		colors.add(Color.WHITE);
		Iterator<Color> c = colors.iterator();
		
		for(TrackElement e : track.getElements()) {
			if(! c.hasNext()){
				c = colors.iterator();
			}
			if(e == train){
				trainColors.put(e, main);
			}else{
				trainColors.put(e, c.next());
			}
			if(e instanceof Train){
				trains.add( (Train) e);
			}else{
				statics.add( (StaticTrackElement) e);
			}
		}
		
	}
	protected TrackElementRenderer getRenderer(TrackElement e){
		if(renderers.containsKey(e.getClass())){
			return renderers.get(e.getClass());
		}
		return renderers.get(TrackElement.class);
	}
	protected void paintChildren(Graphics g){
		g.setColor(Color.BLACK);
		g.drawLine(0, GROUND, getWidth(), GROUND);
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(getWidth()/2  -  train.getPosition(), 0D);
		//paint();
		for(Train e : trains) {
			getRenderer(e).renderElement(this, e, g2, trainColors.get(e));
		}
		for(StaticTrackElement e : statics) {
			getRenderer(e).renderElement(this, e,  g2, trainColors.get(e));
		}
		renderTrack(g2);
	}
	protected void renderTrack(Graphics2D g){
		g.setColor(Color.BLACK);
		int pos = (int) train.getPosition();
		int start = (int) (pos - (getWidth() / 2D));
		
		int numTicks = getWidth() / TICK_SPACING;
		numTicks++;
		int level = GROUND;
		int level2 = level + TICK_SIZE;
		int textLevel = level + 12;
		int x, m;
		start -= pos % TICK_SPACING;
		for(int i = 0; i <= numTicks; i++){
			x = start + i * TICK_SPACING;
			m = (int) Math.round((start + i * TICK_SPACING) / TICK_SPACING) * TICK_SPACING;
			if(m < 0){
				m += TICK_SPACING;
			}
			g.drawLine(x, level, x, level2);
			g.drawString(m + " m", x + 2, textLevel);
		}
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(1000, 100);
	}
	@Override
	public void trackPostitionsUpdated(Track track) {
		repaint();
	}
}
