package com.dm.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggerHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "Error converting object to JSON: " + e.getMessage();
        }
    }
}