package com.juanfrancisco.pubsub.subcriber;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Component
@Slf4j
public class SubcriberImpl implements Subcriber {

    @Value(value = "${jsa.rabbitmq.exchange}")
    private String queue;

    @Value(value = "${jsa.rabbitmq.queue2}")
    private String queue2;

    @Value(value = "${jsa.rabbitmq.exchange}")
    private String exchange;


    @Override
    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(String msg) {
        log.info("Simple recievedMessage", msg);

    }





    @SneakyThrows
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "${jsa.rabbitmq.queue}"),
                    exchange = @Exchange(value = "${jsa.rabbitmq.exchange}"),
                    key = "${sample.rabbitmq.routingkey}"
            ))
    public void process(String str,Channel channel, Message message,@Header(AmqpHeaders.DELIVERY_TAG) long tag)  {




        try {
            log.info("process: " + str );
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);



                    //channel.basicAck( tag, true);
            //Deny the news, be denied, then rejoin the team and be consumed again
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            //Reject the message, the message will be discarded and will not be returned to the queue
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception  e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            log.error(e.getMessage());
        }
    }
}
