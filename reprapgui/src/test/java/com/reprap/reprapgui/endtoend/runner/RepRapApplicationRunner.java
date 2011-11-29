package com.reprap.reprapgui.endtoend.runner;

import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.endtoend.driver.PrinterConnectDriver;

/**
 * The RepRapApplicationRunner is an end-to-end test runner which starts up the
 * application for us, starts a {@link PrinterConnectDriver} which will look at the
 * GUI for changes specific to each test and be called from the actual @Test
 * class itself to run the driver verification methods.
 */
public class RepRapApplicationRunner {
	
	public RepRapApplicationRunner() {
		driver = new PrinterConnectDriver();
	}

	private final PrinterConnectDriver driver;

	public void showMainWindowAppears() {
		driver.hasTitle(StaticConstants.APPLICATION_NAME);
	}

	public void pressConnectButton() {
		driver.connectButton().click();
	}

	public void canAddPortPathToTextField() {
		driver.hasTextFieldAndValue(StaticConstants.PORT_TEXT_FIELD, "/tty/usd-A9008FBW");
	}

	public void canAddBaudSpeedToTextField() {
		driver.hasTextFieldAndValue(StaticConstants.BAUD_SPEED_TEXT_FIELD,"192000");
	}
	
	public void canClearBothTextFields() {
		driver.clearTextField(StaticConstants.PORT_TEXT_FIELD);
		driver.clearTextField(StaticConstants.BAUD_SPEED_TEXT_FIELD);
	}

	public void showsPrinterIsConnected() {
		driver.hasLabel(StaticConstants.CONNECT_STATE_LABEL, MessageConstants.STATE_CONNECTED);
	}

	public void showsPrinterIsDisconnected() {
		driver.hasLabel(StaticConstants.CONNECT_STATE_LABEL, MessageConstants.STATE_DISCONNECTED);
	}

	public void cantAddNonNumericValuesToBaudSpeedTextField() {
		driver.hasTextFieldAndEmpty(StaticConstants.BAUD_SPEED_TEXT_FIELD,"ABCDEFGHIJ XYZ");
	}

	public void hasEventLogTextArea() {
		driver.hasTextAreaForDisplayingEventLogs(StaticConstants.EVENT_LOG_TEXT_AREA);
	}

	public void dispose() {
		driver.dispose();
	}

}
