package com.reprap.reprapgui.endtoend;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import gnu.io.PortInUseException;

import java.io.IOException;
import java.util.TooManyListenersException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.reprap.reprapgui.controller.communication.SerialCommunicationsPortConnector;
import com.reprap.reprapgui.controller.communication.SerialPortConnector;
import com.reprap.reprapgui.controller.interfaces.PrinterController;
import com.reprap.reprapgui.endtoend.runner.RepRapApplicationRunner;

/**
 * This class performs a number of tests to assert the main window has a log
 * event text pane and that it logs events.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-application-context.xml")
public class TestEventLog {

	private static RepRapApplicationRunner  application;     // the test application runner
	private @Mock SerialPortConnector   	mockConnector;   // mock the serial connector
	private @Autowired PrinterController 	printController; // spring bean print controller

	@Before
	public void before() {
		mockConnector = Mockito.mock(SerialCommunicationsPortConnector.class);
		ReflectionTestUtils.setField(printController, "connector", mockConnector);
		application = new RepRapApplicationRunner();
	}

	@Test
	public void testMainWindowAppears() {
		application.showMainWindowAppears();
	}

	@Test
	public void testEventLogIsVisible() {
		application.hasEventLogTextArea();
	}

	@Test
	public void testEventLogShowsConnectMessageOnConnect() throws PortInUseException, TooManyListenersException, IOException {
	
		Mockito.doCallRealMethod().when(printController).connect();

		application.canAddPortPathToTextField();
		application.canAddBaudSpeedToTextField();
		application.pressConnectButton();
		
		delay();
		
		verify(mockConnector, times(1)).open(anyInt(), anyString(), anyInt());
	}

	/**
	 * Inject a little delay to allow the Windowlicker driver to hit the buttons
	 * and the Mockito framework to pick up the interaction.
	 */
	private void delay() {
		try {
			Thread.sleep(100);
		} catch (final InterruptedException e) {
			// don't care
		}
	}
}
