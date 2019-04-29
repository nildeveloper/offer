package com.graph;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-26
 * Time: 9:36 AM
 * Description: 图  邻接矩阵
 */
public class GraphWithVertex {
    
    // 顶点
    class Vertex {
        public char label;
        public boolean visited;
        public Vertex(char label) {
            this.label = label;
            visited = false;
        }
    }
    
    class Graph {
        private final int maxVertices = 20;  // 最大顶点数
        private Vertex[] vertexList;  // 顶点集
        private int[][] adjMatrix;  // 边集
        private int vertexCount;  // 顶点个数
        private LinkedList<Integer> stack;
        private LinkedList<Integer> queue;
        
        public Graph() {
            vertexList = new Vertex[maxVertices];
            adjMatrix = new int[maxVertices][maxVertices];
            vertexCount = 0;
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {
                    adjMatrix[i][j] = 0;
                }
            }
            stack = new LinkedList();
            queue = new LinkedList();
        }
        
        public void addVertex(char label) {
            vertexList[vertexCount++] = new Vertex(label);
        }
        
        public void addEdge(int start, int end) {
            adjMatrix[start][end] = 1;
            adjMatrix[end][start] = 1;
        }
        
        public void displayVertex(int v) {
            System.out.println(vertexList[v].label);
        }
        
        public void dfs() {
            vertexList[0].visited = true;
            displayVertex(0);
            stack.push(0);
            while (!stack.isEmpty()) {
                int v = getAdjUnvisitedVertex(stack.peek());
                if (v == -1) {
                    stack.pop();
                } else {
                    vertexList[v].visited = true;
                    displayVertex(v);
                    stack.push(v);
                }
            }
            for (int j = 0; j < vertexCount; j++) {
                vertexList[j].visited = false;
            }
        }
        
        public void bfs() {
            vertexList[0].visited = true;
            displayVertex(0);
            queue.offer(0);
            int v2;
            while (!queue.isEmpty()) {
                int v1 = queue.poll();
                while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                    vertexList[v2].visited = true;
                    displayVertex(v2);
                    queue.offer(v2);
                }
            }
            for (int j = 0; j < vertexCount; j++) {
                vertexList[j].visited = false;
            }
        }

        public int getAdjUnvisitedVertex(int v) {
            for (int i = 0; i < vertexCount; i++) {
                if (adjMatrix[v][i] == 1 && vertexList[i].visited == false) {
                    return i;
                }
            }
            return -1;
        }
        
    }
}
