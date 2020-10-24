package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Purpose of this
 *
 * @author Logan Jolicoeur
 */
public class MainFrame extends JFrame {

    //Window
    private final JFrame frame;
    private final String title = "TextEditor v.1";
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
        frame = new JFrame(title);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        initMenu();
        frame.setVisible(true);
    }
    
    private void initMenu() {
        //Create the menubar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar); // Add it the window or the "frame"

        //Text/////////////////
        textArea = new JTextArea();
        frame.add(textArea);

        //
        JScrollPane scrollTextArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        frame.add(scrollTextArea);

        /////////////
        //Add main menu buttons 
        JMenu file = new JMenu("File");
        menuBar.add(file);

        // File Add Menu items
        newFile = new JMenuItem("New File");
        newFile.setToolTipText("New file to edit");
        file.add(newFile);
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        openFile = new JMenuItem("Open File");
        openFile.setToolTipText("Open a file from your directory");
        file.add(openFile);
        openFile.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                //File Dialog box to choose our files, open in the project
                //Directory
                JFileChooser fileChooser = new JFileChooser(".");

                //If the user accepts programs need to open files continue
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    
                    File file = fileChooser.getSelectedFile(); //get selected
                    String filePath = file.getPath(); // Based on that file get the path

                    //In a try catc
                    try {
                        //Read the byte stream line by line from the file
                        BufferedReader readFile = new BufferedReader(new FileReader(filePath));
                        
                        StringBuilder stringBuild = new StringBuilder();
                        //Read the bytes coming in
                        char[] buffer = new char[100];

                        //While reading the file, if the buffer is not equal to -1 
                        //Meaning we've read the entire file
                        while (readFile.read(buffer) != -1) {

                            //Append the chars to the stringbuild
                            stringBuild.append(new String(buffer));
                            //Add 10 more to buffer
                            buffer = new char[100];
                        }

                        //Append the text from the file to the textArea
                        textArea.setText(stringBuild.toString());

                        //Make sure to close the file
                        readFile.close();
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "An Error Occured" + ex.toString());
                    }
                    
                }
            }
        });
        
        saveFile = new JMenuItem("Save File");
        saveFile.setToolTipText("Save your work!");
        file.add(saveFile);
        saveFile.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");
                fileChooser.setDialogTitle("Save your file");
//                
//                if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
//                    File file = fileChooser.getSelectedFile();
//                    System.out.println("File saved: " + file.getAbsolutePath());
//                }
                
            }
        });
        
        print = new JMenuItem("Print");
        print.setToolTipText("Print your work");
        file.add(print);
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("(Alt+F4) Exit out of the program");
        file.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Edit Add menu      
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);
        
    }
}
