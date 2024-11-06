package com.gust.cafe.gen.config.threadlocal;

import java.util.Locale;

/**
 * 记录线程国际化参数
 *
 * @author dororo
 * @date 2024-11-06 10:50:23
 */
public class LocaleHolder {
    private static final ThreadLocal<Locale> userThreadLocal = new ThreadLocal<>();

    public static Locale getLocale() {
        Locale locale = userThreadLocal.get();
        if (locale == null) {
            return Locale.forLanguageTag("zh-CN"); // 默认中文
        }
        return locale;
    }

    public static void setLocale(Locale locale) {
        userThreadLocal.set(locale);
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
