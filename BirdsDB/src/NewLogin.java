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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				String userName = textField.getText();
				char[] pWord = passwordField.getPassword();
				char[] pWordConfirm = passwordField_1.getPassword();
				String pWordArr = String.copyValueOf(pWord);
				String pWordConfArr = String.copyValueOf(pWordConfirm);
				
				
				
				//check if username is unique
				//check if passwordfields match
				
				if(CheckUN(userName) && CheckPW(pWordArr, pWordConfArr)) {
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
			            
			            
			            PreparedStatement state = connect.prepareStatement("INSERT INTO users (user_name, user_pass, admin_status) VALUES (?, ?, 0)");
			            
			            state.setString(1, userName); //these replace the "?" in the prepared statement
			            state.setString(2, pWordArr);
			            state.executeUpdate(); //executes the prepared statement
			            
						JOptionPane.showMessageDialog(null, "New Account Created!");
			            
			        } catch(Exception er){
			            er.printStackTrace();
			        }
					
					
				}
				
				//or not, tell them
				
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 12));
		button.setBounds(140, 146, 131, 25);
		contentPane.add(button);
		
		JButton btnReturnToLogin = new JButton("Return to Login");
		btnReturnToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(0);
	            setVisible(false);
				//go back to login page
				
			}
		});
		btnReturnToLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnReturnToLogin.setBounds(140, 171, 131, 25);
		contentPane.add(btnReturnToLogin);
	}
	
	private boolean CheckUN(String uName){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
            //"birdwatcheruser" is the database name, "javauser" is your 
            //mysql user (root is the base), "newerPassword" is your mysql password
            
            PreparedStatement state = connect.prepareStatement("SELECT * FROM users WHERE user_name=?");
            //"user" is my user table, with "username" and "password" as attributes
            state.setString(1, uName); //these replace the "?" in the prepared statement
            ResultSet result = state.executeQuery(); //executes the prepared statement
            
            if(result.next()){
            	JOptionPane.showMessageDialog(null, "Username not Unique");
                return false; //found a match (not unique)
            } else {
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
      
    }
	
	private boolean CheckPW(String pWord, String pWordConf){
		
		if(pWord.equals(pWordConf)) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Passwords Do Not Match");
			return false;
		}
		
      
    }
	
}
