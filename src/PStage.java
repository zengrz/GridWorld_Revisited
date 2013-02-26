public class PStage extends ActorWorld {

    private Player you;

    public PStage()
    {
        super();
    }

    public PStage(Grid<Actor> grid)
    {
        super(grid);
    }

    public void setPlayer(Player you)
    {
        this.you = you;
    }

    public boolean keyPressed(String description, Location loc)
    {
        System.out.println(description);
        if (you != null){
            if (description.equals("W")) {
                you.moveNorth();
            }
            if (description.equals("S")) {
                you.moveSouth();
            }
            if (description.equals("A")) {
                you.moveWest();
            }
            if (description.equals("D")) {
                you.moveEast();
              }
            if (description.equals("NUMPAD0")) {
                you.shootBullet();
            }
            if (description.equals("NUMPAD1")) {
                you.placeWall();
            }
            if (description.equals("NUMPAD2")) {
                you.throwBomb();
            }
            if (description.equals("NUMPAD3")) {
                you.releaseBug();
            }
            if (description.equals("I")) {
                you.setStatus(100, 100, 100, 100);
            }
            if (description.equals("O")) {
                you.setStatus(0, 0, 0, 1);
            }
        }
        return false;
    }

    public void initializeMaze(int type) {
        switch(type) {
            case 1:
                new MazeCS106B(this);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

}