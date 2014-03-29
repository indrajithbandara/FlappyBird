import java.awt.geom.AffineTransform;


public class Ring extends GameObject{
	public boolean collected = false;
	private double rotation = 0.0;
	private boolean turn;
	public Ring()
	{
        width = 32;
        height = 32;
        int maxY = App.HEIGHT - 80 - height;
		x = App.WIDTH;
        y = rnd.nextInt(maxY);
        xvel = 5;
        path = "res/ring.png";
        turn = rnd.nextBoolean();
	}
	public void update()
	{
		double turnSpeed = .25;
		if(turn)
		{
			rotation -= turnSpeed;
		}
		else
		{
			rotation += turnSpeed;
		}
		x -= (int)xvel;
		
	}
    public void deathUpdate(GameObjectHandler gameObjectHandler)
    {
    	gameObjectHandler.gameObjects.add(Particle.getRingParticle(x, y, rotation));
    }
	public void doCollide(GameObject gameObject)
	{
		if(gameObject instanceof Bird)
		{
			alive = false;
			collected = true;
		}
		if(gameObject instanceof Ring)
		{
			if(x < gameObject.x)x--;
			else gameObject.x--;
		}
	}
	public Render getRender()
	{
		Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage(path);     
        }
        r.image = image;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
	}
}
