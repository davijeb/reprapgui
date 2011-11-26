package com.reprap.reprapgui.view.panels;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;

/**
 * This class is a panel consisting two text fields, allowing the user to enter
 * the printer port and baud speed. It also contains a button allowing the user 
 * to connect and finally a label which shows the current printer connection state.
 *
 */
@SuppressWarnings("serial")
public class FabricatorPrintPanel extends AbstractViewPanel {

	public static final String CONNECTION_LABEL_NAME = "ConnectionLabel";

	@Autowired private JLabel     portLabel, baudSpeedLabel, connectionStateLabel;
	@Autowired private JTextField portPath, baudSpeed;
	@Autowired private JButton    printerConnectButton;

	/**
	 * C'tor which sets the layout to be the MigLayout format
	 */
	public FabricatorPrintPanel() {
		super(new MigLayout());
	}
	
	/**
	 * Add the labels, text fields and buttons to the panel
	 */
	public void initialise() {

		add(portLabel);
		add(portPath);
		add(baudSpeedLabel, "gap unrelated");
		add(baudSpeed, "wrap");
		add(printerConnectButton, "span,grow");
		add(connectionStateLabel, "span,grow");
	}

	/**
	 * The connection state has change to be true/false, therefore we change the button 
	 * text and set the connection state label. In addition we also disable the text entry 
	 * fields when connected and enable them should the printer be disconnected.
	 * @param state is true if connected
	 */
	private void setConnectedState(final boolean connectedState) {
		
		if(connectedState) {
			connectionStateLabel.setText(MessageConstants.STATE_CONNECTED);
			printerConnectButton.setText(StaticConstants.DISCONNECT_BUTTON);
			
		} else {
			connectionStateLabel.setText(MessageConstants.STATE_DISCONNECTED);
			printerConnectButton.setText(StaticConstants.CONNECT_BUTTON);
		}
		// reverse logic - if connected = true, enabled = false
		portLabel.setEnabled(!connectedState);
		portPath.setEnabled(!connectedState);
		baudSpeedLabel.setEnabled(!connectedState);
		baudSpeed.setEnabled(!connectedState);
	}
	
	/**
	 * The controller has fire a model property change and we detect this here.
	 */
	@Override
	public void modelPropertyChange(final PropertyChangeEvent evt) {
		/**
		 * IFF the port and baud speed fields are populated then we enable the 
		 * printer button.
		 */
		if(evt.getPropertyName().equals(StaticConstants.ALLOW_CONNECT)) {
			printerConnectButton.setEnabled((Boolean)evt.getNewValue());
		}
		/**
		 * If a connection state change is identified then we indicate this via the 
		 * connection label and disable/enable the fields accordingly
		 */
		else if(evt.getPropertyName().equals(MessageConstants.STATE_CONNECTED)) {
			setConnectedState((Boolean)evt.getNewValue());
		}
	}
}