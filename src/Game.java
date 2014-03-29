
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    public Keyboard keyboard;

    public Background[] backgrounds = new Background[3];
    public GameObjectHandler gameObjectHandler = new GameObjectHandler();

    public Boolean paused;
    public static Boolean gameover;
    public Boolean started;

    public static int score, highScore;
    public int pauseDelay, restartDelay, ringDelay, flameDelay, rockDelay;
    public int pauseDelayTime, restartDelayTime, ringDelayTime, flameDelayTime, rockDelayTime;
    public int backgroundOffset = 350;
    public Game () {
        keyboard = Keyboard.getInstance();
        backgrounds[0] = new Background();
        backgrounds[1] = new Background();
        backgrounds[2] = new Background();
        backgrounds[1].x = backgroundOffset;
        backgrounds[2].x = backgroundOffset * 2;
        restartDelayTime = 5;
        pauseDelay = 10;
        ringDelayTime = 15;
        flameDelayTime = 100;
        rockDelayTime = 50;
        restart();
    }

    public void update () {

    	 for(Particle particle : gameObjectHandler.bird.particles)
	     {
	        particle.update();
	     }
        if (restartDelay > 0)
            restartDelay--;
        if(rockDelay > 0)
        	rockDelay--;
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
        if (flameDelay > 0)
        	flameDelay--;
        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = pauseDelayTime;
        }
        if(rockDelay <= 0)
        {
        	gameObjectHandler.gameObjects.add(new Rock());
        	rockDelay = rockDelayTime;
        }
        if(ringDelay <= 0)
        {
        	gameObjectHandler.gameObjects.add(new Ring());
        	ringDelay = ringDelayTime;
        }
        if(flameDelay <= 0)
        {
        	gameObjectHandler.gameObjects.add(new FlameRing());
        	flameDelay = flameDelayTime;
        }
        if (!paused) {
        	gameObjectHandler.update();
            
        }
        if(!gameObjectHandler.bird.alive)
        {
        	gameover = true;
        }
        if(backgrounds[0].x == -backgroundOffset)
        {
        	backgrounds[0].x = 0;
        	backgrounds[1].x = backgroundOffset;
        	backgrounds[2].x = backgroundOffset * 2;
        }
        for(Background background : backgrounds)
        {
        	background.update();
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
        for(Background background : backgrounds)
        {
        	renders.add(background.getRender());
        }
        for(GameObject gameObject : gameObjectHandler.gameObjects)
        {
        	if(gameObject != null)
        	{
        		renders.add(gameObject.getRender());
        	     for(Particle particle : gameObject.particles)
        	     {
        	    	 renders.add(particle.getRender());	
        	     }
        	}
        }
        return renders;
    }
}
