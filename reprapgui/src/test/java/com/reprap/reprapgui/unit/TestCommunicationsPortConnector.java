package com.reprap.reprapgui.unit;

import static org.junit.Assert.assertNotNull;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.reprap.reprapgui.controller.communication.SerialCommunicationsPortConnector;
import com.reprap.reprapgui.controller.communication.SerialEventLog;
import com.reprap.reprapgui.controller.communication.SerialPortConnector;
import com.reprap.reprapgui.unit.mocks.MockSerialEventLogger;

public class TestCommunicationsPortConnector {
	
	@Mock private final SerialEventLog serialEventLog = Mockito.mock(MockSerialEventLogger.class);

	private final SerialPortConnector portConnector = new SerialCommunicationsPortConnector(serialEventLog);;
	
	@Before
	public void before() throws Exception {
		portConnector.open(0, "XYZ", 1000);
		//portConnector = new SerialCommunicationsPortConnector(serialEventLog);
	}
	
	@After
	public void after() throws IOException {
		portConnector.close();
	}

	@Test
	public void testIOStreams() throws PortInUseException, IOException, TooManyListenersException {

		final InputStream is = portConnector.getInputStream();
		final OutputStream os = portConnector.getOutputStream();
		
		assertNotNull("Expected to have an input stream but saw nothing", is);
		assertNotNull("Expected to have an output stream but saw nothing", os);
	}
}
