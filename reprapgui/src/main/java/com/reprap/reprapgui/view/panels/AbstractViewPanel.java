package com.reprap.reprapgui.view.panels;

import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractViewPanel extends JPanel {

	public AbstractViewPanel(final LayoutManager layout) {
		super(layout);
	}

	/**
     * Called by the controller when it needs to pass along a property change 
     * from a model.
     *
     * @param evt The property change event from the model
     */
    public abstract void modelPropertyChange(PropertyChangeEvent evt);

}
