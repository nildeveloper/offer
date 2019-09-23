package com.lhl.boot.utils.excel;

import java.lang.annotation.*;

/**
 * @Author: shixu
 * @Date: 2018/5/30 18:53
 * @Description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
	String key() default "";

	/**
	 *从1开始
	 * @return
	 */
	int col() default 0;
}