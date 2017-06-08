package com.lkprogramator.tool_translations.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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
public class CommonUtilsTest {

    public CommonUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
     * Test of createPathForResultFile method, of class CommonUtils.
     */
    @Test
    public void testCreatePathForResultFile() {
        System.out.println("createPathForResultFile");
        String pathToResultFolder = "my/test/path/to/folder/with";
        String fileName = "test";
        String fileExtension = "file";
        String expResult = "my/test/path/to/folder/with/test.file";
        String result = CommonUtils.createPathForResultFile(pathToResultFolder, fileName, fileExtension);
        assertEquals(expResult, result);

    }

    /**
     * Test of createPathForResultFile method, of class CommonUtils.
     */
    @Test
    public void testCreatePathForResultFileV2() {
        System.out.println("createPathForResultFile");
        String pathToResultFolder = "my/test/path/to/folder/with/";
        String fileName = "test";
        String fileExtension = "file";
        String expResult = "my/test/path/to/folder/with/test.file";
        String result = CommonUtils.createPathForResultFile(pathToResultFolder, fileName, fileExtension);
        assertEquals(expResult, result);

    }

    /**
     * Test of saveStringToFile method, of class CommonUtils.
     */
    @Test
    public void testSaveStringToFile() {
        System.out.println("saveStringToFile");

        Path resourceDirectory = Paths.get("src/test/resource");

        String filePath = resourceDirectory.toString() + "/genTestFiles/TestStringToFile.txt";
        String content = "Test content.";
        CommonUtils.saveStringToFile(filePath, content);

        File file = new File(resourceDirectory.toString() + "/genTestFiles/TestStringToFile.txt");
        String result = CommonUtils.readFileToString(file, "UTF-8");

        assertEquals(content, result);

    }

    /**
     * Test of readFileToString method, of class CommonUtils.
     */
    @Test
    public void testReadFileToString_String_String() {
        System.out.println("readFileToString");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("readFromFileTest.json").getFile());
        String filePath = file.getAbsolutePath();
        String encoding = "UTF-8";
        String expResult = "{\"AD\":\"Andorra\",\"AF\":\"Afghanistan\",\"DZ\":\"Algeria\",\"AL\":\"Albania\",\"AO\":\"Angola\"}";

        String result = CommonUtils.readFileToString(filePath, encoding);
        System.out.println("readFileToString result   : " + result);
        System.out.println("readFileToString expResult: " + expResult);

        assertEquals(expResult, result);

    }

    /**
     * Test of readFileToString method, of class CommonUtils.
     */
    @Test
    public void testReadFileToString_File_String() {
        System.out.println("readFileToString");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("readFromFileTest.json").getFile());

        String encoding = "UTF-8";
        String expResult = "{\"AD\":\"Andorra\",\"AF\":\"Afghanistan\",\"DZ\":\"Algeria\",\"AL\":\"Albania\",\"AO\":\"Angola\"}";
        String result = CommonUtils.readFileToString(file, encoding);
        assertEquals(expResult, result);
    }

    /**
     * Test of sanitizeMapsValues method, of class CommonUtils.
     */
    @Test
    public void testSanitizeMapsValues() {
        System.out.println("sanitizeMapsValues");
        Map<String, String> mapObject = new HashMap<String, String>();

        mapObject.put("row", "row Lorem ipsum dolor sit amet,\r Lorem ipsum dolor sit amet.");
        mapObject.put("newline", "newline Lorem ipsum dolor sit amet,\n Lorem ipsum dolor sit amet.");
        mapObject.put("quote", "quote Lorem ipsum dolor sit amet,'Lorem' ipsum dolor sit amet.");

        Map<String, String> expResult = new HashMap<String, String>();
        expResult.put("row", "row Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet.");
        expResult.put("newline", "newline Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet.");
        expResult.put("quote", "quote Lorem ipsum dolor sit amet,\\\'Lorem\\\' ipsum dolor sit amet.");

        Map<String, String> result = CommonUtils.sanitizeMapsValues(mapObject);
        assertEquals(expResult, result);

    }

    /**
     * Test of sanitizeString method, of class CommonUtils.
     */
    @Test
    public void testSanitizeString() {
        System.out.println("sanitizeString");

        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, quis nostrud exercitation ullamco 'laboris' nisi ut aliquip ex ea commodo consequat.\r Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        String expResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco \\\'laboris\\\' nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        String result = CommonUtils.sanitizeString(text);

        System.out.println("prepareStringForJson: " + expResult);
        System.out.println("prepareStringForJson: " + result);
        assertEquals(expResult, result);

    }

}
