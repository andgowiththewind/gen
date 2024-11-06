package com.gust.cafe.gen.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.gust.cafe.gen.config.threadlocal.LocaleHolder;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 国际化工具类
 */
public class GenLang {
    public static String msg(String key) {
        Assert.notBlank(key);
        key = StrUtil.trim(key);
        // 约定:空格
        if (StrUtil.equalsIgnoreCase(key, "i18n_000000")) {
            return " ";
        }
        // 查询国际化
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        String value = messageSource.getMessage(key, null, LocaleHolder.getLocale());
        // 去掉可能存在的前后空格
        value = StrUtil.trim(value);
        return value;
    }

    public static String msg(String... keys) {
        if (keys.length == 0) {
            return "";
        }
        return Arrays.stream(keys).map(GenLang::msg).collect(Collectors.joining());
    }
}
