package com.example.datagateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.datagateway.constants.ApplicationConstants;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DataGatewayServiceImpl {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void encrichData(JsonNode data) {
		kafkaTemplate.send(ApplicationConstants.DATA_GATEWAY_TOPIC, data);
	}

}
