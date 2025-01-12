package org.amjad.consumerservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "output-topic", groupId = "kafka-consumer-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}