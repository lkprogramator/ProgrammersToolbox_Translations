package com.lkprogramator.tool_translations.utils;

/*
 * @author LKprogramator
 */
import com.fasterxml.jackson.databind.MappingIterator;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * Extracts data from csv file given
     *
     * @param csvFilePath
     * @param separator The separator can be: ',', ';' or '\t'
     * @return List of Map reprezentations of data
     * @throws IOException when csv file has invalid format
     */
    public List<Map<String, String>> parseCsvFile(String csvFilePath, char separator) throws IOException {

        LoggerMessage lm = new LoggerMessage("parsing Csv File ").addValue("csvFilePath", csvFilePath);
        LOGGER.info(lm.toString());

        List<Map<String, String>> parseedCsv = new ArrayList<>();

        CsvMapper mapper = new CsvMapper();

        CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(separator); // use first row as header

        MappingIterator<Map<String, String>> it = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(new File(csvFilePath));

        while (it.hasNext()) {
            Map<String, String> rowAsMap = it.next();
            parseedCsv.add(rowAsMap);
        }

        return parseedCsv;
    }

    /**
     * Convert the given List of String keys-values as a CSV String.
     *
     * @param csvAsListOfMaps The List of key-value pairs
     * @param separator The separator can be: ',', ';' or '\t'
     *
     * @return The generated CSV string
     */
    public String getCSV(List<Map<String, String>> csvAsListOfMaps, String separator) {
        Set<String> headers = collectHeaders(csvAsListOfMaps);
        String csvString = StringUtils.join(headers.toArray(), separator) + "\n";

        for (Map<String, String> map : csvAsListOfMaps) {
            csvString = csvString + getSeperatedColumns(headers, map, separator) + "\n";
        }

        return csvString;
    }

    /**
     * Write the given CSV string to the given file.
     *
     * @param csvString The csv string to write into the file
     * @param fileName The file to write (including the path)
     */
    public void writeToFile(String csvString, String fileName) {
        try {
            FileUtils.writeStringToFile(new File(fileName), csvString);
        } catch (IOException ex) {

            LoggerMessage lm = new LoggerMessage("writing To File has faild ").addValue("fileName", fileName).addValue("csvString", csvString);
            LOGGER.error(lm.toString(), ex);

        }
    }

    /**
     * Get separated comlumns used a separator (comma, semi column, tab).
     *
     * @param headers The CSV headers
     * @param map Map of key-value pairs contains the header and the value
     *
     * @return a string composed of columns separated by a specific separator.
     */
    private String getSeperatedColumns(Set<String> headers, Map<String, String> map, String separator) {
        List<String> items = new ArrayList<String>();
        for (String header : headers) {
            String value = map.get(header) == null ? "" : map.get(header).replace(separator, "");
            items.add(value);
        }

        return StringUtils.join(items.toArray(), separator);
    }

    /**
     * Get the CSV header.
     *
     * @param flatJson
     *
     * @return a Set of headers
     */
    public Set<String> collectHeaders(List<Map<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<String>();

        for (Map<String, String> map : flatJson) {
            headers.addAll(map.keySet());
        }

        return headers;
    }

    /**
     * Extracts data from csv file into list of key-value pairs
     *
     * @param csvFilePath
     * @param CSVSeparator The separator can be: ',', ';' or '\t'
     *
     * @return The List of key-value pairs
     */
    public List<Map<String, String>> readCsvAsListOfMaps(String csvFilePath, char CSVSeparator) {

        List<Map<String, String>> csvData = new ArrayList<Map<String, String>>();

        try {
            csvData = parseCsvFile(csvFilePath, CSVSeparator);
        } catch (IOException ex) {

            LoggerMessage lm = new LoggerMessage("Loading of CSV file has faild ").addValue("csvFilePath", csvFilePath);
            LOGGER.error(lm.toString(), ex);

        }

        return csvData;

    }

}
