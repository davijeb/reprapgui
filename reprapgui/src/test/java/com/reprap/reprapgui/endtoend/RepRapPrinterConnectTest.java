package com.reprap.reprapgui.endtoend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
public class RepRapPrinterConnectTest {

	private static RepRapApplicationRunner application;

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
	public void checkConnectButtonPressed() {
		application.pressConnectButton();
	}
	
	@Test
	public void connectPressOpensMessageDialogue() {
		application.pressConnectButton();
		application.showConnectMessageDialogue();
	}
}
