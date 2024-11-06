package com.gust.cafe.gen.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.gust.cafe.gen.config.threadlocal.LocaleHolder;
import com.gust.cafe.gen.domain.SysUser;
import com.gust.cafe.gen.dto.core.R;
import com.gust.cafe.gen.mapper.SysUserMapper;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final SysUserMapper sysUserMapper;

    public LoginController(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @PostMapping("/test")
    public R test(@RequestBody Map<String, Object> map) {
        List<SysUser> sysUsers = sysUserMapper.selectAll();

        Locale locale = LocaleHolder.getLocale();
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        String message = messageSource.getMessage("i18n_000000", null, LocaleHolder.getLocale());
        int a = 1 / 0;
        return R.data(IdUtil.simpleUUID().toLowerCase());
    }
}
