package com.victor.kafka.embedded;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.http.client.ClientProtocolException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.victor.kafka.embedded.services.SinaisVitaisService;


@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload = null;
    
	@Value(value = "${eglicemia.api.url}")
	private String eglicemiaApiUrl;
	
	@Value(value = "${eglicemia.api.url.interval}")
	private Integer interval;
	
	@Autowired
	private SinaisVitaisService sinaisVitais;

    @KafkaListener(topics = "glicemias", groupId = "fiap")
    public void receive(ConsumerRecord<?, ?> consumerRecord) throws ClientProtocolException, IOException, InterruptedException {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        System.out.println("******* TESTE glicemia = " + consumerRecord.value());
        System.out.println("sinaisVitais.getEglicemiaApiUrl() = " + sinaisVitais.getEglicemiaApiUrl());
        sinaisVitais.post(consumerRecord.value().toString());
        setPayload(consumerRecord.toString());
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getPayload() {
        return payload;
    }

    private void setPayload(String payload) {
        this.payload = payload;
    }
    
    
   
}
