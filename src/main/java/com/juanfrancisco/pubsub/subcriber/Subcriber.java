package com.juanfrancisco.pubsub.subcriber;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

public interface Subcriber {


    void recievedMessage(String msg);


    void process(String str,Channel channel, Message message,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException;
}
