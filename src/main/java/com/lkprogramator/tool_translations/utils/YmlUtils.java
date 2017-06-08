package com.lkprogramator.tool_translations.utils;

/**
 *
 * @author LKprogramator
 */
import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

public class YmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(YmlUtils.class);

    /**
     * Reads data from file
     *
     * @param path Path to file
     * @return Data from file as Map
     * @throws IOException
     */
    public static HashMap<String, String> readMap(File path) throws IOException {

        FileInputStream is = new FileInputStream(path);

        return readMap(is);
    }

    /**
     * Reads data from file
     *
     * @param is Input stream from file
     * @return Data from file as Map
     * @throws IOException
     */
    public static HashMap<String, String> readMap(InputStream is) throws IOException {

        Yaml yaml = new Yaml();

        HashMap<String, String> casted = null;

        try {
            @SuppressWarnings("unchecked")
            HashMap<Object, Object> loaded = HashMap.class.cast(yaml.load(is));

            casted = new HashMap<String, String>();

            for (Object key : loaded.keySet()) {
                if (loaded.get(key) != null) {
                    casted.put(key.toString(), loaded.get(key).toString());
                }
            }
        } catch (ClassCastException ex) {
            LoggerMessage lm = new LoggerMessage("Class Cast has faild ");
            LOGGER.error(lm.toString(), ex);
        }

        return casted;
    }

    /**
     * Reads data from file with given structure
     *
     * @param url Url format of path to file
     * @param valueType Data type of value
     * @return Data from file in given class
     * @throws IOException
     */
    public static <T> T read(URL url, Class<T> valueType) throws IOException {

        Yaml yaml = new Yaml();

        T loaded = valueType.cast(yaml.load(url.openStream()));

        return loaded;
    }

    /**
     * Store data to yaml file
     *
     * @param yourMap Data in as map HashMap
     * @param saveLocation File to store data to
     */
    public static void save(HashMap<String, String> yourMap, File saveLocation) {
        Yaml yaml = new Yaml();
        try {
            yaml.dump(yourMap, new FileWriter(saveLocation));
        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Save to file has faild ").addValue("saveLocation", saveLocation.getAbsolutePath());
            LOGGER.error(lm.toString(), ex);
        }
    }

    /**
     * Store data to yaml file
     *
     * @param yourMap Data in as map
     * @param saveLocation File to store data to
     */
    public static void saveMap(Map<String, String> yourMap, String saveLocation) {

        Yaml yaml = new Yaml();
        try {

            File f = new File(saveLocation);
            FileUtils.writeStringToFile(f, yaml.dump(yourMap), "UTF-8");

        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Save to file has faild ").addValue("saveLocation", saveLocation);
            LOGGER.error(lm.toString(), ex);
        }

    }

}
