import java.util.*;

public class MazeCS106B {

    public MazeCS106B(ActorWorld world) {
        makeMaze(world);
    }


    private static void makeMaze(ActorWorld world) {
        ArrayList<Wall> removableWalls = new ArrayList<Wall>();
        ArrayList<HashSet<Location> > allChambers = new ArrayList<HashSet<Location> >();
//        HashSet<Location> irremovableWalls = new HashSet<Location>();

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
                       Location cell = new Location(i, j);
                       HashSet<Location> chamber = new HashSet<Location>();
                       chamber.add(cell);
                       allChambers.add(chamber);
                   }
                } else {
                    // Boundaries
                    world.add(new Location(i, j), new Rock());
                }

            }
        }

        while (removableWalls.size() > 0) {
            int rand = rgen.nextInt(removableWalls.size());
            Wall curWall = removableWalls.get(rand);
            removableWalls.remove(rand);
            if (SeparatesTwoChambers(curWall, allChambers)) {
                CombineChambers(curWall, allChambers);
                world.remove(curWall.cur);
            }
        }

        world.remove(new Location(1, 0));
        world.remove(new Location(world.getGrid().getNumRows() - 1, world.getGrid().getNumCols() - 2));

        System.out.println(allChambers.get(0).size());
    }

    private static void CombineChambers(Wall curWall, ArrayList<HashSet<Location> > allChambers) {
        HashSet<Location> newChamber = new HashSet<Location>();
        System.out.println("start");
        // Search for first cell
        for (int i = 0; i < allChambers.size(); i++) {
            if (allChambers.get(i).contains(curWall.cell1)) {
                newChamber.addAll(allChambers.get(i));
                allChambers.remove(i);
                //System.out.println("1111111111111111111111111");
                break;
            }
        }
        // Search for second cell
        for (int i = 0; i < allChambers.size(); i++) {
            if (allChambers.get(i).contains(curWall.cell2)) {
                newChamber.addAll(allChambers.get(i));
                allChambers.remove(i);
                //System.out.println("2222222222222222222222222");
                break;
            }
        }
        allChambers.add(newChamber);
    }

    private static boolean SeparatesTwoChambers(Wall curWall, ArrayList<HashSet<Location> > allChambers) {
        for (int i = 0; i < allChambers.size(); i++) {
            if (allChambers.get(i).contains(curWall.cell1) && allChambers.get(i).contains(curWall.cell2)) {
                return false;
            }
        }
        return true;
    }

    private static Random rgen = new Random();
}

class Wall
{
    public Wall() {}
    
    Location cur;
    Location cell1;
    Location cell2;
}