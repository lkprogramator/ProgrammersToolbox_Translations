package com.lkprogramator.tool_translations.utils;

import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

/**
 *
 * @author LKprogramator
 */
public class CommonUtils {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * Assamble path to file from path to parent folder, name of file and file
     * extension
     *
     * @param pathToResultFolder
     * @param fileName
     * @param fileExtension without dot
     * @return Fully assambled path
     */
    public static String createPathForResultFile(String pathToResultFolder, String fileName, String fileExtension) {

        if (pathToResultFolder.endsWith("/")) {
            return pathToResultFolder + fileName + "." + fileExtension;
        } else {
            return pathToResultFolder + "/" + fileName + "." + fileExtension;
        }

    }

    /**
     * Save String to file
     *
     * @param filePath
     * @param content
     */
    public static void saveStringToFile(String filePath, String content) {

        try {
            FileUtils.writeStringToFile(new File(filePath), content);
        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Save to file has faild ");
            LOGGER.error(lm.toString(), ex);
        }

    }

    /**
     * File content as String
     *
     * @param filePath
     * @param encoding
     * @return the file content as string, never null
     */
    public static String readFileToString(String filePath, String encoding) {

        String fileAsString = "";

        try {

            fileAsString = FileUtils.readFileToString(new File(filePath), encoding);

        } catch (IOException ex) {

            LoggerMessage lm = new LoggerMessage("Reading from file has faild ").addValue("filePath", filePath);
            LOGGER.error(lm.toString(), ex);

        }

        return fileAsString;

    }

    /**
     * File content as String
     *
     * @param file
     * @param encoding
     * @return the file content as string, never null
     */
    public static String readFileToString(File file, String encoding) {

        String fileAsString = "";

        try {

            fileAsString = FileUtils.readFileToString(file, encoding);

        } catch (IOException ex) {
            LoggerMessage lm = new LoggerMessage("Reading from file has faild ").addValue("file", file.getAbsolutePath()).addValue("encoding", encoding);
            LOGGER.error(lm.toString(), ex);
        }

        return fileAsString;

    }

    /**
     * Clean unwanted characters from maps values
     *
     * @param mapObject String to by cleanup
     * @return Map with out unwanted characters
     *
     */
    public static Map<String, String> sanitizeMapsValues(Map<String, String> mapObject) {

        for (Map.Entry<String, String> entry : mapObject.entrySet()) {

            entry.setValue(sanitizeString(entry.getValue()));

        }
        return mapObject;
    }

    /**
     * Clean unwanted characters from string
     *
     * @param text String to by cleanup
     * @return String with out unwanted characters
     *
     */
    public static String sanitizeString(String text) {

        text = text.replace("\n", "").replace("\r", "").replace("\"", "\\\"").replace("\'", "\\\'").trim();

        return text;
    }

}
