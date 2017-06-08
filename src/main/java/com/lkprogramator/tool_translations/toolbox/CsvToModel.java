package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CsvUtils;
import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author LKprogramator
 */
public class CsvToModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvToModel.class);

    final String nameOfKeyColoumInCsvheader = "KEY";

    public List<TranslationsByLanguage> processCsvToModel(String pathToCsv, char CSVSeparator) {

        CsvUtils csvUtils = new CsvUtils();

        List<Map<String, String>> csvData = csvUtils.readCsvAsListOfMaps(pathToCsv, CSVSeparator);

        List<TranslationsByLanguage> processedTranslations = assambleModel(csvData);

        return processedTranslations;

    }

    public List<TranslationsByLanguage> assambleModel(List<Map<String, String>> csvData) {

        List<TranslationsByLanguage> processedTranslations = new ArrayList<TranslationsByLanguage>();

        Set<String> languageSet = getLanguages(csvData.get(0).keySet());

        LoggerMessage lm = new LoggerMessage("prepareTranslations").addValue("languageSet", languageSet.toString());
        LOGGER.info(lm.toString());

        for (String language : languageSet) {

            TranslationsByLanguage tbl = new TranslationsByLanguage();
            tbl.setLang(language);

            Map<String, String> languageMap = new HashMap<String, String>();

            for (Map<String, String> map : csvData) {

                languageMap.put(map.get(nameOfKeyColoumInCsvheader), map.get(language));
            }
            tbl.setTranslations(languageMap);
            processedTranslations.add(tbl);

        }

        return processedTranslations;
    }

    public Set<String> getLanguages(Set<String> keySet) {

        Set<String> languages = new HashSet<String>();
        for (String lang : keySet) {

            if (!lang.equalsIgnoreCase(nameOfKeyColoumInCsvheader)) {
                languages.add(lang);
            }

        }

        return languages;
    }

}
