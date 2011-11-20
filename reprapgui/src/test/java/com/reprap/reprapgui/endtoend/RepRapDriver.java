package com.reprap.reprapgui.endtoend;

import static org.hamcrest.Matchers.equalTo;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JButtonDriver;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.driver.JOptionPaneDriver;
import com.objogate.wl.swing.driver.JTextFieldDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import com.reprap.reprapgui.view.config.PrinterStatus;
import com.reprap.reprapgui.view.frames.FabricatorWindow;

/**
 * The RepRapGUIDriver is part of the WindowLicker framework which enables GUIs
 * to be included as part of the test program.
 */
@SuppressWarnings("unchecked")
public class RepRapDriver extends JFrameDriver {

	/**
	 * C'tor which checks for the existence of a single {@link FabricatorWindow}
	 */
	public RepRapDriver() {
		super(new GesturePerformer(), JFrameDriver.topLevelFrame(
				named(FabricatorWindow.MAIN_WINDOW_NAME), showingOnScreen()),
				new AWTEventQueueProber(1000, 200));
	}

	/**
	 * Check GUI label value against the {@link PrinterStatus} value.
	 * 
	 * @param connected the printer status label we are looking for
	 */
	public void showApplicationStatus(final PrinterStatus connected) {
		new JLabelDriver(this, named(FabricatorWindow.CONNECTION_LABEL_NAME))
				.hasText(equalTo(connected.toString()));
	}

	/**
	 * Get the printer connect/disconnect button
	 * @return button pointer
	 */
	public JButtonDriver connectButton() {
		return new JButtonDriver(this, JButton.class,
				named(FabricatorWindow.CONNECTION_BUTTON_NAME));
	}
	
	public void hasConnectMessageDialogue() {
		new JOptionPaneDriver(this, JOptionPane.class).clickOK();
	}

	public void hasTextField(final String textfield, final String textToEnter) {
		
		final JTextFieldDriver driver = getTextFieldDriver(textfield);
		driver.focusWithMouse();
        driver.typeText(textToEnter);
        driver.hasText(equalTo(textToEnter));
	}

	public void clearTextField(final String textfield) {
		final JTextFieldDriver driver = getTextFieldDriver(textfield);
		driver.focusWithMouse();
        driver.clearText();
        driver.isEmpty();
	}
	
	public JTextFieldDriver getTextFieldDriver(final String textfield) {
		return new JTextFieldDriver(this, JTextField.class, named(textfield));
	}
	
	JOptionPaneDriver od = null;

	public void hasWarningDialogue() {
		od = new JOptionPaneDriver(this, JOptionPane.class, showingOnScreen());
		od.clickOK();
	}

}
