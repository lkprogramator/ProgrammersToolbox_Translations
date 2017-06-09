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
public class ModelToYamlTest {

    static String pathToResultFolder;
    static List<TranslationsByLanguage> translationsByLanguage;
    static ClassLoader classLoader;

    @BeforeClass
    public static void setUpClass() {

        Path resourceDirectory = Paths.get("src/test/resource");
        pathToResultFolder = resourceDirectory.toString();

        classLoader = ModelToYamlTest.class.getClassLoader();

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
     * Test of ConvertModelToYaml method, of class ModelToYaml.
     */
    @Test
    public void testConvertModelToYaml() {
        System.out.println("ConvertModelToYaml");

        List<TranslationsByLanguage> model = translationsByLanguage;

        Path resultFolder = Paths.get(pathToResultFolder + "/genTestFiles/");

        ModelToYaml instance = new ModelToYaml();

        instance.ConvertModelToYaml(model, resultFolder);

        File resultfile = new File(pathToResultFolder + "/genTestFiles/EN.yaml");

        String resultDataFromFile = CommonUtils.readFileToString(resultfile, "UTF-8");

        File expResultfile = new File(pathToResultFolder + "/expectedResultYamlEN.yaml");

        String expResultDataFromFile = CommonUtils.readFileToString(expResultfile, "UTF-8");

        assertEquals(expResultDataFromFile, resultDataFromFile);

    }

}
