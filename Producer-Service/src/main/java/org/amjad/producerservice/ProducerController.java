package org.amjad.producerservice;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        String transformedMessage;
        if (message.equals(message.toUpperCase())) {
            transformedMessage = message.toLowerCase();
        } else {
            transformedMessage = message.toUpperCase();
        }
        kafkaTemplate.send("input-topic", transformedMessage);
        return ResponseEntity.ok("Message sent to Kafka topic: " + transformedMessage);
    }
}