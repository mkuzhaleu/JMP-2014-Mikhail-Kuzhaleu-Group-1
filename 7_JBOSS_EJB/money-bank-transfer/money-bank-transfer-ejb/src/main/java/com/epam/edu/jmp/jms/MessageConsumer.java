package com.epam.edu.jmp.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
 
/**
 * Message-Driven Bean implementation class for: MDBSample- This is for Consume the Queue
 */
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test"),
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class MessageConsumer implements MessageListener {
 
    /**
     * Default constructor.
     */
    public MessageConsumer() {
 
    }
 
    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
 
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("Received message is ==========> " + tm.getText());
        } catch (JMSException e) {
 
            e.printStackTrace();
        }
 
    }
 
}