package com.sky.jms.queue;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {

	private static final String url="tcp://127.0.0.1:61616";
	private static final String queuename="queue-test";
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
		Destination destination=session.createQueue(queuename);
		//6.创建一个生产者
		MessageProducer messageProducer= session.createProducer(destination);
		 for(int i=0;i<100;i++){
			 //7.创建消息
			 TextMessage textMessage=session.createTextMessage("text"+i);
			 //8.发布消息
			 messageProducer.send(textMessage);
			 System.out.println("发送消息"+textMessage.getText());
		 }
		 //9.关闭链接
		 connection.close();
	}
	
}
