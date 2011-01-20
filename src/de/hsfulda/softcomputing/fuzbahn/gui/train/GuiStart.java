package de.hsfulda.softcomputing.fuzbahn.gui.train;

import javax.swing.SwingUtilities;


/**
 * @author Daniel Buth
 *
 */
public class GuiStart  {

	private static final long serialVersionUID = 5600638819720019418L;
	GuiCreator myGui;
	
	/* Anonymous Constructor 
	 * 
	 */
	public GuiStart () {
		myGui = new GuiCreator();
	}
	
	public void OpenGui()
	{
		// Fuehre den obigen Quellcode im Event-Dispatch-Thread aus
		SwingUtilities.invokeLater(myGui);
		
	}

	
	public static void main(String[] args) {
		GuiStart gui = new GuiStart();
		gui.OpenGui();
	}
}
