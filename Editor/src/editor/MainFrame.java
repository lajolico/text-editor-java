package editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    private final static String title = "TextEditor";
    private int WIDTH = 800;
    private int HEIGHT = 600;

    //Text Area
    private static JTextArea textArea;

    //File menu items
    private static JMenuItem openFile, newFile, saveFile, print;

    //Edit menu items
    private static JMenuItem copy, cut, paste, delete;

    public MainFrame() {
        frame = new JFrame(title);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        //TODO
        try {
            frame.setIconImage(ImageIO.read(new File("//text-editor-java\\res\\ico_base.png")));
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(frame, ex.getMessage());
        }

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
        newFile = new JMenuItem("New");
        newFile.setToolTipText("New");
        fileMenu.add(newFile);
        newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        openFile = new JMenuItem("Open File");
        openFile.setToolTipText("Open a file from your directory");
        fileMenu.add(openFile);
        openFile.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //File Dialog box to choose our files, open in the project
                //Directory
                JFileChooser fileChooser = new JFileChooser(".");

                fileChooser.setDialogTitle("Open");

                FileNameExtensionFilter filt = new FileNameExtensionFilter(
                        "Text Documents", "txt");

                fileChooser.setFileFilter(filt);

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

                }
            }
        });

        /**
         * Save your file
         */
        saveFile = new JMenuItem("Save File");
        saveFile.setToolTipText("Save your work!");
        fileMenu.add(saveFile);
        saveFile.addActionListener(new ActionListener() {

            /**
             * In charge of saving the textArea text to a file
             */
            public void actionPerformed(ActionEvent e) {
                //Open it up in the directory it's located
                JFileChooser fileChooser = new JFileChooser(".");

                FileNameExtensionFilter filt = new FileNameExtensionFilter(
                        "Text Documents", "txt");

                fileChooser.setFileFilter(filt);

                //Set the title
                fileChooser.setDialogTitle("Save");

                //If the user accepts the action to save to a file, proceed
                if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {

                    //Get the selected file and path
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    //Try & catch
                    try {
                        //Using the BufferWriter with FileWriter, write to said file
                        BufferedWriter writeToFile = new BufferedWriter(new FileWriter(file));

                        //Write to the file
                        writeToFile.write(textArea.getText());

                        //FLush the stream
                        writeToFile.flush();

                        //Close the file
                        writeToFile.close();

                    } catch (IOException ex) {
                        //If something goes wrong print the message to the user
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }

                }
            }
        });

        /**
         * Print your text file
         */
        print = new JMenuItem("Print");
        print.setToolTipText("Print your work");
        fileMenu.add(print);
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea.print();
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        /**
         * Exit out of the program
         */
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("(Alt+F4) Exit out of the program");
        fileMenu.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ////////////
        // Edit Add menu tems  
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);

        /**
         * Cut function
         */
        cut = new JMenuItem("Cut");
        cut.setToolTipText("Ctrl+X");
        edit.add(cut);
        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        /**
         * Copy function
         */
        copy = new JMenuItem("Copy");
        copy.setToolTipText("Ctrl+C");
        edit.add(copy);
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        /**
         * Paste function
         */
        paste = new JMenuItem("Paste");
        paste.setToolTipText("Ctrl+V");
        edit.add(paste);
        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });

        /**
         * Delete Function
         */
        delete = new JMenuItem("Delete");
        delete.setToolTipText("Del");
        edit.add(delete);
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO write the delete code on deleting text that is selected
                textArea.replaceSelection("");
            }
        });
    }
}
