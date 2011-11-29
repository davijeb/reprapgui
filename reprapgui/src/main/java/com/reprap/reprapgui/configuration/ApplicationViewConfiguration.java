package com.reprap.reprapgui.configuration;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.controller.utils.MessageConstants;
import com.reprap.reprapgui.controller.utils.StaticConstants;
import com.reprap.reprapgui.view.buttons.AxisButton;
import com.reprap.reprapgui.view.buttons.PrinterConnectButton;
import com.reprap.reprapgui.view.components.IntegerTextField;
import com.reprap.reprapgui.view.components.MessageLabel;
import com.reprap.reprapgui.view.components.StringTextField;
import com.reprap.reprapgui.view.frames.FabricatorWindow;
import com.reprap.reprapgui.view.panels.FabricatorAxisViewPanel;
import com.reprap.reprapgui.view.panels.FabricatorEventViewPanel;
import com.reprap.reprapgui.view.panels.FabricatorPrintPanel;

/**
 * This class sets up the Spring IoC beans used by the view
 * aspect of the MVC pattern.
 */
@Configuration
public class ApplicationViewConfiguration {
	
	@Autowired Axis axisXUp, axisXDown, axisYUp, axisYDown, axisZUp, axisZDown;
	
	/**
	 * Create the main window
	 * @throws
	 */
	@Bean
	@Autowired
	public FabricatorWindow MainApplication() throws Throwable {
		return new FabricatorWindow(
				StaticConstants.APPLICATION_NAME, 
				printView(), 
				axisView(),
				eventLogView());
	}

	/**
	 * --------------------------------------------------
	 * 						Panels
	 * --------------------------------------------------
	 */
	@Bean(initMethod="initialise")
	public FabricatorPrintPanel printView() {
		return new FabricatorPrintPanel();
	}
	
	@Bean(initMethod="initialise")
	public FabricatorAxisViewPanel axisView() {
		return new FabricatorAxisViewPanel();
	}
	
	@Bean(initMethod="initialise")
	public FabricatorEventViewPanel eventLogView() {
		return new FabricatorEventViewPanel();
	}
	
	/**
	 * --------------------------------------------------
	 * 						Buttons
	 * --------------------------------------------------
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
	 * --------------------------------------------------
	 * 					Text fields
	 * --------------------------------------------------
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
	 * --------------------------------------------------
	 * 				 	  Text areas
	 * --------------------------------------------------
	 */
	@Bean
	public JTextArea eventLogTextArea() {
		return new JTextArea(30, 40);
	}
	

	/**
	 * --------------------------------------------------
	 * 						Labels
	 * --------------------------------------------------
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
