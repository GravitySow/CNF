/*
 *  Code By GravityS 
 */
package theory;

import java.util.ArrayList;

/**
 *
 * @author gravitys
 */
public class Data {

    ArrayList<String> rule;
    ArrayList<String> channel1;
    ArrayList<String> channel2;
    ArrayList<String> channel1String;
    ArrayList<String> channel2String;

    public Data() {
        rule = new ArrayList<String>();
        channel1 = new ArrayList<String>();
        channel2 = new ArrayList<String>();
        channel1String = new ArrayList<String>();
        channel2String = new ArrayList<String>();
    }

    public void add(String rule, String channel1, String channel2, String channel1String,String channel2String) {
        this.rule.add(rule);
        this.channel1.add(channel1);
        this.channel2.add(channel2);
        this.channel1String.add(channel1String);
        this.channel2String.add(channel2String);
    }
}
