package GCJ2014.Round1A;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-13.
 */
public class FullBinaryTree {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;
    ArrayList<LinkedList<Integer>> neighborhoods;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        neighborhoods = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < N+1; i++){
            neighborhoods.add(new LinkedList<Integer>());
        }
        for(int i = 0; i < N-1; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            neighborhoods.get(a).add(b);
            neighborhoods.get(b).add(a);
        }
        int ret = N+1;
        for(int i = 1; i <= N; i++){
            ret = Math.min(ret, N - maxSubTreeNodes(i, 0));
        }
        pw.println(ret);
    }

    public int maxSubTreeNodes(int node, int parent){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return i2 - i1;
            }
        }){};
        for(int n : neighborhoods.get(node)){
            if(n == parent){
                continue;
            }
            pq.add(maxSubTreeNodes(n, node));
        }
        if (pq.size() >=2 ){
            return 1 + pq.poll() + pq.poll();
        }else{
            return 1;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new FullBinaryTree().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
