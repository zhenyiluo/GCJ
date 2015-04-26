package GCJ2013.Round1B;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Zhenyi Luo on 15-4-23.
 */
public class C {
    final static String PROBLEM_NAME = "C-large-practice";
    final static String WORK_DIR = "/Users/cecilia/Downloads/";
    private static char[][] DICTIONARY;
    public void solve(Scanner sc, PrintWriter pw) {
        String s = sc.next();
        char[] ch = s.toCharArray();
        int n = s.length();
        int[][] d = new int[n + 1 + 100][5];
        for (int[] v : d) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }
        d[0][4] = 0;
        for (int consumed = 0; consumed < n; consumed++) {
            for (int unchanged = 0; unchanged < 5; unchanged++) {
                if (d[consumed][unchanged] < Integer.MAX_VALUE) {
                    for (char[] word : DICTIONARY) {
                        int nextIndexAllowedToChange = 4 - unchanged;
                        int newUnchanged = unchanged;
                        int countChanges = 0;
                        boolean good = word.length + consumed <= n;
                        for (int i = 0; i < word.length && good; i++) {
                            newUnchanged++;
                            if (word[i] != ch[consumed + i]) {
                                newUnchanged = 0;
                                if (i < nextIndexAllowedToChange) {
                                    good = false;
                                    break;
                                }
                                nextIndexAllowedToChange = i + 5;
                                countChanges++;
                            }
                        }
                        if (good) {
                            newUnchanged = Math.min(newUnchanged, 4);
                            if (d[consumed + word.length][newUnchanged] > d[consumed][unchanged] + countChanges) {
                                d[consumed + word.length][newUnchanged] = d[consumed][unchanged] + countChanges;
                            }
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int x : d[n]) {
            ans = Math.min(ans, x);
        }
        pw.println(ans);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader(WORK_DIR + PROBLEM_NAME + ".in"));
        PrintWriter pw = new PrintWriter(new FileWriter(WORK_DIR + PROBLEM_NAME + ".out"));
        Scanner sc1 = new Scanner(new FileReader(WORK_DIR + "garbled_email_dictionary.txt"));
        List<String> dic = new ArrayList<>();
        while(sc1.hasNext()){
            dic.add(sc1.next());
        }
        DICTIONARY = new char[dic.size()][];
        for (int i = 0; i < dic.size(); i++) {
            DICTIONARY[i] = dic.get(i).toCharArray();
        }
        int caseCnt = sc.nextInt();
        for (int caseNum=0; caseNum<caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum+1) + ": ");
            new C().solve(sc, pw);
        }
        pw.flush();
        pw.close();
        sc.close();
    }
}
