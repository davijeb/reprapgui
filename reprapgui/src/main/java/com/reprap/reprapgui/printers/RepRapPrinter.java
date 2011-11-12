package com.reprap.reprapgui.printers;

import java.util.Observable;

import com.reprap.reprapgui.config.PrinterStatus;
import com.reprap.reprapgui.printers.exceptions.PrinterConnectionException;


/**
 * The RepRapPrinter is a concrete implementation of a Cartesian printer
 */
public class RepRapPrinter extends Observable  {
	
	/**
	 * Connect the the main electronic board
	 * @return true if connected, false if not
	 */
	public boolean connect() {
		try {
			setConnectionState(true);
			return true;
		} catch(final Exception t) {
			setConnectionState(false);
			throw new PrinterConnectionException(t);
		}
	}

	/**
	 * Set the connection state for the printer.
	 * 
	 * @param state is a boolean flag, true=ok, false=not ok
	 */
	public void setConnectionState(final boolean state) {
		setChanged();
		if(state) {
			notifyObservers(PrinterStatus.Connected);
		} else {
			notifyObservers(PrinterStatus.ConnectionFailure);
		}
	}
}
