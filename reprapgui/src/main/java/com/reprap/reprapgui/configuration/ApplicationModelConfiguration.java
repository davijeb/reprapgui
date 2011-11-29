package com.reprap.reprapgui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.model.AxisModel;
import com.reprap.reprapgui.model.EventModel;
import com.reprap.reprapgui.model.PrintConnectionModel;

/**
 * This class sets up the Spring IoC beans used by the model
 * aspect of the MVC pattern.  
 */
@Configuration
public class ApplicationModelConfiguration {

	@Bean
	public PrintConnectionModel printModel() {
		return new PrintConnectionModel();
	}
	
	@Bean
	public AxisModel axisModel() {
		return new AxisModel();
	}
	
	@Bean
	public EventModel eventModel() {
		return new EventModel();
	}
	
	@Bean
	public Axis axisXUp() {
		return Axis.XUp;
	}
	
	@Bean
	public Axis axisXDown() {
		return Axis.XDown;
	}
	
	@Bean
	public Axis axisYUp() {
		return Axis.YUp;
	}
	
	@Bean
	public Axis axisYDown() {
		return Axis.YDown;
	}
	
	@Bean
	public Axis axisZUp() {
		return Axis.ZUp;
	}
	
	@Bean
	public Axis axisZDown() {
		return Axis.ZDown;
	}
	
}
