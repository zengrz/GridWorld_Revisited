/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Bug Billy = new Bug();
        Bug Betty = new Bug();
        Rock Rocky = new Rock();
        world.add(Billy);
        world.add(Betty);
        world.add(Rocky);

        world.show();
    }
}
