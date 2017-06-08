package com.lkprogramator.tool_translations.translation;

import com.lkprogramator.tool_translations.utils.JsonUtils;
import java.io.File;
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
public class TranslationsByLanguageTest {

    static TranslationsByLanguage testDataObj;

    @BeforeClass
    public static void setUpClass() {

        ClassLoader classLoader = TranslationsByLanguageTest.class.getClassLoader();

        File file = new File(classLoader.getResource("TranslationsByLanguageObjectEN.json").getFile());

        testDataObj = JsonUtils.readValuesFromFileAsObject(file.getAbsolutePath(), TranslationsByLanguage.class);

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
     * Test of getLang method, of class TranslationsByLanguage.
     */
    @Test
    public void testGetLang() {
        System.out.println("getLang");
        String expResult = "EN";
        String result = testDataObj.getLang();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLang method, of class TranslationsByLanguage.
     */
    @Test
    public void testSetLang() {
        System.out.println("setLang");
        String lang = "RU";
        TranslationsByLanguage instance = new TranslationsByLanguage();
        instance.setLang(lang);

        assertEquals(lang, instance.getLang());
    }

    /**
     * Test of getTranslations method, of class TranslationsByLanguage.
     */
    @Test
    public void testGetTranslations() {
        System.out.println("getTranslations");

        Map<String, String> result = new HashMap<String, String>();

        result.put("AF", "Afghanistan");
        result.put("AL", "Albania");
        result.put("DZ", "Algeria");
        result.put("AD", "Andorra");
        result.put("AO", "Angola");

        Map<String, String> expResult = testDataObj.getTranslations();
        assertEquals(expResult, result);

    }

    /**
     * Test of setTranslations method, of class TranslationsByLanguage.
     */
    @Test
    public void testSetTranslations() {
        System.out.println("setTranslations");
        Map<String, String> translations = new HashMap<String, String>();

        translations.put("AF", "Afghanistan");
        translations.put("AL", "Albania");
        translations.put("DZ", "Algeria");
        translations.put("AD", "Andorra");
        translations.put("AO", "Angola");

        TranslationsByLanguage instance = new TranslationsByLanguage();
        instance.setTranslations(translations);

        assertEquals(testDataObj.getTranslations(), instance.getTranslations());

    }

    /**
     * Test of hashCode method, of class TranslationsByLanguage.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        TranslationsByLanguage instance = new TranslationsByLanguage();

        Map<String, String> translations = new HashMap<String, String>();

        translations.put("AF", "Afghanistan");
        translations.put("AL", "Albania");
        translations.put("DZ", "Algeria");
        translations.put("AD", "Andorra");
        translations.put("AO", "Angola");

        instance.setTranslations(translations);
        instance.setLang("EN");

        assertTrue(testDataObj.hashCode() == instance.hashCode());
    }

    /**
     * Test of equals method, of class TranslationsByLanguage.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        TranslationsByLanguage instance = new TranslationsByLanguage();

        Map<String, String> translations = new HashMap<String, String>();

        translations.put("AF", "Afghanistan");
        translations.put("AL", "Albania");
        translations.put("DZ", "Algeria");
        translations.put("AD", "Andorra");
        translations.put("AO", "Angola");

        instance.setTranslations(translations);
        instance.setLang("EN");

        assertTrue(testDataObj.equals(instance) && instance.equals(testDataObj));
    }

    /**
     * Test of toString method, of class TranslationsByLanguage.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "TranslationsByLanguage{\"lang\": \"EN\", \"translations\": {\"AF\": \"Afghanistan\", \"AL\": \"Albania\", \"DZ\": \"Algeria\", \"AD\": \"Andorra\", \"AO\": \"Angola\"}}";

        String result = testDataObj.toString();

        assertEquals(expResult, result);
    }

}
