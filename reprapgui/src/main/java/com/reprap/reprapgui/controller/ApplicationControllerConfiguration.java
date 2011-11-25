package com.reprap.reprapgui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.reprap.reprapgui.model.PrintConnectionModel;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

@Configuration
public class ApplicationControllerConfiguration {

	@Autowired PrintConnectionModel printModel, axisModel;
	@Autowired FabricatorPrintPanel printView, axisView;
	
	@Bean
	@Lazy(value=true)
	public PrinterController printController() {
		final PrinterController printerController = new PrinterController();
		printerController.setModel(printModel);
		printerController.setView(printView);
		
		return printerController;
	}
	
	@Bean
	public AxisController axisController() {
		 final AxisController axisController = new AxisController();
		 axisController.setModel(axisModel);
		 axisController.setView(axisView);
		 
		 return axisController;
	}
}
