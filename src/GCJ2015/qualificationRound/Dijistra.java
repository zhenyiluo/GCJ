package GCJ2015.qualificationRound;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-10.
 */
public class Dijistra {
    final static String PROBLEM_NAME = "C-small-attempt2";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        long l = sc.nextLong();
        long x = sc.nextLong();
        String s = sc.next();
        StringBuilder sb = new StringBuilder("");
        for(long i = 0; i < x; i++){
            sb.append(s);
        }
        char[] cc = sb.toString().toCharArray();
        long len = sb.length();
        if(len < 3){
            pw.println("NO");
            return;
        }
        Cell prev = new Cell('1', true);
        for(int i = 0; i < len -2; i++){
            convert(prev, cc[i]);
            if(prev.flag && prev.c == 'i'){
                Cell prev1 = new Cell('1', true);
                for(int j = i+1; j < len-1;j++){
                    convert(prev1, cc[j]);
                    if(prev1.flag && prev1.c =='j'){
                        Cell prev2 = new Cell('1', true);
                        for(int k = j+1; k < len; k++){
                            convert(prev2, cc[k]);
                        }
                        if(prev2.flag && prev2.c == 'k'){
                            pw.println("YES");
                            return;
                        }
                    }
                }

            }
        }

        pw.println("NO");
    }

    public void convert(Cell c1, char c2){
        if(c1.c == '1'){
            if(c2 == 1){
                c1.c = c2;
            }else if(c2 == 'i'){
                c1.c = c2;
            }else if(c2 == 'j'){
                c1.c = c2;
            }else if(c2 == 'k'){
                c1.c = c2;
            }
        }else if( c1.c == 'i'){
            if(c2 == 1){
                c1.c = 'i';
            }else if(c2 == 'i'){
                c1.c = '1';
                c1.flag = !c1.flag;
            }else if(c2 == 'j'){
                c1.c = 'k';
            }else if(c2 == 'k'){
                c1.c = 'j';
                c1.flag = !c1.flag;
            }
        }else if( c1.c == 'j'){
            if(c2 == 1){
                c1.c = 'j';
            }else if(c2 == 'i'){
                c1.c = 'k';
                c1.flag = !c1.flag;
            }else if(c2 == 'j'){
                c1.c = '1';
                c1.flag = !c1.flag;
            }else if(c2 == 'k'){
                c1.c = 'i';
            }
        }else if( c1.c == 'k'){
            if(c2 == 1){
                c1.c = 'k';
            }else if(c2 == 'i'){
                c1.c = 'j';
            }else if(c2 == 'j'){
                c1.c = 'i';
                c1.flag = !c1.flag;
            }else if(c2 == 'k'){
                c1.c = '1';
                c1.flag = !c1.flag;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new Dijistra().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}

class Cell{
    char c;
    boolean flag;
    public Cell(char c, boolean flag){
        this.c = c;
        this.flag = flag;
    }
}
