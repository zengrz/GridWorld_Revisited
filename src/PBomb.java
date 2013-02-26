import java.util.ArrayList;

public class PBomb extends Actor {

    public PBomb() {
        super();
    }

    public void act() {
        Location front = getLocation().getAdjacentLocation(getDirection());
        if (getGrid().isValid(front)) {
            Actor frontActor = getGrid().get(front);

            if (frontActor instanceof Actor) {
                ArrayList<Actor> areaOfEffect = getGrid().getNeighbors(front);
                removeActors(areaOfEffect);
                frontActor.removeSelfFromGrid();
                if (getGrid() != null)
                    removeSelfFromGrid();
            } else
                moveTo(getLocation().getAdjacentLocation(getDirection()));
        }
        else
            removeSelfFromGrid();
    }

    public void removeActors(ArrayList<Actor> areaOfEffect) {
        for (Actor a : areaOfEffect)
        {
            a.removeSelfFromGrid();
        }
    }

}