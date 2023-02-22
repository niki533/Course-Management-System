package College_Management_System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

    public class Student extends JFrame {

        //reference variables
        JPanel name, panel1;
        JTextField firstNameTxt, addressTxt, phoneNo;
        JComboBox selectCourse, level;
        JButton clearBtn, addBtn, viewResult;
        JTable table;
        DefaultTableModel tableModel;
        Student self = this;
        Connection connection;

        //constructor
        public Student() {
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setLayout(new GridLayout(0, 2));
            panel1 = new JPanel();
            setJMenuBar(getMenu());

            panel1.setLayout(new GridLayout(2, 0));
            setSize(929, 535);
            setLocationRelativeTo(null);

            panel1.add(infoUI());
            panel1.add(btnUI());
            getContentPane().add(dataPanel());
            getContentPane().add(panel1);
            pack();
            setLocationRelativeTo(null);
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            get();
            get2();
        }
        private void insert(String fN, String add, long pN, String cour, String lvl){
            String query =  "INSERT INTO student(student_name, address, phone_number, course, level) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement stm = connection.prepareStatement(query);//passing queries
                stm.setString(1, fN);
                stm.setString(2, add);
                stm.setLong(3, pN);
                stm.setString(4, cour);
                stm.setString(5, lvl);
                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        private void get(){
            String query = "SELECT * FROM courseAdministrator";
            try {
                PreparedStatement stm = connection.prepareStatement(query);
                ResultSet result = stm.executeQuery();
                while (result.next()){

                    String st= result.getString("course_name");
                    selectCourse.addItem(st);
                }
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        private void get2() {
            String query = "SELECT * FROM student";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet res = st.executeQuery();
                while (res.next()) {
                    tableModel.addRow(new Object[]{
                            res.getString("student_name"),
                            res.getString("address"),
                            res.getLong("phone_number"),
                            res.getString("course"),
                            res.getString("level")
                    });
                }
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Menu Bar
        private JMenuBar getMenu() {
            JMenuBar menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("File");
            JMenuItem subFileExit = new JMenuItem("Exit");
            fileMenu.add(subFileExit);

            JMenu editMenu = new JMenu("Edit");
            JMenuItem clear = new JMenuItem("Clear");
            JMenuItem add = new JMenuItem("Register");

            editMenu.add(clear);
            editMenu.addSeparator();
            editMenu.add(add);


            JMenu helpMenu = new JMenu("Help");
            JMenuItem about = new JMenuItem("About");
            helpMenu.add(about);

            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);

            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearBtn.doClick();
                }
            });

            subFileExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            about.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(self, " ");
                }
            });
            //mnemonics (Shortcut keys) for different events to happen automatically from the keyboard.
            fileMenu.setMnemonic(KeyEvent.VK_F);
            editMenu.setMnemonic(KeyEvent.VK_E);
            helpMenu.setMnemonic(KeyEvent.VK_H);
            subFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
            add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
            clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
            about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));


            fileMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            return menuBar;
        }

        private JPanel infoUI() {
            JPanel info = new JPanel();
            info.setBackground(Color.PINK);
            info.setBorder(BorderFactory.createTitledBorder("Info"));
            info.setLayout(new GridLayout(8, 2));
            JLabel label = new JLabel("Student Name");
            label.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label);
            firstNameTxt = new JTextField(20);
            firstNameTxt.setBackground(new Color(216, 191, 216));
            firstNameTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(firstNameTxt);

            JLabel label_1 = new JLabel("Address");
            label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_1);
            addressTxt = new JTextField(20);
            addressTxt.setBackground(new Color(216, 191, 216));
            addressTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(addressTxt);

            JLabel label_2 = new JLabel("Phone");
            label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_2);
            phoneNo = new JTextField(20);
            phoneNo.setBackground(new Color(216, 191, 216));
            phoneNo.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(phoneNo);

            JLabel label_3 = new JLabel("Course");
            label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_3);
            selectCourse = new JComboBox(new String[]{});
            selectCourse.setBackground(new Color(216, 191, 216));
            selectCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(selectCourse);


            JLabel label_4 = new JLabel("Level");
            label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_4);
            level = new JComboBox(new String[]{"Level 4", "Level 5", "Level 6"});
            level.setBackground(new Color(216, 191, 216));
            level.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(level);

            return info;
        }

        //Button Panel for various options of buttons and for different events to happen
        private JPanel btnUI() {
            JPanel panel2 = new JPanel();
            panel2.setBackground(Color.PINK);
            panel2.setBorder(new EmptyBorder(100, 0, 0, 0));
            table = new JTable();
            tableModel = new DefaultTableModel();
            addBtn = new JButton("Register");
            addBtn.setBackground(new Color(216, 191, 216));
            addBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            clearBtn = new JButton("Clear");
            clearBtn.setBackground(new Color(216, 191, 216));
            clearBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            viewResult = new JButton("Check Result");
            viewResult.setBackground(new Color(216, 191, 216));
            viewResult.setFont(new Font("Tahoma", Font.BOLD, 15));

            panel2.add(clearBtn);
            panel2.add(addBtn);
            panel2.add(viewResult);

            //action listeners

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String firstName = firstNameTxt.getText().trim();
                    String address = addressTxt.getText().trim();
                    String course =  selectCourse.getSelectedItem().toString();
                    String lvl = level.getSelectedItem().toString();

                    try {

                        long number = Long.parseLong(phoneNo.getText());
                        if (firstNameTxt.getText().trim().isEmpty() || addressTxt.getText().trim().isEmpty()  || phoneNo.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(self, "Fields cannot be empty!!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(self, "Added successfully!!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            insert(firstName, address, number, course, lvl);
                        }

                        tableModel.setRowCount(0);
                        get2();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(self, "Invalid data.", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    clearBtn.doClick();
                }
            });
            clearBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (firstNameTxt.getText().isEmpty() && phoneNo.getText().isEmpty()){
                        JOptionPane.showMessageDialog(self, "Table is empty!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }else{
                        firstNameTxt.setText("");
                        addressTxt.setText("");
                        phoneNo.setText("");
                        selectCourse.setSelectedIndex(0);
                        level.setSelectedIndex(0);
                    }
                }
            });


            viewResult.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Result res= new Result();
                    res.show();
                    dispose();
                }
            });


            return panel2;
        }

        private JPanel dataPanel() {

            name = new JPanel();

            name.setBorder(BorderFactory.createTitledBorder("Name"));
            name.setLayout(new GridLayout(1, 0));

            table = new JTable();
            table.setFont(new Font("Tahoma", Font.BOLD, 15));
            table.setBackground(new Color(216, 191, 216));
            table.setDefaultEditor(Object.class, null);

            Object[] column = {"Student_Name", "Address", "Phone_Number", "Course", "Level"};

            Object[][] row = {

            };
            tableModel = new DefaultTableModel(row, column);
            table.setModel(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            	   
            name.add(scrollPane);
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = table.getSelectedRow();
                    firstNameTxt.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    addressTxt.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    phoneNo.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    selectCourse.setSelectedItem(tableModel.getValueAt(selectedRow,3).toString());
                    level.setSelectedItem(tableModel.getValueAt(selectedRow,4).toString());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            return name;
        }

        //main method
        public static void main(String[] args) {

            new Student(); //calling constructor.
        }
    }