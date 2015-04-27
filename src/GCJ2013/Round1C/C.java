package GCJ2013.Round1C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-26.
 */
public class C {
    final static String PROBLEM_NAME = "C-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/";
    static int treeSize;
    static int treeMin[];
    public static class Attack implements Comparable<Attack>{
        int time;
        int left;
        int right;
        int strength;
        public Attack(int time, int left, int right, int strength){
            this.time = time;
            this.left = left;
            this.right = right;
            this.strength = strength;
        }

        @Override
        public int compareTo(Attack a){
            return this.time - a.time;
        }
    }
    public void solve(Scanner sc, PrintWriter pw) {
        int n = sc.nextInt();
        List<Attack> attacksL = new ArrayList<Attack>();
        for(int i = 0; i < n; i++){
            int di = sc.nextInt();
            int ni = sc.nextInt();
            int wi = sc.nextInt();
            int ei = sc.nextInt();
            int si = sc.nextInt();
            int ddi = sc.nextInt();
            int dpi = sc.nextInt();
            int dsi = sc.nextInt();
            for(int j = 0; j < ni; j++){
                attacksL.add(new Attack(di, wi, ei, si));
                di += ddi;
                wi += dpi;
                ei += dpi;
                si += dsi;
            }
        }
        Attack[] attacks = (Attack[]) attacksL.toArray(new Attack[attacksL.size()]);
        Arrays.sort(attacks);
        int[] points = new int[2* attacks.length];
        for(int i = 0; i < attacks.length; i++){
            points[2 * i] = attacks[i].left;
            points[2 * i + 1] = attacks[i].right;
        }
        Arrays.sort(points);
        int numPoints = 0;
        for(int i = 0, prev = Integer.MIN_VALUE; i < points.length; i++){
            if(points[i] != prev){
                points[numPoints++] = points[i];
            }
        }

        for(int i = 0; i < attacks.length; i++){
            attacks[i].left = Arrays.binarySearch(points, 0, numPoints, attacks[i].left);
            attacks[i].right = Arrays.binarySearch(points, 0, numPoints, attacks[i].right);
        }

        treeSize = 1;
        while(treeSize < numPoints -1){
            treeSize *= 2;
        }

        treeMin = new int[treeSize * 2];
        int ans = 0;
        for(int i = 0; i < attacks.length;){
            int j;
            for(j = i; j < attacks.length && attacks[j].time == attacks[i].time; j++){
                if(treeMin(attacks[j].left, attacks[j].right) < attacks[j].strength){
                    ans++;
                }
            }
            for(int k = i; k < j; k++){
                treeUpdate(attacks[k].left, attacks[k].right, attacks[k].strength);
            }
            i = j;
        }
        pw.println(ans);
    }

    public static int treeMin(int from , int to){
        return treeMin(1, 0, treeSize, from, to);
    }

    public static int treeMin(int cur, int l, int r, int from, int to){
        if( from >= r || to <= l){
            return Integer.MAX_VALUE;
        }
        int ans = treeMin[cur];
        if(from > l || to < r){
            int mid = (l + r) >> 1;
            ans = Math.max(ans, Math.min(treeMin(cur << 1, l, mid, from, to), treeMin((cur << 1) + 1, mid, r, from, to)));
        }
        return ans;
    }

    public static void treeUpdate(int from, int to, int value){
        treeUpdate(1, 0, treeSize, from, to, value);
    }

    public static void treeUpdate(int cur, int l, int r, int from ,int to, int value){
        if(from >= r || to <= l){
            return;
        }
        if(from <= l && r <= to){
            treeMin[cur] = Math.max(treeMin[cur], value);
        }else{
            int mid = (l+r) >> 1;
            treeUpdate(cur << 1, l, mid, from, to, value);
            treeUpdate((cur << 1) + 1, mid, r, from, to, value);
            treeMin[cur] = Math.max(treeMin[cur], Math.min(treeMin[cur << 1], treeMin[(cur << 1) + 1]));
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + PROBLEM_NAME + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + PROBLEM_NAME + ".out"));
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
