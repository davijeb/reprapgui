package com.reprap.reprapgui.printers.exceptions;

/**
 * The PrinterConnectionException identifies a specific
 * type of exception relating to issues connecting to
 * the RepRap printer.
 */
public class PrinterConnectionException extends RuntimeException {
	
	private static final long serialVersionUID = 3603810714864317284L;

	public PrinterConnectionException() {
		super();
	}

	public PrinterConnectionException(final Throwable t) {
		super(t);
	}
}
