package com.gust.cafe.gen.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.gust.cafe.gen.dto.core.R;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/test")
    public R test(@RequestBody Map<String, Object> map) {
        return R.data(IdUtil.simpleUUID().toLowerCase());
    }
}
