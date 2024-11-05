package com.gust.cafe.gen.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/test")
    public String test(@RequestBody Map<String, Object> map) {
        return DateUtil.now();
    }
}
