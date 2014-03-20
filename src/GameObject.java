import java.awt.Image;
import java.util.Random;


public class GameObject
{
	public boolean alive = true;
    public int x;
    public int y;
    public int width;
    public int height;

    // y velocity
    public double xvel;
    public double yvel;
    public final double gravity = .5;
    protected Image image;
    protected Random rnd = new Random();
    public Render getRender(String path) {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage(path);     
        }
        r.image = image;

        return r;
    }
}
