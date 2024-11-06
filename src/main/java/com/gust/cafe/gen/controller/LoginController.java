package com.gust.cafe.gen.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.gust.cafe.gen.constant.CacheConstant;
import com.gust.cafe.gen.domain.SysUser;
import com.gust.cafe.gen.dto.core.R;
import com.gust.cafe.gen.dto.req.SignInOrSignUpReqDTO;
import com.gust.cafe.gen.enums.YesNoEnum;
import com.gust.cafe.gen.exception.GenServiceException;
import com.gust.cafe.gen.mapper.SysUserMapper;
import com.gust.cafe.gen.util.IdUtils;
import io.swagger.annotations.Api;
import org.springframework.cache.CacheManager;
import org.springframework.validation.annotation.Validated;
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
    public R signInOrSignUp(@RequestBody @Validated SignInOrSignUpReqDTO reqDTO) {
        SysUser sysUser = sysUserMapper.selectOne(SysUser.builder()
                .username(reqDTO.getUsername())
                .isEnabled(YesNoEnum.YES.getCode())
                .build());
        // 简化:直接注册
        if (sysUser == null) {
            String username = StrUtil.trim(reqDTO.getUsername());
            GenServiceException.wrapper(() -> Assert.isFalse(StrUtil.equalsIgnoreCase(username, "admin"), "当前用户名已存在"));
            //
            SysUser insertVo = SysUser.builder()
                    .id(IdUtils.shortNanoId(11))
                    .displayName(StrUtil.format("新用户{}", IdUtils.shortNanoId(5)))
                    .avatar(null)
                    .username(reqDTO.getUsername())
                    .password(DigestUtil.sha256Hex(reqDTO.getPassword()))
                    .email(null)
                    .isEnabled(YesNoEnum.YES.getCode())
                    .createBy("admin")
                    .createTime(DateUtil.now())
                    .updateBy("admin")
                    .updateTime(DateUtil.now())
                    .build();
            sysUserMapper.insert(insertVo);
            String token = IdUtils.shortNanoId(32);
            cacheManager.getCache(CacheConstant.TOKEN).put(token, insertVo);
            return R.data(token).putOpt("msg", "注册成功");
        }

        // sysUser 不为 null,校验密码
        String currentPass = DigestUtil.sha256Hex(reqDTO.getPassword());
        GenServiceException.wrapper(() -> Assert.isTrue(StrUtil.equals(sysUser.getPassword(), currentPass), "密码错误"));

        // 登录成功,生成 token
        String token = IdUtils.shortNanoId(32);
        cacheManager.getCache(CacheConstant.TOKEN).put(token, sysUser);
        return R.data(token).putOpt("msg", "登录成功");
    }


}
