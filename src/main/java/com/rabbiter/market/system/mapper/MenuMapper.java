package com.rabbiter.market.system.mapper;

import com.rabbiter.market.system.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 根据角色id集合查询权限信息
     *
     * @param rids
     * @return
     */
    List<Menu> queryByRids(@Param("rids") Set<Long> rids);


}
