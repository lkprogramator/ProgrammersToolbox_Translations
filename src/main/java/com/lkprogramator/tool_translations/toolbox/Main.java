package com.lkprogramator.tool_translations.toolbox;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lkprogramator.tool_translations.utils.messages.LoggerMessage;
import com.lkprogramator.tool_translations.translation.TranslationsByLanguage;

/**
 *
 * @author LKprogramator
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    //private static final String ARGUMENT_PREFIX ="-";
    private static final String ARGUMENT_SEPARATOR = "=";
    private static final String ARGUMENT_CSV_FILE = "csvFile";
    private static final String ARGUMENT_RESULT_FOLDER = "resultFolder";
    private static final String ARGUMENT_PROCESS_TO = "processTo";
    private static final char CSV_SEPARATOR = ';';

    private static Path pathToCsv = Paths.get("./");
    private static String processTo = "json";
    private static Path pathToResultFolder = Paths.get("./");

    public static void main(String[] args) {

        //java -jar ProgrammersToolbox_Translations-1.0-SNAPSHOT.jar csvFile=C:/Users/ZAX/Documents/mycsvfile.csv 
        //processTo=json resultFolder=C:/Users/ZAX/Documents/
        args = new String[]{"csvFile=C:/Users/ZAX/Documents/zzztest/testprekladu.csv", "processTo=json", "resultFolder=C:/Users/ZAX/Documents/zzztest/"};
        extractAndValidateArguments(args);

        convertModelToFile(buildModel());

    }

    private static void extractAndValidateArguments(String[] args) {

        if (args.length == 0) {

            LoggerMessage lm = new LoggerMessage("Error No console parameters, App has ended.");
            LOGGER.error(lm.toString());

            System.exit(0);
        }

        //test
        for (String s : args) {
            System.out.println("Args: " + s);
        }
        LOGGER.info("Arguments: ", args.toString());

        for (int i = 0; i < args.length; i++) {

            String[] keyVal = args[i].split(ARGUMENT_SEPARATOR);

            switch (keyVal[0]) {
                case ARGUMENT_CSV_FILE:

                    if (!Files.exists(Paths.get(keyVal[1]))) {
                        LoggerMessage lm = new LoggerMessage("Error CSV file not exist.").addValue("Csv file path", keyVal[1]);
                        LOGGER.error(lm.toString());
                        System.exit(0);
                    } else {
                        pathToCsv = Paths.get(keyVal[1]);
                    }

                    break;
                case ARGUMENT_PROCESS_TO:

                    if (!keyVal[1].toLowerCase().matches("json|yaml|php")) {
                        LoggerMessage lm = new LoggerMessage("Error processTo value is incorect.");
                        LOGGER.error(lm.toString());
                        System.exit(0);
                    } else {
                        processTo = keyVal[1].toLowerCase();
                    }

                    break;

                case ARGUMENT_RESULT_FOLDER:

                    pathToResultFolder = Paths.get(keyVal[1]);

                    if (!Files.isDirectory(pathToResultFolder)) {
                        LoggerMessage lm = new LoggerMessage("Error result folder not exist");
                        LOGGER.error(lm.toString());

                        if (Files.isDirectory(pathToCsv.getParent())) {
                            pathToResultFolder = pathToCsv.getParent();

                            LoggerMessage lmr = new LoggerMessage("Result folder not exist. Use same folder as csv file as result folder.");
                            LOGGER.info(lmr.toString());

                        }
                        //System.exit(0);
                    }

                    break;
            }

        }

    }

    private static List<TranslationsByLanguage> buildModel() {

        CsvToModel csvData = new CsvToModel();
        List<TranslationsByLanguage> model = csvData.processCsvToModel(pathToCsv.toString(), CSV_SEPARATOR);

        return model;

    }

    private static void convertModelToFile(List<TranslationsByLanguage> model) {

        switch (processTo) {
            case "json":
                ModelToJson modelToJson = new ModelToJson();
                modelToJson.ConvertModelToJson(model, pathToResultFolder);

                break;
            case "yaml":

                ModelToYaml modelToYaml = new ModelToYaml();
                modelToYaml.ConvertModelToYaml(model, pathToResultFolder);

                break;
            case "php":

                ModelToPHP modelToPHP = new ModelToPHP();
                modelToPHP.ConvertModelToPHP(model, pathToResultFolder);

                break;

        }

    }

}
