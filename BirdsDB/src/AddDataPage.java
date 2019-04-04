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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class AddDataPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtCommonName;
	private JButton button_3;
	private JButton button_4;
	private JComboBox comboBoxTime;
	private JLabel lblMaleColor;
	private JLabel lblFemaleColor;
	private JComboBox comboBoxType;
	private JComboBox comboBoxMigration;
	private JLabel lblBirdType;
	private JLabel lblMigration;
	private JComboBox comboBoxEndangerment;
	private JLabel lblEndangerment;

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
		txtCommonName.setBounds(26, 49, 174, 20);
		contentPane.add(txtCommonName);
		
		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(1);
	            setVisible(false);
				//back to defaultPage
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(26, 7, 100, 31);
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
		button_1.setBounds(304, 5, 100, 31);
		contentPane.add(button_1);
		
		button_3 = new JButton("Remove Data");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
			        Statement myStmt = (Statement) connect.createStatement();
			        String query = "DELETE FROM BIRD_SPECIES WHERE SPECIES_COMMON_NAME='"+txtCommonName.getText()+"';";
			        myStmt.executeUpdate(query);
			        JOptionPane.showMessageDialog(null, "Data removed successfully");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!");
					
				}
				
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(156, 212, 120, 31);
		contentPane.add(button_3);
		
		comboBoxTime = new JComboBox();
		comboBoxTime.setModel(new DefaultComboBoxModel(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		comboBoxTime.setToolTipText("");
		comboBoxTime.setBounds(90, 80, 85, 20);
		contentPane.add(comboBoxTime);
		
		JComboBox comboBoxMale = new JComboBox();
		comboBoxMale.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Blue (Light)", "Brown", "Gray", "Gray (Light)", "Green", "Green (Light)", "Light Peach", "Olive", "Orange", "Peach", "Pink", "Purple", "Red", "Rust", "Tan", "Violet", "White", "Yellow"}));
		comboBoxMale.setToolTipText("");
		comboBoxMale.setBounds(26, 128, 149, 20);
		contentPane.add(comboBoxMale);
		
		JComboBox comboBoxFemale = new JComboBox();
		comboBoxFemale.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Blue (Light)", "Brown", "Gray", "Gray (Light)", "Green", "Green (Light)", "Light Peach", "Olive", "Orange", "Peach", "Pink", "Purple", "Red", "Rust", "Tan", "Violet", "White", "Yellow"}));
		comboBoxFemale.setToolTipText("");
		comboBoxFemale.setBounds(26, 170, 149, 20);
		contentPane.add(comboBoxFemale);
		
		JButton btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
			        Statement myStmt = (Statement) connect.createStatement();
			        String query = " INSERT INTO" + 
			        		" BIRD_SPECIES(SPECIES_COMMON_NAME,"
			        		+ " SPECIES_MALE_COLOR,"
			        		+ " SPECIES_FEMALE_COLOR," 
			        		+ " SPECIES_PEAK_TIME,"
			        		+ " SPECIES_MIGRATION,"
			        		+ " SPECIES_TYPE,"
			        		+ " SPECIES_ENDANGERMENT)" 
			        		+ " VALUES" 
			        		+ "	('"+txtCommonName.getText()+"',"
			        		+ " '"+comboBoxMale.getSelectedItem()+"',"
			        		+ " '"+comboBoxFemale.getSelectedItem()+"',"
			        		+ " '"+comboBoxTime.getSelectedItem()+"',"
			        		+ " '"+comboBoxMigration.getSelectedItem()+"',"
			        		+ " '"+comboBoxType.getSelectedItem()+"',"
			        		+ " '"+comboBoxEndangerment.getSelectedItem()+"');";
			        		myStmt.executeUpdate(query);
			        		JOptionPane.showMessageDialog(null, "Data added successfully");
				
			        
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!");
					
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!");
					
				}
		         				
			}
		});
		btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddData.setBounds(26, 212, 120, 31);
		contentPane.add(btnAddData);
		
		JLabel lblPeakTime = new JLabel("Peak time:");
		lblPeakTime.setBounds(26, 83, 100, 14);
		contentPane.add(lblPeakTime);
		
		lblMaleColor = new JLabel("Male color:");
		lblMaleColor.setBounds(26, 112, 78, 14);
		contentPane.add(lblMaleColor);
		
		lblFemaleColor = new JLabel("Female color:");
		lblFemaleColor.setBounds(26, 156, 100, 14);
		contentPane.add(lblFemaleColor);
		
		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"Landbird", "Raptor", "Shorebird", "Waterbird", "Waterfowl"}));
		comboBoxType.setToolTipText("");
		comboBoxType.setBounds(230, 81, 174, 20);
		contentPane.add(comboBoxType);
		
		comboBoxMigration = new JComboBox();
		comboBoxMigration.setModel(new DefaultComboBoxModel(new String[] {"Permanent", "Long-distance", "Medium-distance", "Short-distance"}));
		comboBoxMigration.setBounds(230, 128, 174, 20);
		contentPane.add(comboBoxMigration);
		
		lblBirdType = new JLabel("Bird type:");
		lblBirdType.setBounds(230, 66, 58, 14);
		contentPane.add(lblBirdType);
		
		lblMigration = new JLabel("Migration:");
		lblMigration.setBounds(230, 112, 58, 14);
		contentPane.add(lblMigration);
		
		comboBoxEndangerment = new JComboBox();
		comboBoxEndangerment.setModel(new DefaultComboBoxModel(new String[] {"Least Concern", "Conservation Dependent", "Near Threatened", "Vulnerable", "Endangered", "Critically Endangered", "Extinct in the Wild", "Extinct"}));
		comboBoxEndangerment.setBounds(230, 170, 174, 20);
		contentPane.add(comboBoxEndangerment);
		
		lblEndangerment = new JLabel("Endangerment:");
		lblEndangerment.setBounds(230, 156, 85, 14);
		contentPane.add(lblEndangerment);
		
		button_4 = new JButton("Edit Data");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
			        Statement myStmt = (Statement) connect.createStatement();
			        String query = "UPDATE bird_species " +
			        	"SET SPECIES_MALE_COLOR = '"+comboBoxMale.getSelectedItem()+"', "
			        	+ "SPECIES_FEMALE_COLOR= '"+comboBoxFemale.getSelectedItem()+"', "
			        	+ "SPECIES_PEAK_TIME= '"+comboBoxTime.getSelectedItem()+"', "
			        	+ "SPECIES_MIGRATION= '"+comboBoxMigration.getSelectedItem()+"', "
			        	+ "SPECIES_ENDANGERMENT= '"+comboBoxEndangerment.getSelectedItem()+"', "
			        	+ "SPECIES_TYPE= '"+comboBoxType.getSelectedItem()+"' "
			        	+ "WHERE SPECIES_COMMON_NAME = '"+txtCommonName.getText()+"';";
			    
			        myStmt.executeUpdate(query);
			        JOptionPane.showMessageDialog(null, "Data edited successfully");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!");
					
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(284, 212, 120, 31);
		contentPane.add(button_4);
	}
}
