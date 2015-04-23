package GCJ2013.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-22.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        x = Math.abs(x);
        int layer = x+y;
        boolean isCenter = (x == 0);
        for(int i = 0; i < layer; i += 2){
            n -= i*2 + 1;
        }
        double ans = 0;
        if(n <= 0){
            ans = 0;
        }else if(n >= 2 * layer + 1){
            ans = 1;
        }else if(isCenter){
            ans = 0;
        }else{
            double[][] dp = new double[5001][5001];
            dp[0][0] = 1;
            for(int i = 0; i < n; i++){
                for(int j = 0; j <= layer; j++){
                    // no choice but to left
                    if(i-j == layer){
                        dp[i+1][j+1] += dp[i][j];
                    // no choice but to right
                    }else if(j == layer){
                        dp[i+1][j] += dp[i][j];
                    }else{
                        // half chance to right
                        dp[i+1][j] += dp[i][j] * 0.5;
                        // half chance to left
                        dp[i+1][j+1] += dp[i][j] * 0.5;
                    }
                }
            }
            for(int i = y+1; i <= layer; i++){
                ans +=dp[n][i];
            }
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
            new B().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
