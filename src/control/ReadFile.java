package control;

import java.io.BufferedReader;
import java.io.FileReader;


import control.BattleShipControler;

import javax.sound.sampled.Control;

public class ReadFile {
    Control control;
    private String source;


    public ReadFile(String source, Control control){
        this.source=source;
        this.control=control;
        lecture();
    }

    private void lecture(){
        try {
            String ligne;
            BufferedReader file = new BufferedReader(new FileReader(source));

            while ((ligne = file.readLine()) != null) {
                if(ligne.charAt(0)=='V'||ligne.charAt(0)=='H'){

                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
