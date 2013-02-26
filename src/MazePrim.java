import java.util.*;

public class MazePrim {

    public MazePrim(ActorWorld world) {
        makeMaze(world);
    }

    private static void makeMaze(ActorWorld world) {
        ArrayList<Wall> removableWalls = new ArrayList<Wall>(); // make these instance variables next time
        ArrayList<Wall> waitingWalls = new ArrayList<Wall>();
        HashSet<Location> maze = new HashSet<Location>();

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
                   //if  (i % 2 == 1 && j % 2  == 1) {
                   //}
                } else {
                    // Boundaries
                    world.add(new Location(i, j), new Rock());
                }

            }
        }

        Location start = world.getRandomEmptyLocation();
        maze.add(start);
        addNeighborWalls(start, removableWalls, waitingWalls);

        while (waitingWalls.size() > 0) {
            Wall curWall = pickWall(waitingWalls); // pick and remove wall from waiting walls
            if (!cellInMaze(curWall, maze)) { // if the cell on opposite side of wall is not in maze                
                Location nextCell = getNextCell(curWall, maze);
                addNeighborWalls(nextCell, removableWalls, waitingWalls); // set new cell to be current cell, add walls from new cell into waiting walls
                maze.add(nextCell); // add nextCell to be part of the maze
                world.remove(curWall.cur); // knock wall off
            }
        }

        world.remove(new Location(1, 0));
        world.remove(new Location(world.getGrid().getNumRows() - 1, world.getGrid().getNumCols() - 2));
        
    }

    private static Location getNextCell(Wall curWall, HashSet<Location> maze) {
        Location temp;
        if (maze.contains(curWall.cell1)) temp = curWall.cell2;
        else if (maze.contains(curWall.cell2)) temp = curWall.cell1;
        else temp = null;
        return temp;
    }

    private static boolean cellInMaze(Wall curWall, HashSet<Location> maze) {
        if (maze.contains(curWall.cell1) && maze.contains(curWall.cell2)) return true;
        return false;
    }

    private static Wall pickWall(ArrayList<Wall> waitingWalls) {
        int select = rgen.nextInt(waitingWalls.size());
        Wall temp = waitingWalls.get(select);
        waitingWalls.remove(select);
        return temp;
    }

    private static void addNeighborWalls(Location curCell, ArrayList<Wall> removableWalls, ArrayList<Wall> waitingWalls) {
        Location up = curCell.getAdjacentLocation(Location.NORTH);
        Location down = curCell.getAdjacentLocation(Location.SOUTH);
        Location left = curCell.getAdjacentLocation(Location.WEST);
        Location right = curCell.getAdjacentLocation(Location.EAST);

        Wall upWall = getWall(up, removableWalls);
        Wall downWall = getWall(down, removableWalls);
        Wall leftWall = getWall(left, removableWalls);
        Wall rightWall = getWall(right, removableWalls);

        if (upWall != null)  waitingWalls.add(upWall);
        if (downWall != null)  waitingWalls.add(downWall);
        if (leftWall != null)  waitingWalls.add(leftWall);
        if (rightWall != null)  waitingWalls.add(rightWall);

    }

    private static Wall getWall(Location wall, ArrayList<Wall> removableWalls) {
        Wall w;
        for (int i = 0; i < removableWalls.size(); i++) {
            if (removableWalls.get(i).cur.equals(wall)) {
                w =  removableWalls.get(i);
                removableWalls.remove(i);
                return w;
            }
        }
        return null;
    }

    private static Random rgen = new Random();
}
