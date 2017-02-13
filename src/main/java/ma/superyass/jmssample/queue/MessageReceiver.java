package ma.superyass.jmssample.queue;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 *
 * @author superyass
 */
@Stateless
public class MessageReceiver {

    @Inject
    private JMSContext context;

    //this is automatically added with the wildfly-maven-plugin (pom)
    @Resource(mappedName = "java:global/jms/myJmsQueue")
    Queue myQueue;

    public String receiveMessageSync() {
        String message = context.createConsumer(myQueue).receiveBody(String.class,1500);
        return "Received Sync :" + message;
    }
    
    public void receiveMessageAsync() {
        context.createConsumer(myQueue).setMessageListener((Message msg) -> {
            try {
                System.out.println("Received Async :"+msg.getBody(String.class));
            } catch (JMSException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
