import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewLogin frame = new NewLogin();
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
	public NewLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("username");
		textField.setColumns(10);
		textField.setBounds(112, 15, 187, 39);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(112, 59, 187, 39);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Confirm Password");
		passwordField_1.setBounds(112, 103, 187, 39);
		contentPane.add(passwordField_1);
		
		JButton button = new JButton("Create New Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check if username is unique
				//check if passwordfields match
				
				JOptionPane.showMessageDialog(null, "New Account Created!");
				//or not, tell them
				
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		button.setBounds(140, 146, 131, 25);
		contentPane.add(button);
		
		JButton btnReturnToLogin = new JButton("Return to Login");
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//go back to login page
				
			}
		});
		btnReturnToLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnReturnToLogin.setBounds(140, 171, 131, 25);
		contentPane.add(btnReturnToLogin);
	}
}
