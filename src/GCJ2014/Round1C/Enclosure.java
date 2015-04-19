package GCJ2014.Round1C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-19.
 */
public class Enclosure {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        pw.println(min_stones(N, M, K));
    }

    public int min_stones(int n, int m, int k){
        if(n > m){
            int tmp = n;
            n = m;
            m = tmp;
        }

        int ret = k;

        for(int r = 2; r < n+1; r++){
            for(int c = r; c < m+1; c++){
                if(r * c >= k){
                    for(int i = 0; i < 2 * r; i++){
                        int cover = r * c;
                        cover -= triangle(i / 4);
                        cover -= triangle((i+1) / 4);
                        cover -= triangle((i+2) / 4);
                        cover -= triangle((i+3) / 4);
                        if(cover < k){
                            break;
                        }
                        int stones = 2 * (r+c) -4 -i;
                        ret = Math.min(ret, stones);
                    }

                }
            }
        }
        return ret;
    }
    public int triangle(int size){
        return (size * (size+1)) / 2;
    }



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new Enclosure().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
