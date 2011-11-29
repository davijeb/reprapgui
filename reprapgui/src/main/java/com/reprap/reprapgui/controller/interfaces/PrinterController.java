package com.reprap.reprapgui.controller.interfaces;

public interface PrinterController {

	void connect();
	void disconnect();
	void printParametersChanged(String name, Object value);

}
