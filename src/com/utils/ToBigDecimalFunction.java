/**
 * Description: 
 * @author 李泽良
 * @date 2018年6月12日
 */
package com.utils;

import java.math.BigDecimal;

/**
 * Description: 
 * @author 李泽良
 * @date 2018年6月12日
 */
@FunctionalInterface
public interface ToBigDecimalFunction<T> {
	BigDecimal applyAsBigDecimal(T value);
}
