
import java.awt.event.KeyEvent;

public class Bird extends GameObject{

    private int jumpDelay;
    public int hunger, maxHunger = 1000;
    private Keyboard keyboard;

    public Bird () {
        x = 100;
        y = 150;
        yvel = 0;
        width = 45;
        height = 32;
        jumpDelay = 0;

        keyboard = Keyboard.getInstance();
        path = "res/bird.png";
        hunger = maxHunger;
    }

    public void update () {
    	if(hunger > 0)hunger--;
    	else alive = false;
        yvel += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
            yvel = -10;
            jumpDelay = 10;
        }

        y += (int)yvel;
        if (y + height > App.HEIGHT - 80) {
            alive = false;
        }
    }
	public void doCollide(GameObject gameObject)
	{
		if(gameObject instanceof Ring)
		{
			gameObject.alive = false;
			((Ring) gameObject).collected = true;
		}
		if(gameObject instanceof Rock)
		{
			alive = false;
		}
	}
}

