package com.gust.cafe.gen.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.gust.cafe.gen.config.threadlocal.LocaleHolder;
import com.gust.cafe.gen.dto.core.R;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/test")
    public R test(@RequestBody Map<String, Object> map) {
        Locale locale = LocaleHolder.getLocale();
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        String message = messageSource.getMessage("i18n_000000", null, LocaleHolder.getLocale());
        int a = 1 / 0;
        return R.data(IdUtil.simpleUUID().toLowerCase());
    }
}
