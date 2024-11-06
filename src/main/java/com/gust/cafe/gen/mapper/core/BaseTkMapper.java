package com.gust.cafe.gen.mapper.core;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface BaseTkMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>, Mapper<T>, InsertListMapper<T>, ConditionMapper<T> {
}