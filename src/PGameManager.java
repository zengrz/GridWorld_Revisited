import java.io.*;
import java.net.*;

public class PGameManager extends Actor {
    public PGameManager() {
        level = 0;
        you = new Player();
        worldSize = 11;
        g = new BoundedGrid(worldSize, worldSize);
        stg = new PStage(g);
        stg.initializeMaze(1);
        you = new Player();
        you.setStatus(1, 1, 1, 3);
        stg.add(new Location(1, 0), you);
        stg.setPlayer(you);
        stgVisible = false;
    }

    public void act() {
        if (!stgVisible) {
            stg.show();
            stgVisible = true;
        }
        if (you.reachedEnd()) {
            stg.dispose();
            level++;
            switch (level) {
                case 1:
                    worldSize = 21;
                    g = new BoundedGrid(worldSize, worldSize);
                    stg = new PStage(g);
                    stg.initializeMaze(1);
                    you = new Player();
                    stg.add(new Location(1, 0), you);
                    stg.setPlayer(you);
                    stgVisible = false;
                    break;
                case 2:
                    worldSize = 31;
                    g = new BoundedGrid(worldSize, worldSize);
                    stg = new PStage(g);
                    stg.initializeMaze(1);
                    you = new Player();
                    you.setStatus(1, 1, 1, 3);
                    stg.add(new Location(1, 0), you);
                    stg.setPlayer(you);
                    stgVisible = false;
                    break;
            }
        }

        if (you.getLocation() == null) {
            if (you.getNumLives() != 0) {
                stg.dispose();
                g = new BoundedGrid(worldSize, worldSize);
                stg = new PStage(g);
                stg.initializeMaze(1);
                you = new Player();
                you.setStatus(1, 1, 1, you.getNumLives() - 1);
                stg.add(new Location(1, 0), you);
                stg.setPlayer(you);
                stg.add(new PEnemy());
                stg.show();
            } else {
                stg.dispose();
            }
        }
    }

    private Player you;
    private BoundedGrid g;
    private PStage stg;
    private int level;
    private int worldSize;
    private boolean stgVisible;
}