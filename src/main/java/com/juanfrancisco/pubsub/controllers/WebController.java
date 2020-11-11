package com.juanfrancisco.pubsub.controllers;

import com.juanfrancisco.pubsub.publisher.PublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class WebController {

    @Autowired
    private PublisherImpl publisher;

    @RequestMapping("/send")
    public String sendMsg(@RequestParam("msg")String msg){
        publisher.produceMsg(msg);
        return "Done";
    }
}
