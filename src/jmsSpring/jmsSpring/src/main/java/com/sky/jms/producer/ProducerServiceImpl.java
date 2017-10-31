package com.sky.jms.producer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ProducerServiceImpl implements ProducerService {

	@Autowired
	JmsTemplate jmsTemplate;
	@Resource(name="queueDestination")
	Destination destination;
	public void sendMessage(final String message) {
		//使用jmsTemplate发送消息
		jmsTemplate.send(destination, new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage(message);
				return textMessage;
			}
		});	
		System.out.println("发送消息："+message);
	}

}
