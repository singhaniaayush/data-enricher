package com.example.dataenricher.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.dataenricher.constants.ApplicationConstants;
import com.example.dataenricher.service.DataEnricherServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataEnricherListener {

	@Autowired
	private DataEnricherServiceImpl dataEnricherServiceImpl;

	@KafkaListener(topics = ApplicationConstants.DATA_INITIATOR_TOPIC, groupId = ApplicationConstants.KAFKA_GROUP_ID)
	public void dataCreatorListener(@Payload JsonNode message,
			@Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) Integer key,
			@Header(KafkaHeaders.RECORD_METADATA) ConsumerRecordMetadata meta) {
		log.info("CacheListener|message:{}", message);
		log.info("CacheListener|offset:{}", meta.offset());
		log.info("CacheListener|partition:{}", meta.partition());
		log.info("CacheListener|timestamp:{}", meta.timestamp());
		log.info("CacheListener|timestampType:{}", meta.timestampType());
		log.info("CacheListener|key:{}", key);

		dataEnricherServiceImpl.encrichData(message);
	}

}
