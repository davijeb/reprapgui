package com.reprap.reprapgui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reprap.reprapgui.model.AbstractModel;
import com.reprap.reprapgui.view.panels.AbstractViewPanel;

@Configuration
public class ApplicationControllerConfiguration {

	@Autowired
	AbstractViewPanel printView, axisView;
	@Autowired
	AbstractModel printModel, axisModel;

	@Bean
	@Autowired
	public PrinterController printController(final AbstractViewPanel printView,final AbstractModel printModel) {
		final PrinterController printerController = new PrinterController();
		printerController.setModel(printModel);
		printerController.setView(printView);
		return printerController;
	}

	@Bean
	@Autowired
	public AxisController axisController(final AbstractViewPanel axisView,final AbstractModel axisModel) {
		final AxisController axisController = new AxisController();
		axisController.setModel(axisModel);
		axisController.setView(axisView);

		return axisController;
	}
}
