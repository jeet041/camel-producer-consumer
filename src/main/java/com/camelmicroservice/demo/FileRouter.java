package com.camelmicroservice.demo;

import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class FileRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		//moveSpecificFileWithBody("Hello");
//		from("timer:active-mq-timer?period=10000")
//        .transform().constant("Hello Message from Apache Camel - TechGeekNext")
//        .log("${body}")
//        //send this message to ActiveMQ queue
//        .to("activemq:my-activemq-queue");
	
	}
	
	public void moveAllFile() {
		from("file:source?noop=true").to("file:destination");
	}
	
	public void moveSpecificFile(String type) {
		from("file:source?noop=true").filter(header(Exchange.FILE_NAME).startsWith(type)).to("file:destination");
	}
	
	public void moveSpecificFileWithBody(String content) {
		from("file:source?noop=true").filter(body().startsWith(content)).to("file:destination");
	}
	public void fileProcess() {
		
		from("file:source?noop=true").process(p->{
			String body = p.getIn().getBody(String.class);
			StringBuilder sb= new StringBuilder();
			Arrays.stream(body.split(" ")).forEach(word->{
				sb.append(word +"\n");
			});
			p.getIn().setBody(sb +",");
		}).to("file:destination");
	}

}
