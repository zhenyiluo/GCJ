package GCJ2013.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-23.
 */
public class C {
    final static String PROBLEM_NAME = "C-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;
    final static HashSet<String> hs = new HashSet<String>();
    public void solve(Scanner sc, PrintWriter pw){
        String s = sc.next();
        int n = s.length();
        int[] dp = new int[n+1];
        for(int i = 0; i < n; i++){
            int min = Integer.MAX_VALUE/10;
            for(int j = 1; j <= 10; j++){
                if(i+1-j < 0){
                    break;
                }
                min = Math.min(min, dp[i+1-j] + getNum(s.substring(i+1-j, i+1)));
            }
            dp[i+1] = min;
        }
        pw.println(dp[n]);
    }
    public int getNum(String s){
        if(hs.contains(s)){
            return 0;
        }
        if(check1(s)){
            return 1;
        }
        if(check2(s)){
            return 2;
        }
        return Integer.MAX_VALUE/10;
    }
    public boolean check1(String s){
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < n; i++){
            char tmp = sb.charAt(i);
            for(char c = 'a'; c <= 'z'; c++){
                if(c != tmp){
                    sb.setCharAt(i, c);
                }
                if(hs.contains(sb.toString())){
                    return true;
                }
            }
            sb.setCharAt(i, tmp);
        }
        return false;
    }

    public boolean check2(String s){
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < n-5; i++){
            char tmp1 = sb.charAt(i);
            for(char c = 'a'; c <= 'z'; c++){
                if(c != tmp1){
                    sb.setCharAt(i, c);
                }
            }
            for(int j = i+5; j < n; j++){
                char tmp2 = sb.charAt(j);
                for(char c = 'a'; c <= 'z'; c++){
                    if(c != tmp2){
                        sb.setCharAt(j, c);
                    }
                    if(hs.contains(sb.toString())){
                        return true;
                    }
                }
                sb.setCharAt(j, tmp2);
            }
            sb.setCharAt(i, tmp1);
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        Scanner sc1 = new Scanner(new FileReader("/Users/cecilia/Downloads/garbled_email_dictionary.txt"));
        while(sc1.hasNext()){
            hs.add(sc1.next());
        }
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.println("Case #" + (caseNum+1) + ": ");
            new C().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
