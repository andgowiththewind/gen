package com.gust.cafe.gen.mapper;

import com.gust.cafe.gen.domain.SysUser;
import com.gust.cafe.gen.mapper.core.BaseTkMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 请输入类描述
 */
@Mapper
public interface SysUserMapper extends BaseTkMapper<SysUser> {
    List<SysUser> findByAll(SysUser sysUser);
}