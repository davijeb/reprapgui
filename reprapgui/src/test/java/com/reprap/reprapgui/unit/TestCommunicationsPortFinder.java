package com.reprap.reprapgui.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;
import gnu.io.CommPortIdentifier;

import java.util.List;

import org.junit.Test;

import com.reprap.reprapgui.controller.communication.SerialCommunicationsPortLocator;
import com.reprap.reprapgui.controller.communication.SerialPortLocator;

public class TestCommunicationsPortFinder {
	
	private final SerialPortLocator portLocator = new SerialCommunicationsPortLocator();
	
	@Test public void 
	testCanFindCommunicationsPorts() {
		assertThat(portLocator, notNullValue());
		assertThat(portLocator.locatePorts(), notNullValue());
		
	}
	
	@Test public void 
	testCanFindAtLeastCommunicationsPorts() {
		assertThat(portLocator.locatePorts().size(), greaterThan(0));
	}
	
	@Test public void 
	testCommunicationsPortsAreSerial() {
		
		final List<CommPortIdentifier> commPorts = portLocator.locatePorts();
		assertThat(commPorts, notNullValue());
		
		for (final CommPortIdentifier commPortIdentifier : commPorts) {
			assertTrue(commPortIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL);
		}
	}
}
