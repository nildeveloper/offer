package com.xiecheng;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            List<String[]> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String str = in.next();
                String[] split = str.substring(1).split("/");
                list.add(split);
            }
            String ret = "";
            for (int i = 0; i < list.size(); i++) {
                String[] strs1 = list.get(i);
                
                for (int j = 0; j < i; j++) {
                   String[] strs2 = list.get(j);
                }
            }
        }
    }

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
