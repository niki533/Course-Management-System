package College_Management_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JSeparator separator_1;
	private JSeparator separator;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	Connection connection;
	public String niki;
	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 767, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(0, 0, 366, 434);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\icon\\college-15-128.png"));
		lblNewLabel_2.setBounds(124, 71, 135, 192);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("L E A R N");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_3.setBounds(111, 278, 148, 40);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(216, 191, 216));
		panel_1.setBounds(365, 0, 388, 434);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtUsername.setText("Username");
		txtUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUsername.setBackground(new Color(216, 191, 216));
		txtUsername.setBounds(159, 115, 179, 35);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		passwordField.setForeground(new Color(0, 0, 0));
		passwordField.setToolTipText("");
		passwordField.setBackground(new Color(216, 191, 216));
		passwordField.setBounds(159, 172, 179, 35);
		panel_1.add(passwordField);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(159, 217, 179, 2);
		panel_1.add(separator_1);
		
		separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(159, 160, 179, 2);
		panel_1.add(separator);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\icon\\1005249-200_1_45x35.png"));
		lblNewLabel.setBounds(82, 119, 45, 35);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\icon\\password-76-48_45x35.png"));
		lblNewLabel_1.setBounds(82, 184, 45, 35);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
	                String Username = txtUsername.getText();
					String Password = String.valueOf(passwordField.getPassword());
					String Selected = comboBox.getSelectedItem().toString();
					if(Username.isEmpty()|| Password.isEmpty()) {
						JOptionPane.showMessageDialog(btnNewButton, "Username/Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						userlogin(Username,Password,Selected);
					}
	            } catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
			}

			private void userlogin(String username, String password, String selected) {
				String query =  "SELECT * FROM user WHERE username = ? AND password = ? AND selected = ?";
		
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
					PreparedStatement stm = connection.prepareStatement(query);
					stm.setString(1, username);
					stm.setString(2, password);
					stm.setString(3, selected);
					ResultSet rs = stm.executeQuery();
					if(rs.next()) {
						if(rs.getString("selected").equals("Student")) {
							dispose();
							Student st = new Student();
							st.show();
						}
						else if(rs.getString("selected").equals("Instructor")) {
							dispose();
							Instructor ins = new Instructor();
							ins.show();
						}
						else if(rs.getString("selected").equals("Admin")) {
							dispose();
							CourseAdministrator admin = new CourseAdministrator();
		                    admin.show();
						}
					}	
					else {
						JOptionPane.showMessageDialog(btnNewButton, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
					 
					
				}catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
			}
			
		});
		btnNewButton.setBackground(new Color(216, 191, 216));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(219, 347, 119, 40);
		panel_1.add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Register r = new Register();
				r.setTitle("Register a user");
				r.setVisible(true);
			}
		});
		btnSignUp.setBackground(new Color(216, 191, 216));
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSignUp.setBounds(82, 347, 119, 40);
		panel_1.add(btnSignUp);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Item", "Student", "Instructor", "Admin"}));
		comboBox.setBackground(new Color(216, 191, 216));
		comboBox.setBounds(169, 262, 169, 35);
		panel_1.add(comboBox);
	}
}
