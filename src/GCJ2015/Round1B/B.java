package GCJ2015.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-5-2.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, 1, -1};
    public void solve(Scanner sc, PrintWriter pw){
        int r = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                int count = 0;
                for(int k = 0; k < 4; k++){
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(isOutOfRange(x, y, r, c)){
                        continue;
                    }
                    count++;
                }
                if((i+j) % 2 == 0){
                    list1.add(count);
                    list2.add(0);
                }else{
                    list1.add(0);
                    list2.add(count);
                }
            }
        }

        Collections.sort(list1);
        Collections.sort(list2);
        long ans1 = 0;
        long ans2 = 0;
        for(int i = 0; i < n; i++){
            ans1 += list1.get(i);
            ans2 += list2.get(i);
        }
        pw.println(Math.min(ans1, ans2));
    }

    public static boolean isOutOfRange(int x, int y, int r, int c){
        if(x < 0 || y < 0 || x >= r || y >= c){
            return true;
        }
        return false;
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
