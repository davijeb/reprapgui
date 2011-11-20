package com.reprap.reprapgui.endtoend;

import static org.hamcrest.core.IsEqual.equalTo;

import com.reprap.reprapgui.view.config.PrinterButton;
import com.reprap.reprapgui.view.frames.FabricatorWindow;

/**
 * The RepRapApplicationRunner is an end-to-end test runner which starts up the
 * application for us, starts a {@link RepRapDriver} which will look at the
 * GUI for changes specific to each test and be called from the actual @Test
 * class itself to run the driver verification methods.
 */
public class RepRapApplicationRunner {

	private final RepRapDriver driver = new RepRapDriver();

	public void showMainWindowAppears() {
		driver.hasTitle(FabricatorWindow.MAIN_WINDOW_NAME);
	}

	public void showMainWindowHasAButtonNamedConnect() {
		driver.connectButton().hasText(equalTo(PrinterButton.Connect.toString()));
	}

	public void pressConnectButton() {
		driver.connectButton().click();
	}

	public void addPortPathToTextField() {
		driver.hasTextField("portentry", "/tty/usd-A9008FBW");
	}

	public void addBaudSpeedToTextField() {
		driver.hasTextField("baudspeed","192000");
	}
	
	public void clearTextFields() {
		driver.clearTextField("portentry");
		driver.clearTextField("baudspeed");
	}

	public void showsWarningDialogue() {
		driver.hasWarningDialogue();
	}

}
