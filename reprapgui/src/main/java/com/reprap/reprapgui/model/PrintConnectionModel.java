package com.reprap.reprapgui.model;

import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;

/**
 * This class is a model that carries the information required to connect
 * to the printer. It holds the <code>port</code> which defined the serial
 * port, the <code>baudSpeed</code> which sets the connection speed and the 
 * current printer state flag (either connected (true) or not (false)
 */
public class PrintConnectionModel extends AbstractModel implements PrintConnection {
	
	private String port;
	private int baudSpeed;
	private boolean printerState = false;
	
	public PrintConnectionModel(){}

	/**
	 * Immutable c'tor with the port and baud speed
	 * @param port the serial port e.g. /tty/usb.900A8FBW
	 * @param speed
	 */
	public PrintConnectionModel(final String port, final int speed) {
		this.port = port;
		this.baudSpeed = speed;
	}

	/*
	 * (non-Javadoc)
	 * @see com.reprap.reprapgui.model.PrintConnection#getPort()
	 */
	@Override
	public String getPort() {
		return port;
	}
	public void setPort(final String port) {
		this.port = port;
		stateChange();
	}

	/*
	 * (non-Javadoc)
	 * @see com.reprap.reprapgui.model.PrintConnection#getBaudSpeed()
	 */
	@Override
	public int getBaudSpeed() {
		return baudSpeed;
	}
	
	public void setBaudSpeed(final Integer baudSpeed) {
		this.baudSpeed = baudSpeed;
		stateChange();
	}

	public boolean isPrinterState() {
		return printerState;
	}

	public void setConnected(final Boolean printerState) {
		firePropertyChange(MessageConstants.STATE_CONNECTED, this.printerState, printerState);
		this.printerState = printerState;
	}
	
	private void stateChange() {
		if(port != null && !port.equals("") && baudSpeed > 0) {
			firePropertyChange(StaticConstants.ALLOW_CONNECT, false, true);
		} else {
			firePropertyChange(StaticConstants.ALLOW_CONNECT, true, false);
		}
	}
}
