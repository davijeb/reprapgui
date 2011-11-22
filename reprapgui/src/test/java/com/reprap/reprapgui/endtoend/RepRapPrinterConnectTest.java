package com.reprap.reprapgui.endtoend;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reprap.reprapgui.controller.PrusaPrinterController;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * This class performs a number of tests to assert the {@link FabricatorPrintPanel} controls
 * behave in the manner we expect.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
public class RepRapPrinterConnectTest {

	private static RepRapApplicationRunner application;
	
	@Autowired @Spy private PrusaPrinterController printController;
	
	@Autowired private JButton printerConnectButton;
	@Autowired private JTextField portPath, baudSpeed; 

	@Before
	public  void init() {
		application = new RepRapApplicationRunner();
	}

	@Test
	public void testMainWindowAppears() {
		application.showMainWindowAppears();
	}
	
	@Test
	public void testPortTextFieldIsEnabled() {
		assertThat(portPath.isEnabled(), is(true));
	}
	
	@Test
	public void testBaudSpeedTextFieldIsEnabled() {
		assertThat(baudSpeed.isEnabled(), is(true));
	}
	
	@Test
	public void testConnectButtonIsDisabled() {
		assertThat(printerConnectButton.isEnabled(), is(false));
	}
	
	@Test
	public void textAddOnlyPortPathToTextField() {
		application.canAddPortPathToTextField();
	}
	
	@Test
	public void testBadSpeedTextFieldDoesNotAcceptNonNumericValues() {
		application.cantAddNonNumericValuesToBaudSpeedTextField();
	}
	
	@Test
	public void testAddOnlyBaudSpeedToTextField() {
		application.canAddBaudSpeedToTextField();
	}
	
	@Test
	public void testAddOnlyPortPathToTextFieldAndCheckConnectButtonDisabled() {
		application.canClearBothTextFields();
		application.canAddPortPathToTextField();
		
		assertThat(printerConnectButton.isEnabled(), is(false));
	}
	
	@Test
	public void testAddOnlyBaudSpeedToTextFieldAndCheckConnectButtonDisabled() {
		application.canClearBothTextFields();
		application.canAddBaudSpeedToTextField();
		
		assertThat(printerConnectButton.isEnabled(), is(false));
	}
	
	@Test
	public void testAddPortPathAndBaudSpeedToTextFieldsThenConnectAndShowConnected() {
		application.canClearBothTextFields();
		application.canAddPortPathToTextField();
		application.canAddBaudSpeedToTextField();
		application.pressConnectButton();
		
		verify(printController,times(1)).connect();
		
		application.showsPrinterIsConnected();
		
		assertThat(portPath.isEnabled(),  is(false));
		assertThat(baudSpeed.isEnabled(), is(false));
	}
	
	@Test
	public void testPrinterCanBeDisconnectedAndThatItIsDisconnected() {
		application.showsPrinterIsConnected();
		application.pressConnectButton();
		application.showsPrinterIsDisconnected();
	}

}
