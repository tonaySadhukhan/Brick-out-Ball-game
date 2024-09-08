import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

class myPanel extends JPanel implements KeyListener, ActionListener {
    Random r1 = new Random();
    int OvalPosx = 320;
    int OvalPosy = 500 - 20;
    final int OvalDia = 20;
    int padalx = 300;
    int padaly = 500;
    int padalW = 70;
    final int padalH = 10;
    int ballxdir = -2;
    int ballydir = -2;
    int row = 9, col = 4;
    int[][] arr = {
            { 1, 1, 1, 1 },
            { 1, 1, 1, 1 },
            { 1, 2, 2, 1 },
            { 1, 2, 2, 1 },
            { 1, 2, 2, 1 },
            { 1, 2, 2, 1 },
            { 1, 2, 2, 1 },
            { 1, 1, 1, 1 },
            { 1, 1, 1, 1 }
    };
    int brickPos = 30;
    int brickposy = 30;
    int i, j;
    int score = 0;
    Timer timer;

    myPanel(int delay) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(OvalPosx, OvalPosy, OvalDia, OvalDia);
        g.setColor(Color.blue);
        g.fillRect(padalx, padaly, padalW, padalH);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Score:-" + score, 400, 20);

        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    brickPos = 30 + 60 * i;
                    brickposy = 30 + 43 * j;
                    g.setColor(Color.BLACK);
                    g.drawRoundRect(brickPos, brickposy, 50, 40, 6, 6);
                    if (arr[i][j] == 1) {
                        g.setColor(Color.green);
                    } else if (arr[i][j] == 2) {
                        g.setColor(Color.blue);
                    } else if (arr[i][j] == 3) {
                        g.setColor(Color.cyan); // Change color to indicate the block is half-broken
                    }
                    g.fillRoundRect(brickPos, brickposy, 50, 40, 6, 6);

                    int ballLeft = OvalPosx;
                    int ballRight = OvalPosx + OvalDia;
                    int ballTop = OvalPosy;
                    int ballBottom = OvalPosy + OvalDia;
                    int brickLeft = brickPos;
                    int brickRight = brickPos + 50;
                    int brickTop = brickposy;
                    int brickBottom = brickposy + 40;

                    boolean collision = ballRight > brickLeft && ballLeft < brickRight && ballBottom > brickTop && ballTop < brickBottom;
                    if (collision) {
                        if (arr[i][j] == 1) {
                            arr[i][j] = 0;
                            score++;
                        } else if (arr[i][j] == 2) {
                            arr[i][j] = 3; // Change to half-broken state
                        } else if (arr[i][j] == 3) {
                            arr[i][j] = 0;
                            padalW = padalW + 20;
                            score++;
                        }
                        ballydir = -ballydir;
                        break; // Exit loop after processing collision
                    }
                }
            }
        }

        if (OvalPosy >= padaly) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString("Game over", 150, 300);
            timer.stop(); // Stop the game timer
        }

        if (score == 36) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.drawString("You won", 150, 300);
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 30));
            g.drawString("Your score is " + score, 150, 320);
            timer.stop(); // Stop the game timer
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            padalx = padalx - 10;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            padalx = padalx + 10;
        }
        padalx = Math.max(0, Math.min(getWidth() - padalW, padalx));
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        OvalPosy += ballydir;
        OvalPosx += ballxdir;
        if (OvalPosx < 0) {
            ballxdir = -ballxdir;
        }
        if (OvalPosy < 0) {
            ballydir = -ballydir;
        }
        if (OvalPosx > 570) {
            ballxdir = -ballxdir;
        }
        if ((OvalPosx > padalx && OvalPosx < padalx + padalW) && OvalPosy > padaly - OvalDia) {
            ballydir = -ballydir;
        }
        repaint();
    }
}
