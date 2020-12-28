package com.lhl.boot;

import com.alibaba.fastjson.JSON;
import com.lhl.boot.db.helper.DBHelper;
import com.lhl.boot.db.po.StudentPo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-12-28
 * @time 17:36
 * @describe:
 */
public class DaoTest {

    @Test
    public void daoTest() throws Exception {
        Map<String, String> dbResult = new HashMap<String, String>(){
            {
                put("id", "1");
                put("name", "张三");
                put("age", "18");
            }
        };
        StudentPo studentPo = DBHelper.formatEntity(dbResult, StudentPo.class);
        System.out.println(JSON.toJSONString(studentPo));
    }

}
