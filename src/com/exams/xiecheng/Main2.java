package com.exams.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main2 {
    
    private static int cnt = 0;
    
    
    private static void dfs(int row, int col, int[][] matrix, int[][] visited) {
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            cnt++;
            return;
        }
        if (row > matrix.length - 1 || col > matrix[0].length - 1 || matrix[row][col] == 1 || visited[row][col] == 1) {
            return;
        }
        visited[row][col] = 1;
        dfs(row + 1, col, matrix, visited);
        dfs(row, col + 1, matrix, visited);
        visited[row][col] = 0;
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _matrixGrid_rows = 0;
        int _matrixGrid_cols = 0;
        _matrixGrid_rows = Integer.parseInt(in.nextLine().trim());
        _matrixGrid_cols = Integer.parseInt(in.nextLine().trim());

        int visited[][] = new int[_matrixGrid_rows][_matrixGrid_cols];
        
        int[][] _matrixGrid = new int[_matrixGrid_rows][_matrixGrid_cols];
        for(int _matrixGrid_i=0; _matrixGrid_i<_matrixGrid_rows; _matrixGrid_i++) {
            for(int _matrixGrid_j=0; _matrixGrid_j<_matrixGrid_cols; _matrixGrid_j++) {
                _matrixGrid[_matrixGrid_i][_matrixGrid_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        dfs(0,0, _matrixGrid, visited);
        System.out.println(String.valueOf(cnt));

    }
}
