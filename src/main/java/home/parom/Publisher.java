package home.parom;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author E.Parominsky 01/03/2024 14:19
 */
public class Publisher {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("user", "user", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("mytestqueue");

            String[] messages = {"First message", "Second message", "Third message", "Fourth message"};

            MessageProducer producer = session.createProducer(destination);
            for (String message : messages) {
                TextMessage textMessage = session.createTextMessage(message);
                producer.send(textMessage);
            }

            session.close();
            connection.close();

            System.out.println("Message Published");

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
