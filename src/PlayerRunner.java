import java.io.File;
import java.net.*;

public class PlayerRunner {

    public static void main(String[] args) {
        BoundedGrid g = new BoundedGrid(1, 1);
        ActorWorld world = new ActorWorld(g);
        PGameManager gm = new PGameManager();
        world.add(gm);
        world.show();
        //System.out.println(System.getProperty("user.dir"));

        File file = new File("sound.wav");
        URI uri = file.toURI();        
        java.applet.AudioClip clip = null;
        try {
                URL url = uri.toURL();
                clip = java.applet.Applet.newAudioClip(url);
        }
        catch(Exception e) {
                System.out.println("Exception: " + e);
        }

        if(clip != null) {
                clip.play();
                try {
                        Thread.sleep(5000);
                }
                catch (Exception e) {}
                System.out.println("Done");
        }       

    }


}