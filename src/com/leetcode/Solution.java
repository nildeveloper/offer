package com.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-06-19
 * Time: 10:05
 * Description:
 */
public class Solution {

    
    // 暴力法：超时
    public String longestPalindrome(String s) {
        if (s == null || s.equals("")) return s;
        String ret = "";
        for (int i = 0; i < s.length(); i++) {  // 开始位置
            for (int j = 0; j < s.length() - i; j++) { // 长度
                String str = s.substring(i, i + j + 1);
                String rev = new StringBuilder(str).reverse().toString();
                if (str.equals(rev)) {
                    if (str.length() > ret.length()) {
                        ret = str;
                    }
                }
            }
        }
        return ret;
    }
    
    // 中心扩展法
    // 从中心向两边扩展，回文子串镜像对称
    // 若字符数为奇数，则有n个中心；若字符数为偶数，则有n-1个中心，一共有2n-1个中心
    public String longestPalindrome1(String s) {
        if (s == null || s.equals("")) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(i, i, s);  // 一个中心
            int len2 = expandAroundCenter(i, i + 1, s);  // 两个中心
            int len = Math.max(len1, len2);  // 判断两个子串谁长度更长
            if (len > end - start) {
                start = i - (len - 1) / 2;  // 标记子串start
                end = i + len / 2;  // 标记子串end
            }
        }
        return s.substring(start, end + 1);
    }
    
    public int expandAroundCenter(int left, int right, String s) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {  // 长度扩展
            l--;
            r++;
        }
        return r - l -1;  // 返回的是一个长度
    }
    
    @Test
    public void testlongestPalindrome() {
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome1("cbbd"));
    }

    // 模拟执行过程
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> list = new ArrayList<>();
        String ret = "";
        // List存放行
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            list.add(new StringBuilder());
        }
        boolean goingDown = false;  // 是否向下走
        int curRow = 0;  // 当前位置指针
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            list.get(curRow).append(aChar);  // 将当前指针所指字符放入到所在行
            // 边界时改变方向
            if (curRow == 0 || curRow == list.size() - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;  // 位置指针移动（上或下）
        }
        for (StringBuilder str : list) {
            ret += str;
        }
        return ret;
    }
    
    @Test
    public void testConvert() {
        System.out.println(convert("LEETCODEISHIRING", 3));
    }


    // 字符串转数字
    public int myAtoi(String str) {
        int ret = 0;
        if (str.trim().equals("0") || str.trim().equals("")) return 0;
        int flag = 1;  // 符号位
        char[] chars = str.trim().toCharArray();
        if (chars[0] == '-') {
            flag = -1;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return 0;
            } else {
                ret = ret * 10 + chars[i] - 48;
            }
        }
        return ret * flag;
    }
    
    @Test
    public void testMyAtoi() {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        
    }
}
