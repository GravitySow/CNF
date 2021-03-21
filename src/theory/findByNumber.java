/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author gravitys
 */
public class findByNumber {
    String[][] g;
    int n;
    
    public findByNumber(String g[][]){
        this.g = g;
        n = g.length - 1;
    }
    
    public void find() {
        TreeSet<String> s = new TreeSet<>();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < g[i].length - 1; j++) {;
                if (g[i][j].length() == 1) {
                    System.out.println(g[i][j]);
                    s.add(g[i][j]);
                }
            }
        }
        ArrayList<String> x = f(10,s);
        
        for(String str:x){
            new Thread(() -> {
                Alogorithm a = new Alogorithm(g,str);
                a.cal();
                if(a.c){
                    System.out.println(str);
                }
            }).run();
        }
        
    }

    public ArrayList<String> f(int x, TreeSet<String> s) {
        ArrayList<String> allChange = new ArrayList<>();
        ArrayList<String> all = new ArrayList<>();
        if (x > 0) {
            all = f(--x, s);
            for (int i = 0; i < all.size(); i++) {
                Iterator<String> itr= s.iterator();  
                while(itr.hasNext()){
                    allChange.add(all.get(i) + "" + itr.next());
                }
            }
        }else{
            allChange.add("");
        }
        return allChange;

    }
}
