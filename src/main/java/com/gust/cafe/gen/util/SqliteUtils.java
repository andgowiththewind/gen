package com.gust.cafe.gen.util;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemUtil;
import lombok.SneakyThrows;
import net.lingala.zip4j.ZipFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SqliteUtils {

    @SneakyThrows
    public static void init() {
        File dir = FileUtil.file(SystemUtil.getUserInfo().getCurrentDir(), "attachment/db");
        File adminSqlite = FileUtil.file(dir, "admin.sqlite");
        if (FileUtil.exist(adminSqlite) && FileUtil.isFile(adminSqlite)) {
            return;
        }
        //
        List<File> zipList = Arrays.stream(FileUtil.ls(dir.getAbsolutePath())).filter(f -> {
            boolean isF = FileUtil.isFile(f);
            boolean zip = StrUtil.isNotBlank(FileUtil.extName(f)) && StrUtil.equalsIgnoreCase(FileUtil.extName(f), "zip");
            boolean startWith = StrUtil.startWith(FileUtil.getName(f), "sqlite_gen_admin_");
            return isF && zip && startWith;
        }).collect(Collectors.toList());
        Assert.notEmpty(zipList, "未找到sqlite_gen_admin_xxx.zip文件");
        //
        // 定位最新时间的文件
        File zip = zipList.stream().sorted((f1, f2) -> {
            String datetimeStr1 = StrUtil.sub(StrUtil.subAfter(FileUtil.mainName(f1), "sqlite_gen_admin_", true), 0, 14);
            DateTime dt1 = DateUtil.parse(datetimeStr1, "yyyyMMdd_HHmmss");
            String datetimeStr2 = StrUtil.sub(StrUtil.subAfter(FileUtil.mainName(f2), "sqlite_gen_admin_", true), 0, 14);
            DateTime dt2 = DateUtil.parse(datetimeStr2, "yyyyMMdd_HHmmss");
            long between = DateUtil.between(dt1, dt2, DateUnit.SECOND, false);
            return (int) between;
        }).findFirst().get();
        //
        new ZipFile(FileUtil.getAbsolutePath(zip)).extractFile("admin.sqlite", FileUtil.getAbsolutePath(dir));
    }
}
