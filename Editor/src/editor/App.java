package editor;

import javax.swing.SwingUtilities;

/**
 * Purpose of this program is to develop a text editor in Java
 *
 * @author Logan Jolicoeur
 */
public class App {

    /**
     * This is where we run the main logic of the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame gui = new MainFrame();
            }
        });

    }
}
