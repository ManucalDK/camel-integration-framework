/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cameltest.microservices.camelmicroservicea.routes.a;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author manuc
 */
@Component
public class MyFirstTimerRouter extends RouteBuilder {



	@Autowired
	private GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	private SimpleLoggingProccessingComponent logginComponent;

	@Override
	public void configure() throws Exception {
		// (timer) escuchar cola endpoint 1
		// transformacion
		// (log) almacenar en base de datos endpoint 2

		from("timer:first-timer") // timer endpoint
				.transform().constant("My constant message")
				.log("${body}")
//        .transform().constant("Time now is: "+ LocalDateTime.now())
				.bean(getCurrentTimeBean, "getCurrentTime")
				.log("${body}")
				.bean(logginComponent, "process")
				.log("${body}")
				.process(new SimpleLoggingProcessor())
				.to("log:first-timmer");
	}

}

@Component
class GetCurrentTimeBean {
	public String getCurrentTime() {
		return "Time now is: " + LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProccessingComponent {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProccessingComponent.class);
	
	public void process(String message) {
		logger.info("SimpleLoggingProccessingComponent {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {
	
	private Logger logger = LoggerFactory.getLogger(SimpleLoggingProccessingComponent.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProccessingComponent {}", exchange.getMessage().getBody());
	}

}