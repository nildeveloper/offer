package com.baidu;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            List<Integer> list = new ArrayList<>();
            int ret = m+n;
            int min = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    if ((n - i) * (m - j) <= k) {
                        min = i + j;
                        list.add(min);
                    }
                }
            }
            Collections.sort(list);
            System.out.println(list.get(0));
        }
    }
}
