package com.exams.mihayou;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-10
 * Time: 19:20
 * Description:
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int[] count = new int[1];
            int r = in.nextInt();
            int c = in.nextInt();
            String[] strs = new String[r];
            String[][] matrix = new String[r][c];
            for (int i = 0; i < matrix.length; i++) {
                strs[i] = in.next();
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = String.valueOf(strs[i].charAt(j));
                }
            }
            int[] pos = new int[4];
            for (int i = 0; i < pos.length; i++) {
                pos[i] = in.nextInt();
            }
            //交换位置
            String tmp = matrix[pos[0]][pos[1]];
            matrix[pos[0]][pos[1]] = matrix[pos[2]][pos[3]];
            matrix[pos[2]][pos[3]] = tmp;
            // 确定消除的数量
            Map<String, Set<String>> map = new HashMap<>();
            Set<String> set = new HashSet<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    String str = matrix[i][j];
                    if (map.get(str) ==null) {
                        set.add(new String(i + "," + j));
                        map.put(str, new HashSet<>(set));
                    } else {
                        Set<String> pos1 = map.get(str);
                        pos1.add(new String(i + "," + j));
                        map.put(str, pos1);
                    }
                }
            }
            System.out.println(count[0]);
        }
     }
     
     
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            String ret = "";
//            int n = in.nextInt();
//            int[] number = new int[n];
//            int zeroCnt = 0;
//            for (int i = 0; i < n; i++) {
//                number[i] = in.nextInt();
//            }
//            for (int i : number) {
//                if (i == 0) {
//                    zeroCnt++;
//                }
//            }
//            int retCnt = zeroCnt;
//            boolean isYes = true;
//            Arrays.sort(number);
//            for (int i = zeroCnt; i < number.length - 1; i++) {
//                if (number[i] == number[i + 1]) {
//                    ret = "NO+" + retCnt;
//                    isYes = false;
//                    break;
//                }
//                zeroCnt -= number[i + 1] - number[i] - 1;
//            }
//            if (isYes && zeroCnt >= 0) {
//                ret = "YES+" + retCnt;
//            } else {
//                ret = "NO+" + retCnt;
//            }
//            System.out.println(ret);
//        }
//    }
}
