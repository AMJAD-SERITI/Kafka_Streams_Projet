package org.amjad.streamsservice;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;


@SpringBootApplication
public class StreamsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamsServiceApplication.class, args);

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-service");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.22.101.156:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream("input-topic");
        stream.mapValues((ValueMapper<String, String>) value -> value.toLowerCase()).to("output-topic");
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }

}