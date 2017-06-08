package com.lkprogramator.tool_translations.translation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author LKprogramator
 */
public class TranslationsByLanguage {

    private String lang;
    private Map<String, String> translations;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.lang);
        hash = 97 * hash + Objects.hashCode(this.translations);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TranslationsByLanguage other = (TranslationsByLanguage) obj;
        if (!Objects.equals(this.lang, other.lang)) {
            return false;
        }
        if (!Objects.equals(this.translations, other.translations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        String template = "\"%s\": \"%s\", ";
        StringBuilder sb = new StringBuilder();

        for (Entry e : translations.entrySet()) {
            sb.append(String.format(template, e.getKey(), e.getValue()));
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.deleteCharAt(sb.lastIndexOf(" "));
        }

        return "TranslationsByLanguage{" + "\"lang\": \"" + lang + "\", \"translations\": {" + sb.toString() + "}}";
    }

}
