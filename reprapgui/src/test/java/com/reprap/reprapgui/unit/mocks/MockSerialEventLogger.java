package com.reprap.reprapgui.unit.mocks;

import gnu.io.SerialPortEvent;

import com.reprap.reprapgui.controller.communication.SerialEventLog;

public class MockSerialEventLogger implements SerialEventLog {

	@Override
	public boolean log(final SerialPortEvent evt) {
		System.out.println("MockSerialEventLogger.log()");
		return true;
	}

}
