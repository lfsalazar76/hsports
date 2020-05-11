package com.lfsalazar;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: JmsConsumer
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "/jms/queue/HsportsQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "/jms/queue/HsportsQueue")
public class JmsConsumer implements MessageListener {

    /**
     * Default constructor. 
     */
    public JmsConsumer() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	
    	System.out.println("From JMS Consumer MDB:");
    	try {
    		System.out.println(message.getBody(String.class));
    	}catch(JMSException e) {
    		e.printStackTrace();
    	}
        
    }

}
