package de.hsfulda.softcomputing.fuzbahn;

import java.util.EventListener;

public interface FuzzyValueListener extends EventListener {
	public void valueChanged(FuzzyValue f);
}
