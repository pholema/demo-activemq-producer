package com.example.activemq.producer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Producer {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;

	@Scheduled(fixedDelay = 3000)
	public void send() {
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logger.info("send message..."+now);
        jmsMessagingTemplate.convertAndSend(queue, "send time:"+now);
    }

}
