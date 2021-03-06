package com.ycl.dao.mapper;

import com.ycl.dao.model.Person;
import org.apache.ibatis.annotations.Mapper;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}