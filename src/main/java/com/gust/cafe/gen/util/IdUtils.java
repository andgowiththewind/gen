package com.gust.cafe.gen.util;

import cn.hutool.core.lang.id.NanoId;

import java.util.Optional;

public class IdUtils {
    public static String shortNanoId(Integer size) {
        size = Optional.ofNullable(size).filter(e -> e > 0).orElse(6);
        char[] alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String nanoId = NanoId.randomNanoId(null, alphabet, size);
        return nanoId;
    }

    public static String shortNanoId() {
        return shortNanoId(null);
    }
}
