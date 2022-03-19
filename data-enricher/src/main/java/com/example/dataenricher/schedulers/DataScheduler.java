package com.example.dataenricher.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dataenricher.constants.ApplicationConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataScheduler {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private ObjectMapper mapper;

	String dataJson = "{\"message\":\"Hi.. There..!!\"}";

	@Scheduled(fixedDelay = 1000)
	public void createData() {
		JsonNode data = mapper.valueToTree(dataJson);
		kafkaTemplate.send(ApplicationConstants.DATA_INITIATOR_TOPIC, data);
	}

}
