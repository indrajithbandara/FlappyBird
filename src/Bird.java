
import java.awt.event.KeyEvent;

public class Bird extends GameObject{

    private int jumpDelay;
    private Keyboard keyboard;

    public Bird () {
        x = 100;
        y = 150;
        yvel = 0;
        width = 45;
        height = 32;
        jumpDelay = 0;

        keyboard = Keyboard.getInstance();
    }

    public void update () {
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
}

