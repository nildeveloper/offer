package com.leetcode.bfs;

import org.junit.Test;
import sun.plugin2.gluegen.runtime.CPU;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-10-25
 * Time: 21:42
 * Description:
 */
public class Solution {

    /**
     * 200. 岛屿数量
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
     * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * <p>
     * 输出: 3
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        // 状态数组
        int[][] state = new int[row][col];
        // 从每个点遍历一遍
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 是岛屿，且没有被访问过
                if (grid[i][j] == '1' && state[i][j] == 0) {
                    count++;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i * col + j);  // queue中存放坐标值
                    // 标记访问
                    state[i][j] = 1;
                    while (!queue.isEmpty()) {
                        // 拿到当前坐标值
                        int pos = queue.poll();
                        int x = pos / col;
                        int y = pos % col;
                        // 判断四个方向 是岛屿 且没有被访问过
                        // 左
                        if (x - 1 >= 0 && x - 1 < row && y >= 0 && y < col && grid[x - 1][y] == '1' && state[x - 1][y] == 0) {
                            queue.offer((x - 1) * col + y);
                            state[x - 1][y] = 1;
                        }
                        // 右
                        if (x + 1 >= 0 && x + 1 < row && y >= 0 && y < col && grid[x + 1][y] == '1' && state[x + 1][y] == 0) {
                            queue.offer((x + 1) * col + y);
                            state[x + 1][y] = 1;
                        }
                        // 上
                        if (x >= 0 && x < row && y - 1 >= 0 && y - 1 < col && grid[x][y - 1] == '1' && state[x][y - 1] == 0) {
                            queue.offer(x * col + (y - 1));
                            state[x][y - 1] = 1;
                        }
                        // 下
                        if (x >= 0 && x < row && y + 1 >= 0 && y + 1 < col && grid[x][y + 1] == '1' && state[x][y + 1] == 0) {
                            queue.offer(x * col + (y + 1));
                            state[x][y + 1] = 1;
                        }
                    }
                }
            }
        }
        return count;
    }

    // 状态数组
    private int[][] state;

    public int numIslandsWithDfs(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        state = new int[row][col];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && state[i][j] == 0) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    // 填充状态数组
    private void dfs(char[][] grid, int i, int j) {
        // 不满足条件，结束
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] == '0' || state[i][j] == 1) {
            return;
        }
        // 走一步
        state[i][j] = 1;
        // 向四周走
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * 130. 被围绕的区域
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     */
    public void solve(char[][] board) {
        if (board.length == 0) {
            return ;
        }
        int row = board.length;
        int col = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 边界 O 先变成 #
                if ((i == 0 || j == 0 || i == row - 1 || j == col - 1) && board[i][j] == 'O') {
                    // 存放坐标
                    queue.offer(i * col + j);
                    // 坐标变换
                    board[i][j] = '#';
                    while (!queue.isEmpty()) {
                        Integer poll = queue.poll();
                        int x = poll / col;
                        int y = poll % col;
                        // 遍历四周
                        if (x - 1 >= 0 && board[x - 1][y] == 'O') {
                            queue.offer((x - 1) * col + y);
                            board[x - 1][y] = '#';
                        }
                        if (x + 1 <= row - 1 && board[x + 1][y] == 'O') {
                            queue.offer((x + 1) * col + y);
                            board[x + 1][y] = '#';
                        }
                        if (y - 1 >= 0 && board[x][y - 1] == 'O') {
                            queue.offer(x * col + y - 1);
                            board[x][y - 1] = '#';
                        }
                        if (y + 1 <= col - 1 && board[x][y + 1] == 'O') {
                            queue.offer(x * col + y + 1);
                            board[x][y + 1] = '#';
                        }
                    }
                }
            }
        }
        // O 变为 X， # 变为 O
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void solveWithDfs(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == 0 || j == 0 || i == row - 1 || j == col - 1) && board[i][j] == 'O') {
                    dfsSolve(board, i, j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfsSolve(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j > board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfsSolve(board, i - 1, j);
        dfsSolve(board, i + 1, j);
        dfsSolve(board, i, j - 1);
        dfsSolve(board, i, j + 1);
    }
    
    
    
    
    
    
    
    @Test
    public void test() {
        char[][] grid = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'0', '1', '0'}};
        System.out.println(numIslands(grid));
    }
    
    @Test
    public void test1() {
        char[][] borad = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        char[][] arr = new char[][]{
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
        };
        solve(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
