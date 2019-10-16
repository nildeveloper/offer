package com.exams.fasthand;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-30
 * Time: 20:41
 * Description:
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int row = in.nextInt();
            int col = in.nextInt();
            int k = in.nextInt();
            int ret = movingCount(row, col, k);
            System.out.println(ret);
        }
    }
    
    public static int movingCount(int rows, int cols, int k) {
        boolean[][] visited = new boolean[rows][cols];
        return countingStep(k, rows, cols, 0, 0, visited);
    }

    public static int countingStep(int limit, int row, int col, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] || bitSum(i) + bitSum(j) > limit) {
            return 0;
        }
        // 成功走一步
        visited[i][j] = true;
        return countingStep(limit, row, col, i - 1, j, visited)
                + countingStep(limit, row, col, i + 1, j, visited)
                + countingStep(limit, row, col, i, j - 1, visited)
                + countingStep(limit, row, col, i, j + 1, visited)
                + 1;
    }

    public static int bitSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }
    
}
