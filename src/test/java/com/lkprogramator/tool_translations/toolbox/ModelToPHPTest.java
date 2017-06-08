package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import com.lkprogramator.tool_translations.utils.JsonUtils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
public class ModelToPHPTest {

    static String pathToResultFolder;

    static List<TranslationsByLanguage> translationsByLanguage;

    @BeforeClass
    public static void setUpClass() {

        Path resourceDirectory = Paths.get("src/test/resource");
        pathToResultFolder = resourceDirectory.toString() + "/genTestFiles/";

        ClassLoader classLoader;
        classLoader = ModelToPHPTest.class.getClassLoader();

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
     * Test of ConvertModelToPHP method, of class ModelToPHP.
     */
    @Test
    public void testConvertModelToPHP() {
        System.out.println("ConvertModelToPHP");

        List<TranslationsByLanguage> model = translationsByLanguage;

        Path resultFolder = Paths.get(pathToResultFolder);

        ModelToPHP instance = new ModelToPHP();

        instance.ConvertModelToPHP(model, resultFolder);

        File file = new File(pathToResultFolder + "EN.php");

        assertTrue(file.exists());
// TODO: read the file. Check that it contains what it's supposed to 
    }

}
