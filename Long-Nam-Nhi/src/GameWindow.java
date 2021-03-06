import constant.Constant;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    private long lastTime = 0;
    private GameCanvas gameCanvas;

    public GameWindow() {
        this.setSize(Constant.Window.WIDTH, Constant.Window.HEIGHT); // set size window

        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
        this.event();

        this.setVisible(true);
    }

    private void event() {
        this.keyboardEvent();
        this.windowEvent();
    }

    private void keyboardEvent() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    gameCanvas.scissor.position.x = Constant.Grow.LEFT;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gameCanvas.scissor.position.x = Constant.Grow.RIGHT;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    gameCanvas.scissor.velocity.set(0, -5);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameCanvas.scissor.velocity.set(0, 5);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameCanvas.scissor.cut();
                    if (gameCanvas.scissor.position.x == Constant.Grow.LEFT) {
                        if (gameCanvas.scissor.position.y < gameCanvas.leftGrow.position.y)
                        gameCanvas.leftGrow.position.set(gameCanvas.scissor.position);
                    } else {
                        if (gameCanvas.scissor.position.y < gameCanvas.rightGrow.position.y)
                        gameCanvas.rightGrow.position.set(gameCanvas.scissor.position);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    gameCanvas.scissor.velocity.set(0, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameCanvas.scissor.velocity.set(0, 0);
                }
            }
        });
    }

    private void windowEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }

        }
    }
}
