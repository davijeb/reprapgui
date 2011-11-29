package com.reprap.reprapgui.controller.communication;

import gnu.io.CommPortIdentifier;

import java.util.List;

public interface SerialPortLocator {

	List<CommPortIdentifier> locatePorts();

}
