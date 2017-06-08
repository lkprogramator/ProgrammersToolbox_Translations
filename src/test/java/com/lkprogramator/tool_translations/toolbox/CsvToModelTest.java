package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import com.lkprogramator.tool_translations.utils.JsonUtils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author LKprogramator
 */
public class CsvToModelTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvToModelTest.class);

    static String pathToCsv;
    static List<Map<String, String>> csvData;
    static List<TranslationsByLanguage> translationsByLanguage;

    @Before
    public void setUpClass() {

        Path resourceDirectory = Paths.get("src/test/resource");
        pathToCsv = resourceDirectory.toString() + "/testcsv.csv";

        ClassLoader classLoader;
        classLoader = CsvToModelTest.class.getClassLoader();

        File file = new File(classLoader.getResource("TranslationsByLanguageTestData.json").getFile());

        String testDataFromFile = CommonUtils.readFileToString(file, "UTF-8");

        translationsByLanguage = JsonUtils.readValuesInList(testDataFromFile, TranslationsByLanguage.class);

        csvData = new ArrayList<>();

        Map<String, String> csvRow1 = new HashMap<String, String>();
        csvRow1.put("KEY", "AF");
        csvRow1.put("CS", "Afghánistán");
        csvRow1.put("EN", "Afghanistan");

        Map<String, String> csvRow2 = new HashMap<String, String>();
        csvRow2.put("KEY", "AL");
        csvRow2.put("CS", "Albánie");
        csvRow2.put("EN", "Albania");

        Map<String, String> csvRow3 = new HashMap<String, String>();
        csvRow3.put("KEY", "DZ");
        csvRow3.put("CS", "Alžírsko");
        csvRow3.put("EN", "Algeria");

        Map<String, String> csvRow4 = new HashMap<String, String>();
        csvRow4.put("KEY", "AD");
        csvRow4.put("CS", "Andorra");
        csvRow4.put("EN", "Andorra");

        Map<String, String> csvRow5 = new HashMap<String, String>();
        csvRow5.put("KEY", "AO");
        csvRow5.put("CS", "Angola");
        csvRow5.put("EN", "Angola");

        csvData.add(csvRow1);
        csvData.add(csvRow2);
        csvData.add(csvRow3);
        csvData.add(csvRow4);
        csvData.add(csvRow5);

    }

    /**
     * Test of processCsvToModel method, of class CsvToModel.
     */
    @Test
    public void testProcessCsvToModel() {

        List<TranslationsByLanguage> result = new ArrayList<TranslationsByLanguage>();

        CsvToModel instance = new CsvToModel();

        result = instance.processCsvToModel(pathToCsv, ';');

        assertEquals(translationsByLanguage, result);

    }

    /**
     * Test of assambleModel method, of class CsvToModel.
     */
    @Test
    public void assambleModel() {

        CsvToModel instance = new CsvToModel();

        List<TranslationsByLanguage> result = instance.assambleModel(csvData);

        assertEquals(translationsByLanguage, result);

    }

}
