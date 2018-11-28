package com.tanghao.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ConsumerDemo {
	
	private static Logger log = LoggerFactory.getLogger(ConsumerDemo.class);
	private static String topic = "pay";
	public static void main(String[] args) {
		newConsumer();
    }
	
	private static void newConsumer(){
		  	Properties pro = new Properties();
	        pro.setProperty("bootstrap.servers", "192.168.1.105:9092");
	        pro.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			pro.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	        pro.setProperty("group.id", "group1");  
	        KafkaConsumer<String,Object> consumer = new KafkaConsumer<String,Object>(pro);
	        consumer.subscribe(Arrays.asList(topic));
	        while (true) {
	            ConsumerRecords<String, Object> records = consumer.poll(100);
	            try {
	                Thread.sleep(20000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            for (ConsumerRecord<String, Object> record : records) {
	                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
	                System.out.println();
	            }
	        }
	}
}
