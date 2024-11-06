package com.gust.cafe.gen.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum YesNoEnum {
    YES(1, "是", true),
    NO(0, "否", false),
    ;
    public final Integer code;
    public final String label;
    public final Boolean boolVal;


    public static List<Integer> codeList() {
        return Arrays.stream(YesNoEnum.values()).map(YesNoEnum::getCode).collect(Collectors.toList());
    }

    public static YesNoEnum getByCode(Integer code) {
        if (code == null) return null;
        for (YesNoEnum anEnum : YesNoEnum.values()) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return null;
    }

    public static YesNoEnum getByLabel(String label) {
        if (StrUtil.isBlank(label)) return null;
        for (YesNoEnum anEnum : YesNoEnum.values()) {
            if (anEnum.getLabel().equals(label)) {
                return anEnum;
            }
        }
        return null;
    }

}
