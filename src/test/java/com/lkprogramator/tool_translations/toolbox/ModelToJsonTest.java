package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import com.lkprogramator.tool_translations.utils.JsonUtils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LKprogramator
 */
public class ModelToJsonTest {

    static String pathToResultFolder;
    static List<TranslationsByLanguage> translationsByLanguage;

    static Map<String, String> translationsDataCS = new HashMap<String, String>();
    static Map<String, String> translationsDataEN = new HashMap<String, String>();

    @BeforeClass
    public static void setUpClass() {

        Path resourceDirectory = Paths.get("src/test/resource");
        pathToResultFolder = resourceDirectory.toString();

        ClassLoader classLoader;
        classLoader = ModelToJsonTest.class.getClassLoader();

        File file = new File(classLoader.getResource("TranslationsByLanguageTestData.json").getFile());

        String testDataFromFile = CommonUtils.readFileToString(file, "UTF-8");

        translationsByLanguage = JsonUtils.readValuesInList(testDataFromFile, TranslationsByLanguage.class);

    }

    /**
     * Test of ConvertModelToJson method, of class ModelToJson.
     */
    @Test
    public void testConvertModelToJson() {

        List<TranslationsByLanguage> model = translationsByLanguage;

        Path resultFolder = Paths.get(pathToResultFolder);

        ModelToJson instance = new ModelToJson();

        instance.ConvertModelToJson(model, resultFolder);

        File file = new File(pathToResultFolder + "/genTestFiles/EN.json");

        assertTrue(file.exists());
// TODO: read the file. Check that it contains what it's supposed to 

    }

}
