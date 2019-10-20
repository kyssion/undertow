package org.linuxq.dictionary.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String objectToString(Object item) throws JsonProcessingException {
        return mapper.writeValueAsString(item);
    }

    public static <T> T stringToObject(String item ,Class<T> itemType) throws IOException {
        return (T) mapper.readValue(item,itemType);
    }
}
