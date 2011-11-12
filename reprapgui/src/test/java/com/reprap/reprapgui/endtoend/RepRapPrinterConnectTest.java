package com.reprap.reprapgui.endtoend;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.reprap.reprapgui.printers.RepRapPrinter;
import com.reprap.reprapgui.printers.exceptions.PrinterConnectionException;

/**
 * The RepRapPrinterConnectTest checks we can/can't connect to a printer and the GUI
 * displays the correct information to the user.
 * 
 * This uses the Mockito framework to add a failure event to the printer connection
 * test.
 */
public class RepRapPrinterConnectTest {

	@Spy
	private static RepRapPrinter printer;
	private static RepRapApplicationRunner application;

	@BeforeClass
	public static void init() {
		printer = Mockito.spy(new RepRapPrinter());
		application = new RepRapApplicationRunner(printer);
	}

	@Test
	public void connectToPrinterSuccessfully() {
		application.connectToPrinter();
		application.showPrinterConnected();
	}

	@Test
	public void connectToPrinterUnSuccessfully()  {

		Mockito.doThrow(new PrinterConnectionException()).when(printer).connect();
		
		try {
			application.connectToPrinter();
		} catch (final PrinterConnectionException e) {
			printer.setConnectionState(false);
			application.showPrinterConnectionFailure();
		}
	}
}
