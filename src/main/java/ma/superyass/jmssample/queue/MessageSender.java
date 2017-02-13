package ma.superyass.jmssample.queue;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author superyass
 */
@Stateless
public class MessageSender {

    @Inject
    JMSContext context;

    //this is automatically added with the wildfly-maven-plugin (pom)
    @Resource(mappedName = "java:global/jms/myJmsQueue")
    Queue queue;

    public void sendMessage(String message) {
        context.createProducer().send(queue, message);
    }

}
