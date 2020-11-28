package com.lhl.boot.dao;

import com.lhl.boot.entity.TestEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-07-21
 * Time: 10:43
 * Description:
 */
@Repository
public class TestDao {

    public TestEntity get(int id) {
        TestEntity entity = TestEntity.builder().id(id).name("test").build();
        return entity;
    }
}
