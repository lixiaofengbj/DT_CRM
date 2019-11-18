package com.dt.mapper;

import com.dt.pojo.CrmMenu;
import com.dt.pojo.CrmRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/6 14:35
 */
public interface MenuMapper extends Mapper<CrmMenu> {

    List<CrmRole> findList(String menuName);
}
