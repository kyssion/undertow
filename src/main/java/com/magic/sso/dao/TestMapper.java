package com.magic.sso.dao;

import com.magic.sso.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("select * from item")
    List<Item> select(Item t);
}
