package GCJ2013.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-22.
 */
public class A {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int A = sc.nextInt();
        int N = sc.nextInt();
        int[] a = new int[N];
        for(int i = 0; i < N; i++){
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);
        int ret = N;

        if(A == 1){
            pw.println(ret);
            return;
        }
        int added = 0;
        for(int i = 0; i < N; i++){
            if(A > a[i]){
                A += a[i];
            }else{
                ret = Math.min(ret, added + N - i);
                Element e = getAdded(A, a[i]);
                added += e.added;
                A = e.value;
            }
        }
        ret = Math.min(ret, added);
        pw.println(ret);
    }

    public Element getAdded(int a, int b){
        int ret = 1;
        int sum = 1;
        int n = 1;
        while((a-1)*sum <= b-a){
            ret++;
            n *= 2;
            sum += n;
        }
        return new Element(ret, a + (a-1) * sum + b);
    }

    public static class Element{
        int added;
        int value;
        public Element(int added, int value){
            this.added = added;
            this.value = value;
        }
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
