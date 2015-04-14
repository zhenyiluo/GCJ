package GCJ2014.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-13.
 */
public class ChargingChaos {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        int L = sc.nextInt();
        long[] ori = new long[N];
        long[] tar = new long[N];
        for(int i = 0; i < N; i++){
            ori[i] = sc.nextLong(2);
        }
        HashSet<Long> hs = new HashSet<Long>();
        for(int i = 0; i < N; i++){
            tar[i] = sc.nextLong(2);
            hs.add(tar[i]);
        }
        int ret = L + 1;
        for(int i = 0; i < N; i++){
            long flip = ori[0] ^ tar[i];
            boolean flag = true;
            for(int j = 0; j < N && flag; j++){
                if(!hs.contains(ori[j] ^ flip)){
                    flag = false;
                }
            }
            if(flag){
                ret = Math.min(ret, Long.bitCount(flip));
            }
        }
        if(ret == L +1){
            pw.println("NOT POSSIBLE");
        }else{
            pw.println(ret);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new ChargingChaos().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
