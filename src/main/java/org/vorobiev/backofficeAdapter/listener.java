package org.vorobiev.backofficeAdapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class listener {

    @Qualifier("JmsTemplateQueue")
    private JmsTemplate jmsQueueTemplate;

    @JmsListener(destination = "back_in")
    public void  receiveMessageModel(JsonNode jsonNode ) throws JMSException, Exception {

             jmsQueueTemplate.convertAndSend("back_out",jsonNode);
    }


    @JmsListener(destination = "notify_in")
    public void  receiveMessageModelN(JsonNode jsonNode ) throws JMSException, Exception {

           jmsQueueTemplate.convertAndSend("notify_out",jsonNode);
    }


}