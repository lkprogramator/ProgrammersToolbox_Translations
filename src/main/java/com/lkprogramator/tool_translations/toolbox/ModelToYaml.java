package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import com.lkprogramator.tool_translations.utils.YmlUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LKprogramator
 */
public class ModelToYaml {

    public void ConvertModelToYaml(List<TranslationsByLanguage> model, Path pathToResultFolder) {

        for (TranslationsByLanguage translationsByLanguage : model) {

            String filePahtWithFileName = CommonUtils.createPathForResultFile(pathToResultFolder.toString(), translationsByLanguage.getLang().toUpperCase(), "yaml");

            Map<String, String> translations = CommonUtils.sanitizeMapsValues(translationsByLanguage.getTranslations());

            YmlUtils.saveMap(translations, filePahtWithFileName);

        }

    }

}
