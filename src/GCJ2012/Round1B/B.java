package GCJ2012.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-5-1.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw) {
        int h = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] ceil = new int[n][m];
        int[][] floor = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ceil[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                floor[i][j] = sc.nextInt();
            }
        }

        double[][] dist = new double[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.MAX_VALUE);
        }

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        dist[0][0] = 0;

        preProcess(h, ceil, floor, dist, visited, n, m);
        if(visited[n-1][m-1]){
            pw.println(dist[n - 1][m - 1]);
            return;
        }

        updateDist(n, m, dist, h, ceil, floor, visited);


        dijistra(n, m, dist, h, ceil, floor, visited);


        pw.println(dist[n - 1][m - 1]);
    }

    public void dijistra(int n, int m, double[][] dist, int h, int[][] ceil, int[][] floor, boolean[][] visited){
        PriorityQueue<Vertex> pq = new PriorityQueue<>(100, new Comparator<Vertex>(){
            @Override
            public int compare(Vertex v1, Vertex v2){
                if(v1.dist < v2.dist){
                    return -1;
                }else if(v1.dist > v2.dist){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        Vertex[][] arr = new Vertex[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j]){
                    Vertex vertex = new Vertex(i, j);
                    vertex.dist = dist[i][j];
                    pq.add(vertex);
                    arr[i][j] = vertex;
                }
            }
        }

        while(!pq.isEmpty()){
            Vertex vertex = pq.poll();
            int x = vertex.x;
            int y = vertex.y;
            visited[x][y] = true;
            if(x == m-1 && y == n-1){
                break;
            }
            double newH = Math.max(floor[x][y], h - dist[x][y] * 10);
            for(int i = 0; i < 4; i++){
                int xx = x + dx[i];
                int yy = y + dy[i];
                if(!isOutOfRange(xx, yy, n, m) && !visited[xx][yy] && isPossibleToReach(x, y, xx, yy, ceil, floor)){
                    double tmp;
                    if(canReachRightNow(x, y, xx, yy, ceil, newH, floor)){
                        double water = newH - floor[x][y];
                        if(water < 20){
                            tmp = dist[x][y] + 10;
                        }else{
                            tmp = dist[x][y] + 1;
                        }
                    }else{
                        double diff = newH + 50 - ceil[xx][yy];
                        double timeNeeded = diff / 10;
                        int currentLevel = ceil[xx][yy] - 50;
                        int water = currentLevel - floor[x][y];
                        if(water < 20){
                            tmp = dist[x][y] + timeNeeded + 10;
                        }else{
                            tmp = dist[x][y] + timeNeeded + 1;
                        }
                    }
                    if(tmp < dist[xx][yy]){
                        pq.remove(arr[xx][yy]);
                        arr[xx][yy].dist = tmp;
                        dist[xx][yy] = tmp;
                        pq.add(arr[xx][yy]);
                    }
                }
            }
        }
    }

    private void updateDist(int n, int m, double[][] dist, int h, int[][] ceil, int[][] floor, boolean[][] visited) {
        for(int x = 0; x < n; x++){
            for(int y = 0; y < m; y++){
                if(dist[x][y] == 0){
                    for(int i = 0; i < 4; i++){
                        int xx = x + dx[i];
                        int yy = y + dy[i];
                        if(!isOutOfRange(xx, yy, n, m) && !visited[xx][yy] && isPossibleToReach(x, y, xx, yy, ceil, floor)){
                            double diff = h + 50 - ceil[xx][yy];
                            double timeNeeded = diff / 10;
                            int currentLevel = ceil[xx][yy] - 50;
                            int water = currentLevel - floor[x][y];
                            if(water < 20){
                                dist[xx][yy] = Math.min(timeNeeded + 10, dist[xx][yy]);
                            }else{
                                dist[xx][yy] = Math.min(timeNeeded + 1, dist[xx][yy]);
                            }
                        }
                    }
                }
            }
        }
    }


    public static class Vertex {
        int x = 0;
        int y = 0;
        double dist = 0;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final static int[] dx = {1, -1, 0, 0};
    final static int[] dy = {0, 0, 1, -1};

    private void preProcess(int h, int[][] ceil, int[][] floor, double[][] dist, boolean[][] visited, int n, int m) {
        Queue<Vertex> vertexes = new LinkedList<>();
        vertexes.add(new Vertex(0, 0));
        while (!vertexes.isEmpty()) {
            Vertex vertex = vertexes.poll();
            int x = vertex.x;
            int y = vertex.y;
            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                if (!isOutOfRange(xx, yy, n, m) && !visited[xx][yy] && isPossibleToReach(x, y, xx, yy, ceil, floor) && canReachRightNow(x, y, xx, yy, ceil, h, floor)) {
                    dist[xx][yy] = 0;
                    vertexes.add(new Vertex(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }
    }

    private boolean isPossibleToReach(int x1, int y1, int x2, int y2, int[][] ceil, int[][] floor) {
        if(ceil[x2][y2] - floor[x1][y1] < 50 || ceil[x2][y2] - floor[x2][y2] < 50 || ceil[x1][y1] - floor[x2][y2] < 50){
            return false;
        }
        return true;
    }

    public static boolean canReachRightNow(int x1, int y1, int x2, int y2, int[][] ceil, double h, int[][] floor) {
        if (ceil[x2][y2] - h < 50) {
            return false;
        }
        return true;
    }

    public static boolean isOutOfRange(int x, int y, int n, int m) {
        if (x < 0 || y < 0 || x >= n || y >= m) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new B().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
