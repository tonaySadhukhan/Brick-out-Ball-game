import javax.swing.*;
import java.util.Scanner;

public class mainGame extends JFrame {
        static  int k;
        static int delay=20;
        static JRadioButton b,b1,b2;
        static JButton bt;


        public static void main(String[] args){




            JFrame welcomeFrame = new JFrame("Welcome to the Game");
            welcomeFrame.setSize(600, 600);
            welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Welcome welcomePanel = new Welcome();
            welcomeFrame.add(welcomePanel);

            welcomeFrame.setVisible(true);
            String path = "music.wav";
            Scanner sc = new Scanner(System.in);
            Playaudio player = new Playaudio();
            player.Audio(path);
            sc.nextLine();
            player.stop();

        }

    }

