package GCJ2015.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-5-2.
 */
public class C {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;
    public class Pair implements Comparable<Pair>{
        long first;
        int second;
        public Pair(long first, int second){
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(Pair p){
            if(this.first != p.first){
                if(this.first < p.first){
                    return -1;
                }else{
                    return 1;
                }
            }else{
                return this.second - p.second;
            }
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> t = new ArrayList<>();
        ArrayList<Boolean> was = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int dd = sc.nextInt();
            int h = sc.nextInt();
            int m = sc.nextInt();
            for(int j = 0; j < h; j++){
                d.add(dd);
                t.add(m + j);
                was.add(false);
            }
        }
        TreeSet<Pair> pairs = new TreeSet<>();
        for(int i = 0; i < d.size(); i++){
            pairs.add(new Pair((long)(360 - d.get(i)) * t.get(i), i));
        }
        int cur = d.size();
        int ans = d.size();
        long last = 0;
        while(cur <= 2 * d.size()){
            long x = pairs.first().first;
            int y = pairs.first().second;
            pairs.remove(pairs.first());
            if(x > last){
                ans = Math.min(ans, cur);
            }
            last = x;
            if(was.get(y)){
                cur++;
            }else{
                cur--;
            }
            was.set(y, true);
            pairs.add(new Pair(x + (long) 360 * t.get(y), y));
        }
        pw.println(ans);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new C().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
