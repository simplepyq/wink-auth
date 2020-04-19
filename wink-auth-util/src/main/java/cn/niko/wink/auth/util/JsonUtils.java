package cn.niko.wink.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niko.pan
 * @version 1.0.0
 * @since 2020/4/18 18:25
 */
@Log4j2
public class JsonUtils {

    private static final String EMPTY_JSON = "{}";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils(){
    }

    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String format(Object pojo) {
        try {
            return MAPPER.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException, pojo = {}", pojo, e);
            return EMPTY_JSON;
        }
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("IOException, json = {}, clazz = {}", json, clazz, e);
        }
        return null;
    }

    public static <T> List<T> parseToList(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz));
        } catch (IOException e) {
            log.error("IOException, json = {}, clazz = {}", json, clazz, e);
        }
        return Lists.newArrayList();
    }


}
