import java.awt.Color;
import java.awt.Graphics;

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
        repaint();
    }

    protected void paintComponent (Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);

        for (Render r : game.getRenders())
        {
        	g.drawImage(r.image, r.x, r.y, null);
        }
        g.drawString("score: " + game.score, 5, App.HEIGHT - 40);
        g.drawString("hunger: " + (int)(game.gameObjectHandler.bird.hunger / 10), 75, App.HEIGHT - 40);
    }
    
    public void run () {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
