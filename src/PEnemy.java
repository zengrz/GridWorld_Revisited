
import java.awt.Color;
import java.util.*;

public class PEnemy extends Critter {
    public PEnemy() {
        setColor(Color.GREEN);
    }

    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

    /* Overrided */
    public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        double chance = Math.random();
        if (chance*10 > 5) {
            int r = (int) (Math.random() * n);
            return locs.get(r);
        } else {
            return getLocation();
        }
    }

    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof PEnemy) && !(a instanceof PBullet) && !(a instanceof PBomb))
                a.removeSelfFromGrid();
        }
    }
}