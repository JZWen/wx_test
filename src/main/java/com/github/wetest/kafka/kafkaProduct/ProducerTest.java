package com.github.wetest.kafka.kafkaProduct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author JZWen
 * @date 2020/11/30
 */

@Service
public class ProducerTest {

    public void send() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "broker1:9092,broker2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(properties);
//        ProducerRecord producerRecord = new ProducerRecord();

        //producer.send();


    }


}
