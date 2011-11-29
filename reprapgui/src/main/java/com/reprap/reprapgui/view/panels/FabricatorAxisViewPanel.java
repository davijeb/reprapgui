package com.reprap.reprapgui.view.panels;

import java.beans.PropertyChangeEvent;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;

import com.reprap.reprapgui.controller.AxisController;
import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.view.buttons.AxisButton;

@SuppressWarnings("serial")
public class FabricatorAxisViewPanel extends AbstractViewPanel {
	
	@Autowired private AxisController axisController;

	@Autowired
	private AxisButton xUpButton,xDownButton, // x
					   yUpButton,yDownButton, // y
					   zUpButton,zDownButton; // z

	/**
	 * C'tor which sets the layout to be the MigLayout format
	 */
	public FabricatorAxisViewPanel() {
		super(new MigLayout());
	}

	/**
	 * Add the labels, text fields and buttons to the panel.
	 * The axis controller is added here as we can't Autowire into the 
	 * button due to a circular dependency.
	 */
	public void initialise() {
		add(   xUpButton.addController(axisController) );
		add( xDownButton.addController(axisController) );
		add(   yUpButton.addController(axisController) );
		add( yDownButton.addController(axisController) );
		add(   zUpButton.addController(axisController) );
		add( zDownButton.addController(axisController) );
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
		if(evt.getPropertyName().equals(StaticConstants.AXIS_COMMAND)) {
		}
	}
}
