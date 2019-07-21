package com.lhl.boot.component;

import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;


/**
 * Created with IntellJ IDEA.
 * User: lhl
 * Date: 2018-09-22
 * Time: 23:18
 * Description:
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StringToNumberConverterFactory.class);
    
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumberConverter<>(targetType);
    }
    
    public static class StringToNumberConverter<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;
        public StringToNumberConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            //"null" -> null
            if (source.isEmpty() || source.trim().equalsIgnoreCase("null"))
                return null;
            try {
                return NumberUtils.parseNumber(source, this.targetType);
            } catch (NumberFormatException e) {
                logger.error("String to Number 转换异常. source string: " + source
                        + " destination type " + this.targetType.getName() + " message:" + e.getMessage());
                throw new IllegalArgumentException("Invalid Number value '" + source + "'");
            }
        }
    }
}
