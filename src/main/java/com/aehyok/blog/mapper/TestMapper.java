package com.aehyok.blog.mapper;

import com.aehyok.blog.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestMapper {
    //@Select("select * from tests")
    List<TestModel> getAll();

    List<TestModel> get(@Param("id") int id);
}
