package GCJ2013.Round1C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-26.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = 0;
        int sum = 0;
        while(sum < Math.abs(x) + Math.abs(y) || (sum + Math.abs(x) + Math.abs(y)) % 2 != 0){
            n++;
            sum += n;
        }
        StringBuilder ans = new StringBuilder("");
        while(n > 0){
            if(Math.abs(x) >= Math.abs(y)){
                if(x > 0){
                    ans.append('E');
                    x -= n;
                }else{
                    ans.append('W');
                    x += n;
                }
            }else{
                if(y > 0){
                    ans.append('N');
                    y -= n;
                }else{
                    ans.append('S');
                    y += n;
                }
            }
            n--;
        }
        pw.println(ans.reverse().toString());
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
