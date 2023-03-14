package com.camelmicroservice.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
//@Component
public class KafkaProducer extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		//Producer route
		from("timer:kafka-test?period=10000")
		.setBody(simple("Welcome Kafka Producer !!!"))
		.to("kafka:myKafkaTopic");
		
		//Consumer route
		from("kafka:myKafkaTopic")
		.log("log:Receving message=>${body}");

	}

}
