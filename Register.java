package College_Management_System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField tfFirstName;
	private JTextField tfMiddleName;
	private JTextField tfLastName;
	private JTextField tfAddress;
	private JTextField tfUsername;
	private JPasswordField passwordField;
	private JButton btnRegister;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	Connection connection;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfFirstName.setForeground(new Color(230, 230, 250));
		tfFirstName.setBackground(new Color(128, 0, 0));
		tfFirstName.setBounds(310, 91, 177, 33);
		panel.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lfirstname = new JLabel("First Name:");
		lfirstname.setForeground(new Color(128, 0, 0));
		lfirstname.setFont(new Font("Tahoma", Font.BOLD, 20));
		lfirstname.setBounds(91, 90, 153, 33);
		panel.add(lfirstname);
		
		tfMiddleName = new JTextField();
		tfMiddleName.setForeground(new Color(230, 230, 250));
		tfMiddleName.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfMiddleName.setBackground(new Color(128, 0, 0));
		tfMiddleName.setColumns(10);
		tfMiddleName.setBounds(310, 152, 177, 33);
		panel.add(tfMiddleName);
		
		tfLastName = new JTextField();
		tfLastName.setForeground(new Color(230, 230, 250));
		tfLastName.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfLastName.setBackground(new Color(128, 0, 0));
		tfLastName.setColumns(10);
		tfLastName.setBounds(310, 214, 177, 33);
		panel.add(tfLastName);
		
		tfAddress = new JTextField();
		tfAddress.setForeground(new Color(230, 230, 250));
		tfAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfAddress.setBackground(new Color(128, 0, 0));
		tfAddress.setColumns(10);
		tfAddress.setBounds(310, 281, 177, 33);
		panel.add(tfAddress);
		
		tfUsername = new JTextField();
		tfUsername.setForeground(new Color(230, 230, 250));
		tfUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfUsername.setBackground(new Color(128, 0, 0));
		tfUsername.setColumns(10);
		tfUsername.setBounds(310, 342, 177, 33);
		panel.add(tfUsername);
		
		JLabel lmiddlename = new JLabel("Middle Name:");
		lmiddlename.setForeground(new Color(128, 0, 0));
		lmiddlename.setFont(new Font("Tahoma", Font.BOLD, 20));
		lmiddlename.setBounds(91, 152, 153, 33);
		panel.add(lmiddlename);
		
		JLabel llastname = new JLabel("Last Name:");
		llastname.setForeground(new Color(128, 0, 0));
		llastname.setFont(new Font("Tahoma", Font.BOLD, 20));
		llastname.setBounds(91, 214, 153, 33);
		panel.add(llastname);
		
		JLabel laddress = new JLabel("Address:");
		laddress.setForeground(new Color(128, 0, 0));
		laddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		laddress.setBounds(91, 281, 153, 33);
		panel.add(laddress);
		
		JLabel lusername = new JLabel("Username");
		lusername.setForeground(new Color(128, 0, 0));
		lusername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lusername.setBounds(91, 342, 153, 33);
		panel.add(lusername);
		
		JLabel tfPassword = new JLabel("Password");
		tfPassword.setForeground(new Color(139, 0, 0));
		tfPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfPassword.setBounds(91, 405, 153, 33);
		panel.add(tfPassword);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(230, 230, 250));
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordField.setBackground(new Color(128, 0, 0));
		passwordField.setBounds(310, 405, 177, 33);
		panel.add(passwordField);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String FirstName = tfFirstName.getText();
				String MiddleName = tfMiddleName.getText();
				String LastName = tfLastName.getText();
				String Address = tfAddress.getText();
				String Username = tfUsername.getText();
				String Password = String.valueOf(passwordField.getPassword());
				String Selected = comboBox.getSelectedItem().toString();
				
				if(FirstName.isEmpty()||MiddleName.isEmpty()||LastName.isEmpty()
						||Address.isEmpty()||Username.isEmpty()||Password.isEmpty()
						||Selected.equals("Select Item")) {
					JOptionPane.showMessageDialog(tfPassword, "Fill up the form properly", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					userRegister(FirstName,MiddleName,LastName,Address,Username,Password,Selected);
				}
			}

			private void userRegister(String firstName, String middleName, String lastName, String address,
					String username, String password, String selected) {
				String query =  "INSERT INTO user (first_name,middle_name,last_name,"
						+ "address, username, password, selected) VALUES (?, ?, ?, ?, ?, ?, ?)";
				try {
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/college_management_system", "root", "");
					PreparedStatement stm = connection.prepareStatement(query);
					stm.setString(1, firstName);
					stm.setString(2, middleName);
					stm.setString(3, lastName);
					stm.setString(4, address);
					stm.setString(5, username);
					stm.setString(6, password);
					stm.setString(7, selected);
					int rs = stm.executeUpdate();
					JOptionPane.showMessageDialog(tfPassword, "User data inserted", "Sucess", JOptionPane.INFORMATION_MESSAGE);
					
				}catch (SQLException throwables) {
	                throwables.printStackTrace();
	            }
			}
		});
		btnRegister.setForeground(new Color(230, 230, 250));
		btnRegister.setBackground(new Color(128, 0, 0));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegister.setBounds(310, 573, 177, 33);
		panel.add(btnRegister);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home h = new Home();
				h.setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(230, 230, 250));
		btnNewButton_1.setBackground(new Color(128, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(91, 573, 177, 33);
		panel.add(btnNewButton_1);
		
		lblNewLabel = new JLabel("R E G I S T E R");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(174, 10, 228, 47);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setForeground(new Color(230, 230, 250));
		comboBox.setBackground(new Color(128, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Item", "Student", "Instructor", "Admin"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setBounds(185, 480, 177, 33);
		panel.add(comboBox);
	}

}
