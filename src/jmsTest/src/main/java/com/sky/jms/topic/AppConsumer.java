package com.sky.jms.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
	private static final String url="tcp://127.0.0.1:61616";
	private static final String topicname="topic-test";
	public static void main(String[] args) throws JMSException {
		//1.創建ConnectionFactory 简单工厂模式
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//2.創建Connection
		Connection connection=connectionFactory.createConnection();
		//3.啟動鏈接
		connection.start();
		//4.创建会话
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建一个目标
		Destination destination=session.createTopic(topicname);
		//6.创建一个消费者
		MessageConsumer messageConsumer=session.createConsumer(destination);
		//7.创建一个监听器
		messageConsumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage=(TextMessage) message;
				try {
					System.out.println("接受消息"+textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		 //8.关闭链接
		 //connection.close();
	}
}
