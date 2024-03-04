package home.parom;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author E.Parominsky 04/03/2024 08:48
 */
public class Consumer {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("user", "user", "tcp://localhost:61616");
        try {
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("mytestqueue");

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
