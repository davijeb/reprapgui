package com.reprap.reprapgui.controller.communication;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public interface SerialPortConnector {

	/**
	 * Open the connection
	 * @param index the index of the particular {@link CommPortIdentifier}
	 * @param name the connection name
	 * @param timeout the timeout is milli-seconds
	 * @return a communications port
	 * @throws PortInUseException
	 * @throws TooManyListenersException 
	 * @throws IOException 
	 */
	CommPort open(int index, String name, int timeout) throws PortInUseException, TooManyListenersException, IOException;
	
	/**
	 * Close the serial port
	 * @throws IOException 
	 */
	void close() throws IOException;

	/**
	 * Get the IO streams
	 * @throws IOException 
	 */
	InputStream getInputStream() throws IOException;
	OutputStream getOutputStream() throws IOException;

	void write(int... bytes) throws IOException;

}
