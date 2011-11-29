package com.reprap.reprapgui.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reprap.reprapgui.controller.AxisController;
import com.reprap.reprapgui.controller.PrinterConnectController;
import com.reprap.reprapgui.controller.communication.SerialCommunicationsPortConnector;
import com.reprap.reprapgui.controller.interfaces.PrinterController;
import com.reprap.reprapgui.model.AbstractModel;
import com.reprap.reprapgui.view.panels.AbstractViewPanel;

/**
 * This class sets up the Spring IoC beans used by the controller
 * aspect of the MVC pattern. 
 */
@Configuration
public class ApplicationControllerConfiguration {

	@Autowired
	AbstractViewPanel printView, axisView, eventLogView;
	@Autowired
	AbstractModel printModel, axisModel, eventModel;

	/**
	 * Create a new print controller given the required views and models it will be controlling.
	 * 
	 * @param printView the print view panel
	 * @param printModel the print view model
	 * @param eventLogView the event view panel
	 * @param eventModel the event view model
	 * @return the print controller instance
	 */
	@Bean
	@Autowired
	public PrinterController printController(
			final AbstractViewPanel printView,
			final AbstractModel printModel, 
			final AbstractViewPanel eventLogView,
			final AbstractModel eventModel) {
		
		final PrinterConnectController printerController = new PrinterConnectController(getSerialPortConnector());
		printerController.setModels(printModel, eventModel);
		printerController.setViews(printView, eventLogView);
		return printerController;
	}

	/**
	 * Create a new axis controller with the views and models it will be controlling.
	 * @param axisView the axis control panel
	 * @param axisModel the axis model
	 * @return the axis controller instance
	 */
	@Bean
	@Autowired
	public AxisController axisController(final AbstractViewPanel axisView,final AbstractModel axisModel) {
		final AxisController axisController = new AxisController();
		axisController.setModel(axisModel);
		axisController.setView(axisView);

		return axisController;
	}
	
	/**
	 * Get an instance of the serial port connector used to connect to the 
	 * micro processor board
	 * @return the port connector
	 */
	@Bean
	public SerialCommunicationsPortConnector getSerialPortConnector() {
		return new SerialCommunicationsPortConnector(null);
	}
}
