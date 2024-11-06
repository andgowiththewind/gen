package com.gust.cafe.gen.dto.core;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 不用放入`com.gust.cafe.gen.mapper`目录下，否则会被当成`Mapper`接口
 */
public interface BaseTkMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>, Mapper<T>, InsertListMapper<T>, ConditionMapper<T> {
}