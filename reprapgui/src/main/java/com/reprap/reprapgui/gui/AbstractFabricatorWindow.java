package com.reprap.reprapgui.gui;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractFabricatorWindow extends JFrame {
	
	Logger log = LoggerFactory.getLogger(AbstractFabricatorWindow.class);

	private static final long serialVersionUID = -2595961850366549223L;

	public AbstractFabricatorWindow(final String name) throws Exception {
		super(name);
	}
}