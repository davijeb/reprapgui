package com.reprap.reprapgui.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import com.reprap.reprapgui.model.PrintConnection;
import com.reprap.reprapgui.model.PrintConnectionModel;

public class PrintConnectionModelTest {
	
	private PrintConnection printConnection;
	
	private final String correctPort  = "/tty/usb.A9008FBW";
	private final int correctSpeed = 192000;
	
	@Before
	public void init() {
		printConnection = new PrintConnectionModel(correctPort, correctSpeed);
	}
	
	@Test
	public void canAddPortPathToModel() {
		assertThat(printConnection.getPort(), equalTo(correctPort));
	}
	
	@Test
	public void canAddBadSpeedToModel() {
		assertThat(printConnection.getBaudSpeed(), equalTo(correctSpeed));
	}

}
