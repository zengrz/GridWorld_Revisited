public class Player extends Actor {

    public Player() {
    }

    public void act()
    {
        if (reachedEnd()) {
            return;
        }
        hasMoved = false;
    }

    public void moveNorth() {
        if (hasMoved == false){
            setDirection(Location.NORTH);
            if (canMove()) {
                Location next = getLocation().getAdjacentLocation(Location.NORTH);
                moveTo(next);
            }
            hasMoved = true;
        }
    }

    public void moveSouth() {
        if (hasMoved == false){
            setDirection(Location.SOUTH);
            if (canMove()) {
                Location next = getLocation().getAdjacentLocation(Location.SOUTH);
                moveTo(next);
            }
            hasMoved = true;
        }
    }

    public void moveEast() {
        if (hasMoved == false){
            setDirection(Location.EAST);
            if (canMove()) {
                Location next = getLocation().getAdjacentLocation(Location.EAST);
                moveTo(next);
            }
            hasMoved = true;
        }
    }

    public void moveWest() {
        if (hasMoved == false){
            setDirection(Location.WEST);
            if (canMove()) {
                Location next = getLocation().getAdjacentLocation(Location.WEST);
                moveTo(next);
            }
            hasMoved = true;
        }
    }

    public void shootBullet() {
        if (hasMoved == false){
            Location front = getLocation().getAdjacentLocation(getDirection());
            if (getGrid().isValid(front) && numBullets > 0) {
                PBullet newOne = new PBullet();
                newOne.putSelfInGrid(getGrid(), front);
                newOne.setDirection(getDirection());
                numBullets--;
            }
            hasMoved = true;
        }
    }

    public void placeWall() {
        if (hasMoved == false){
            Location front = getLocation().getAdjacentLocation(getDirection());
            if (getGrid().isValid(front) && numWalls > 0) {
                new Rock().putSelfInGrid(getGrid(), front);
                numWalls--;
            }
        }
    }

    public void throwBomb() {
        if (hasMoved == false){
            Location front = getLocation().getAdjacentLocation(getDirection());
            if (getGrid().isValid(front) && numBombs > 0) {
                PBomb newOne = new PBomb();
                newOne.putSelfInGrid(getGrid(), front);
                newOne.setDirection(getDirection());
                numBombs--;
            }
            hasMoved = true;
        }
    }

    public void releaseBug() {
        if (hasMoved == false) {
            Location front = getLocation().getAdjacentLocation(getDirection());
            if (getGrid().isValid(front) && numBug > 0) {
                Bug solver = new Bug();
                solver.putSelfInGrid(getGrid(), front);
                solver.setDirection(getDirection());
                numBug--;
            }
        }
    }

    public int getNumBullets() {
        return numBullets;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public int getNumWalls() {
        return numWalls;
    }

    public int getNumLives() {
        return numLives;
    }

    public boolean reachedEnd()
    {
        if (getLocation().equals(new Location(getGrid().getNumRows() - 1, getGrid().getNumCols() - 2))) return true;
        return false;
    }

    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null);
    }

    public void setStatus(int numBullets, int numBombs, int numWalls, int numLives) {
        this.numBullets = numBullets;
        this.numBombs = numBombs;
        this.numWalls = numWalls;
        this.numLives = numLives;
    }

    /* Private instance variable */
    private boolean hasMoved = false; // make it false every turn
    private int numBullets;
    private int numBombs;
    private int numWalls;
    private int numLives;
    private int numBug;
}