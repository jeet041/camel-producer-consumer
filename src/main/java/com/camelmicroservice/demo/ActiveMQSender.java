package com.camelmicroservice.demo;

import org.apache.camel.builder.RouteBuilder;
//@Component
public class ActiveMQSender extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:test?period=10000").setBody(simple("Welcome into activeMQ world")).to("activemq:test-mq");
		from("activemq:test-mq").log("log: receing message=>>${body}");
	}

}
