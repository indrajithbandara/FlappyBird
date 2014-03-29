import java.awt.geom.AffineTransform;


public class FireBall extends GameObject
{
	public boolean collected = false;
	private double rotation = 0.0;
	private int life = 100;
	public FireBall(int x, int y)
	{
		this.x = x;
		this.y = y;
        width = 8;
        height = 8;
        xvel = 10;
        yvel = -3;
        path = "res/fire.png";
	}
	public void update()
	{
		double turnSpeed = .25;
		rotation += turnSpeed;
		x += (int)xvel;
		y += (int)yvel;
		yvel += gravity / 2;
		life--;
		if(life <= 0)alive = false;
	}
	public void doCollide(GameObject gameObject)
	{
		if(gameObject instanceof Rock)
		{
			alive = false;
			gameObject.alive = false;
			((Rock)gameObject).hit = true;
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

