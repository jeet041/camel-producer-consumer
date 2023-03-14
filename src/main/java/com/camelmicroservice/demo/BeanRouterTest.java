package com.camelmicroservice.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BeanRouterTest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:bean-timer-test?period=10000")
		.bean("math").log("log:message =>${body}")
		.to("log:bean-timer-test");
	}
	
@Component(value = "math")
class Math{
	public void add() {
		System.out.println("Welcome Bean Router and calling add function");
	}
}

}
