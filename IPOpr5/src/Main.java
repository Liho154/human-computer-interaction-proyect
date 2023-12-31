import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * {@code @brief:} Inicialización de la aplicación
 *
 */

public class Main {
    public static void main(String[] args) {

            homepage todo = new homepage();
            JFrame frame = new JFrame("UJA Lobby");
            frame.setJMenuBar(todo.getMenuBar());
            frame.setContentPane(todo.getPanel1());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            centerFrame(frame);
            frame.setVisible(true);

        }
    private static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setLocation(x, y);
    }
}

