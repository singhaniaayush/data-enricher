package com.example.datagateway.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.datagateway.constants.ApplicationConstants;
import com.example.datagateway.service.DataGatewayServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataGatewayListener {

	@Autowired
	private DataGatewayServiceImpl dataGatewayServiceImpl;

	@KafkaListener(topics = ApplicationConstants.DATA_ENRICHER_TOPIC, groupId = ApplicationConstants.KAFKA_GROUP_ID)
	public void dataCreatorListener(@Payload JsonNode message,
			@Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) Integer key,
			@Header(KafkaHeaders.RECORD_METADATA) ConsumerRecordMetadata meta) {
		log.info("DataGatewayListener|message:{}", message);
		log.info("DataGatewayListener|offset:{}", meta.offset());
		log.info("DataGatewayListener|partition:{}", meta.partition());
		log.info("DataGatewayListener|timestamp:{}", meta.timestamp());
		log.info("DataGatewayListener|timestampType:{}", meta.timestampType());
		log.info("DataGatewayListener|key:{}", key);

		dataGatewayServiceImpl.encrichData(message);
	}

}
