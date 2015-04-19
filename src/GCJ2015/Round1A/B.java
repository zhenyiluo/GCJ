package GCJ2015.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Zhenyi Luo on 15-4-17.
 */
public class B {
    final static String PROBLEM_NAME = "B-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public void solve(Scanner sc, PrintWriter pw){
        int B = sc.nextInt();
        int N = sc.nextInt();
        long[] a = new long[B];
        for(int i = 0; i < B; i++){
            a[i] = sc.nextLong();
        }

        BigInteger cmp = commonMultiple(a);

        BigInteger sum = BigInteger.valueOf(0);
        for(int i = 0; i < B; i++){
            sum = sum.add(cmp.divide(BigInteger.valueOf(a[i])));
        }
        BigInteger mod = BigInteger.valueOf(N).mod(sum);
        if(mod.compareTo(BigInteger.valueOf(0)) == 0){
            pw.println(B);
        }else{

            pw.println(getIndex(a, mod));
        }
    }
    public static class Element implements Comparable<Element>{
        long value;
        int id;
        public Element(long value, int id){
            this.value = value;
            this.id = id;
        }
        @Override
        public int compareTo(Element e){
            if(this.value != e.value){
                return (int)(this.value - e.value);
            }
            return this.id - e.id;
        }
    }
    public int getIndex(long[] a, BigInteger b){
        if(b.compareTo(BigInteger.valueOf(a.length)) <= 0){
            return b.intValue();
        }
        b = b.subtract(BigInteger.valueOf(a.length));
        TreeSet<Element> ts = new TreeSet<Element>();
        for(int i = 0; i < a.length; i++){
            ts.add(new Element(a[i], i));
        }
        Element e = null;
        while(b.compareTo(BigInteger.valueOf(0)) > 0){
            e = ts.first();
            ts.remove(ts.first());
            e.value = e.value + a[e.id];
            ts.add(e);
            b = b.subtract(BigInteger.valueOf(1));
        }
        return e.id+1;
    }
    public static BigInteger commonDivisor(BigInteger n,BigInteger m){
        if(m.compareTo(BigInteger.valueOf(0)) == 0){
            return n;
        }
        return commonDivisor(m, n.mod(m));
    }

    public static BigInteger commonMultiple(long[] a){
        BigInteger value= BigInteger.valueOf(a[0]);
        for(int i=1;i<a.length;i++){
            value=commonMultiple(value,a[i]);
        }
        return value;
    }

    public static BigInteger commonMultiple(BigInteger n,long m){
        return n.multiply(BigInteger.valueOf(m)).divide(commonDivisor(n, BigInteger.valueOf(m)));
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
