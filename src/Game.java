
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    public Keyboard keyboard;

    public Background background;
    public GameObjectHandler gameObjectHandler = new GameObjectHandler();

    public Boolean paused;
    public static Boolean gameover;
    public Boolean started;

    public static int score, highScore;
    public int pauseDelay, restartDelay, ringDelay, skullDelay;
    public int pauseDelayTime, restartDelayTime, ringDelayTime, skullDelayTime;

    public Game () {
        keyboard = Keyboard.getInstance();
        background = new Background();
        restartDelayTime = 5;
        pauseDelay = 100;
        ringDelayTime = 30;
        skullDelayTime = 30;
        restart();
    }

    public void update () {


        if (restartDelay > 0)
            restartDelay--;
        if(skullDelay > 0)
        	skullDelay--;
        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = restartDelayTime;
        }
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }

        if (!started)
            return;

        if (pauseDelay > 0)
            pauseDelay--;
        
        if (ringDelay > 0)
            ringDelay--;
        
        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = pauseDelayTime;
        }
        if(skullDelay <= 0)
        {
        	gameObjectHandler.gameObjects.add(new Rock());
        	skullDelay = skullDelayTime;
        }
        if(ringDelay <= 0)
        {
        	gameObjectHandler.gameObjects.add(new Ring());
        	ringDelay = ringDelayTime;
        }
        if (!paused) {
        	gameObjectHandler.update();
            
        }
        if(!gameObjectHandler.bird.alive)
        {
        	gameover = true;
        }
    }

    public void restart () {
    	gameObjectHandler = new GameObjectHandler();
        paused = false;
        gameover = false;
        started = false;

        pauseDelay = 0;
        restartDelay = 0;
        ringDelay = 0;
        score = 0;
    }
    
    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(background.getRender());
        gameObjectHandler.bird.getRender();
        for(GameObject gameObject : gameObjectHandler.gameObjects)
        {
        	if(gameObject != null)
        	{
        		renders.add(gameObject.getRender());
        	}
        }
        return renders;
    }
}
