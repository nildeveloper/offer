package com.string;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-24
 * Time: 7:31 PM
 * Description:  字符串匹配算法
 */
public class Match {

    /**
     * 暴力匹配
     * @param t
     * @param t
     * @return
     */
    public int violentMatch(String t, String p) {
        int i = 0, j = 0;
        while (i < t.length() && j < p.length()) {
            if (t.charAt(i) == p.charAt(j)) {  // 两字符匹配
                i++;
                j++;
            } else {
                i = i - j + 1;  // 不匹配，两指针回退
                j = 0;
            }
        }
        if (j == p.length()) {
            return i - j;
        }
        return -1;
    }

    /**
     * KMP 匹配算法
     * @param t
     * @param p
     * @return
     */
    public int kmpMatch(String t, String p) {
        int i = 0, j = 0;
        int[] next = getNext(p);
        while (i < t.length() && j < p.length()) {
            if (j == -1 || t.charAt(i) == p.charAt(j)) { // 当j为-1时，要移动的是i，当然j也要归0
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) {
            return i - j;
        }
        return -1;
    }

    /**
     * 求 next 数组
     * @param ps
     * @return
     */
    public int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        // 根据已知的前j位推测第j+1位
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[j + 1] == p[k + 1]) {
                    next[++j] = next[++k];
                } else {
                    next[++j] = ++k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }
    
    @Test
    public void testViolentMatch() {
        String t = "aaabcde";
        String p = "abc";
        String p1 = "abd";
        System.out.println(violentMatch(t, p));
        System.out.println(violentMatch(t, p1));
    }

    @Test
    public void testKMPMatch() {
        String t = "aaabcde";
        String p = "abc";
        String p1 = "abd";
        System.out.println(kmpMatch(t, p));
        System.out.println(kmpMatch(t, p1));
    }
}
