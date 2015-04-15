package GCJ2014.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-14.
 */
public class NewLotteryGame {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    static HashMap<Tri, Long> hm = new HashMap<Tri, Long>();
    public void solve(Scanner sc, PrintWriter pw){
        int A = sc.nextInt();
        int B = sc.nextInt();
        int K = sc.nextInt();
        long ret = f(A, B, K);
        pw.println(ret);
    }

    public static long f(int A, int B, int K){
        if( A == 0 || B == 0 || K == 0){
            return 0;
        }

        if(A == 1 && B == 1){
            return 1;
        }
        Tri tmp = new Tri(A, B, K);
        if(hm.containsKey(tmp)){
            return hm.get(tmp);
        }else{
            long val = f(A >> 1, B >> 1, K >>1) +
                    f((A+1) >> 1, B >> 1, (K+1) >>1) +
                    f(A >> 1, (B+1) >> 1, (K+1) >> 1) +
                    f((A+1) >> 1, (B+1) >> 1, (K+1) >> 1);
            hm.put(tmp, val);
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new NewLotteryGame().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}

class Tri{
    int a;
    int b;
    int k;
    public Tri(int a, int b, int k){
        this.a = a;
        this.b = b;
        this.k = k;
    }

    @Override
    public int hashCode(){
        return  a/3+b/3+k/3;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Tri){
            if(((Tri) o).a == this.a && ((Tri) o).b == this.b && ((Tri) o).k == this.k){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
