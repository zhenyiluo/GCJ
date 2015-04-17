package GCJ2014.Round1C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-16.
 */
public class PartElf {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        String[] s = sc.next().split("/");
        long P = Long.valueOf(s[0]);
        long Q = Long.valueOf(s[1]);
        long g = gcd(P, Q);
        P /= g;
        Q /= g;
        if(!check(Q)){
            pw.println("impossible");
        }else{
            int ret = 0;
            while(P < Q){
                ret++;
                P *= 2;
            }
            pw.println(ret);
        }
    }

    public boolean check(long a){
        if(a == 1){
            return true;
        }else{
            if(a % 2 != 0){
                return false;
            }else{
                return check(a/2);
            }
        }
    }

    public long gcd(long a, long b){
        if(b == 0){
            return a;
        }else{
            return gcd(b, a % b);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new PartElf().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
