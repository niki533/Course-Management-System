package College_Management_System;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;

public class AssignedLeader extends JFrame{

        DefaultTableModel tableModel;
        JTable table;
        JPanel name;
        Connection con;
        AssignedLeader pb = this;

        //constructor
        public AssignedLeader(){
            setTitle("Module_Leader");
            setMinimumSize(new Dimension(400,400));
            setJMenuBar(getMenu());
            setVisible(true);
            getContentPane().setLayout(new GridLayout(0,1));
            getContentPane().add(infoPanel());

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null);

            //Connecting database and intellij
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system","root","");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            get();
        }
        
        private void select() {
        	String query = "USE college_management_system";
        	try {
        		Statement statement = con.createStatement();
                int value = statement.executeUpdate(query);
                System.out.println(value);
                System.out.println("Database Created!!!");
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        private void get() {
            String query = " select* from module";

            try {
                PreparedStatement stm = con.prepareStatement(query);
                ResultSet result = stm.executeQuery();

                while (result.next()) {

                    tableModel.addRow(new Object[]{
                            result.getString("module_leader"),
                            result.getString("module_name"),

                    });
                }

                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Menu Bar
        private JMenuBar getMenu() {
            JMenuBar menuBar=new JMenuBar();
            JMenu fileMenu=new JMenu("File") ;
            JMenu editMenu=new JMenu("Edit") ;
            JMenu helpMenu=new JMenu("Help") ;
            JMenuItem exitMenu = new JMenuItem(" Exit");
            fileMenu.add(exitMenu);
            JMenuItem appendMenu= new JMenuItem("append");
            editMenu.add(appendMenu);
            JMenuItem aboutMenu= new JMenuItem("About");
            helpMenu.add(aboutMenu);
            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);

            exitMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            //ActionListener for AboutMenu
            appendMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(pb, "You cannot Edit Here!!");
                }
            });


            //ActionListener for AboutMenu
            aboutMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(pb, "It is still in trial version!!");
                }
            });


            //adding Shortcut key
            fileMenu.setMnemonic(KeyEvent.VK_F);
            editMenu.setMnemonic(KeyEvent.VK_E);
            helpMenu.setMnemonic(KeyEvent.VK_H);
            exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
            appendMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
            aboutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));


            fileMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            return menuBar;
        }

        //Info Panel for taking data from users
        private JPanel infoPanel() {
            name = new JPanel();
            name.setBackground(Color.PINK);

            name.setBorder(BorderFactory.createTitledBorder("Module Assigned for Teacher"));
            name.setLayout(new GridLayout(1, 0));

            table = new JTable();
            table.setFont(new Font("Tahoma", Font.BOLD, 15));
            table.setBackground(new Color(216, 191, 216));
            table.setDefaultEditor(Object.class, null);

            Object[] column = {"Module Leader", "Module Name"};

            Object[][] row = {

            };
            tableModel = new DefaultTableModel(row, column);
            table.setModel(tableModel);
            table.setRowSelectionAllowed(false);
            JScrollPane scrollPane = new JScrollPane(table);
            name.add(scrollPane);

            return name;
        }
        public static void main(String[] args) {

            new AssignedLeader();  //calling constructor
        }
    }
