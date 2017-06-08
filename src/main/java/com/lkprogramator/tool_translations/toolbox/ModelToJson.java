package com.lkprogramator.tool_translations.toolbox;

import java.nio.file.Path;
import java.util.List;
import com.lkprogramator.tool_translations.utils.JsonUtils;
import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import java.util.Map;

/**
 *
 * @author LKprogramator
 */
public class ModelToJson {

    public void ConvertModelToJson(List<TranslationsByLanguage> model, Path pathToResultFolder) {

        for (TranslationsByLanguage translationsByLanguage : model) {

            String filePahtWithFileName = CommonUtils.createPathForResultFile(pathToResultFolder.toString(), translationsByLanguage.getLang().toUpperCase(), "json");
            Map<String, String> translations = CommonUtils.sanitizeMapsValues(translationsByLanguage.getTranslations());

            JsonUtils.writeMapToJsonFile(translations, filePahtWithFileName);

        }

    }

}
