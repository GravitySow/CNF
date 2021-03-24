/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Meenu
 */
public class OpenAndSave {

    String rule;
    boolean readFile(String location) throws IOException {
        try {
            File file = new File(location);
            Scanner myReader = new Scanner(file);
            rule = "";
            while(myReader.hasNext()){
                rule += myReader.nextLine();
                if(myReader.hasNext()){
                    rule += "\n";
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Read File Error\nCode : 400", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    void saveFile(String location,String rule) {
        if (location.equals("nullnull")) {
           
        }else{
            try {
                FileWriter myWriter = new FileWriter(location + ".txt");
                myWriter.write(rule);
                myWriter.close();
                JOptionPane.showMessageDialog(null, "Save File Succes");
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                JOptionPane.showMessageDialog(null, "Write File Error\nCode : 410", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
