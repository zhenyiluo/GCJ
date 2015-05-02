package GCJ2015.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-5-2.
 */
public class B {
    final static String PROBLEM_NAME = "B-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;
    public void solve(Scanner sc, PrintWriter pw){
        int r = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();
        if(r > c){
            int tmp = c;
            c = r;
            r = tmp;
        }
        int bound = (r*c +1) /2;
        if( bound >= n){
            System.out.println(0);
            pw.println(0);
            return;
        }
        long ans = 0;
        n -= bound;
        int count = 0;
        while(r >= 1 && c >= 1 && n > 0){
            if(r== 1 && c== 1){
                if(count == 0){

                }
            }

            if( r-2 > 1 && c-2 > 1){
                int count = (r-2) * (c-2) ;
            }

            if(2 * (r+c) - 4 >= n){
                ans += (n - bound + 1) * 2;
                System.out.println(ans);
                pw.println(ans);
                return;
            }else{
                ans += (r-1 + c-1) * 2;
                n -= 2 * (r+c) -4;
            }
            r--;
            c--;
        }
        System.out.println(ans);
    }

//    public static class Vertex{
//        int x;
//        int y;
//        ArrayList<Vertex> adj;
//        public Vertex(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    final static int[] dx = {-1, 1, 0, 0};
//    final static int[] dy = {0, 0, 1, -1};
//    public void solve(Scanner sc, PrintWriter pw){
//        int r = sc.nextInt();
//        int c = sc.nextInt();
//        int n = sc.nextInt();
//        Vertex[][] vertexes = new Vertex[r][c];
//        for(int i = 0; i < r; i++){
//            for(int j = 0; j < c; j++){
//                vertexes[i][j] = new Vertex(i, j);
//            }
//        }
//
//        for(int i = 0; i < r; i++){
//            for(int j = 0; j < c; j++){
//                vertexes[i][j].adj = new ArrayList<>();
//                for(int k = 0; k < 4; k++){
//                    int x = i + dx[k];
//                    int y = j + dy[k];
//                    if(!isOutOfRange(x, y, r, c)){
//                        vertexes[i][j].adj.add(vertexes[x][y]);
//                    }
//                }
//            }
//        }
//
//        boolean[][] visited = new boolean[r][c];
//        ArrayList<ArrayList<Vertex>> list = new ArrayList<>();
//        for(int i = 0; i < 4; i++){
//            list.add(new ArrayList<Vertex>());
//        }
//        for(int i = 0; i < r; i++){
//            for(int j = 0; j < c; j++){
//                if(i == 0 && j == 0){
//                    continue;
//                }
//                int count = vertexes[i][j].adj.size();
//                list.get(count-1).add(vertexes[i][j]);
//            }
//        }
//        if(n == 0){
//            pw.println(0);
//            return;
//        }
//        visited[0][0]  = true;
//        long ans  = dfs(list, visited, 0, 0, n-1);
//    }

//    private long dfs(ArrayList<ArrayList<Vertex>> list, boolean[][] visited, int x, int y, int n) {
//        if(n == 0){
//            return 0;
//        }
//        long ans = 0;
//        for(int i = 0; i < 4; i++){
//            ArrayList<Vertex> tmp = list.get(i);
//            for(Vertex vertex : tmp){
//                if()
//            }
//        }
//    }
//
//    public static boolean isOutOfRange(int x, int y, int r, int c){
//        if(x < 0 || y < 0 || x >= r || y >= c){
//            return true;
//        }
//        return false;
//    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new B().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
