import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class GameObjectHandler
{
	public Bird bird = new Bird();
	public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private Keyboard keyboard;
	public GameObjectHandler()
	{
		gameObjects.add(bird);
		keyboard = Keyboard.getInstance();
	}
	public void update()
	{
		if(bird.fireTime > 0 && keyboard.isDown(KeyEvent.VK_SHIFT))
		{
			gameObjects.add(new FireBall(bird.x + bird.width - 17, bird.y + 12));
			bird.fireTime--;
		}
		for(GameObject gameObject : gameObjects)
		{
			if(gameObject != null)
			{
				gameObject.update();
				for(Particle particle : gameObject.particles)
				{
					if(gameObject.particleGen)
					{
						particle.update();
					}
				}
				for(GameObject otherGameObject : gameObjects)
				{
					if(otherGameObject != null)
					{
						if(gameObject.collide(otherGameObject))
						{
							gameObject.doCollide(otherGameObject);
						}
					}
				}
			}
		}
		for(int i = 0;i < gameObjects.size() - 1;i++)
		{
			if(gameObjects.get(i) != null)
			{
				if(gameObjects.get(i) instanceof Ring && ((Ring)gameObjects.get(i)).collected)
				{
					if(gameObjects.get(i) instanceof FlameRing)
					{
						Game.score += 1;
						bird.fireTime = 20;
					}
					else
					{
						Game.score += 1;
						bird.health += 50;
						if(bird.health > bird.maxHealth)bird.health = bird.maxHealth;
					}
				}
				for(int j = 0; j < gameObjects.get(i).particles.size() - 1;j++)
				{
					if(gameObjects.get(i).particleGen)
					{
						if(!gameObjects.get(i).particles.get(j).alive) gameObjects.get(i).particles.remove(j);
					}
				}
				if(gameObjects.get(i).x + gameObjects.get(i).width < 0)
				{
					gameObjects.remove(gameObjects.get(i));
				}
				else if(!gameObjects.get(i).alive)
				{
					gameObjects.get(i).deathUpdate(this);
					gameObjects.remove(gameObjects.get(i));
				}
			}
		}
		if (bird.y + bird.height > App.HEIGHT - 100) {
			bird.alive = false;
        }
	}
}
