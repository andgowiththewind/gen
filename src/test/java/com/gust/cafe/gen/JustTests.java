package com.gust.cafe.gen;

import cn.hutool.core.util.StrUtil;
import com.gust.cafe.gen.util.GenIdUtil;
import org.junit.jupiter.api.Test;

public class JustTests {
    @Test
    public void test001() {
        for (int i = 0; i < 10; i++) {
            System.out.println(StrUtil.format("i18n_{}", GenIdUtil.shortNanoId()));
        }
    }
}
