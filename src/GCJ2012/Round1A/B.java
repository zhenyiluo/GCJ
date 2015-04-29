package GCJ2012.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-28.
 */
public class B {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;
    public static class Pair{
        int a;
        int b;
        boolean flag;
        public Pair(int a, int b, boolean flag){
            this.a = a;
            this.b = b;
            this.flag = flag;
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        ArrayList<Pair> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(new Pair(sc.nextInt(), sc.nextInt(), true));
        }
        int sum = 0;
        Collections.sort(list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.b - o2.b;
            }
        });
        int ans = 0;
        while(list.size() > 0){
            Iterator<Pair> iter = list.iterator();
            while(iter.hasNext()){
                Pair tmp = iter.next();
                if(tmp.b <= sum){
                    ans++;
                    if(tmp.flag){
                        sum += 2;
                    }else{
                        sum += 1;
                    }
                    iter.remove();
                }else{
                    break;
                }
            }
            if(list.size() == 0){
                pw.println(ans);
                return;
            }else{
                Pair select = null;
                iter = list.iterator();
                while(iter.hasNext()){
                    Pair tmp = iter.next();
                    if(tmp.flag && tmp.a <= sum){
                        if(select == null){
                            select = tmp;
                        }else if(tmp.b > select.b){
                            select = tmp;
                        }
                    }
                }
                if(select == null){
                    pw.println("Too Bad");
                    return;
                }else{
                    ans++;
                    sum++;
                    select.flag = false;
                }
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
