package com.lkprogramator.tool_translations.toolbox;

import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;
import com.lkprogramator.tool_translations.utils.CommonUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LKprogramator
 */
public class ModelToPHP {

    public void ConvertModelToPHP(List<TranslationsByLanguage> model, Path pathToResultFolder) {

        for (TranslationsByLanguage translationsByLanguage : model) {

            String filePahtWithFileName = CommonUtils.createPathForResultFile(pathToResultFolder.toString(), translationsByLanguage.getLang().toUpperCase(), "php");

            Map<String, String> translations = CommonUtils.sanitizeMapsValues(translationsByLanguage.getTranslations());

            String fileContent = buildContentAsString(translations);

            CommonUtils.saveStringToFile(filePahtWithFileName, fileContent);

        }

    }

    private String buildContentAsString(Map<String, String> translations) {

        StringBuilder fileContentBuilder = new StringBuilder();
        fileContentBuilder.append("<?php  \n");

        for (Map.Entry<String, String> entry : translations.entrySet()) {

            fileContentBuilder.append("$messages['").append(entry.getKey()).append("'] = '").append(entry.getValue()).append("';\n");

        }
        fileContentBuilder.append("\n ?>");
        return fileContentBuilder.toString();

    }

}
