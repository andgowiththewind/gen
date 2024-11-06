package com.gust.cafe.gen.service;

import com.gust.cafe.gen.domain.SysUser;
import com.gust.cafe.gen.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请输入类描述
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    public List<SysUser> findByAll(SysUser sysUser) {
        return sysUserMapper.findByAll(sysUser);
    }

}
