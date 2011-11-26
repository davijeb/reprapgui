package com.reprap.reprapgui.controller;

import com.reprap.reprapgui.controller.utils.MessageConstants;

/**
 * This class provides as print control functionality. It includes
 * the ability to connect to the printer and disconnect to the printer.
 * 
 * It also allows the model properties to be set directly using both these
 * methods and the parameter changed method.
 */
public class PrinterController extends AbstractController {

	/**
	 * Connect to the printer and set the model property to be connected.
	 */
	public void connect() {
		setModelProperty(MessageConstants.STATE_CONNECTED,true);
	}

	/**
	 * Disconnect from the printer and set the model property to be disconnected.
	 */
	public void disconnect() {
		setModelProperty(MessageConstants.STATE_CONNECTED, false);
	}

	/**
	 * The printer parameters have been modified on the screen
	 * @param name the name of the parameter
	 * @param value the new value of the parameter
	 */
	public void printParametersChanged(final String name, final Object value) {
		setModelProperty(name,value);
	}
}