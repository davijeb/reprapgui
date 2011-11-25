package com.reprap.reprapgui.endtoend;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reprap.reprapgui.controller.AxisController;
import com.reprap.reprapgui.controller.utils.Axis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-application-context.xml")
public class AxisTest {
	
	@Autowired @Spy private AxisController axisController;
	
	private final AxisConnectDriver driver = new AxisConnectDriver();

	
	@Test
	public void testScreenXUpButton() {
		driver.pressMovementButton(Axis.XUp);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.XUp);
	}
	
	@Test
	public void testScreenXDownButton() {
		driver.pressMovementButton(Axis.XDown);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.XDown);
	}
	
	@Test
	public void testScreenYUpButton() {
		driver.pressMovementButton(Axis.YUp);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.YUp);
	}
	
	@Test
	public void testScreenYDownButton() {
		driver.pressMovementButton(Axis.YDown);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.YDown);
	}
	
	@Test
	public void testScreenZUpButton() {
		driver.pressMovementButton(Axis.ZUp);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.ZUp);
	}
	
	@Test
	public void testScreenZDownButton() {
		driver.pressMovementButton(Axis.ZDown);
		delay();
		verify(axisController,times(1)).moveAxis(Axis.ZDown);
	}
	
	/**
	 * Inject a little delay to allow the driver to hit the buttons and the Mockito
	 * framework to pick up the interaction.
	 */
	private void delay() {
		try {
			Thread.sleep(100);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
