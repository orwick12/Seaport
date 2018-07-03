// File SeaPortProgram.java
//Date: Jun 28 2018
//Author Brian Orwick
// Purpose: Create a working seaport with objects fighting for resources


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.* ;
import java.util.*;


public class SeaPortProgram extends JFrame {

    static final long serialVersionUID = 123L;

    JTextArea textArea1 = new JTextArea();
    JComboBox <String> comboBox;
    JTextField textField1;


    World world = new World(null);
    //GUI Constructor
    public SeaPortProgram() {

        System.out.println("InConstructor");
        setTitle("SeaPortProgram");
        setVisible(true);
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        add(scrollPane1, BorderLayout.CENTER);

        JTree tree = new JTree(createNodes("Seaport"));
        JScrollPane pane2 = new JScrollPane(tree);
        add(pane2, BorderLayout.WEST);

        JButton readButton = new JButton("Read");
        JButton displayButton = new JButton("Display");
        JButton searchButton = new JButton("Search");

        JLabel searchLabel = new JLabel("Search Target");

        JTextField textField1 = new JTextField(10);

        JComboBox <String> comboBox = new JComboBox<String> ();
        comboBox.addItem("Index");
        comboBox.addItem("Type");
        comboBox.addItem("Name");

        JPanel jp = new JPanel();
        jp.add(readButton);
        jp.add(searchButton);
        jp.add(displayButton);
        jp.add(searchLabel);
        jp.add(textField1);
        jp.add(comboBox);


        add(jp, BorderLayout.PAGE_START);

        validate();

        readButton.addActionListener (e -> readFile());

        displayButton.addActionListener (e -> displaySeaport());

        searchButton.addActionListener (e -> search ((String)(comboBox.getSelectedItem()), textField1.getText()));
    }//end constructor



    public  Scanner readFile(){

        textArea1.append("Read file button pressed\n");
        JFileChooser chooser = new JFileChooser(".");

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try{
                StringBuilder sb = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while(line != null ) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = reader.readLine();
                    textArea1.append("File[" + chooser.getSelectedFile().getName() + "] file loaded.\n");
                    world.process(line);
                }
            }
            catch (IOException e){
                textArea1.append("Problem occurred while reading selected file.\n");
            }
        }
            return null;
    }
    //end method readFile

    void displaySeaport(){
        textArea1.append(world + " is the world showing");
    }

    public void search(String type, String target){
        textArea1.append(String.format ("Search button pressed, type: >%s<, " +
                "target: >%s<\n", type, target));
    }
    public static void main(String[] args) {
        SeaPortProgram sp = new SeaPortProgram();

    }//end main

        public DefaultMutableTreeNode createNodes(String title) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(title);
        DefaultMutableTreeNode spn, sn;
        for(Seaport sp: world.ports) {
            spn = new DefaultMutableTreeNode(sp.name);
            top.add(spn);
            for(Ship sh: sp.ships) {
                sn = new DefaultMutableTreeNode("shipname: " + sh.name);
                spn.add(sn);
                for(Job j: sh.jobs)
                    sn.add(new DefaultMutableTreeNode("Job: " + j.name));

            }
        }
        return top;
    } // end method createNodes

}//end class SeaPortProgram
