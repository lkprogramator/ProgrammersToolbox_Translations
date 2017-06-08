# ProgrammersToolbox_Translations

## Dependency

* Java installed
* maven to run the project

## How to use it

### Download this project
### Create Excel file

| KEY | CS | EN | ... |
| ------ | ------ | ------ | ------ |
| myKey.hello | Ahoj | Hello | ... |
| myKey.goodbye | Naschledanou | Goodbye | ... |
| myKey.something | ... | ... | ... |

Where first column holds keys, and other columns holds translations by language.
Keep Headers (first row) in capital letters.

**Save** file as CSV, set **separator** to **";"** (with out the quotation marks)


### Build project to obtain jar

### Use command line 

| Param | Description | 
| ------ | ------ | 
| csvFile | Path to CSV file | 
| processTo | Result file type (Json, Yaml, PHP) |
| resultFolder | **Folder**  where result files will be generated |

**Example:**
```sh
java -jar C://Path/to/the/<jar-file-name>.jar csvFile=Path/to/mycsvfile.csv processTo=json resultFolder=save/my/translationFiles/here/
```

## To-dos

* Add settings from external file
* Add command line argument "options" 
* Generate CSV file from translation file
* Add google table input
