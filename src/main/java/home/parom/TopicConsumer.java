package home.parom;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author E.Parominsky 04/03/2024 10:17
 */
public class TopicConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("1");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic("Demo-Topic");

            MessageConsumer consumer = session.createDurableSubscriber(topic, "Consumer-1");
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

            session.close();
            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
