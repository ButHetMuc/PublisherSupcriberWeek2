package lap2;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer  implements Runnable{
	ActiveMQConnectionFactory connectionFactory = null; 
	
	public TopicProducer(ActiveMQConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			// first create a connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			// now create a session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE	);
			
			// create a topic. if the topic exist, it will return that
			Destination destination =  session.createTopic("butle conversation");
			
			//create a messageProducer from the session to the topic or queue 
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			//create a message for the current topic
			String text = "hello i am butle";
			TextMessage textMessage = session.createTextMessage(text);
			
			//send the message to the topic 
			producer.send(textMessage);
			session.close();
			connection.close();
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.printStackTrace();
		}
		
	}

}
