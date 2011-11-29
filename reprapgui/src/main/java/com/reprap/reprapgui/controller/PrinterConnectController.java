package com.reprap.reprapgui.controller;

import gnu.io.PortInUseException;

import java.io.IOException;
import java.util.TooManyListenersException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.communication.SerialPortConnector;
import com.reprap.reprapgui.controller.interfaces.PrinterController;
import com.reprap.reprapgui.controller.utils.MessageConstants;

/**
 * This class provides as print control functionality. It includes
 * the ability to connect to the printer and disconnect to the printer.
 * 
 * It also allows the model properties to be set directly using both these
 * methods and the parameter changed method.
 */
public class PrinterConnectController extends AbstractController implements PrinterController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final SerialPortConnector connector;

	@Autowired
	public PrinterConnectController(final SerialPortConnector connector) {
		this.connector = connector;
	}

	/**
	 * Connect to the printer and set the model property to be connected.
	 * @throws IOException 
	 * @throws TooManyListenersException 
	 * @throws PortInUseException 
	 */
	@Override
	public void connect()  {
		
		try {
			connector.open(0, this.getClass().getSimpleName(), 1000);
			setModelProperty(MessageConstants.STATE_CONNECTED,true);
		} catch (final Exception e) {
			logger.error("Unable to connect to board", e);
			setModelProperty(MessageConstants.STATE_CONNECTED,false);
		} 
	}

	/**
	 * Disconnect from the printer and set the model property to be disconnected.
	 * @throws Exception 
	 */
	@Override
	public void disconnect() {
		try {
			connector.close();
			setModelProperty(MessageConstants.STATE_CONNECTED, false);
		} catch (final IOException e) {
			setModelProperty(MessageConstants.STATE_EVENT, "Could not disconnect: " + e);
			setModelProperty(MessageConstants.STATE_CONNECTED,false);
			logger.error("Unable to disconnect from the board", e);
		}
	}

	/**
	 * The printer parameters have been modified on the screen
	 * @param name the name of the parameter
	 * @param value the new value of the parameter
	 */
	@Override
	public void printParametersChanged(final String name, final Object value) {
		setModelProperty(name,value);
	}
}