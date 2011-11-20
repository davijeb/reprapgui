package com.reprap.reprapgui.endtoend;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reprap.reprapgui.controller.GenericPrinter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
public class RepRapPrinterConnectTest {

	private static RepRapApplicationRunner application;
	
	@Autowired
	@Spy
	private GenericPrinter printer;
	
	@Autowired
	private JTextField portPath;
	@Autowired
	private JTextField baudSpeed;

	@Before
	public  void init() {
		application = new RepRapApplicationRunner();
	}

	@Test
	public void checkMainWindowAppears() {
		application.showMainWindowAppears();
	}

	@Test
	public void checkMainWindowHasAButtonNamedConnect() {
		application.showMainWindowHasAButtonNamedConnect();
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
	public void addOnlyPortPathToTextFieldAndPressConnect() {
		application.clearTextFields();
		application.addPortPathToTextField();
		application.pressConnectButton();
		application.showsWarningDialogue();
	}
	
	@Test
	public void addOnlyBaudSpeedToTextFieldAndPressConnect() {
		application.clearTextFields();
		application.addBaudSpeedToTextField();
		application.pressConnectButton();
		application.showsWarningDialogue();
	}
	
	@Test
	public void addPortPathAndBaudSpeedToTextFieldAndPressConnect() {
		application.clearTextFields();
		application.addPortPathToTextField();
		application.addBaudSpeedToTextField();
		application.pressConnectButton();
		
		verify(printer,times(1)).connect();
	}
	
	@Test
	public void checkConnectButtonPressed() {
		application.pressConnectButton();
	}

}
