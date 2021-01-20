
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

    private Vis mainPanel;


    public Main() {

        JMenuBar mb = setupMenu();
        setJMenuBar(mb);

        mainPanel = new Vis();
        setContentPane(mainPanel);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Put the title of your program here");
        setVisible(true);
    }

    //select count(*) from derbyDB
    private int runSimpleCountQuery(String q) {
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:pollster");
            System.out.println("Printing Connection c ="+ c);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            rs.next();
            int count = rs.getInt(1);
            System.out.println("End of the try");

            return count;
        } catch (SQLException e) {
            System.out.println("could not connect to Derby!");
            System.out.println(e);
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            System.err.println("  Message:    " + e.getNextException());

            return 0;
        }
    }

    private JMenuBar setupMenu() {
        //instantiate menubar, menus, and menu options
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("Item 1");
        JMenuItem item2 = new JMenuItem("Item 2");
        JMenuItem item3 = new JMenuItem("Item 3");
        JMenuItem item4 = new JMenuItem("Item 4");
        JMenuItem item5 = new JMenuItem("Item 5");
        JMenuItem item6 = new JMenuItem("Item 6");
        JMenuItem item7 = new JMenuItem("Item 7");


        
        JMenu subMenu = new JMenu("Submenu");
//        JMenuItem item2 = new JMenuItem("Item 2");

        //setup action listeners
        
        // All the students 
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 1");
                int gilmo = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019");
                System.out.println("I found " + gilmo + " students in the table.");
                mainPanel.setText("I found " + gilmo + " rows in the table.");
            }
        });
        
        //All CS Students
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 2");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Computer Science'");
                System.out.println("I found " + output + " Computer Science Students in the table.");
                mainPanel.setText("I found " + output + " Computer Science Students in the table.");
            }
        });
        
        // All IS Students
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 3");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Information Systems'");
                System.out.println("I found " + output + " Information Systems Students in the table.");
                mainPanel.setText("I found " + output + " Information Systems Students in the table.");
            }
        });
        
    
        // All IT Student
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 4");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Information Technology'");
                System.out.println("I found " + output + " Information Technology Students in the table.");
                mainPanel.setText("I found " + output + " Information Technology Students in the table.");
            }
        });
        
        // CS or IT students
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 5");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Information Technology' OR major = 'Computer Science'");
                System.out.println("I found " + output + " Information Technology and Computer Science Students in the table.");
                mainPanel.setText("I found " + output + " Information Technology and Computer Science Students in the table.");
            }
        });
        
        // Female Computer Science 
        item6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 6");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Computer Science' AND gender = 'F'");
                System.out.println("I found " + output + " Female Computer Science Students in the table.");
                mainPanel.setText("I found " + output + " Female Computer Science Students in the table.");
            }
        });
        
        // Male Computer Science 
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 7");
                int output = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019 WHERE  major = 'Computer Science' AND gender = 'M'");
                System.out.println("I found " + output + " Male Computer Science Students in the table.");
                mainPanel.setText("I found " + output + " Male Computer Science Students in the table.");
            }
        });


        //now hook them all together
//        subMenu.add(item2);

        fileMenu.add(item1);
        fileMenu.add(item2);
        fileMenu.add(item3);
        fileMenu.add(item4);
        fileMenu.add(item5);
        fileMenu.add(item6);
        fileMenu.add(item7);
        fileMenu.add(subMenu);
        menuBar.add(fileMenu);

        return menuBar;
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}