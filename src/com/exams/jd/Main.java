package com.exams.jd;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-04-13
 * Time: 18:34
 * Description:
 */
public class Main {
    
//    static class Entity {
//        public int a1 = 0;
//        public int a2 = 0;
//
//        public Entity(int a1, int a2) {
//            this.a1 = a1;
//            this.a2 = a2;
//        }
//
//        @Override
//        public String toString() {
//            return "[" + a1 + "," + a2 + "]";
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int m = in.nextInt();
//            int ret = 0;
//            String[] strs = new String[m];
//            for (int i = 0; i < m; i++) {
//                strs[i] = in.next();
//            }
//            String t = in.next();
//            List<List<Entity>> list = new ArrayList();
//            for (int i = 0; i < strs.length; i++) {
//                String str = strs[i];
//                List<Entity> lt = new ArrayList<>();
//                for (int j = 0; j < t.length(); j++) {
//                    int start = j;
//                    int end = start + str.length();
//                    if (end <= t.length() && str.equals(t.substring(start, end))) {
//                        lt.add(new Entity(start, end - 1));
//                    }
//                }
//                list.add(lt);
//            }
//            List<Entity> l1 = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                for (int j = 0; j < list.get(i).size(); j++) {
//                    l1.add(list.get(i).get(j));
//                }
//            }
//            Collections.sort(l1, (en1, en2) -> en1.a2 - en2.a1);
//            for (int i = 0; i < l1.size() - 1; i++) {
//                if (l1.get(i).a2 < l1.get(i + 1).a1) {
//                    ret++;
//                }
//            }
//            System.out.println(l1);
//            System.out.println(ret == 0 ? 0 : ret + 1);
//        }
//    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        ArrayList<Integer>[] graph = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int from = input.nextInt(), to = input.nextInt();
            from--;
            to--;
            graph[from].add(to);
            graph[to].add(from);
        }

//		for(int i = 0;i < n;i++)
//			System.out.println(graph[i]);
        System.out.println(leastTime(graph));
    }

    private static int leastTime(ArrayList<Integer>[] graph) {
        int result = 0;
        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        for (int i = 0; i < graph[0].size(); i++) {
            visited[graph[0].get(i)] = true;
            int count = countOfChild(graph, graph[0].get(i), visited);
            result = Math.max(result, count);
        }

        return result;
    }

    private static int countOfChild(ArrayList<Integer>[] graph, Integer x, boolean[] visited) {
        int sum = 1;
        for (int i = 0; i < graph[x].size(); i++) {
            int next = graph[x].get(i);
            if (!visited[next]) {
                visited[next] = true;
                sum += countOfChild(graph, next, visited);
            }
        }
        return sum;
    }
    
}
