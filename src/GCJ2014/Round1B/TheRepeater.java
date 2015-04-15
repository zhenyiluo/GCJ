package GCJ2014.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-14.
 */
public class TheRepeater {
    final static String PROBLEM_NAME = "A-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    ArrayList<LinkedList<Integer>> neighborhoods;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        ArrayList<Element>[] arrayOfQueues = new ArrayList[N];
        for(int i = 0; i <N; i++){
            String s = sc.next();
            arrayOfQueues[i] = getQueueOfElement(s);
        }

        boolean flag = true;
        int num = arrayOfQueues[0].size();
        for(int i = 1; i < N && flag; i++){
            if(arrayOfQueues[i].size() != num){
                flag = false;
            }
        }

        if(!flag){
            pw.println("Fegla Won");
            return;
        }

        char[] cArray = new char[num];
        int index = 0;
        Iterator<Element> it = arrayOfQueues[0].iterator();
        while(it.hasNext()){
            cArray[index++] = it.next().c;
        }

        for(int i = 1; i < N && flag; i++){
            Iterator<Element> tmp = arrayOfQueues[i].iterator();
            int tmpIndex = 0;
            while(tmp.hasNext() && flag){
                if(cArray[tmpIndex++] != tmp.next().c){
                    flag = false;
                }
            }
        }

        if(!flag){
            pw.println("Fegla Won");
            return;
        }

        int ret = 0;
        for(int i = 0; i < num; i++){
            Set<Integer> hs = new HashSet<Integer>();
            for(int j = 0; j < N; j++){
                hs.add(arrayOfQueues[j].get(i).n);
            }
            int min = Integer.MAX_VALUE;

            for(int n : hs){
                int sum = 0;
                for(int j = 0; j < N; j++){
                    sum += Math.abs(n - arrayOfQueues[j].get(i).n);
                }
                min = Math.min(min, sum);
            }
            ret += min;
        }
        pw.println(ret);
    }

    public static ArrayList<Element> getQueueOfElement(String s){
        ArrayList<Element> ret = new ArrayList<Element>();
        char prev = ';';
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == prev){
                count++;
            }else{
                if(count != 0){
                    ret.add(new Element(prev, count));
                }
                prev = s.charAt(i);
                count = 1;
            }
        }
        if(count != 0){
            ret.add(new Element(prev, count));
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new TheRepeater().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}

class Element{
    char c;
    int n;
    public Element(char c, int n){
        this.c = c;
        this.n = n;
    }
}