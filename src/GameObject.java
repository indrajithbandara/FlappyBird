import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public abstract class GameObject
{
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public boolean particleGen = true;
	public boolean alive = true;
    public int x;
    public int y;
    public int width;
    public int height;

    // y velocity
    public double xvel;
    public double yvel;
    public final double gravity = .4;
    protected Image image;
    protected Random rnd = new Random();
    protected String path;
    public int groundHeight = App.HEIGHT - 100;
    public Render getRender() {

        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage(path);     
        }
        r.image = image;

        return r;
    }
    public void update(){}
    public void doCollide(GameObject gameObject){}
    public void deathUpdate(GameObjectHandler gameObjectHandler){}
	private boolean collide(Rectangle r)
	{
		 if(r.intersects(new Rectangle((int)x, (int)y, (int)width, (int)height)))return true;
		 return false;
	}
	public boolean collide(GameObject o)
	{
		 if(o.collide(new Rectangle((int)x, (int)y, (int)width, (int)height)))return true;
		 return false;
	}
}
