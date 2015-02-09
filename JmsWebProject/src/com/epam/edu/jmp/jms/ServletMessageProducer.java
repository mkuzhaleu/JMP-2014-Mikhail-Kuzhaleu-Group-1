package com.epam.edu.jmp.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletMessageProducer
 * See here http://localhost:8080/JmsWebProject/ServletMessageProducer message sent.
 * Check JBoss logs that message is recieved.
 */
@WebServlet("/ServletMessageProducer")
public class ServletMessageProducer extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    public ServletMessageProducer() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        final String QUEUE_LOOKUP = "queue/test";
        final String CONNECTION_FACTORY = "ConnectionFactory";
         
        PrintWriter out = response.getWriter();
        try{
            Context context = new InitialContext();
            QueueConnectionFactory factory = 
                (QueueConnectionFactory)context.lookup(CONNECTION_FACTORY);
            QueueConnection connection = factory.createQueueConnection();
            QueueSession session = 
                connection.createQueueSession(false, 
                    QueueSession.AUTO_ACKNOWLEDGE);
             
            Queue queue = (Queue)context.lookup(QUEUE_LOOKUP);
            QueueSender sender = session.createSender(queue);
             
            //1. Sending TextMessage to the Queue 
            TextMessage message = session.createTextMessage();
            message.setText("Hello EJB3 MDB Queue!!!");
            sender.send(message);
            out.println("1. Sent TextMessage to the Queue");
             
            session.close();
        }catch(Exception e){e.printStackTrace();}
    }
} 