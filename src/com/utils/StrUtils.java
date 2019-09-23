package com.utils;
//
//import com.google.common.collect.Lists;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 字符串工具类
 *
 * @author zidong.luo
 */
public class StrUtils {

    /**
     * str根据','解析成List<Integer>
     * 如果出现解析错误的便跳过
     *
     * @param str "133,13,1313,13123"
     * @return
     */
//    public static List<Integer> str2List(String str) {
//        if (StringUtils.isBlank(str)) {
//            return Lists.newArrayList();
//        }
//        String[] defenderIds = str.split("[,，]");
//        List<Integer> ids = Lists.newArrayList();
//        for (String id : defenderIds) {
//            int intId;
//            try {
//                intId = Integer.valueOf(id.trim());
//            } catch (Exception e) {
//                continue;
//            }
//            ids.add(intId);
//        }
//        return ids;
//    }
//
//    /**
//     * list->string （默认','分隔符）
//     * 分隔符之间没有空格
//     *
//     * @param list
//     * @return 元素1,元素2,元素3
//     */
//    public static String list2Str(List<?> list) {
//        if (CollectionUtils.isEmpty(list)) {
//            return "";
//        }
//        return list2Str(list, ",");
//    }
//
//    /**
//     * list->string
//     *
//     * @param list
//     * @param separator 分隔符
//     * @return
//     */
//    public static String list2Str(List<?> list, String separator) {
//        if (CollectionUtils.isEmpty(list)) {
//            return "";
//        }
//        return StringUtils.join(list.toArray(), separator);
//    }
}
