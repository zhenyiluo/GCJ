package GCJ2014.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-15.
 */
public class TheBoaredTravellingSalesMan {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    static boolean[][] g;
    static boolean[] vis;
    static int n;
    static class City{
        int code;
        int id;
        public City(int code, int id){
            this.code = code;
            this.id = id;
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        n = sc.nextInt();
        int m = sc.nextInt();
        City[] a = new City[n];
        for(int i = 0; i < n; i++){
            a[i] = new City(sc.nextInt(), i);
        }
        Arrays.sort(a, new Comparator<City>(){
            @Override
            public int compare(City c1, City c2){
                return c1.code - c2.code;
            }
        });

        int[] newId = new int[n];
        for(int i = 0; i < n; i++){
            newId[a[i].id] = i;
        }

        g = new boolean[n][n];
        for(int i = 0; i < m; i++){
            int v1 = newId[sc.nextInt() -1];
            int v2 = newId[sc.nextInt() -1];
            g[v1][v2] = g[v2][v1] = true;
        }

        vis = new boolean[n];
        int[] ans = new int[n];
        boolean[] used = new boolean[n];

        for(int i = 0; i < n; i++){
            boolean flag = true;
            for(int j = 0; j < n && flag; j++){
                if(!used[j]){
                    used[j] = true;
                    ans[i] = j;
                    if(can(ans, i+1)){
                        flag = false;
                        continue;
                    }
                    used[j] = false;
                }
            }
        }

        for(int i = 0; i < n; i++){
            pw.print(a[ans[i]].code);
        }
        pw.println();
    }

    public static boolean can(int[] seq, int size){
        Set<Integer> bad = new HashSet<Integer>();
        List<Integer> stack = new ArrayList<Integer>();
        stack.add(seq[0]);

        for(int i = 1; i < size; i++){
            int next = seq[i];
            boolean flag = true;
            while(!stack.isEmpty() && flag){
                int v = stack.get(stack.size() -1);
                if(g[v][next]){
                    stack.add(next);
                    flag = false;
                    continue;
                }
                bad.add(stack.remove(stack.size() -1));
            }
            if(stack.isEmpty()){
                return false;
            }
        }

        Arrays.fill(vis, false);

        int reach = dfs(seq[0], bad);
        return reach == n - bad.size();
    }

    public static int dfs(int v, Set<Integer> bad){
        vis[v] = true;
        int ret = 1;
        for(int to = 0; to < n; to++){
            if(g[v][to] && !vis[to] && ! bad.contains(to)){
                ret += dfs(to, bad);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new TheBoaredTravellingSalesMan().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
