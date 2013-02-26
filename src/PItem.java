public class PItem extends Actor {

    public PItem() {
        item = (int)Math.random()*10;
        if (item >= 0 && item < 2) {
            // bullet
        } else if (item >= 2 && item < 5) {
            // bomb
        } else if (item >= 5 && item < 6) {
            // wall
        } else if (item >= 6 && item < 8) {
            // bug
        } else {
            // surprise!
        }
    }

    public void act() {
    }

    private int item;

}
