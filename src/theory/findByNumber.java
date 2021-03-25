/*
 * Code By GravityS
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
    private String[][] g;
    private int n;
    
    public findByNumber(String g[][]){
        this.g = g;
        n = g.length - 1;
    }
    
    public ArrayList<String> find(int num) {
        TreeSet<String> s = new TreeSet<>();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < g[i].length; j++) {
                if(g[i][j] == null) break;
                if (g[i][j].length() == 1) {
                    s.add(g[i][j]);
                }
            }
        }
        ArrayList<String> x = f(num,s);
        
        ArrayList<String> result = new ArrayList<>();
        for(String str:x){
            new Thread(() -> {
                Algorithm a = new Algorithm(g,str);
                a.cal();
                if(a.c){
                    result.add(str);
                }
            }).run();
        }
        return result;
    }

    private ArrayList<String> f(int x, TreeSet<String> s) {
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