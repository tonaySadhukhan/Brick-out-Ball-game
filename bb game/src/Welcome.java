import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Welcome extends JPanel {
    private CircularButton startButton;

    Welcome() {
        setLayout(new BorderLayout());

        // Load background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("background.jpg")); // Replace "background.jpg" with your image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a subclass of JPanel to draw the background image with opacity
        BufferedImage finalBackgroundImage = backgroundImage;
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f)); // Opacity 60%
                    g2d.drawImage(finalBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                    g2d.dispose();
                }
            }
        };
        backgroundPanel.setLayout(new GridBagLayout()); // Using GridBagLayout for centering

        // Create the custom circular button with play logo
        startButton = new CircularButton("play_logo.png"); // Replace "play_logo.png" with your play logo image path
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Start the game here
                JFrame frame = new JFrame("Graphics Demo");
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myPanel m1 = new myPanel(20);
                m1.setBackground(Color.yellow);

                frame.add(m1); // Adding myPanel directly to the JFrame
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });

        backgroundPanel.add(startButton); // Adding button to the background panel

        // Add the background panel to the center of the Welcome panel
        add(backgroundPanel, BorderLayout.CENTER);
    }
}

// Custom button class for circular button with play logo
class CircularButton extends JButton {
    private BufferedImage buttonImage;

    CircularButton(String imagePath) {
        try {
            buttonImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(100, 100)); // Adjust size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (buttonImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setClip(new Ellipse2D.Float(0, 0, getWidth(), getHeight()));
            g2d.drawImage(buttonImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // Adjust size as needed
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }
}

