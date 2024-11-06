package com.gust.cafe.gen.controller;


import com.gust.cafe.gen.dto.core.R;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private final CacheManager cacheManager;


    public TestController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @GetMapping("/test001")
    public R test001(@RequestParam("name") String name, @RequestParam("key") String key) {
        Cache cache = cacheManager.getCache(name);
        Object object = cache.get(key, Object.class);
        return R.data(object);
    }


    @GetMapping("/test002")
    public R test002(@RequestParam("name") String name, @RequestParam("key") String key, @RequestParam("value") String value) {
        Cache cache = cacheManager.getCache(name);
        cache.put(key, value);
        return R.ok();
    }

}
