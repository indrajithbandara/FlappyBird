import java.awt.geom.AffineTransform;
import java.util.Random;

public class Particle extends GameObject
{
	public String imgPath;
	public int x, y, life;
	protected double rotation, rotvel;
    public final double gravity = 1;
    public boolean isSolid = true, hasWeight = true;
	public Particle(String imgPath, int x, int y, int life, double rotation, double xvel, double yvel)
	{
		this.imgPath = imgPath;
		this.x = x;
		this.y = y;
		this.life = life;
		this.rotation = rotation;
		this.xvel = xvel;
		this.yvel = yvel;
	}
	public static Particle getFireParticle(int x, int y)
	{
		Particle particle = new Particle("res/fire.png", x+12-new Random().nextInt(20), y+10-new Random().nextInt(20), new Random().nextInt(20), new Random().nextInt(6), 5-new Random().nextInt(10), 5-new Random().nextInt(10));
		particle.width = 8;
		particle.height = 8;
		particle.isSolid = false;
		particle.hasWeight = false;
		return particle;
	}
	public static Particle getRingParticle(int x, int y, double rotation)
	{
		Particle particle = new Particle("res/used_ring.png", x, y, 1000, rotation, 1, 0);
		particle.width = 32;
		particle.height = 32;
		particle.isSolid = false;
		return particle;
	}
	public static Particle getSmallRockParticle(int x, int y)
	{
		Particle particle = new Particle("res/rock_small.png", x-5+new Random().nextInt(10), y-5+new Random().nextInt(10), 200, new Random().nextInt(), 5-new Random().nextInt(10), 5-new Random().nextInt(10));
		particle.width = 8;
		particle.height = 8;
		return particle;
	}
	public static Particle getBirdParticle(int x, int y, double rotation)
	{
		Particle particle = new Particle("res/bird.png", x, y, 200, 0.275, -10, 0);
		System.out.println(particle.rotation);
		particle.width = 45;
		particle.height = 32;
		return particle;
	}
	public static Particle getBloodParticle(int x, int y)
	{
		int xrange = 20, yrange = 15;
		x = x - xrange;
		y = y - yrange;
		x = x + new Random().nextInt(xrange * 2);
		y = y + new Random().nextInt(yrange * 2);
		Particle particle = new Particle("res/blood.png", x, y, 200, new Random().nextInt(), 7-(new Random().nextInt(15)) , new Random().nextInt(20) - 10);
		particle.width = 8;
		particle.height = 8;
		
		return particle;
	}
	public Render getRender()
	{
		Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage(imgPath);     
        }
        r.image = image;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
	}

	 public void update () {
	    	life--;
	    	if(life <= 0)alive = false;
	        if(hasWeight)yvel += gravity;
	        rotation += rotvel;
	        y += (int)yvel;
	        x += (int)xvel;
	        if(isSolid)
	        {
	        	int groundHeight = App.HEIGHT - 100;
		        if (y + height > groundHeight - height) {
	                y = groundHeight - height;
	                xvel = -5;
		        }
	        }
	    }
}
