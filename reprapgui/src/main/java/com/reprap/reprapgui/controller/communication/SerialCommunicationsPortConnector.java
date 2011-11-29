package com.reprap.reprapgui.controller.communication;

import gnu.io.CommPort;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * This class loops over the serial ports it finds and attempts to open a {@link CommPort}.
 */
public class SerialCommunicationsPortConnector implements SerialPortConnector, SerialPortEventListener {
	
	private       SerialPort        commPort;
	private final SerialPortLocator portLocator;
	private final SerialEventLog    eventLog;
	
	private InputStream			    inputStream;
	private OutputStream		    outputStream;

	/**
	 * 
	 * @param serialEventLog
	 */
	public SerialCommunicationsPortConnector(final SerialEventLog serialEventLog) {
		this.portLocator = new SerialCommunicationsPortLocator();
		this.eventLog = serialEventLog;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.reprap.reprapgui.controller.communication.SerialPortConnector#open(int, java.lang.String, int)
	 */
	@Override
	public CommPort open(final int index,final String name, final int timeout) throws PortInUseException, TooManyListenersException, IOException {
		commPort = (SerialPort) portLocator.locatePorts().get(index).open(name, timeout);
		
		inputStream  = commPort.getInputStream();
		outputStream = commPort.getOutputStream();
		
		commPort.addEventListener(this);
		commPort.notifyOnDataAvailable(true);
		
		write(0);
		
		return commPort;
	}
	
	@Override
	public void write(final int... data) throws IOException {
		for (final int dataToWrite : data) {
				outputStream.write(dataToWrite);
				outputStream.flush();
		}
	}

	@Override
	public void close() throws IOException {
		if(commPort != null) {
			inputStream.close();
			outputStream.close();
			commPort.close();
		}
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		return inputStream;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return outputStream;
	}

	@Override
	public void serialEvent(final SerialPortEvent evt) {
		if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				final byte singleData = (byte) inputStream.read();
			} catch (final IOException e) {
				e.printStackTrace();
			}
			eventLog.log(evt);
		}
	}
}
