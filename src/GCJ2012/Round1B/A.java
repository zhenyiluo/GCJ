package GCJ2012.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-30.
 */
public class A {
    final static String PROBLEM_NAME = "A-small-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME;

    public static class Contestant{
        int id;
        int value;
        boolean flag = true;
        double perc = 0;
        public Contestant(int id, int value){
            this.id = id;
            this.value = value;
        }
    }
    public void solve(Scanner sc, PrintWriter pw){
        int n = sc.nextInt();
        ArrayList<Contestant> list = new ArrayList<>();
        int sum = 0;
        boolean flag = true;
        int count = 0;
        for(int i = 0; i < n; i++){
            int num = sc.nextInt();
            if(num == 0){
                flag = false;
                count++;
            }
            sum += num;
            list.add(new Contestant(i, num));
        }
        if(!flag){
            for(Contestant contestant : list){
                if(contestant.value!= 0){
                    contestant.perc = 0;
                }else{
                    contestant.perc = 100.0 / count;
                }
                pw.print(contestant.perc + " ");
            }
            pw.println();
            return;
        }

        Collections.sort(list, new Comparator<Contestant>(){
           @Override
            public int compare(Contestant c1, Contestant c2){
               return c2.value - c1.value;
           }
        });
        for(Contestant contestant : list){
            if(1.0 * contestant.value >= (2.0 * sum / n)){
                contestant.flag = false;
                n--;
                sum -= contestant.value;
            }else{
                break;
            }
        }
        for(Contestant contestant : list){
            if(contestant.flag){
                contestant.perc = ((2.0 * sum / n) - contestant.value) / sum;
            }
        }
        Collections.sort(list, new Comparator<Contestant>(){
            @Override
            public int compare(Contestant c1, Contestant c2){
                return c1.id - c2.id;
            }
        });
        for(Contestant contestant : list){
            pw.print(contestant.perc + " ");
        }
        pw.println();
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
