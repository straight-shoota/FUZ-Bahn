/**
 * 
 */
package de.hsfulda.softcomputing.fuzbahn.gui.train;

/**
 * @author Daniel Buth
 * 
 */
class GuiCreator implements Runnable {

	public Runnable guiCreator;

	public GuiCreator() {
		super();
	}

	// Verpacke den auszufuehrenden Quellcode in ein eigenes
	// Runnable-Objekt, um diesen nachher im Event Dispatching
	// Thread ausfuehren zu kuennen
	public void run() {

		GuiWindow window = new GuiWindow();
		window.init();
	}

}
