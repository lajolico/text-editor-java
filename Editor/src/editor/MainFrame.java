package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Main code that runs the Text Editor
 *
 * @author Logan Jolicoeur
 */
public class MainFrame extends JFrame {

    //Window
    private final JFrame frame;
    private final String title = "TextEditor";
    private int WIDTH = 500;
    private int HEIGHT = 500;

    //Text Area
    private JTextArea textArea;

    //File menu items
    private JMenuItem openFile;
    private JMenuItem newFile;
    private JMenuItem saveFile;
    private JMenuItem print;

    //File Filters
//    private final FileNameExtensionFilter filter = new FileNameExtensionFilter( ".txt", 
//                                                                        ".txt", ".html", ".docx", ".xml" );

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

        //Give the user the ability to manuever the textArea
        JScrollPane scrollTextArea = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(scrollTextArea);

        /////////////
        //Add main menu buttons 
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // File Add Menu items
        newFile = new JMenuItem("New File");
        newFile.setToolTipText("New file to edit");
        fileMenu.add(newFile);
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        openFile = new JMenuItem("Open File");
        openFile.setToolTipText("Open a file from your directory");
        fileMenu.add(openFile);
        openFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //File Dialog box to choose our files, open in the project
                //Directory
                JFileChooser fileChooser = new JFileChooser(".");

                fileChooser.setDialogTitle("Open");

                //Filter the choices of file extensions
                //fileChooser.setFileFilter(filter);

                //If the user accepts programs need to open files continue
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //get selected file and path

                    //In a try catch block
                    try {
                        //Read the byte stream line by line from the file
                        BufferedReader readFile = new BufferedReader(new FileReader(file));

                        StringBuilder stringBuild = new StringBuilder();

                        //Read the bytes coming in
                        char[] buffer = new char[10];

                        //While reading the file, if the buffer is not equal to -1 
                        //Meaning if we haven't read the entire file continue
                        while (readFile.read(buffer) != -1) {

                            //Append the chars to the stringbuild
                            stringBuild.append(new String(buffer));
                            //Add 10 more to buffer
                            buffer = new char[10];

                        }

                        //Append the text from the file to the textArea
                        textArea.setText(stringBuild.toString());

                        //Make sure to close the file
                        readFile.close();

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "User Cancelled");
                }
            }
        });

        saveFile = new JMenuItem("Save File");
        saveFile.setToolTipText("Save your work!");
        fileMenu.add(saveFile);
        saveFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(".");

             //   fileChooser.setFileFilter(filter);

                fileChooser.setDialogTitle("Save");

                if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {

                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        BufferedWriter writeToFile = new BufferedWriter(new FileWriter(file));

                        writeToFile.write(textArea.getText());

                        writeToFile.flush();

                        writeToFile.close();

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "User Cancelled");
                }

            }
        });

        print = new JMenuItem("Print");
        print.setToolTipText("Print your work");
        fileMenu.add(print);
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("(Alt+F4) Exit out of the program");
        fileMenu.add(exit);
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
