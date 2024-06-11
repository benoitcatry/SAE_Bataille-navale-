package control;
import java.io.*;

public class AudioController {
    static InputStream in;
    public static void playMiss(){
        try {
            in = new FileInputStream("../Elements/audio/Miss.mp3");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        playMiss();
    }
}
