package College_Management_System;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Result extends JFrame {
    //reference variables
    JPanel name, panel;
    JTable table;
    DefaultTableModel model1, model2;
    Connection connection;

    //constructor
    public Result() {
        setVisible(true);
        setTitle("Result");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        setSize(600, 600);
        panel.add(dataUI());
        panel.add(dataPanel2());
        getContentPane().add(panel);
        setLocationRelativeTo(null);
        pack();

        //Connecting to database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        get();
        get2();
    }
    private void select() {
    	String query = "USE college_management_system";
    	try {
    		Statement statement = connection.createStatement();
            int value = statement.executeUpdate(query);
            System.out.println(value);
            System.out.println("Database Created!!!");
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private void get() {
        String query = "SELECT * FROM instructor where marks < 40";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                model1.addRow(new Object[]{
                        result.getString("module_Name"),
                        result.getString("student_name"),
                        result.getString("marks"),
                });
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void get2() {
        String query = "SELECT * FROM instructor where marks >= 40";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                model2.addRow(new Object[]{
                        result.getString("module_Name"),
                        result.getString("student_name"),
                        result.getString("marks"),
                });
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Data Panel (JTable) for storing data of the students who passed the exam
    private JPanel dataUI() {

        name = new JPanel();
        name.setBorder(BorderFactory.createTitledBorder("Failed Students"));
        name.setLayout(new GridLayout(1, 0));

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 15));
        table.setBackground(new Color(216, 191, 216));
        table.setDefaultEditor(Object.class, null);

        Object[] column = {"Module_Name", "Student_Name", "Marks"};

        Object[][] row = {

        };
        model1 = new DefaultTableModel(row, column);
        table.setModel(model1);
        JScrollPane scrollPane = new JScrollPane(table);
        name.add(scrollPane);


        return name;
    }

    //Data Panel (JTable) for storing data of the students who failed the exam
    private JPanel dataPanel2() {

        name = new JPanel();
        name.setBorder(BorderFactory.createTitledBorder("Passed Students"));
        name.setLayout(new GridLayout(1, 0));

        table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 15));
        table.setBackground(Color.PINK);
        table.setDefaultEditor(Object.class, null);

        Object[] column = {"Module_Name", "Student_Name", "Marks"};

        Object[][] row = {

        };
        model2 = new DefaultTableModel(row, column);
        table.setModel(model2);
        JScrollPane scrollPane = new JScrollPane(table);
        name.add(scrollPane);


        return name;
    }

    //main method
    public static void main(String[] args) {

        new Result(); //calling Constructor
    }
}
