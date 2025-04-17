package com.droidgeniuslabs.tooltip.Util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LangUtil {
    private static Locale currentLocale = Locale.ENGLISH;
    private static ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);

    public static void setLanguage(String langCode) {
	currentLocale = Locale.forLanguageTag(langCode);
	bundle = ResourceBundle.getBundle("messages", currentLocale);
    }

    public static String get(String key) {
	return bundle.getString(key);
    }

    public static Locale getCurrentLocale() {
	return currentLocale;
    }
}
