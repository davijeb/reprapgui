package com.reprap.reprapgui;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.reprap.reprapgui.gui.RepRapWindow;
import com.reprap.reprapgui.printers.RepRapPrinter;

/**
 * The Main class is the actual application itself. It holds onto the 
 * {@link RepRapWindow} user interface and starts this when the <code>main</code> 
 * method is called.
 */
public class Main {
	
	public static final String MAIN_WINDOW_NAME = "RepRapGUI";
	
	private static RepRapWindow window;
	
	public Main() throws Exception {
		startUserInterface();
	}
	
	public static void main(final RepRapPrinter printer) throws Exception {
		new Main();
		printer.addObserver(window); // the window will want to know about printer state changes
	}

	private void startUserInterface() throws InterruptedException, InvocationTargetException {
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				window = new RepRapWindow();
			}
		});
	}
	
	public static void main(final String[] args) throws Exception {
		new Main();
		final RepRapPrinter p = new RepRapPrinter();
		p.addObserver(window);
		
		p.connect();
	}

}
