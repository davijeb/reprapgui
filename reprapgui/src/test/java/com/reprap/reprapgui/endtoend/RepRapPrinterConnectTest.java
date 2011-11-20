package com.reprap.reprapgui.endtoend;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reprap.reprapgui.controller.PrusaPrinterController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
public class RepRapPrinterConnectTest {

	private static RepRapApplicationRunner application;
	
	@Autowired
	@Spy
	private PrusaPrinterController printController;
	
	@Autowired private JButton printerConnectButton;

	@Before
	public  void init() {
		application = new RepRapApplicationRunner();
	}

	@Test
	public void checkMainWindowAppears() {
		application.showMainWindowAppears();
	}
	
	@Test
	public void addOnlyPortPathToTextField() {
		application.addPortPathToTextField();
	}
	
	@Test
	public void addOnlyBaudSpeedToTextField() {
		application.addBaudSpeedToTextField();
	}
	
	@Test
	public void addOnlyPortPathToTextFieldAndCheckConnectButtonDisabled() {
		application.clearTextFields();
		application.addPortPathToTextField();
		
		assertThat(printerConnectButton.isEnabled(), is(false));
	}
	
	@Test
	public void addOnlyBaudSpeedToTextFieldAndCheckConnectButtonDisabled() {
		application.clearTextFields();
		application.addBaudSpeedToTextField();
		
		assertThat(printerConnectButton.isEnabled(), is(false));
	}
	
	@Test
	public void addPortPathAndBaudSpeedToTextFieldAndPressConnect() {
		application.clearTextFields();
		application.addPortPathToTextField();
		application.addBaudSpeedToTextField();
		application.pressConnectButton();
		
		verify(printController,times(1)).connect();
		
		application.showsPrinterIsConnected();
	}
	
	@Test
	public void checkConnectButtonPressed() {
		application.pressConnectButton();
		application.showsPrinterIsDisconnected();
	}

}
