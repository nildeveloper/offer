package com.lhl.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-07-21
 * Time: 10:43
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestEntity {

    private int id;

    private String name;

}
