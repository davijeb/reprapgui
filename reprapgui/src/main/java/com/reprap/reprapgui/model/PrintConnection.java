package com.reprap.reprapgui.model;

public interface PrintConnection {

	/**
	 * Get the serial port path
	 * @return the port path string
	 */
	String getPort();
	
	/**
	 * Get the serial connection speed
	 * @return the speed
	 */
	int getBaudSpeed();

}
