package com.reprap.reprapgui.view.panels;

import java.beans.PropertyChangeEvent;

import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;

public class FabricatorEventViewPanel extends AbstractViewPanel {
	
	private static final long serialVersionUID = -958849850684786156L;
	
	@Autowired
	private JTextArea eventLogTextArea;

	public FabricatorEventViewPanel() {
		super(new MigLayout());
	}

	public void initialise() {
		eventLogTextArea.setName(StaticConstants.EVENT_LOG_TEXT_AREA);
		add(eventLogTextArea);
	}

	@Override
	public void modelPropertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(MessageConstants.STATE_CONNECTED)) {
			eventLogTextArea.append(MessageConstants.STATE_CONNECTED + "\n");
		}
		if (evt.getPropertyName().equals(MessageConstants.STATE_DISCONNECTED)) {
			eventLogTextArea.append(MessageConstants.STATE_DISCONNECTED + "\n");
		}
		if (evt.getPropertyName().equals(MessageConstants.STATE_EVENT)) {
			eventLogTextArea.append(MessageConstants.STATE_EVENT + "\n");
		}
	}
}
