package GCJ2015.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-17.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int B = sc.nextInt();
        long N = sc.nextLong();
        int[] T = new int[B];

        for(int i = 0; i < B; i++){
            T[i] = sc.nextInt();
        }
        long L = 0;
        long R = 1000000000000000L;
        while(R - L > 1){
            long mid = (L + R) / 2;
            if(f(mid, T) < N){
                L = mid;
            }else{
                R = mid;
            }
        }
        long when = L;
        long x = f(when, T);
        long toBeServed = N - x;
        for(int i = 0; i < B; i++){
            if(when % T[i] == 0){
                toBeServed --;
                if(toBeServed == 0){
                    pw.println(i+1);
                    return;
                }
            }
        }
    }

    public static long f(long x, int[] m){
        long sum = 0;
        for(int i = 0; i < m.length; i++){
            sum += (x-1) / m[i] +1;
        }
        return sum;
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
