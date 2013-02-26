public class BoxBugRunner
{
    public static void main(String[] args) {
        BoundedGrid g1 = new BoundedGrid(51, 51);
        ActorWorld world1 = new ActorWorld(g1);
        new MazeCS106B(world1);
        Bug jared = new Bug();
        world1.add(new Location(1, 0), jared);
        world1.show();
    }

}