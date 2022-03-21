package com.example.datagateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	private static String inputString = "{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":0.55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"},{\"id\":\"1002\",\"type\":\"Chocolate\"},{\"id\":\"1003\",\"type\":\"Blueberry\"},{\"id\":\"1004\",\"type\":\"Devil's Food\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5005\",\"type\":\"Sugar\"},{\"id\":\"5007\",\"type\":\"Powdered Sugar\"},{\"id\":\"5006\",\"type\":\"Chocolate with Sprinkles\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]}";

	public static void main(String[] args) throws JsonProcessingException {
		// input, output map
		Map<String, Object> input = getInput();
		
		Map<String, Object> output = createDeepCopy(input);

		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("********************************************************************************");
		System.out.println("Input");
		System.out.println("********************************************************************************");
		System.out.println("--------------------------------------------------------------------------------");
		
		input.forEach((k,v) -> {
			System.out.println("key: " + k + " value:" + v);
		});

		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("********************************************************************************");
		System.out.println("Output");
		System.out.println("********************************************************************************");
		System.out.println("--------------------------------------------------------------------------------");
		
		output.forEach((k,v) -> {
			System.out.println("key: " + k + " value:" + v);
		});
		
	}

	private static Map<String, Object> createDeepCopy(Map<String, Object> input) {
		Map<String, Object> output = new HashMap<>(input.size());
		// iterate over the map
		input.forEach((k, v) -> {
//			System.out.println("key: " + k + " value:" + v);
			// instanceOf check/ typeCastException check
			// primitive
			Object outputData = helperFn(v);
			if (output != null) {
				output.put(k.toUpperCase(), outputData);
			}
		});
		return output;
	}

	private static Object helperFn(Object v) {
		if (validateType(v, String.class)) {
			return new String(v.toString()).toUpperCase();

		} else if (validateType(v, Double.class)) {
			return new Double(v.toString());

		} else if (validateType(v, List.class)) {
			// iterate over the list
			List<Object> objects = (ArrayList<Object>) v;
			List<Object> outputList = new ArrayList<>(objects.size());

			objects.forEach(s -> {
				helperFn(s);
				outputList.add(s);
			});

			return outputList;

		} else if (validateType(v, Map.class)) {
			// iterate over the list
			Map<String, Object> objects = (Map) v;
			objects.forEach((k, v1) -> {
				helperFn(v1);
			});
			return objects;
		}
		return null;
	}

	private static <T> boolean validateType(Object data, Class<T> classType) {

		try {

			T castedData = (T) data;

			return true;
		} catch (ClassCastException e) {

			return false;
		} catch (Exception e) {

			throw e;
		}
	}

	private static Map<String, Object> getInput() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> input = objectMapper.readValue(inputString, Map.class);
		return input;
	}

}
