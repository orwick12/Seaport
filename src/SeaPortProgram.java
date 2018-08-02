// File SeaPortProgram.java
//Date: Jun 28 2018
//Author Brian Orwick
// Purpose: Create a working seaport with objects fighting for resources


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class SeaPortProgram extends JFrame {

    static final long serialVersionUID = 123L;

    JTextArea textArea1 = new JTextArea();
    JComboBox<String> comboBox;
    JTextField textField1;
    JPanel jp = new JPanel();
    JPanel jobPanel = new JPanel();
    JButton jobButton = new JButton();
    JSplitPane splitPane = new JSplitPane();
    JPanel jpJobs;


    World world = new World(null);

    //GUI Constructor
    public SeaPortProgram() {

        System.out.println("InConstructor");
        setTitle("SeaPortProgram");
        setVisible(true);
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane1 = new JScrollPane(textArea1);

        add(scrollPane1, BorderLayout.EAST);
        scrollPane1.setPreferredSize(new Dimension(250, 400));

        jpJobs = new JPanel();
        jpJobs.setLayout(new java.awt.GridLayout(0, 1));
        jpJobs.add(new JLabel("The Jobs"));

        add(jpJobs, BorderLayout.CENTER);
        jpJobs.setPreferredSize(new Dimension(750, 400));



        JButton readButton = new JButton("Read");
        JButton displayButton = new JButton("Display");
        JButton searchButton = new JButton("Search");
        JButton sortButton = new JButton("Sort");
        JLabel searchLabel = new JLabel("Search Target");
        JButton jobButton = new JButton("Jobs");
        JTextField textField1 = new JTextField(10);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Index");
        comboBox.addItem("Skill");
        comboBox.addItem("Name");

        JLabel sortLabel = new JLabel("Sort by: ");
        JComboBox<String> comboBox2 = new JComboBox<String>();
        comboBox2.addItem("All Ships Alphabetically by Name");
        comboBox2.addItem("All Persons Alphabetically by Name");
        comboBox2.addItem("All Ships in Que Alphabetically by Name");
        comboBox2.addItem("All Docks Alphabetically by Name");
        comboBox2.addItem("Ship in que by Draft");
        comboBox2.addItem("Ship in que by Length");
        comboBox2.addItem("Ship in que by Width");
        comboBox2.addItem("Ship in que by Weight");


        jp.add(readButton);
        jp.add(displayButton);
        jp.add(searchLabel);
        jp.add(textField1);
        jp.add(comboBox);
        jp.add(searchButton);
        jp.add(sortLabel);
        jp.add(comboBox2);
        jp.add(sortButton);
        jp.add(jobButton);

        add(jp, BorderLayout.PAGE_START);

        validate();

        //actionlisteners for buttons
        readButton.addActionListener(e -> readFile());
        displayButton.addActionListener(e -> displaySeaport());
        searchButton.addActionListener(e -> search((String) (comboBox.getSelectedItem()), textField1.getText()));
        sortButton.addActionListener(e -> sortOptions((String) (comboBox2.getSelectedItem())));
        jobButton.addActionListener(e -> addJobs());

    }//end constructor

    public void addJobs() {

        if (world == null) return;
        for (Seaport p : world.ports)
            for (Ship s : p.ships)
                    for (Job j : s.jobs)
                        jpJobs.add(j.jPPanel);
        validate();
        //for (Seaport port : world.ports)
         //   for (Ship ship : port.ships)
           //     for(Job job : ship.jobs)
            //        job.runJob();
             //  validate();
    }



    public void backgroundMusic(){
        try {
            String url = "https://www.youtube.com/watch?v=VOgFZfRVaww";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //method for reading choosing and reading a file to supply world data
    public Scanner readFile() {
        textArea1.append("Read file button pressed\n");
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            textArea1.append("File[" + chooser.getSelectedFile().getName() + "] file loaded.\n");
            try {
                //create scanner object from file to parse through the file and supply data to the process()
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    if (sc.nextLine().startsWith("//")) {
                        continue;
                    }
                    world.process(sc);

                }
               // createTable();
            } catch (IOException e) {
                textArea1.append("Problem occurred while reading selected file.\n");
            }
        }
        return null;
    }

    void displaySeaport() {
        textArea1.append(world + "\n\n It is a large world!\n\n");
    }




    //search method input type and target determine which type with switch and use that search by method
    public void search(String type, String target) {
        switch (type) {
            case "Name":
                System.out.println("Searching by name: \n");
                textArea1.append(world.searchByName(target));
                break;
            case "Index":
                System.out.println("Searching by index: \n");
                textArea1.append(world.searchByIndex(Integer.parseInt(target)));
                break;
            case "Skill":
                System.out.println("Searching by skill: \n");
                textArea1.append(world.searchBySkill(target));
                break;
        }
    }

    public void sortOptions(String type) {
        switch (type) {
            case "All Ships Alphabetically by Name":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.ships, (a, b) -> a.name.compareTo(b.name));
                    //textArea1.append(sp.ships.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "All Persons Alphabetically by Name":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.persons, (a, b) -> a.name.compareTo(b.name));
                    //textArea1.append(sp.persons.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "All Ships in Que Alphabetically by Name":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.queue, (a, b) -> a.name.compareTo(b.name));
                    //textArea1.append(sp.queue.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "All Docks Alphabetically by Name":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.docks, (a, b) -> a.name.compareTo(b.name));
                    //textArea1.append(sp.docks.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "Ship in que by Draft":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.queue, (a, b) -> Double.compare(a.draft, b.draft));
                    //textArea1.append(sp.queue.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "Ship in que by Length":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.queue, (a, b) -> Double.compare(a.length, b.length));
                    //textArea1.append(sp.queue.toString());
                    textArea1.append(world.toString());
                }
                break;
            case "Ship in que by Width":
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.queue, (a, b) -> Double.compare(a.width, b.width));
                    //textArea1.append(sp.queue.toString());
                    textArea1.append(world.toString());
                }
                break;

            case "Ship in que by Weight":
                System.out.println("Sort Ships by weight");
                for (Seaport sp : world.getPorts()) {
                    Collections.sort(sp.queue, (a, b) -> Double.compare(a.weight, b.weight));
                    //textArea1.append(sp.queue.toString());
                    textArea1.append(world.toString());
                }
                break;
        }
    }

        public static void main (String[]args){
            SeaPortProgram sp = new SeaPortProgram();
        }//end main

}

