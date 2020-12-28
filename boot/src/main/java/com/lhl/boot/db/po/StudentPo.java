package com.lhl.boot.db.po;

import com.lhl.boot.db.annotation.DBColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-12-28
 * @time 16:13
 * @describe:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPo {

    @DBColumn(name = "id", pk = true)
    private int id;

    @DBColumn(name = "name")
    private String name;

    @DBColumn(name = "age")
    private int age;

}
