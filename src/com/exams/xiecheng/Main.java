package com.exams.xiecheng;

import java.util.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Main {

    /*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 
******************************开始写代码******************************/
    static boolean CheckBlackList(String userIP, String blackIP) {
        if (userIP == null || blackIP == null) {
            return false;
        }
        // IPV4
        if (userIP.equals(blackIP)) {
            return true;
        }
        // 子网
        if (blackIP.contains("/")) {
            return isInRange(userIP, blackIP);
        }
        return false;
    }

    private static boolean isInRange(String ip, String cidr) {
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);
        return (ipAddr & mask) == (cidrIpAddr & mask);
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean res;

        String _userIP;
        try {
            _userIP = in.nextLine();
        } catch (Exception e) {
            _userIP = null;
        }

        String _blackIP;
        try {
            _blackIP = in.nextLine();
        } catch (Exception e) {
            _blackIP = null;
        }

        res = CheckBlackList(_userIP, _blackIP);
        System.out.println(String.valueOf(res ? 1 : 0));
    }
    

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int n = in.nextInt();
//            List<String[]> list = new ArrayList<>();
//            for (int i = 0; i < n; i++) {
//                String str = in.next();
//                String[] split = str.substring(1).split("/");
//                list.add(split);
//            }
//            String ret = "";
//            for (int i = 0; i < list.size(); i++) {
//                String[] strs1 = list.get(i);
//                
//                for (int j = 0; j < i; j++) {
//                   String[] strs2 = list.get(j);
//                }
//            }
//        }
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String str = in.next();
//            int k = in.nextInt();
//            String[] strs = str.substring(1, str.length() - 1).split(",");
//            for (int i = 0; i + k <= strs.length; i += k) {
//                for (int j = i, m = i + k - 1; j < m; j++, m--) {
//                    String temp = strs[j];
//                    strs[j] = strs[m];
//                    strs[m] = temp;
//                }
//            }
//            String ret = Arrays.toString(strs).replaceAll(" ", "");
//            System.out.println(ret);
//        }
//    }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            boolean ret = false;
//            String str = in.next();
//            String[] split = str.split(",");
//            Set<String> set = new HashSet<>();
//            for (String s : split) {
//                if (set.contains(s)) {
//                    ret = true;
//                }
//                set.add(s);
//            }
//            System.out.println(ret);
//        }
//    }
}
