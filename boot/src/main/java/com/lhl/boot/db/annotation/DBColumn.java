package com.lhl.boot.db.annotation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-12-28
 * @time 16:06
 * @describe: 字段属性描述注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DBColumn {

    /**
     * column name
     * @return
     */
    String name() default "fieldName";

    /**
     * is pk
     * @return
     */
    boolean pk() default false;
}
