package com.lkprogramator.tool_translations.utils;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.translation.TranslationsByLanguageTest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LKprogramator
 */
public class JsonUtilsTest {

    static String pathToResultFolder;

    static List<TranslationsByLanguage> translationsByLanguage;

    static ClassLoader classLoader;

    @BeforeClass
    public static void setUpClass() {

        Path resourceDirectory = Paths.get("src/test/resource");

        pathToResultFolder = resourceDirectory.toString() + "/genTestFiles/";

        classLoader = JsonUtilsTest.class.getClassLoader();

        File file = new File(classLoader.getResource("TranslationsByLanguageTestData.json").getFile());

        String testDataFromFile = CommonUtils.readFileToString(file, "UTF-8");

        translationsByLanguage = JsonUtils.readValuesInList(testDataFromFile, TranslationsByLanguage.class);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of writeMapToJsonFile method, of class JsonUtils.
     */
    @Test
    public void testWriteMapToJsonFile() {
        System.out.println("writeMapToJsonFile");
        Map<String, String> mapObject = new HashMap<String, String>();

        mapObject.put("AF", "Afghánistán");
        mapObject.put("AL", "Albánie");
        mapObject.put("DZ", "Alžírsko");
        mapObject.put("AD", "Andorra");
        mapObject.put("AO", "Angola");

        String jsonFilePath = pathToResultFolder + "mapToJsonTest.json";
        JsonUtils.writeMapToJsonFile(mapObject, jsonFilePath);

        File file = new File(jsonFilePath);
        String result = CommonUtils.readFileToString(file, "UTF-8");
        String expResult = "{\"AD\":\"Andorra\",\"AF\":\"Afghánistán\",\"DZ\":\"Alžírsko\",\"AL\":\"Albánie\",\"AO\":\"Angola\"}";

        assertEquals(expResult, result);
    }

    /**
     * Test of prepareStringForJson method, of class JsonUtils.
     */
    @Test
    public void testReadValuesInList() {

        File file = new File(classLoader.getResource("TranslationsByLanguageTestData.json").getFile());

        String testDataFromFile = CommonUtils.readFileToString(file, "UTF-8");

        List<TranslationsByLanguage> result = JsonUtils.readValuesInList(testDataFromFile, TranslationsByLanguage.class);

        List<TranslationsByLanguage> expResult = translationsByLanguage;

        assertEquals(expResult, result);

    }

}
