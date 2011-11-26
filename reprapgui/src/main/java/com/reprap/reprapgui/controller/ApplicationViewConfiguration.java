package com.reprap.reprapgui.controller;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.model.AbstractModel;
import com.reprap.reprapgui.view.buttons.AxisButton;
import com.reprap.reprapgui.view.buttons.PrinterConnectButton;
import com.reprap.reprapgui.view.components.IntegerTextField;
import com.reprap.reprapgui.view.components.MessageLabel;
import com.reprap.reprapgui.view.components.StringTextField;
import com.reprap.reprapgui.view.frames.FabricatorWindow;
import com.reprap.reprapgui.view.panels.AxisViewPanel;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

@Configuration
public class ApplicationViewConfiguration {
	
	@Autowired(required=true) AbstractModel printModel, axisModel;
	@Autowired Axis axisXUp, axisXDown, axisYUp, axisYDown, axisZUp, axisZDown;
	
	/**
	 * Main Window
	 * @throws Throwable 
	 */
	@Bean
	public FabricatorWindow MainApplication() throws Throwable {
		return new FabricatorWindow(StaticConstants.APPLICATION_NAME, printView(), axisView());
	}
	
	/**
	 * Panels
	 */
	@Bean(initMethod="initialise")
	public FabricatorPrintPanel printView() {
		return new FabricatorPrintPanel();
	}
	
	@Bean
	public PrinterController printController() {
		final PrinterController printerController = new PrinterController();
		printerController.setModel(printModel);
		printerController.setView(printView());
		
		return printerController;
	}
	
	
	@Bean
	@Lazy(value=true)
	public AxisController axisController() {
		 final AxisController axisController = new AxisController();
		 axisController.setModel(axisModel);
		 axisController.setView(axisView());
		 
		 return axisController;
	}
	
	@Bean(initMethod="initialise")
	public AxisViewPanel axisView() {
		return new AxisViewPanel();
	}
	
	/**
	 * Buttons
	 */
	@Bean
	public PrinterConnectButton printerConnectButton() {
		return new PrinterConnectButton(StaticConstants.CONNECT_BUTTON.toString(), "Connect");
	}
	
	@Bean
	public AxisButton xUpButton() {
		return new AxisButton(axisXUp);
	}
	
	@Bean
	public AxisButton xDownButton() {
		return new AxisButton(axisXDown);
	}
	
	@Bean
	public AxisButton yUpButton() {
		return new AxisButton(axisYUp);
	}
	
	@Bean
	public AxisButton yDownButton() {
		return new AxisButton(axisYDown);
	}
	
	@Bean
	public AxisButton zUpButton() {
		return new AxisButton(axisZUp);
	}
	
	@Bean
	public AxisButton zDownButton() {
		return new AxisButton(axisZDown);
	}
	
	/**
	 * Text fields
	 */
	@Bean
	public JTextField portPath() {
		return new StringTextField(StaticConstants.PORT_TEXT_FIELD.toString(),15);
	}
	
	@Bean
	public JTextField baudSpeed() {
		return new IntegerTextField(StaticConstants.BAUD_SPEED_TEXT_FIELD.toString(),15);
	}
	
	/**
	 * Labels
	 */
	@Bean
	public JLabel portLabel() {
		return new JLabel(StaticConstants.PORT_LABEL_TEXT.toString());
	}
	
	@Bean
	public JLabel baudSpeedLabel() {
		return new JLabel(StaticConstants.BAUD_SPEED_LABEL_TEXT.toString());
	}
	
	@Bean
	public JLabel connectionStateLabel() {
		return new MessageLabel(MessageConstants.STATE_DISCONNECTED.toString(),StaticConstants.CONNECT_STATE_LABEL);
	}

}
