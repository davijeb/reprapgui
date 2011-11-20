package com.reprap.reprapgui.view.panels;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.utils.StaticConstants;

@SuppressWarnings("serial")
public class FabricatorPrintPanel extends AbstractViewPanel {

	public static final String CONNECTION_LABEL_NAME = "ConnectionLabel";

	@Autowired private JLabel     portLabel, baudSpeedLabel, connectionStateLabel;
	@Autowired private JTextField portPath, baudSpeed;
	@Autowired private JButton    printerConnectButton;

	public FabricatorPrintPanel() {
		super(new MigLayout());
	}
	
	public void initialise() {
		add(portLabel);
		add(portPath);
		add(baudSpeedLabel, "gap unrelated");
		add(baudSpeed, "wrap");
		add(printerConnectButton, "span,grow");
		add(connectionStateLabel, "span,grow");

	}

	@Override
	public void modelPropertyChange(final PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(StaticConstants.CONNECT_STATE)) {
			connectionStateLabel.setText("Connected");
		}
	}
}