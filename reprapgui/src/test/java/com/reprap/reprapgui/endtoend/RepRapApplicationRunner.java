package com.reprap.reprapgui.endtoend;


import com.reprap.reprapgui.Main;
import com.reprap.reprapgui.config.PrinterStatus;
import com.reprap.reprapgui.printers.RepRapPrinter;
import com.reprap.reprapgui.printers.exceptions.PrinterConnectionException;

/**
 * The RepRapApplicationRunner is an end-to-end test runner
 * which starts up the application for us, starts a {@link RepRapGUIDriver}
 * which will look at the GUI for changes specific to each test and be called
 * from the actual @Test class itself to run the driver verification methods.
 */
public class RepRapApplicationRunner {
	
	final RepRapPrinter printer;
	
	private final RepRapGUIDriver driver = new RepRapGUIDriver();
	
	public RepRapApplicationRunner(final RepRapPrinter printer) {
		try {
			Main.main(printer);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		
		this.printer=printer;
	}

	public void connectToPrinter() throws PrinterConnectionException {
		printer.connect();
	}

	public void showPrinterConnected() {
		driver.showApplicationStatus(PrinterStatus.Connected);
	}

	public void showPrinterConnectionFailure() {
		driver.showApplicationStatus(PrinterStatus.ConnectionFailure);
	}

}
