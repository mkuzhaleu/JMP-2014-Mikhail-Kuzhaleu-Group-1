package com.epam.edu.jmp.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTopicProducer See here
 * http://localhost:8080/JmsMessageSenderClient/ServletTopicProducer topic message sent.
 * Check JBoss logs that message is received.
 */
@WebServlet("/ServletTopicProducer")
public class ServletTopicProducer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTopicProducer() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        final String TOPIC_LOOKUP = "topic/test";
        final String CONNECTION_FACTORY = "ConnectionFactory";
        
        PrintWriter out = response.getWriter();

        try {

            out.println("Begin send Topic Async");

            // Setup the PubSub connection, session
            InitialContext iniCtx = new InitialContext();

            Object tmp;

            tmp = iniCtx.lookup(CONNECTION_FACTORY);

            TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;

            TopicConnection conn = tcf.createTopicConnection();

            Topic topic = (Topic) iniCtx.lookup(TOPIC_LOOKUP);

            TopicSession session = conn.createTopicSession(false,
                    TopicSession.AUTO_ACKNOWLEDGE);
            conn.start();

            TopicPublisher send = session.createPublisher(topic);

            String text = "A sample TOPIC text msg, now = " + System.currentTimeMillis();

            TextMessage tm = session.createTextMessage(text);

            send.publish(tm);
            out.println("1. Topic published. Sent text=" + tm.getText());

            conn.stop();
            session.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    }

}
