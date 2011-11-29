package com.reprap.reprapgui.controller.communication;

import gnu.io.SerialPortEvent;

public interface SerialEventLog {

	boolean log(SerialPortEvent evt);

}
