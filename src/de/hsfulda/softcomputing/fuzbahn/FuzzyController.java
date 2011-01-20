package de.hsfulda.softcomputing.fuzbahn;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyController {
	private FIS fis;
	private Train train;

	private Map<String, FuzzyValue> values;
	
	public FuzzyController(Train train) throws FuzzyUnavailableException {
		// this(train, "/fcl/Fuzzy-Project.fcl");
		this(train, "/fuz-controller.fcl");
	}

	public FuzzyController(Train train, String fisFile)
			throws FuzzyUnavailableException {
		this.train = train;

		fis = FIS.load(getClass().getResourceAsStream(fisFile), true);
		if (fis == null) {
			throw new FuzzyUnavailableException();
		}

		values = new HashMap<String, FuzzyValue>();
		addValue(new FuzzyValue.Speed());
		addValue(new FuzzyValue.TargetSpeed());
		addValue(new FuzzyValue.TargetDistance());
		addValue(new FuzzyValue.Brake());
		addValue(new FuzzyValue.Power());
	}

	public void addValue(FuzzyValue f) {
		f.setVariable(getFis().getVariable(f.getName()));
		f.setTrain(train);
		this.values.put(f.getName(), f);
	}

	public Map<String, FuzzyValue> getValues() {
		return values;
	}

	public FIS getFis() {
		return fis;
	}
	public Train getTrain(){
		return train;
	}
	public synchronized void update() {
		for (FuzzyValue v : getValues().values()) {
			if (v.isInput()) {
				v.update();
			}
		}

		fis.evaluate();

		for (FuzzyValue v : getValues().values()) {
			if (v.isOutput()) {
				v.update();
			}
		}
	}
}