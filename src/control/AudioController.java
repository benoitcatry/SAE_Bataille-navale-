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

<<<<<<< Updated upstream
    public static void main(String[] args){
        playMiss();
=======
    public void playRater() {
        if(soundEffect){
            play("Rater.wav");
        }
    }




    public void playTir() {
        if(soundEffect){}
        play("Tir.wav");
    }

    public void playToucher() {

        if(soundEffect){play("Toucher.wav");}
    }

    public void playDestruction() {
        if(soundEffect){play("Destruction.wav");}


    }

    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
            System.out.println("Playback stopped.");
        }
    }

    public void toggleMusic(){
        if (playMusic==true){
            playMusic=false;
            audioClip.stop();
        }
        else {
            playMusic=true;
            music();
        }
    }


    public void music() {
        try {
            // Ouvrir le fichier audio
            File audioFile = new File("src/Elements/Audio/rater.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Créer un clip audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Jouer le clip en boucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void listAudioFiles() {
        File directory = new File(audioPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Directory not found: " + audioPath);
            return;
        }

        try {
            Files.list(Paths.get(audioPath))
                    .filter(Files::isRegularFile)
                    .forEach(path -> System.out.println(path.getFileName().toString()));
        } catch (IOException e) {
            System.err.println("Error listing audio files.");
            e.printStackTrace();
        }
    }

    public void toggleSoundEffect() {
        soundEffect=!soundEffect;
>>>>>>> Stashed changes
    }

    }



