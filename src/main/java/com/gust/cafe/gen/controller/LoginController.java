package com.gust.cafe.gen.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.gust.cafe.gen.domain.SysUser;
import com.gust.cafe.gen.dto.core.R;
import com.gust.cafe.gen.dto.req.SignInOrSignUpReqDTO;
import com.gust.cafe.gen.enums.YesNoEnum;
import com.gust.cafe.gen.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "登录")
public class LoginController {
    private final CacheManager cacheManager;
    private final SysUserMapper sysUserMapper;

    public LoginController(CacheManager cacheManager, SysUserMapper sysUserMapper) {
        this.cacheManager = cacheManager;
        this.sysUserMapper = sysUserMapper;
    }


    @PostMapping("/signInOrSignUp")
    public R signInOrSignUp(@RequestBody SignInOrSignUpReqDTO reqDTO) {
        String sha256Hex = DigestUtil.sha256Hex(reqDTO.getPassword());
        SysUser sysUser = sysUserMapper.selectOne(SysUser.builder()
                .email(reqDTO.getEmail())
                .password(sha256Hex)
                .isEnabled(YesNoEnum.YES.getCode())
                .build());


        return null;
    }


}
