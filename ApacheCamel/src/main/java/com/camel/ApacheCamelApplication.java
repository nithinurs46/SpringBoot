package com.camel;

import java.util.Arrays;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApacheCamelApplication extends RouteBuilder {
	
	@Autowired 
	ProducerTemplate producer;
	
	@Autowired
	ConsumerTemplate consumer;

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelApplication.class, args);
	}

	@Override
	public void configure() throws Exception {
		// copyAllFiles();
		// copySpecificFiles();
		// copyBasedOnFileContent();
		//processFile();
		producerConsumer();
	}

	private void copyAllFiles() {
		// from("file:D:\\temp\\src").to("file:D:\\temp\\dest");
		// if noop=true is not added, when file is moved from src to dest folder, .camel
		// folder gets created in src
		from("file:D:\\temp\\src?noop=true").to("file:D:\\temp\\dest");
	}

	private void copySpecificFiles() {
		String srcFolder = "file:D:\\temp\\src?noop=true";
		String destFolder = "file:D:\\temp\\dest";
		from(srcFolder).filter(header(Exchange.FILE_NAME).startsWith("abd")).to(destFolder);
	}

	private void copyBasedOnFileContent() {
		String srcFolder = "file:D:\\temp\\src?noop=true";
		String destFolder = "file:D:\\temp\\dest";
		String specifcData = "hii";
		from(srcFolder).filter(body().startsWith(specifcData)).to(destFolder);
	}

	private void processFile() {
		String srcFolder = "file:D:\\temp\\src\\process?noop=true";
		String destFolder = "file:D:\\temp\\dest\\process?fileName=new.xls";
		from(srcFolder).process(p -> {

			String body = p.getIn().getBody(String.class);
			StringBuilder sb = new StringBuilder();
			String[] spaceIndex = body.split(" ");
			Arrays.stream(spaceIndex).forEach(s -> {
				sb.append(s + ", "); // adds comma after each space
			});
			p.getIn().setBody(sb);
		}).to(destFolder);
	}

	private void producerConsumer() {

		from("direct:start").to("seda:end");
		
		producer.sendBody("direct:start", "producer consumer ex with camel");

		String rcvdMsg = consumer.receiveBody("seda:end", String.class);
		System.out.println("Message sent from consumer--> " + rcvdMsg);

	}

}
