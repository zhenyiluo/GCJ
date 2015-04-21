package GCJ2013.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-20.
 */
public class A {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        long r = sc.nextLong();
        long t = sc.nextLong();
        pw.println(calFast(r, t));
    }

    public long calFast(long r, long t){
        long high = 1;
        while((2* r + 2* high -1)* high <= t){
            high*=2;
        }
        long low = high/2;
        while(low <= high){
            long mid = (low + high)/2;
            if((2* r + 2* mid -1)* mid < t){
                low = mid+1;
            }else if((2* r + 2* mid -1)* mid > t){
                high = mid-1;
            }else{
                return mid;
            }
        }
        return high;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new A().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
