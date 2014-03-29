import java.awt.geom.AffineTransform;


public class Rock extends Enemy
{
	public double speed = .25;
	public double rotation = 0.0;
	public boolean hit = false, onFire = false;
	public Rock()
	{
        width = 32;
        height = 32;
        int maxY = App.HEIGHT - 80 - height;
		x = App.WIDTH;
        y = rnd.nextInt(maxY);
        xvel = 2;
        path = "res/rock.png";
	}
	public void update()
	{
        rotation -= xvel * .025;
		x -= (int)xvel;
		y += (int)yvel;
		if(hit)yvel += gravity;
		if(y + height > groundHeight)
		{
			alive = false;
		}
		if(onFire)
		{
			particles.add(Particle.getFireParticle(x + width / 2, y + height / 2));
		}
	}
	public Render getRender()
	{
		Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("res/rock.png");     
        }
        r.image = image;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
	}
    public void deathUpdate(GameObjectHandler gameObjectHandler)
    {
    	for(int i = 0;i < 20;i++)
    	{
    		gameObjectHandler.gameObjects.add(Particle.getSmallRockParticle(x, y));
    	}
    }
	public void doCollide(GameObject gameObject)
	{
		if(gameObject instanceof Ring)
		{
			xvel += speed * 2;
			gameObject.alive = false;
			if(xvel > speed * 12)
			{
				onFire = true;
			}
			if(xvel > speed * 15)
			{
				xvel = speed * 15;
			}
		}
		if(gameObject instanceof Rock)
		{
			if(x < gameObject.x)x-=gameObject.xvel;
			else gameObject.x-=xvel;
			if(hit)((Rock)gameObject).hit = true;
			if(((Rock)gameObject).hit)hit = true;
			if(((Rock)gameObject).onFire)onFire = true;
			if(onFire)((Rock)gameObject).onFire = true;
		}
		if(gameObject instanceof Bird)
		{
			xvel = -2;
			Bird bird = ((Bird)gameObject);
			if(onFire)bird.health -= bird.maxHealth;
			else bird.health -= bird.maxHealth * .90;
			bird.bleedTime = 5;
			if(bird.health > 0)alive = false;
			else hit = true;
		}
	}
}
