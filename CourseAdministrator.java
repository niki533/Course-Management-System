package College_Management_System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

    public class CourseAdministrator extends JFrame {

        //reference variables
        JPanel name, panel1, panel2;
        JTextField addCourseTxt, addCourseId;
        JButton cBtn, uBtn, rBtn, aBtn,modBtn;
        JTable table;
        DefaultTableModel model;
        CourseAdministrator self = this;
        Connection connection;

        //constructor
        public CourseAdministrator() {
            setVisible(true);
            setTitle("Administrator");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setLayout(new GridLayout(0, 2));
            panel1 = new JPanel();
            setJMenuBar(getMenu());

            panel1.setLayout(new GridLayout(2, 0));
            setSize(750, 500);
            setLocationRelativeTo(null);

            panel1.add(infoPanel());
            panel1.add(buttonPanel());

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
        //method of inserting data into database
        private void insert(int cId, String cN){
            String query =  "INSERT INTO courseAdministrator (course_id, course_name) VALUES (?, ?)";
            try {
                PreparedStatement stm = connection.prepareStatement(query);
                stm.setInt(1, cId);
                stm.setString(2, cN);
                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //method getting data in the JTable
        private void get(){
            String query = "SELECT * FROM courseAdministrator";
            try {
                PreparedStatement stm = connection.prepareStatement(query);
                ResultSet result = stm.executeQuery();
                while (result.next()){
                    model.addRow(new Object[]{
                            result.getInt("course_id"),
                            result.getString("course_name")
                    });
                }
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //Method of updating data in the database and JTable
        private void update(Integer cId, String mN){
            String query = "UPDATE courseAdministrator SET course_id = ?, course_name=? WHERE course_id = ?";

            try {
                PreparedStatement stm = connection.prepareStatement(query);
                stm.setInt(1, cId);
                stm.setString(2, mN);
                stm.setInt(3, cId);

                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Method of deleting data from database and JTable
        private void delete(String cN) {
            String query = "DELETE FROM courseAdministrator WHERE course_id = ?";

            try {
                PreparedStatement stm = connection.prepareStatement(query);
                stm.setString(1, cN);
                stm.executeUpdate();
                stm.close();
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
            JMenuItem update = new JMenuItem("Update");
            JMenuItem add = new JMenuItem("Add");
            JMenuItem remove = new JMenuItem("Remove");

            editMenu.add(clear);
            editMenu.addSeparator();
            editMenu.add(update);
            editMenu.addSeparator();
            editMenu.add(add);
            editMenu.addSeparator();
            editMenu.add(remove);

            JMenu helpMenu = new JMenu("Help");
            JMenuItem about = new JMenuItem("About");
            helpMenu.add(about);

            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);

            //action listeners and happening of different events

            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    aBtn.doClick();
                }
            });

            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    uBtn.doClick();
                }
            });
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cBtn.doClick();
                }
            });

            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rBtn.doClick();
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

            //mnemonics (Shortcut keys) for different events
            fileMenu.setMnemonic(KeyEvent.VK_F);
            editMenu.setMnemonic(KeyEvent.VK_E);
            helpMenu.setMnemonic(KeyEvent.VK_H);
            subFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
            add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
            update.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
            remove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
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

        //Info Panel for taking data from users
        private JPanel infoPanel() {
            JPanel info = new JPanel();
            info.setBorder(BorderFactory.createTitledBorder("Info"));
            info.setLayout(new GridLayout(2, 2));
            JLabel label = new JLabel("Course Id");
            label.setBackground(Color.PINK);
            label.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label);
            addCourseId = new JTextField(20);
            addCourseId.setFont(new Font("Tahoma", Font.BOLD, 15));
            addCourseId.setBackground(Color.PINK);
            info.add(addCourseId);
            JLabel label_1 = new JLabel("Course Name");
            label_1.setBackground(Color.PINK);
            label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_1);
            addCourseTxt = new JTextField(20);
            addCourseTxt.setBackground(Color.PINK);
            addCourseTxt.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(addCourseTxt);
            return info;
        }

        //Button Panel for various options of buttons and for different events to happen when clicked
        private JPanel buttonPanel() {
            panel2 = new JPanel();
            panel2.setBackground(new Color(216, 191, 216));
            table = new JTable();
            model = new DefaultTableModel();

            panel2.setBorder(new EmptyBorder(80, 10, 0, 10));
            cBtn = new JButton("Clear");
            cBtn.setBackground(Color.PINK);
            cBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            uBtn = new JButton("Update");
            uBtn.setBackground(Color.PINK);
            uBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            aBtn = new JButton("Add");
            aBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            aBtn.setBackground(Color.PINK);
            rBtn = new JButton("Remove");
            rBtn.setBackground(Color.PINK);
            rBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            modBtn = new JButton("Add Module -->");
            modBtn.setBackground(Color.PINK);
            modBtn.setFont(new Font("Tahoma", Font.BOLD, 15));

            panel2.add(cBtn);
            panel2.add(uBtn);
            panel2.add(aBtn);
            panel2.add(rBtn);
            panel2.add(new JLabel());
            panel2.add(modBtn);

            //action listeners and happening of events

            cBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (addCourseTxt.getText().isEmpty() && addCourseId.getText().isEmpty()){
                        JOptionPane.showMessageDialog(self, "Empty", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        addCourseTxt.setText("");
                        addCourseId.setText("");
                    }
                }
            });

            uBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    Integer cId = Integer.parseInt(addCourseId.getText());
                    String cN = addCourseTxt.getText();
                    update(cId, cN);
                    model.setRowCount(0);
                    get();
                    JOptionPane.showMessageDialog(self, "Data updated Successfully!");
                    cBtn.doClick();
                }
            });

            //action listeners and happening of events
            aBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer courseId = Integer.parseInt(addCourseId.getText());
                    String courseName = addCourseTxt.getText().trim();
                    try {

                        if (addCourseTxt.getText().trim().isEmpty() || addCourseId.getText().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(self, "Fields cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(self, "Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                            insert(courseId, courseName);
                        }
                        model.setRowCount(0);
                        get();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(self, "Invalid data.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    cBtn.doClick();
                }
            });

            rBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String row = JOptionPane.showInputDialog(name, "Enter row: ",
                            "Question",
                            JOptionPane.QUESTION_MESSAGE);
                    int confirm = JOptionPane.showConfirmDialog(name, "Are you sure that you want to delete this row?"
                            , "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    try {
                        if (confirm == JOptionPane.YES_OPTION) {
                            delete(new String(row));
                            model.setRowCount(0);
                            get();
                            JOptionPane.showMessageDialog(self, "Removed Successfully!!");
                            cBtn.doClick();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(name, "Invalid data",
                                "Error Processing", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(name, "Invalid row!!",
                                "Row doesn't exist", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            modBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Module mod = new Module();
                    mod.show();
                    dispose();
                }
            });
            return panel2;
        }

        //Data Panel (JTable) for storing data of the users
        private JPanel dataPanel() {

            name = new JPanel();
            name.setBackground(new Color(216, 191, 216));

            name.setBorder(BorderFactory.createTitledBorder("Name"));
            name.setLayout(new GridLayout(1, 0));

            table = new JTable();
            table.setFont(new Font("Tahoma", Font.BOLD, 15));
            table.setBackground(new Color(216, 191, 216));
            table.setDefaultEditor(Object.class, null);

            Object[] column = {"Course_Id", "Course_Name"};

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
                    addCourseId.setText(model.getValueAt(selectedRow, 0).toString());
                    addCourseTxt.setText(model.getValueAt(selectedRow, 1).toString());
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

            new CourseAdministrator(); //calling constructor
        }

    }
