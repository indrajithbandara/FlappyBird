import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    private Background bg;
    private Game game;

    public GamePanel () {
        game = new Game();

        new Thread(this).start();
    }

    public void update () {
        game.update();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);
        g.fillRect(10, App.HEIGHT - 80, App.WIDTH - 40, 40);
        g.setColor(Color.white);
        g.drawString("score: " + game.score, 12, App.HEIGHT - 60);
        if(game.gameObjectHandler.bird.health / 20 < 50)
        {
        	g.setColor(Color.yellow);
        }
        if(game.gameObjectHandler.bird.health / 20 < 25)
        {
        	g.setColor(Color.red);
        }
        g.drawString("hp: " + (int)(game.gameObjectHandler.bird.health / 20), 75, App.HEIGHT - 60);
    }
    public void render()
    {
    	repaint();
    }
    public void run () {
    	long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while(true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			render();
			while(delta >= 1)
			{
				update();
				delta--;
			}
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
			}
		}
    }
}
