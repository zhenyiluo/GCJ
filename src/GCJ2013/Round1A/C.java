package GCJ2013.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-21.
 */
public class C {
    final static String PROBLEM_NAME = "C-small-practice-2";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int R = sc.nextInt();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        Arrays.fill(arr, 2);
        int[] end = new int[N];
        Arrays.fill(end, M);
        ArrayList<int[]> arrs = new ArrayList<int[]>();
        while(!Arrays.equals(arr, end)){
            arrs.add(arr.clone());
            int i = N - 1;
            while(arr[i] == M){
                i--;
            }
            arr[i]++;
            for(int j = i + 1; j < N; j++){
                arr[j] = arr[i];
            }
        }
        arrs.add(arr.clone());
        int[][] c = new int[N+1][N+1];
        for(int i = 0; i <=N; i++){
            c[i][0] = 1;
            c[i][i] = 1;
        }
        for(int i = 2; i <= N; i++){
            for(int j = 1; j < i; j++){
                c[i][j] = c[i-1][j-1] + c[i-1][j];
            }
        }
        double[] prob = new double[arrs.size()];
        for(int i = 0; i < arrs.size(); i++){
            int[] tmp = arrs.get(i);
            int[] count = new int[M+1];
            int num = N;
            for(int j = 0; j < tmp.length; j++){
                count[tmp[j]]++;
            }
            for(int j = 2; j <=M; j++){
                if(count[j]!= 0){
                    prob[i] += Math.log(c[num][count[j]]);
                    num -= count[j];
                }
            }
        }

        ArrayList<HashMap<Long, Integer>> products = new ArrayList<HashMap<Long, Integer>>();
        for(int i = 0; i < arrs.size(); i++){
            products.add(new HashMap<Long, Integer>());
            for(int mask = 1; mask < (1 << N); mask++){
                long prod = 1;
                for(int j = 0; j < N; j++){
                    if((mask & (1 << j))!= 0){
                        prod *= arrs.get(i)[j];
                    }
                }
                if(products.get(i).get(prod) != null){
                    products.get(i).put(prod, products.get(i).get(prod) + 1);
                }else{
                    products.get(i).put(prod, 1);
                }
            }
        }

        for(int i = 0; i < R; i++){
            long[] kk = new long[K];
            boolean[] mark = new boolean[arrs.size()];
            double[] p = new double[arrs.size()];
            for(int l = 0; l < arrs.size(); l++){
                p[l] = prob[l];
            }
            for(int j = 0; j < K; j++){
                kk[j] = sc.nextLong();
                if(kk[j] ==1){
                    continue;
                }
                for(int l = 0; l < arrs.size(); l++){
                    if(!mark[l]){
                        Integer t = products.get(l).get(kk[j]);
                        if(t!=null){
                            p[l] += Math.log(t);
                        }else{
                            mark[l] = true;
                        }
                    }
                }
            }
            int maxIndex = 0;
            double maxP = 0;
            for(int l = 0; l < arrs.size(); l++){
                if(!mark[l] && p[l] > maxP){
                    maxIndex = l;
                    maxP = p[l];
                }
            }

            for(int l = 0; l < N; l++){
                int result= arrs.get(maxIndex)[l];
                pw.print(result);
            }
            pw.println();
        }


    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
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
