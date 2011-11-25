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
import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.view.frames.FabricatorWindow;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * This class defined a number of methods used to automatically
 * interact with the actual GUI. It uses the WindowLicker framework
 * which allows a number of assertions/tests to be carried out while
 * actually interacting with the graphical component.
 */
@SuppressWarnings("unchecked")
public class AxisConnectDriver extends JFrameDriver {

	/**
	 * C'tor which checks for the existence of a single {@link FabricatorWindow}
	 */
	public AxisConnectDriver() {
		super(new GesturePerformer(), JFrameDriver.topLevelFrame(
				named(StaticConstants.APPLICATION_NAME), showingOnScreen()),
				new AWTEventQueueProber(100,2000));
	}

	/**
	 * Check GUI label value against the {@link PrinterStatus} value.
	 */
	public void showApplicationStatus() {
		new JLabelDriver(this,
				named(FabricatorPrintPanel.CONNECTION_LABEL_NAME))
				.hasText(equalTo(MessageConstants.STATE_CONNECTED));
	}

	/**
	 * Get the printer connect/disconnect button
	 * 
	 * @return button pointer
	 */
	public JButtonDriver connectButton() {
		return new JButtonDriver(this, JButton.class,
				named(StaticConstants.CONNECT_BUTTON), enabled());
	}

	/**
	 * Has the frame opened an option window?
	 */
	public void hasConnectMessageDialogue() {
		new JOptionPaneDriver(this, JOptionPane.class).clickOK();
	}

	/**
	 * Can we find a text label with the <code>textField</code> name and does
	 * the field contain the value <code>textToEnter</code>
	 * @param textfield the {@link JTextField}
	 * @param textToEnter the text value of the field
	 */
	public void hasTextFieldAndValue(final String textfield, final String textToEnter) {

		final JTextFieldDriver driver = getTextFieldDriver(textfield);
		driver.focusWithMouse();
		driver.typeText(textToEnter);
		driver.hasText(equalTo(textToEnter));
	}
	
	/**
	 * Can we find a text label with the <code>textField</code> name and does
	 * the field contain nothing, even though we attempted to enter 
	 * the value <code>textToEnter</code>
	 * @param textfield the {@link JTextField}
	 * @param textToEnter the value we tried to enter.
	 */
	public void hasTextFieldAndEmpty(final String textfield, final String textToEnter) {
		
		final JTextFieldDriver driver = getTextFieldDriver(textfield);
		driver.focusWithMouse();
		driver.typeText(textToEnter);
		driver.isEmpty();
	}

	/**
	 * Clear the entire contents from a text field
	 * @param textfield the {@link JTextField}
	 */
	public void clearTextField(final String textfield) {
		
		final JTextFieldDriver driver = getTextFieldDriver(textfield);
		driver.focusWithMouse();
		driver.clearText();
		driver.isEmpty();
	}

	/**
	 * Utility method to get the text field driver given the text field
	 * name.
	 * @param textfield the text field name
	 * @return the driver for the text field
	 */
	private JTextFieldDriver getTextFieldDriver(final String textfield) {
		return new JTextFieldDriver(this, JTextField.class, named(textfield));
	}

	/**
	 * Can we find a component with a label named <code>name</code>
	 * having the value <code>text</code>.
	 * @param name the label name
	 * @param text the label text value
	 */
	public void hasLabel(final String name, final String text) {
		new JLabelDriver(this, named(name)).hasText(equalTo(text));
	}

	public void pressAxisDecrementButton() {
		new JButtonDriver(this, JButton.class,
				named(StaticConstants.DECREMENT_BUTTON), enabled()).click();
		
	}

	public void pressAxisIncrementButton() {
		new JButtonDriver(this, JButton.class,
				named(StaticConstants.INCREMENT_BUTTON), enabled()).click();
		
	}

	public void pressMovementButton(final Axis axis) {
		new JButtonDriver(this, JButton.class,
				named(axis.toString()), enabled(), showingOnScreen()).click();
	}

}
