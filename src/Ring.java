
public class Ring extends GameObject{
	public Ring()
	{
		int xStart = App.WIDTH/2;
        width = 32;
        height = 32;
		x = xStart + rnd.nextInt(App.HEIGHT - xStart);
        y = rnd.nextInt(App.HEIGHT - height * 2);
        xvel = 0;
	}
	public void update()
	{
		xvel += .125;
		x -= (int)xvel;
		if(x < 0)
		{
			alive = false;
		}
	}

}
