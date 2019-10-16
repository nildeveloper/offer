package com.test;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: haolu.liu
 * Date: 2019-08-26 9:47
 * Description:
 */
public class Test1 {

    @Test
    public void test() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        System.out.println(year + " " + month + " " + day);
    }
    

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        int result = 0;
        int left = 0;
        //声明一个HashMap，用来存储k Distinct，还可以根据value用来判断元素是否可以删除
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            //右指针依次把字符串中的字符放到map中
            map.put(s.charAt(right),right);
            //当map元素大于k Distinct时，得到满足要求的子字符串
            while (map.size() > k) {
                // 删除最左的字符，删除成功，则退出循环
                char leftChar = s.charAt(left);
                if (map.get(leftChar) == left) {
                    map.remove(leftChar);
                }
                // 左指针右移
                left++;
            }
            //子结果即左右指针之间的长度
            int subResult = right - left + 1;
            //结果取最大
            result = Math.max(result,subResult);
        }
        return result;
    }

    public int lengthOfLongestSubstringKDistinct1(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        int j = 0;
        int maxLength = 0;
        Map<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && hash.size() <= k) {
                if (hash.size() == k && !hash.containsKey(s.charAt(j))) {
                    break;
                }
                hash.put(s.charAt(j), hash.getOrDefault(s.charAt(j), 0) + 1);
                j++;
            }
            maxLength = Math.max(maxLength, j - i);
            int currentCount = hash.get(s.charAt(i)) - 1;
            if (currentCount == 0) {
                hash.remove(s.charAt(i));
            } else {
                hash.put(s.charAt(i), currentCount);
            }
        }
        return maxLength;
    }

    
    public static int find(String str,  int k) {
        if (str == null || str.length() == 0 || k == 0) {
            return 0;
        }
        int[] arr = new int[256];
        Arrays.fill(arr, -1);
        int ret = 0;
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j < str.length() && countNumber(arr) <= k) {
                // 不包含这个元素
                if (countNumber(arr) == k && arr[str.charAt(j)] == -1) {
                    break;
                }
                arr[str.charAt(j)] += 1;
                j++;
            }
            ret = Math.max(ret, j - i);
            // 前指针后移
            if (arr[str.charAt(i)] > -1) {
                arr[str.charAt(i)] -= 1;
            }
        }
        return ret;
    }
    
    private static int countNumber(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                count++;
            }
        }
        return count;
    }











}
