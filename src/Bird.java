
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Bird extends GameObject{

    private int jumpDelay;
    public int health, maxHealth = 2000;
    private Keyboard keyboard;
    private double rotation = 0.0;
    public int bleedTime = 0, fireTime = 0;
    public Bird () {
        x = 200;
        y = 150;
        yvel = 0;
        width = 45;
        height = 32;
        jumpDelay = 0;

        keyboard = Keyboard.getInstance();
        path = "res/bird.png";
        health = maxHealth;
    }

    public void update () {
    	if(bleedTime > 0)
    	{
    		particles.add(Particle.getBloodParticle(x + width / 2, y + height / 2));
    		particles.add(Particle.getBloodParticle(x + width / 2, y + height / 2));
    		particles.add(Particle.getBloodParticle(x + width / 2, y + height / 2));
    		bleedTime--;
    	}
    	if(health > 0)
    	{
    		health--;
    		if(y < 150)health--;
    		if(y < 0)health -= 2;
    	}
        yvel += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0 && health > 0) {
            yvel = -8;
            jumpDelay = 12;
        }
        y += (int)yvel;
        if (y + height > App.HEIGHT - 100) {
            alive = false;
        }
    }
    public void deathUpdate(GameObjectHandler gameObjectHandler)
    {
    	gameObjectHandler.gameObjects.add(Particle.getBirdParticle(x, y, rotation));
    	for(int i = 0; i < 200;i++)
    	{
        	gameObjectHandler.gameObjects.add(Particle.getBloodParticle(x, y));
    	}
    }
	public Render getRender()
	{
		Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Util.loadImage("res/bird.png");     
        }
        r.image = image;

        rotation = (90 * (yvel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(x + width / 2, y + height / 2);
        r.transform.rotate(rotation);
        r.transform.translate(-width / 2, -height / 2);

        return r;
	}
}

