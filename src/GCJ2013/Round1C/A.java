package GCJ2013.Round1C;

import javax.swing.text.Element;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-25.
 */
public class A {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public static class Element implements Comparable<Element>{
        int start;
        int end;
        public Element(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Element e){
            return this.end - e.end;
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        String s = sc.next();
        int L = s.length();
        int n = sc.nextInt();
        TreeSet<Element> ts = new TreeSet<Element>();
        int start = -1;
        for(int i = 0; i < L; i++){
            char c = s.charAt(i);
            if(isCon(c)){
                if(start == -1){
                    start = i;
                }
            }else{
                if(start != -1){
                    int end = i-1;
                    if(end - start + 1 >= n){
                        ts.add(new Element(start, end));
                    }
                    start = -1;
                }
            }
        }
        if(start!= -1 && L-start >= n){
            ts.add(new Element(start, L-1));
        }

        long ans = 0;
        for(int i = 0; i < L; i++){
            Element e = ts.ceiling(new Element(-1, i+n-1));
            if(e == null){
                continue;
            }
            if(i < e.start){
                ans += L-e.start-n+1;
            }else{
                ans += L - i -n +1;
            }
        }
        pw.println(ans);
    }

    public boolean isCon(char c){
        if(c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u'){
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
            new A().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
