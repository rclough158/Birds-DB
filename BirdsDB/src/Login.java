import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private boolean loggedIn;
	private JButton btnCreateNewLogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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

	public Login() {
		loggedIn = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(110, 17, 187, 39);
		txtUsername.setText("username");
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setBounds(110, 61, 187, 39);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(172, 109, 63, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().length() ==0 /*&& passwordField.getPassword().length() == 0 */){
		            JOptionPane.showMessageDialog(null, "Continuing without login");
		            //switch to main window, with loggedIn = true
		        }
		        String userName = txtUsername.getText();
			char[] pWord = passwordField.getPassword();
			String pWordArr = String.copyValueOf(pWord);

		        if(CheckLogin(userName, pWordArr)){
		            JOptionPane.showMessageDialog(null, "Login Success!");
		            loggedIn = true;
		            //change to main window
		        } else {
		            JOptionPane.showMessageDialog(null, "Login unsuccessful.");
		        }
			}
		});
				
		
		
		btnLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(btnLogin);
		
		JButton btnContinueWithoutLogin = new JButton("Continue without login");
		btnContinueWithoutLogin.setBounds(126, 134, 155, 25);
		btnContinueWithoutLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Continuing without login");
				//Change to main window (loggedIn = false)
				
			}
		});
		btnContinueWithoutLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(btnContinueWithoutLogin);
		
		btnCreateNewLogin = new JButton("Create New Login");
		btnCreateNewLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//change to NewLogin
				
			}
		});
		btnCreateNewLogin.setBounds(138, 159, 131, 25);
		btnCreateNewLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPane.add(btnCreateNewLogin);
	}
	
	//This is where I access the database on MY MACHINE
	//The Connection and PreparedStatement will need to be edited, it will NOT function on other machines in its current state
	private boolean CheckLogin(String uName, String pWord){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatcheruser", "javaUser", "newerPassword");
            //"birdwatcheruser" is the database name, "javauser" is your 
            //mysql user (root is the base), "newerPassword" is your mysql password
            
            PreparedStatement state = connect.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            //"user" is my user table, with "username" and "password" as attributes
            state.setString(1, uName); //these replace the "?" in the prepared statement
            state.setString(2, pWord);
            ResultSet result = state.executeQuery(); //executes the prepared statement
            
            if(result.next()){
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
      
    }

}
