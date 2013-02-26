public class PBullet extends Actor {

    public PBullet() {
        super();
    }

    public void act()
    {
        Location front = getLocation().getAdjacentLocation(getDirection());
        if (getGrid().isValid(front)) {
            Actor frontActor = getGrid().get(front);
            if (frontActor instanceof Actor) {
                frontActor.removeSelfFromGrid();
                if (getGrid() != null)
                    removeSelfFromGrid();
            } else
                moveTo(getLocation().getAdjacentLocation(getDirection()));
        }
        else
            removeSelfFromGrid();
    }

}