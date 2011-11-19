package com.reprap.reprapgui.endtoend;

import org.junit.Test;

import com.reprap.reprapgui.SerialTest;

public class SerialTester {
	
	@Test
	public void test() {
		new SerialTest().initialize();
	}

}
