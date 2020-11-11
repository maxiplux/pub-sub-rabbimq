package com.juanfrancisco.pubsub.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PublisherImpl implements Publisher {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;


    @Value("${sample.rabbitmq.routingkey}")
    private String routerKey;

    @Override
    public void produceMsg(String msg){
        CorrelationData correlationData = new CorrelationData();
        amqpTemplate.convertAndSend(exchange,routerKey, ""+msg);




        log.info("Send msg = " + msg);
    }
}
