package College_Management_System;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

    public class Module extends JFrame {
        //reference variables
        JPanel name, panel1, panel2;
        JTextField addModuleId, addModuleName, addModuleLeader, addLevel;
        JButton cBtn, uBtn, rBtn, aBtn;
        JTable table;
        DefaultTableModel tableModel;
        Module self = this;
        Connection connection;

        //constructor
        public Module() {
            setVisible(true);
            setTitle("Module");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setLayout(new GridLayout(0, 2));
            panel1 = new JPanel();
            panel1.setBackground(new Color(216, 191, 216));
            setJMenuBar(getMenu());
            panel1.setLayout(new GridLayout(3, 0));
            setSize(500, 500);
            setLocationRelativeTo(null);
            panel1.add(infoUI());
            panel1.add(buttonPanel());
            getContentPane().add(dataUI());
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
        private void insert(int moduleId, String moduleName, String moduleLeader, int level) {
            String query = "INSERT INTO module (module_id, module_name, module_leader, level) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement stm = connection.prepareStatement(query);//passing queries
                stm.setInt(1, moduleId);
                stm.setString(2, moduleName);
                stm.setString(3, moduleLeader);
                stm.setInt(4, level);
                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //method of getting data in the JTable
        private void get() {
            String query = "SELECT * FROM module";
            try {
                PreparedStatement stm = connection.prepareStatement(query);
                ResultSet result = stm.executeQuery();
                while (result.next()) {
                    tableModel.addRow(new Object[]{
                            result.getInt("module_id"),
                            result.getString("module_name"),
                            result.getString("module_leader"),
                            result.getInt("level")
                    });
                }
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Method of updating data in the database and JTable
        private void update(Integer moduleId, String moduleName, String moduleLeader, Integer level) {
            String query = "UPDATE module SET module_id=?, module_name=?, module_leader=?, level=? WHERE module_id=?";

            try {
                PreparedStatement stm = connection.prepareStatement(query);
                stm.setInt(1, moduleId);
                stm.setString(2, moduleName);
                stm.setString(3, moduleLeader);
                stm.setInt(4, level);
                stm.setInt(5, moduleId);

                stm.executeUpdate();
                stm.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        //Method of deleting data from database and JTable
        private void delete(Integer mId) {
            String query = "DELETE FROM module WHERE module_id = ?";

            try {
                PreparedStatement stm = connection.prepareStatement(query);
                stm.setInt(1, mId);
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

            //action listeners

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
                    JOptionPane.showMessageDialog(self, "It is still in trial version!!");
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
        private JPanel infoUI() {
            JPanel info = new JPanel();
            info.setBackground(new Color(216, 191, 216));
            info.setBorder(BorderFactory.createTitledBorder("Information"));
            info.setLayout(new GridLayout(5, 0));

            JLabel label = new JLabel("Module Id");
            label.setBackground(Color.PINK);
            label.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label);
            addModuleId = new JTextField(20);
            addModuleId.setFont(new Font("Tahoma", Font.BOLD, 15));
            addModuleId.setBackground(Color.PINK);
            info.add(addModuleId);

            JLabel label_1 = new JLabel("Module Name");
            label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
            label_1.setBackground(Color.PINK);
            info.add(label_1);
            addModuleName = new JTextField(20);
            addModuleName.setFont(new Font("Tahoma", Font.BOLD, 15));
            addModuleName.setBackground(Color.PINK);
            info.add(addModuleName);

            JLabel label_2 = new JLabel("Module Leader");
            label_2.setBackground(Color.PINK);
            label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_2);
            addModuleLeader = new JTextField(20);
            addModuleLeader.setBackground(Color.PINK);
            addModuleLeader.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(addModuleLeader);

            JLabel label_3 = new JLabel("Level");
            label_3.setBackground(Color.PINK);
            label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
            info.add(label_3);
            addLevel = new JTextField(20);
            addLevel.setFont(new Font("Tahoma", Font.BOLD, 15));
            addLevel.setBackground(Color.PINK);
            info.add(addLevel);

            return info;
        }
        private JPanel buttonPanel() {
            panel2 = new JPanel();
            panel2.setBackground(new Color(216, 191, 216));
            table = new JTable();
            tableModel = new DefaultTableModel();

            panel2.setBorder(new EmptyBorder(100, 0, 0, 0));

            cBtn = new JButton("Clear");
            cBtn.setBackground(Color.PINK);
            cBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            aBtn = new JButton("Add");
            aBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            aBtn.setBackground(Color.PINK);
            uBtn = new JButton("Update");
            uBtn.setBackground(Color.PINK);
            uBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            rBtn = new JButton("Remove");
            rBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
            rBtn.setBackground(Color.PINK);

            panel2.add(cBtn);
            panel2.add(aBtn);
            panel2.add(uBtn);
            panel2.add(rBtn);

            //action listeners and happening of events

            cBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (addModuleId.getText().isEmpty() && addModuleName.getText().isEmpty() && addModuleLeader.getText().isEmpty() && addLevel.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(self, "No data to clear!!", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        addModuleId.setText("");
                        addModuleName.setText("");
                        addModuleLeader.setText("");
                        addLevel.setText("");
                    }
                }
            });

            aBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer moduleId = Integer.parseInt(addModuleId.getText());
                    String moduleName = addModuleName.getText().trim();
                    String moduleLeader = addModuleLeader.getText().trim();
                    Integer level = Integer.parseInt(addLevel.getText());
                    try {

                        if (addModuleId.getText().isEmpty() && addModuleName.getText().isEmpty() && addModuleLeader.getText().isEmpty() && addLevel.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(self, "Please, fill up all the fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(self, "Successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
                            insert(moduleId, moduleName, moduleLeader, level);
                        }
                        tableModel.setRowCount(0);
                        get();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(self, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    cBtn.doClick();
                }
            });

            uBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    Integer moduleId = Integer.parseInt(addModuleId.getText());
                    String moduleName = addModuleName.getText();
                    String moduleLeader = addModuleLeader.getText();
                    Integer level = Integer.parseInt(addLevel.getText());
                    update(moduleId, moduleName, moduleLeader, level);
                    cBtn.doClick();
                    tableModel.setRowCount(0);
                    get();
                    JOptionPane.showMessageDialog(self, "Successful!");
                }
            });

            rBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String row = JOptionPane.showInputDialog(name, "Enter row ",
                            "Question",
                            JOptionPane.QUESTION_MESSAGE);
                    int confirm = JOptionPane.showConfirmDialog(name, "Are you sure that you want to delete this row?"
                            , "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    try {
                        if (confirm == JOptionPane.YES_OPTION) {
                            delete(new Integer(row));
                            tableModel.setRowCount(0);
                            get();
                            JOptionPane.showMessageDialog(self, "Successful!!");
                        }
                        cBtn.doClick();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(name, "Invalid data",
                                "Error Processing", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(name, "Invalid number",
                                "Row doesn't exist", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            return panel2;
        }
        private JPanel dataUI() {

            name = new JPanel();

            name.setBorder(BorderFactory.createTitledBorder("Name"));
            name.setLayout(new GridLayout(1, 0));

            table = new JTable();
            table.setFont(new Font("Tahoma", Font.BOLD, 15));
            table.setBackground(new Color(216, 191, 216));
            table.setDefaultEditor(Object.class, null);

            Object[] column = {"Module_Id", "Module_Name", "Module_Leader", "Level"};

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
                    addModuleId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    addModuleName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    addModuleLeader.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    addLevel.setText(tableModel.getValueAt(selectedRow, 3).toString());
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

            new Module(); //calling constructor
        }

    }
