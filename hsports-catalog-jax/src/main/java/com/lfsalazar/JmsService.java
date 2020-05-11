package com.lfsalazar;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

@ApplicationScoped
public class JmsService {

	@Resource(mappedName="java:/jms/queue/HsportsQueue")
	private Queue hsportsQueue ;
	
	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;
	
	public void send(String message) {
		
		try {
			TextMessage textMessage = context.createTextMessage(message);
			context.createProducer().send(this.hsportsQueue,textMessage);
			System.out.println("Message sent to the queue.");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
