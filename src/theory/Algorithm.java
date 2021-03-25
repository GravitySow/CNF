/*
 * Code By GravityS
 */
package theory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author gravitys
 */
public class Algorithm {

    private String[][] g;
    String str;
    private String[][] ans;
    private int n;
    private int n2;
    boolean c;
    private HashMap<String, Data> create = new HashMap<String, Data>();
    public HashMap<Integer,String> com = new HashMap<Integer,String>();
    LinkedList<StringConvert> link = new LinkedList<>();

    //Input Rule and String
    public Algorithm(String[][] g, String str) {
        this.g = g;
        n = g.length - 1;
        this.str = str;
        n2 = str.length();
        this.ans = new String[n2 + 1][n2 + 1];
    }

    //Input Rule
    public Algorithm(String[][] g) {
        this.g = g;
        n = g.length - 1;
    }

    public Algorithm() {
    }

    public void setStr(String str) {
        this.str = str;
        n2 = str.length();
    }

    public String[][] cal(String[][] g, String str) {
        this.g = g;
        n = g.length - 1;
        this.str = str;
        n2 = str.length();
        this.ans = new String[n2 + 1][n2 + 1];

        setArray(ans);
        addTable();
        calToTable();
        check();
        return ans;
    }

    //Change
    //Frist Alogotithm
    private void addTable() {
        for (int i = 1; i <= n2; i++) {
            String s = "" + str.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                for (int z = 1; z < g[j].length; z++) {
                    if (s.equals(g[j][z])) {
                        if (!ans[i][i].equals("")) {
                            ans[i][i] += ",";
                        }
                        ans[i][i] += g[j][0];
                        Data data = new Data();
                        if (create.containsKey(i + "," + i)) {
                            data = create.get(i + "," + i);
                        }
                        data.add(g[j][0], "Rule", "Rule", "Rule", "Rule");
                        create.put(i + "," + i, data);
                    }
                }
            }
        }
    }

    //Second Alogorithm
    private void calToTable() {
        for (int l = 2; l <= n2; l++) {
            for (int i = 1; i <= n2 - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j - 1; k++) {
                    String[] s1 = ans[i][k].split(",");
                    String[] s2 = ans[k + 1][j].split(",");
                    for (int i2 = 0; i2 < s1.length; i2++) {
                        for (int j2 = 0; j2 < s2.length; j2++) {
                            String s = s1[i2] + "" + s2[j2];
                            for (int z = 1; z <= n; z++) {
                                boolean c = false;
                                for (int z2 = 1; z2 < g[z].length; z2++) {
                                    if (g[z][z2] == null) {
                                        break;
                                    }
                                    if (s.equals(g[z][z2])) {
                                        c = true;

                                    }
                                }
                                if (c) {

                                    if (!ans[i][j].equals("")) {

                                        ans[i][j] += ",";
                                    }
                                    ans[i][j] += g[z][0];

                                    Data data = new Data();
                                    if (create.containsKey(i + "," + j)) {
                                        data = create.get(i + "," + j);
                                    }
                                    data.add(g[z][0], i + "," + k, k + 1 + "," + j, s1[i2], s2[j2]);
                                    create.put(i + "," + j, data);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Set Array to spec
    private void setArray(String ans[][]) {
        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[i].length; j++) {
                ans[i][j] = "";
            }
        }
    }

    //Check CYK
    private void check() {
        c = false;
        String[] x = ans[1][ans.length - 1].split(",");
        for (int j = 0; j < x.length; j++) {
            if (x[j].equals("S")) {
                //System.out.println("Acc");
                c = true;
            }
        }
    }

    //Use Alogorithm 
    public String[][] cal() {
        setArray(ans);
        addTable();
        calToTable();
        check();
        if(c){
            firstConvert();
        }
        return ans;
    }

    public Data getInfo(String row, String col) {
        return create.get(row + "," + col);
    }

    public void firstConvert() {
        String s = 1 + "," + n2;
        link.add(new StringConvert("S", s));
        int count = 1;
        while (true) {
            boolean cc = false;
            String t = "";
            for (StringConvert sc : link) {
                if (sc.next) {
                    cc = true;
                }
                t += sc.s;
                //System.out.print(sc.s + " ");
            }
            if(!cc){
                break;
            }
            com.put(count,t);
            System.out.println(t);
            //System.out.println("");
            count++;
            convert();
        }
    }

    private void convert() {
        int len = link.size();
        for (int i = 0; i < len; i++) {
            StringConvert f = link.get(i);
            if (f.next) {
                Data d = create.get(f.channel);
                if (d.channel1.get(0).equals("Rule")) {
                    //System.out.println(f.s+" Stop");
                    f.next = false;
                } else {
                    for (int j = 0; j < d.rule.size(); j++) {
                        
                        if (d.rule.get(j).equals(f.s)) {
                            //System.out.println("------1-------");
                            //System.out.println(d.channel1String.get(j) + " " + d.channel1.get(j));
                            //System.out.println(d.channel2String.get(j) + " " + d.channel2.get(j));
                            //System.out.println("--------------");
                            link.add(i + 1, new StringConvert(d.channel1String.get(j), d.channel1.get(j)));
                            link.add(i + 2, new StringConvert(d.channel2String.get(j), d.channel2.get(j)));
                            link.remove(i);
                            i++;
                            len++;
                            return;
                        }
                    }
                }
            }
        }
    }
}
