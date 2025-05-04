package br.com.pizzaAPI.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Logs {

    public static void logEndpoint(String metodo, String endpoint, String className) {
        Logger LOGGER = LoggerFactory.getLogger(className);
        LOGGER.info("Executing method: {} | Endpoint: \"{}\"", metodo, endpoint);
    }

    public static void logDtoService(String nomeMetodo, String dto, String className) throws JsonProcessingException {
        Logger LOGGER = LoggerFactory.getLogger(className);
        ObjectMapper om = new ObjectMapper().configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        Map<String, Object> map = om.readValue(dto, HashMap.class);
        String json = om.writeValueAsString(map);
        LOGGER.info("Executing method {} with the entry: {}", nomeMetodo, json);
    }
}
