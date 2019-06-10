package com.magic.sso.dao;

import com.magic.sso.Item;
import com.magic.sso.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    Item[] update(User iii);

}
