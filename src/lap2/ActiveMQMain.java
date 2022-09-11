package lap2;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQMain  {
	public static void main(String[] args) throws JMSException{
		
		//create the connection factory
		ActiveMQConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory("tcp://localhost:61616");
		//create the consumer. it will wait to listen the topic
		Thread topicConsumerThread = new Thread(new TopicConsumer(connectionFactory));
		topicConsumerThread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create a message. as soon as the message published on the topic,
		// it will be consume by consumer
		Thread topicProducerThread = new Thread(new TopicProducer(connectionFactory));
		topicProducerThread.start();
	}
}
