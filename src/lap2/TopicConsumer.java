package lap2;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer implements Runnable{
	ActiveMQConnectionFactory activeMQConnectionFactory = null;
	
	public TopicConsumer(ActiveMQConnectionFactory activeMQConnectionFactory) {
		this.activeMQConnectionFactory = activeMQConnectionFactory;
	}
	@Override
	public void run() {
		Connection connection;
		try {
			connection = activeMQConnectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination topicDestination = session.createTopic("butle conversation");
			
			MessageConsumer messageConsumer = session.createConsumer(topicDestination);
			
			//get messages
			Message message = messageConsumer.receive();
			
			TextMessage textMessage = (TextMessage) message;
			System.out.println(textMessage.getText());
			
			session.close();
			connection.close();
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
	}

}
