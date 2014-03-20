
public class Rock extends Enemy
{
	public Rock()
	{
        width = 32;
        height = 32;
        int maxY = App.HEIGHT - 80 - height;
		x = App.WIDTH;
        y = rnd.nextInt(maxY);
        xvel = 0;
        path = "res/rock.png";
	}
	public void update()
	{
		xvel += .25;
		x -= (int)xvel;
		if(x + width < 0)
		{
			alive = false;
		}
	}
}
