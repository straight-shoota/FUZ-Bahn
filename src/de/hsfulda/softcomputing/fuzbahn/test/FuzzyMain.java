package de.hsfulda.softcomputing.fuzbahn.test;

import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import de.hsfulda.softcomputing.fuzbahn.*;

public class FuzzyMain
extends AbstractTest {
	FuzzyController fc;
	
	FuzzyMain(){
		track = new Track(10000);
	}

	protected void init() {
		Train t = getDefaultPrototype().createTrain();
		t.setSpeed(10);
		t.update(0);
		track.add(t);
		
		try {
			fc = new FuzzyController(t);
			showCharts(fc);
			/*for(FunctionBlock b : fc.getFis()){
				System.out.println(b.getName());
				for(Variable v : b.getVariables().values()){
					System.out.println("\t" + v.getName());
				}
			}*/
		}catch(FuzzyUnavailableException exc){
			exc.printStackTrace();
		}
	}

	protected void doStep(double deltaT) {
		for(Train t : track.getTrains()) {
			fc.update();
			t.update(deltaT);
			System.out.println(t);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FuzzyMain().start();
	}

}
