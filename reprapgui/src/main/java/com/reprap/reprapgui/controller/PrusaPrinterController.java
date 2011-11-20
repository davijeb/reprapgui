package com.reprap.reprapgui.controller;

import com.reprap.reprapgui.controller.utils.MessageConstants;

public class PrusaPrinterController extends AbstractController implements GenericPrinter {

	@Override
	public void connect() {
		setModelProperty(MessageConstants.STATE_CONNECTED,true);
	}

	@Override
	public void disconnect() {
		setModelProperty(MessageConstants.STATE_CONNECTED, false);
	}

	public void printParametersChanged(final String name, final Object value) {
		setModelProperty(name,value);
	}
}