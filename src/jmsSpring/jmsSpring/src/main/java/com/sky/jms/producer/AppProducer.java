package com.sky.jms.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppProducer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("producer.xml");
		ProducerService service=applicationContext.getBean(ProducerService.class);
		for(int i=0;i<100;i++){

			service.sendMessage("test"+i);
		}
		applicationContext.close();
	}
}
