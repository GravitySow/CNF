/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theory;

import java.util.Scanner;

/**
 *
 * @author Meenu
 */
public class CFG {

    public void cal() {

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
            //System.out.println(y[1]);
            String z[] = y[1].split("\\|");
            //System.out.println(z[0]);
            //System.out.println(z[1]);
            for (int j = 0; j < 2; j++) {
                try {
                    g[i][j + 1] = z[j];
                } catch (Exception e) {
                    //System.out.println(e);
                    break;
                }
            }
        }
        String str = in.nextLine();
        int n2 = str.length();
        String[][] ans = new String[n2 + 1][n2 + 1];

        for (int i = 1; i <= n2; i++) {
            for (int j = 1; j <= n2; j++) {
                ans[i][j] = "";
            }
        }
        //First Algorithm
        for (int i = 1; i <= n2; i++) {
            String s = "" + str.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                for (int z = 1; z < g[j].length; z++) {
                    //System.out.println(s+" "+g[j][z]);
                    //System.out.println(g[j][0]+" "+s+" "+g[j][z]);
                    if (s.equals(g[j][z])) {
                        //System.out.println("x");
                        //System.out.println(g[j][0]+" "+s+" "+g[j][z]);
                        if (!ans[i][i].equals("")) {
                            ans[i][i] += ",";
                        }
                        ans[i][i] += g[j][0];
                    }
                }
            }
        }
        //
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
                                //System.out.println(s);
                                for (int z2 = 1; z2 < 3; z2++) {
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

        for (int i = 1; i <= n2; i++) {
            for (int j = 1; j <= n2; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println("");
        }

        /*for(int i = 1; i<= n;i++){
            System.out.println(g[i][0]+"=>"+g[i][1]+"|"+g[i][2]);
        }*/
    }
}
