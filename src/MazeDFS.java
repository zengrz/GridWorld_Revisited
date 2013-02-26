import java.util.*;

public class MazeDFS {

    public MazeDFS(ActorWorld world) {
        makeMaze(world);
    }

    private void makeMaze(ActorWorld world) {
        for (int i = 0; i < world.getGrid().getNumRows(); i++) {
            for (int j = 0; j < world.getGrid().getNumCols(); j++) {
                if (i != 0 && i != world.getGrid().getNumRows() - 1 && j != 0 && j != world.getGrid().getNumCols() - 1) {
                    // Irremovable walls
                   if (i % 2 == 0 && j % 2 == 0) {
                       Location irremovableWall = new Location(i, j);
                       //irremovableWalls.add(irremovableWall);
                       world.add(irremovableWall, new Rock());
                   }
                   // Vertical walls
                   if (i % 2 == 1 && j % 2 == 0) {
                       Wall removableWall = new Wall();
                       removableWall.cur = new Location(i, j);
                       removableWall.cell1 = new Location(i, j - 1);
                       removableWall.cell2 = new Location(i, j + 1);
                       removableWalls.add(removableWall);
                       world.add(removableWall.cur, new Rock());
                   }
                   // Horizontal walls
                   if (i % 2 == 0 && j % 2 == 1) {
                       Wall removableWall = new Wall();
                       removableWall.cur = new Location(i, j);
                       removableWall.cell1 = new Location(i - 1, j);
                       removableWall.cell2 = new Location(i + 1, j);
                       removableWalls.add(removableWall);
                       world.add(removableWall.cur, new Rock());
                   }
                   // Empty cells
                   if  (i % 2 == 1 && j % 2  == 1) {
                   }
                } else {
                    // Boundaries
                    world.add(new Location(i, j), new Rock());
                }
            }
        }
        
        Location start = world.getRandomEmptyLocation();
        Location curLoc, nextLoc;

        //while ()
    }

    private static ArrayList<Wall> removableWalls = new ArrayList<Wall>();
    private static Random rgen = new Random();

}
