package com.example.citymanagementnew.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaProducerConfig {

    @Value("${app.kafka.producer.topic}")
    private String topic;
    @Value(value = "${app.kafka.producer.number-partition}")
    private String numberPartition;
    @Value(value = "${app.kafka.producer.replication-factor}")
    private String replicationFactor;

    @Bean
    public NewTopic newTopic(){
        return new NewTopic(topic, Integer.parseInt(numberPartition), Short.parseShort(replicationFactor));
    }
}
