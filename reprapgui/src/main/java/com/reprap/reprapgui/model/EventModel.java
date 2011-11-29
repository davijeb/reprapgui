package com.reprap.reprapgui.model;

import java.util.ArrayList;
import java.util.List;

import com.reprap.reprapgui.controller.utils.MessageConstants;

public class EventModel extends AbstractModel {
	
	private final List<String> events = new ArrayList<String>();
	
	public List<String> getEvents() {
		return events;
	}
	public void setEvent(final String event) {
		events.add(event);
		firePropertyChange(MessageConstants.STATE_EVENT, false, true);
	}
}
