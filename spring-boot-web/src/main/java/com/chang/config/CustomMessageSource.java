package com.chang.config;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component("messageSource")
public class CustomMessageSource extends AbstractMessageSource {

    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    Map<Locale, Map<String, String>> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Map<String, String> defaultLookup = new ConcurrentHashMap<>();
        Map<String, String> zhCNLookup = new ConcurrentHashMap<>();
        Map<String, String> zhTWLookup = new ConcurrentHashMap<>();
        Map<String, String> enUSLookup = new ConcurrentHashMap<>();

        defaultLookup.put("intel", "intel");
        enUSLookup.put("intel", "intel");
        zhCNLookup.put("intel", "intel简体中文");
        zhTWLookup.put("intel", "intel繁体中文");

        map.put(DEFAULT_LOCALE, defaultLookup);
        map.put(Locale.US, enUSLookup);
        map.put(Locale.SIMPLIFIED_CHINESE, zhCNLookup);
        map.put(Locale.TRADITIONAL_CHINESE, zhTWLookup);
    }

    @Override
    protected MessageFormat resolveCode(String key, Locale locale) {
        Map<String, String> lookup = map.getOrDefault(locale, map.get(DEFAULT_LOCALE));
        String message = lookup.getOrDefault(key, key);
        return new MessageFormat(message, locale);
    }

    public void add(Locale locale, String key, String value) {
        Map<String, String> lookup = map.get(locale);
        if (null != lookup) {
            lookup.put(key, value);
        } else {
            lookup = new ConcurrentHashMap<>();
            lookup.put(key, value);
            map.put(locale, lookup);
        }
    }

}
