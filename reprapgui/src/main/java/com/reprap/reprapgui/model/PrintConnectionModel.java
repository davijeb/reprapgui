package com.reprap.reprapgui.model;

import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;

public class PrintConnectionModel extends AbstractModel implements PrintConnection {
	
	private String port;
	private int baudSpeed;
	private boolean printerState = false;
	
	public PrintConnectionModel(){}

	public PrintConnectionModel(final String port, final int speed) {
		this.port = port;
		this.baudSpeed = speed;
	}

	@Override
	public String getPort() {
		return port;
	}
	public void setPort(final String port) {
		this.port = port;
		stateChange();
	}

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
