package com.reprap.reprapgui.controller.communication;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * This class searches for available ports and then adds them to a set to be returned. 
 */
public class SerialCommunicationsPortLocator implements SerialPortLocator {

	/**
	 * Locate the available ports and add them to a {@link Set} as the {@link CommPortIdentifier} class
	 * is not well written and returns raw Enumerations instead of Generic Collections.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<CommPortIdentifier> locatePorts() {
		final List<CommPortIdentifier> ports = new ArrayList<CommPortIdentifier>();
		
		final Enumeration locatedPorts = CommPortIdentifier.getPortIdentifiers();
		while(locatedPorts.hasMoreElements()){
			ports.add((CommPortIdentifier) locatedPorts.nextElement());
		}
		return ports;
	}

}
