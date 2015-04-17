package GCJ2014.Round1C;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Zhenyi Luo on 15-4-16.
 */
public class ReorderingTrainCars {
    final static String PROBLEM_NAME = "B-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/" + PROBLEM_NAME ;

    public void solve(Scanner sc, PrintWriter pw){
        int N = sc.nextInt();
        String[] s = new String[N];
        for(int i = 0; i < N; i++)
        {
            s[i] = sc.next();
        }

        preProcess(s);

        for(int i = 0; i < s.length; i++){
            if(!check(s[i])){
                pw.println(0);
                return;
            }
        }

        long ret = 1;

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for(int i = 0; i < s.length; i++){
            if(s[i].length() ==1){
                if(!hm.containsKey(s[i])){
                    hm.put(s[i], 1);
                }else{
                    hm.put(s[i], hm.get(s[i]) +1);
                }
            }else{
                if(hm.containsKey(s[i])){
                    pw.println(0);
                    return;
                }else{
                    hm.put(s[i], 1);
                }
            }
        }

        HashMap<Character, String> startsWith = new HashMap<Character, String>();
        HashMap<Character, String> endsWith = new HashMap<Character, String>();
        HashMap<Character, Integer> singles = new HashMap<Character, Integer>();

        for(String key : hm.keySet()){
            if(key.length() == 1){
                singles.put(key.charAt(0), hm.get(key));
            }else{
                char start = key.charAt(0);
                char end = key.charAt(key.length()-1);
                if(startsWith.containsKey(start)){
                    pw.println(0);
                    return;
                }else{
                    startsWith.put(start, key);
                }

                if(endsWith.containsKey(end)){
                    pw.println(0);
                    return;
                }else{
                    endsWith.put(end, key);
                }
            }
        }

        for(char c = 'a'; c <= 'z'; c++){
            if(singles.containsKey(c)){
                int n = singles.get(c);
                for(int i = 2; i <= n; i++){
                    ret = (ret * i) % 1000000007;
                }
            }

            if(startsWith.containsKey(c) || endsWith.containsKey(c)){
                singles.remove(c);
            }

            if(startsWith.containsKey(c) && endsWith.containsKey(c)){
                String start = endsWith.get(c);
                int startL = start.length();
                String end = startsWith.get(c);
                String concat = "" + start.substring(0, startL - 1) + end.substring(1);
                if(!check(concat)){
                    pw.println(0);
                    return;
                }
                endsWith.remove(c);
                startsWith.remove(c);
                startsWith.put(concat.charAt(0), concat);
                endsWith.put(concat.charAt(concat.length()-1), concat);
            }
        }
        HashSet<String> hs = new HashSet<String>();
        for(char c: singles.keySet()){
            hs.add(String.valueOf(c));
        }
        hs.addAll(startsWith.values());
        hs.addAll(endsWith.values());

        StringBuilder sb = new StringBuilder("");
        for(String tmp : hs){
            sb.append(tmp);
        }

        if(!check(sb.toString())){
            pw.println(0);
            return;
        }
        int num = hs.size();

        for(int i =2 ; i <= num; i++){
            ret = (ret * i) % 1000000007;
        }
        pw.println(ret);
    }

    public boolean check(String s){
        HashSet<Character> hs = new HashSet<Character>();
        for(int i = 0; i < s.length(); i++){
            if(hs.contains(s.charAt(i))) {
                return false;
            }else{
                hs.add(s.charAt(i));
            }
        }
        return true;
    }

    public void preProcess(String[] sArray){
        for(int i = 0; i < sArray.length; i++){
            StringBuilder tmp = new StringBuilder("");
            char prev = '%';
            String s = sArray[i];
            for(int j = 0; j < s.length(); j++){
                if(prev != s.charAt(j)){
                    tmp.append(s.charAt(j));
                    prev = s.charAt(j);
                }
            }
            sArray[i] = tmp.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + ".out"));
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new ReorderingTrainCars().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
