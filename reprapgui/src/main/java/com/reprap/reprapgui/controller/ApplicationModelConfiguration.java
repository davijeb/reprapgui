package com.reprap.reprapgui.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reprap.reprapgui.controller.utils.Axis;
import com.reprap.reprapgui.model.AxisModel;
import com.reprap.reprapgui.model.PrintConnectionModel;

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
