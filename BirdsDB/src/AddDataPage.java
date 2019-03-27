import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDataPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtCommonName;
	private JTextField txtMigration;
	private JTextField txtMalecolor;
	private JTextField txtEndangered;
	private JTextField txtSpeciesName;
	private JTextField txtActiveTime;
	private JTextField txtFemaleColor;
	private JTextField txtType;
	private JTextField txtState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDataPage frame = new AddDataPage();
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
	public AddDataPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCommonName = new JTextField();
		txtCommonName.setText("Common Name");
		txtCommonName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCommonName.setColumns(10);
		txtCommonName.setBounds(219, 42, 185, 20);
		contentPane.add(txtCommonName);
		
		txtMigration = new JTextField();
		txtMigration.setText("Migration Time");
		txtMigration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMigration.setColumns(10);
		txtMigration.setBounds(219, 66, 185, 20);
		contentPane.add(txtMigration);
		
		txtMalecolor = new JTextField();
		txtMalecolor.setText("Male Color");
		txtMalecolor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMalecolor.setColumns(10);
		txtMalecolor.setBounds(219, 90, 185, 20);
		contentPane.add(txtMalecolor);
		
		txtEndangered = new JTextField();
		txtEndangered.setText("Endangered Level");
		txtEndangered.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEndangered.setColumns(10);
		txtEndangered.setBounds(219, 114, 185, 20);
		contentPane.add(txtEndangered);
		
		txtSpeciesName = new JTextField();
		txtSpeciesName.setText("Species Name");
		txtSpeciesName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpeciesName.setColumns(10);
		txtSpeciesName.setBounds(26, 42, 185, 20);
		contentPane.add(txtSpeciesName);
		
		txtActiveTime = new JTextField();
		txtActiveTime.setText("Active Time");
		txtActiveTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtActiveTime.setColumns(10);
		txtActiveTime.setBounds(26, 66, 185, 20);
		contentPane.add(txtActiveTime);
		
		txtFemaleColor = new JTextField();
		txtFemaleColor.setText("Female Color");
		txtFemaleColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFemaleColor.setColumns(10);
		txtFemaleColor.setBounds(26, 90, 185, 20);
		contentPane.add(txtFemaleColor);
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtType.setColumns(10);
		txtType.setBounds(26, 114, 185, 20);
		contentPane.add(txtType);
		
		txtState = new JTextField();
		txtState.setText("State");
		txtState.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtState.setColumns(10);
		txtState.setBounds(26, 138, 185, 20);
		contentPane.add(txtState);
		
		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(1);
	            setVisible(false);
				//back to defaultPage
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(26, 170, 100, 31);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(0);
	            setVisible(false);
				//Back to Login
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(317, 5, 87, 31);
		contentPane.add(button_1);
		
		JButton btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Check for values that aren't allowed to be null
				JOptionPane.showMessageDialog(null, "Data added successfully");
				
				
			}
		});
		btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddData.setBounds(284, 170, 120, 31);
		contentPane.add(btnAddData);
		
		JButton button_2 = new JButton("Clear Fields");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Clears all fields
				
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBounds(26, 5, 124, 31);
		contentPane.add(button_2);
	}

}
