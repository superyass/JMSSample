package ma.superyass.jmssample;

import java.time.LocalDateTime;
import ma.superyass.jmssample.queue.MessageSender;
import ma.superyass.jmssample.queue.MessageReceiver;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author superyass
 */
@javax.ejb.Startup
@Stateless
public class Startup {
    
    @EJB
    MessageSender messageSender;
    @EJB
    MessageReceiver messageReceiver;
    
    //every 10 seconds
    @Schedule(hour = "*",minute = "*", second = "*/10",persistent = false)
    public void schedule(){
        System.out.println("[sending a message:]");
        messageSender.sendMessage("ceci est un message, date :"+LocalDateTime.now());
        
        System.out.println(messageReceiver.receiveMessageSync());
        
        //wont work due this bug https://java.net/jira/browse/MQ-264
        messageReceiver.receiveMessageAsync();
    }
    
    
}
