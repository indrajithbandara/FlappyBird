import java.util.ArrayList;


public class GameObjectHandler
{
	public Bird bird = new Bird();
	public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	public GameObjectHandler()
	{
		gameObjects.add(bird);
	}
	public void update()
	{
		for(GameObject gameObject : gameObjects)
		{
			if(gameObject != null)
			{
				gameObject.update();
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
					Game.score += 1;
					bird.hunger += 50;
					if(bird.hunger > bird.maxHunger)bird.hunger = bird.maxHunger;
				}
				if(!gameObjects.get(i).alive)gameObjects.remove(gameObjects.get(i));
			}
		}
		if (bird.y + bird.height > App.HEIGHT - 80) {
			bird.alive = false;
        }
	}
}
