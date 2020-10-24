package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 * Purpose of this
 *
 * @author Logan Jolicoeur
 */
public class MainFrame extends JFrame implements ActionListener {

    //Window
    private final JFrame frame;
    private int WIDTH = 500;
    private int HEIGHT = 500;

    //Text Area
    private JTextArea textArea;

    //File menu items
    private JMenuItem openFile;
    private JMenuItem newFile;
    private JMenuItem saveFile;
    private JMenuItem print;

    //Edit menu items
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem cut;

    public MainFrame() {
        super("Text Editor v.1");

        frame = new JFrame();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        initMenu();
        frame.show();
    }

    private void initMenu() {

        //Create the menubar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar); // Add it the window or the "frame"

        //Add main menu buttons 
        JMenu file = new JMenu("File");
        menuBar.add(file);

        // File Add Menu items
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("(Alt+F4) Exit out of the program");
        file.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        newFile = new JMenuItem("New File");
        newFile.setToolTipText("New file to edit");
        file.add(newFile);
        newFile.addActionListener(this);

        openFile = new JMenuItem("Open File");
        newFile.setToolTipText("Open a file from your directory");
        file.add(openFile);
        openFile.addActionListener(this);

        saveFile = new JMenuItem("Save File");
        saveFile.setToolTipText("Save your work!");
        file.add(saveFile);
        saveFile.addActionListener(this);
        
        print = new JMenuItem("Print");
        print.setToolTipText("Print your work");
        file.add(print);
        print.addActionListener(this);

        // Edit Add menu      
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);

    }

    private void FileMaintainer(Object command) {

        if(command == openFile){
            JFileChooser jc = new JFileChooser();
            
            
            
        }
        
        
    }

    /**
     * Handle the user inputs from the keyboard or mouse
     *
     * @param e based on an event that occurs with the mouse or keyboard use it
     * to signify some type of user action is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //String command = e.getActionCommand();

        //File commands
        if (e.getSource() == openFile) {
            FileMaintainer(openFile);
        }else if (e.getSource() == newFile) {
            FileMaintainer(newFile);
        }else if(e.getSource() == saveFile){
            FileMaintainer(saveFile);
        }else if(e.getSource() == print){
            FileMaintainer(print);
        }
        
        //Edit Commands
            
           

    }
}
