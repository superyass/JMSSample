package ma.superyass.jmssample.queue;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author superyass
 */

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup",propertyValue = "java:global/jms/myJmsQueue")
})
public class MessageReceiverEJB implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println("Received in EJB :"+msg.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(MessageReceiverEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
