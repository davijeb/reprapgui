package com.reprap.reprapgui.endtoend;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.view.frames.FabricatorWindow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-application-context.xml")
@Ignore
public class FestTest {

	private FrameFixture window;

	@Autowired
	private FabricatorWindow MainApplication;

	@BeforeClass
	public static void setUpOnce() {
		//FailOnThreadViolationRepaintManager.install();
	}

	@Before
	@RunsInEDT
	public void setUp() {
		final FabricatorWindow frame = GuiActionRunner
				.execute(new GuiQuery<FabricatorWindow>() {
					@Override
					protected FabricatorWindow executeInEDT() {
						return MainApplication;
					}
				});
		window = new FrameFixture(frame);
		//window.show(); // shows the frame to test
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButton() {
		window.button(StaticConstants.CONNECT_BUTTON).click();
		window.slider(StaticConstants.SLIDER).focus();
		window.slider(StaticConstants.SLIDER).slideTo(10);
		window.slider(StaticConstants.SLIDER).click();
		window.slider(StaticConstants.SLIDER).slideTo(10);
		window.slider(StaticConstants.SLIDER).slideTo(20);
		window.slider(StaticConstants.SLIDER).slideTo(30);
		window.slider(StaticConstants.SLIDER).slideTo(40);
		window.slider(StaticConstants.SLIDER).slideTo(50);
		window.slider(StaticConstants.SLIDER).slideTo(60);
		window.slider(StaticConstants.SLIDER).slideTo(70);
		window.slider(StaticConstants.SLIDER).slideTo(80);
		window.slider(StaticConstants.SLIDER).slideTo(90);
		window.slider(StaticConstants.SLIDER).slideTo(100);
		window.button(StaticConstants.CONNECT_BUTTON).click();
	}

}
