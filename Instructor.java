package College_Management_System;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

    public class Instructor extends JFrame {
        JTextField  module, student, marks;
        JButton btnAdd,btnClear, viewLeader, viewResult;
        JPanel name, panel;
        JTable table;
        DefaultTableModel model;
        Connection connection;
        Instructor self = this;

        public Instructor() {
            setVisible(true);
            setTitle("Instructor");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setLayout(new GridLayout(0, 2));
            panel = new JPanel();

            panel.setLayout(new GridLayout(1, 0));
            setSize(400, 500);
            setLocationRelativeTo(null);
            setJMenuBar(menu());
            panel.add(infoUI());
            getContentPane().add(panel);
            getContentPane().add(nameUI());
            pack();
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            get();
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
        private void insert(String mN, String sN, int marks){
            String query =  "INSERT INTO instructor (module_name, student_name, marks) VALUES (?, ?, ?)";
            try {
                PreparedStatement stm = connection.prepareStatement(query);//passing queries
                stm.setString(1, mN);
                stm.setString(2, sN);
                stm.setInt(3, marks);
                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        private void get(){
            String query = "SELECT * FROM instructor";
            try {
                PreparedStatement stm = connection.prepareStatement(query);
                ResultSet result = stm.executeQuery();
                while (result.next()){
                    model.addRow(new Object[]{
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
        private JMenuBar menu() {
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenu editMenu = new JMenu("Edit");
            JMenu helpMenu = new JMenu("Help");
            JMenuItem exitMenu = new JMenuItem("Exit");
            fileMenu.add(exitMenu);
            JMenuItem addMenu = new JMenuItem("Register");
            JMenuItem aboutMenu = new JMenuItem("About");
            helpMenu.add(aboutMenu);
            editMenu.add(addMenu);
            editMenu.addSeparator();
            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);
            fileMenu.setMnemonic(KeyEvent.VK_F);
            editMenu.setMnemonic(KeyEvent.VK_E);
            helpMenu.setMnemonic(KeyEvent.VK_H);
            exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
            exitMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);

                }
            });
            addMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
            addMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnAdd.doClick();
                }
            });

            aboutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));

            return menuBar;

        }


        private JPanel infoUI() {
            JPanel panel1 = new JPanel();

            panel1.setBorder(BorderFactory.createTitledBorder("Info"));
            panel1.setLayout(new GridLayout(5, 2));
            JLabel label = new JLabel("Module Name");
            label.setBackground(new Color(216, 191, 216));
            label.setFont(new Font("Tahoma", Font.BOLD, 15));
            panel1.add(label);
            module = new JTextField(20);
            module.setFont(new Font("Tahoma", Font.BOLD, 15));
            module.setBackground(new Color(216, 191, 216));
            panel1.add(module);
            JLabel label_1 = new JLabel("Student Name");
            label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
            label_1.setBackground(new Color(216, 191, 216));
            panel1.add(label_1);
            student= new JTextField(20);
            student.setFont(new Font("Tahoma", Font.BOLD, 15));
            student.setBackground(new Color(216, 191, 216));
            panel1.add(student);
            JLabel label_2 = new JLabel("Marks");
            label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
            label_2.setBackground(new Color(216, 191, 216));
            panel1.add(label_2);
            marks = new JTextField(20);
            marks.setFont(new Font("Tahoma", Font.BOLD, 15));
            marks.setBackground(new Color(216, 191, 216));
            panel1.add(marks);

            btnClear=new JButton("Clear");
            btnClear.setBackground(new Color(216, 191, 216));
            btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAdd = new JButton("Add");
            btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
            btnAdd.setBackground(new Color(216, 191, 216));
            viewLeader = new JButton("View Leader");
            viewLeader.setFont(new Font("Tahoma", Font.BOLD, 15));
            viewLeader.setBackground(new Color(216, 191, 216));
            viewResult= new JButton("Check Result");
            viewResult.setBackground(new Color(216, 191, 216));
            viewResult.setFont(new Font("Tahoma", Font.BOLD, 15));

            panel1.add(btnClear);
            panel1.add(btnAdd);
            panel1.add(viewLeader);
            panel1.add(viewResult);

            btnClear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    module.setText("");
                    student.setText("");
                    marks.setText("");
                    table.clearSelection();
                }
            });

            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String moN = module.getText().trim();
                    String sN = student.getText().trim();

                    try {
                        Integer m = Integer.parseInt(marks.getText());
                        if (module.getText().trim().isEmpty() || student.getText().trim().isEmpty() || marks.getText().trim().isEmpty() ) {
                            JOptionPane.showMessageDialog(self, "Fields cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        else {

                            JOptionPane.showMessageDialog(self, "Successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            insert(moN, sN, m);
                        }
                        model.setRowCount(0);
                        get();
                        File file = new File("viewResult.txt");
                        file.createNewFile();
                        FileWriter writer = new FileWriter("viewResult.txt",true);
                        writer.write(" " + moN +"\t"+ sN + "\t" + m);
                        writer.write("\n");
                        writer.write(System.getProperty("line.separator"));
                        writer.close();

                    } catch (NumberFormatException | IOException ex) {
                        JOptionPane.showMessageDialog(self, "Invalid Data!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    btnClear.doClick();
                }
            });


            viewLeader.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AssignedLeader leader= new AssignedLeader();
                    leader.show();
                    dispose();
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

            return panel1;
        }

        private JPanel nameUI() {

            name = new JPanel();

            name.setBorder(BorderFactory.createTitledBorder("Name"));
            name.setLayout(new GridLayout(1, 0));

            table = new JTable();
            table.setFont(new Font("Tahoma", Font.BOLD, 15));
            table.setBackground(Color.PINK);
            table.setDefaultEditor(Object.class, null);

            Object[] column = {"Course_Name", "Module_Name", "Marks"};

            Object[][] row = {

            };
            model = new DefaultTableModel(row, column);
            table.setModel(model);
            JScrollPane scrollPane = new JScrollPane(table);
            name.add(scrollPane);

            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = table.getSelectedRow();
                    module.setText(model.getValueAt(selectedRow, 0).toString());
                    student.setText(model.getValueAt(selectedRow, 1).toString());
                    marks.setText(model.getValueAt(selectedRow,2).toString());
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

        public static void main(String[] args) {
            new Instructor();//calling constructor
        }
    }


