package home.parom;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author E.Parominsky 04/03/2024 09:49
 */
public class TopicPublisher {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("user", "user", "tcp://localhost:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("Demo-Topic");

            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage("Message for Topic");

            producer.send(textMessage);
            System.out.println("Message publisher to topic");

            session.close();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
