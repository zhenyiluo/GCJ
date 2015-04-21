package GCJ2013.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-21.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public static class Element implements Comparable<Element>{
        int value;
        int index;
        public Element(int value, int index){
            this.value = value;
            this.index = index;
        }
        @Override
        public int compareTo(Element e){
            if(e.value != this.value){
                return e.value - this.value;
            }
           return this.index - e.index;
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        long E = sc.nextLong();
        long R = sc.nextLong();
        int N = sc.nextInt();
        int[] v = new int[N];
        for(int i = 0; i < N; i++){
            v[i] = sc.nextInt();
        }
        long ret = 0;
        if(R >= E){
            for(int i = 0; i < N; i++){
                ret += E * v[i];
            }
            pw.println(ret);
            return;
        }

        TreeSet<Element> ts = new TreeSet<Element>();
        for(int i = 0; i < N; i++){
            ts.add(new Element(v[i], i));
        }

        long[] upper = new long[N];
        long[] lower = new long[N];
        for(int i = 0; i < N; i++){
            upper[i] = E;
            lower[i] = 0;
        }

        for(int i = 0; i < N; i++){
            Element e = ts.first();
            ts.remove(ts.first());
            long energy = upper[e.index] - lower[e.index];
            ret += energy * e.value;
            update(e.index, lower, upper, R, E);
        }
        pw.println(ret);
    }

    public void update(int index, long[] lower, long[] upper, long R, long E){
        int N = lower.length;
        long tmpPrev = upper[index] - R;
        int indexLeft = index -1;
        while(tmpPrev > 0 && indexLeft >= 0){
            lower[indexLeft] = Math.max(tmpPrev, lower[indexLeft]);
            indexLeft--;
            tmpPrev -= R;
        }

        long tmpPost = lower[index] + R;
        int indexRight = index +1;
        while(indexRight < N && tmpPost < E){
            upper[indexRight] = Math.min(upper[indexRight], tmpPost);
            indexRight++;
            tmpPost += R;
        }
    }
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
