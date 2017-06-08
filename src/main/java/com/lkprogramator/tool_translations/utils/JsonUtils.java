package com.lkprogramator.tool_translations.utils;

import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Save Map<String, String> to JSON file
     *
     * @param mapObject map to be saved as JSON file.
     * @param jsonFilePath Path where file will be generated.
     */
    public static void writeMapToJsonFile(Map<String, String> mapObject, String jsonFilePath) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(jsonFilePath), mapObject);
        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Unable Access the file. ").addValue("jsonFilePath", jsonFilePath);
            LOGGER.error(lm.toString(), ex);
        }
    }

    /**
     * Converts JSON string to List of Objects, represented by given class type.
     * Conversion is done using Jackson processor.
     *
     * @param content Json string to convert.
     * @param valueType class type to return.
     * @return List of objects representing converted Json string.
     */
    public static <T> List<T> readValuesInList(String content, Class<T> valueType) {
        List<T> out = null;
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        JavaType jType = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.setAnnotationIntrospector(introspector);
        try {
            out = mapper.readValue(content, jType);
        } catch (IOException e) {
            LoggerMessage lm = new LoggerMessage("Unable to convert json string to list").addValue("content", content)
                    .addValue("valueType", valueType.toString());
            LOGGER.error(lm.toString(), e);

        }
        return out;
    }

    /**
     * Converts JSON string to Object, represented by given class type.
     * Conversion is done using Jackson processor.
     *
     * @param <T>
     * @param jsonFilePath path to Json
     * @param valueType class type to return.
     * @return List of objects representing converted Json string.
     *
     */
    public static <T> T readValuesFromFileAsObject(String jsonFilePath, Class<T> valueType) {

        T loaded = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            loaded = mapper.readValue(new File(jsonFilePath), valueType);
        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Unable to convert json string to object ").addValue("jsonFilePath", jsonFilePath)
                    .addValue("valueType", valueType.toString());
            LOGGER.error(lm.toString(), ex);

        }

        return loaded;
    }

}
