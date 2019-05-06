package com.magic.sso.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
    @Select("select * from item")
    Item[] select(@Param("iii")int[] iii);

}
