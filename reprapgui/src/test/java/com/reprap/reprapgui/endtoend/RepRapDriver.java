package com.reprap.reprapgui.endtoend;

import static org.hamcrest.core.IsEqual.equalTo;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.driver.JOptionPaneDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import com.reprap.reprapgui.config.PrinterStatus;
import com.reprap.reprapgui.gui.RepRapWindow;

/**
 * The RepRapGUIDriver is part of the WindowLicker framework which enables GUIs
 * to be included as part of the test program.
 */
public class RepRapDriver extends JFrameDriver {

	/**
	 * C'tor which checks for the existence of a single {@link RepRapWindow}
	 */
	@SuppressWarnings("unchecked")
	public RepRapDriver() {
		super(new GesturePerformer(), JFrameDriver.topLevelFrame(
				named(RepRapWindow.MAIN_WINDOW_NAME), showingOnScreen()),
				new AWTEventQueueProber(1000, 1000));
	}

	/**
	 * Check GUI label value against the {@link PrinterStatus} value.
	 * 
	 * @param connected
	 *            the printer status label we are looking for
	 */
	@SuppressWarnings("unchecked")
	public void showApplicationStatus(final PrinterStatus connected) {
		new JLabelDriver(this, named(RepRapWindow.CONNECTION_LABEL_NAME))
				.hasText(equalTo(connected.toString()));
	}

	/**
	 * Get the printer connect/disconnect button
	 * @return button pointer
	 */
	@SuppressWarnings("unchecked")
	public JButtonDriver connectButton() {
		return new JButtonDriver(this, JButton.class,
				named(RepRapWindow.CONNECTION_BUTTON_NAME));
	}
	
	public void hasConnectMessageDialogue() {
		new JOptionPaneDriver(this, JOptionPane.class).clickOK();
	}

}
