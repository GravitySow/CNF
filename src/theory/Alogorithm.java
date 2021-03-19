/*
 * Code By GravityS
 */
package theory;

import java.util.Scanner;

/**
 *
 * @author gravitys
 */
public class Alogorithm {

    String[][] g;
    String str;
    String[][] ans;
    int n;
    int n2;
    //Input Rule and String
    public Alogorithm(String[][] g, String str) {
        this.g = g;
        this.str = str;
        n2 = str.length();
        n = g.length - 1;
        this.ans = new String[n2 + 1][n2 + 1];
    }
    //Frist Alogotithm
    public void addTable() {
        for (int i = 1; i <= n2; i++) {
            String s = "" + str.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                for (int z = 1; z < g[j].length; z++) {
                    if (s.equals(g[j][z])) {
                        if (!ans[i][i].equals("")) {
                            ans[i][i] += ",";
                        }
                        ans[i][i] += g[j][0];
                    }
                }
            }
        }
    }
    //Second Alogorithm
    public void calToTable() {
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
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //Set Array to spec
    public void setArray(String ans[][]){
        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[i].length; j++) {
                ans[i][j] = "";
            }
        }
    }
    //Use Alogorithm 
    public String[][] cal() {
        setArray(ans);
        addTable();
        calToTable();
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        //Rule
        String[][] g = new String[n + 1][3];
        //Input Rule
        for (int i = 1; i <= n; i++) {
            String x = in.nextLine();
            String y[] = x.split("->");
            g[i][0] = y[0];
            String z[] = y[1].split("\\|");
            for (int j = 0; j < z.length; j++) {
                try {
                    g[i][j + 1] = z[j];
                } catch (Exception e) {
                    break;
                }
            }
        }
        String str = in.nextLine();
        Alogorithm a = new Alogorithm(g, str);
        String[][] ans = a.cal();

        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[i].length; j++) {
                System.out.print(ans[i][j] + "");
                if (ans[i][j].equals("")) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }

    }
}