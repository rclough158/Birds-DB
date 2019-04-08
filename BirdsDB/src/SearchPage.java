import java.awt.BorderLayout;
import java.awt.EventQueue;

 import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

 import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

 public class SearchPage extends JFrame {

 	private JPanel contentPane;
	private JTable table;
	private JTextField txtCommonName;
	private JTextField txtActiveTime;
	private JTextField txtColor;
	private JTextField txtType;
	static private String command;

 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage frame = new SearchPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

 	private DefaultTableModel ExSearch(String command) {

 		String[] columns = {"ID", "Species Name", "Male Color", 
				"Female Color", "Peak Time", "Migration", 
				"Endangerment", "Type"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		String[] header = {"ID", "Species Name", "Male Color", 
				"Female Color", "Peak Time", "Migration", 
				"Endangerment", "Type"};
		tableModel.addRow(header);
		//System.out.println("This is my command" + command);
		
 		try{

             Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
            Statement myStmt = (Statement) connect.createStatement();

             ResultSet rs = myStmt.executeQuery(command);
            rs.beforeFirst();
           while (rs.next()) {
                String id_species = (String)rs.getString("id_species");
                String species_common_name = (String)rs.getString("SPECIES_COMMON_NAME");
                String species_male_color = (String)rs.getString("SPECIES_MALE_COLOR");
                String species_female_color = (String)rs.getString("SPECIES_FEMALE_COLOR");
                String species_peak_time = (String)rs.getString("SPECIES_PEAK_TIME");
                String species_migration = (String)rs.getString("SPECIES_MIGRATION");
                String species_endangerment = (String)rs.getString("SPECIES_ENDANGERMENT");
                String species_type = (String)rs.getString("SPECIES_TYPE");

                 String[] data = {id_species, species_common_name, species_male_color, 
                		species_female_color, species_peak_time, species_migration,
                		species_endangerment, species_type};
             	tableModel.addRow(data);

              //	System.out.println("id = " + id_species);
            }

 		}catch(Exception e){
                e.printStackTrace();
                System.out.println("Bypassed.");
            }
		return tableModel;
	}


 	/**
	 * Create the frame.
	 */

 	public SearchPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

 		txtCommonName = new JTextField();
		txtCommonName.setToolTipText("Common Name");
		txtCommonName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCommonName.setText("Common Name");
		txtCommonName.setBounds(5, 39, 124, 20);
		contentPane.add(txtCommonName);
		txtCommonName.setColumns(10);

 		txtActiveTime = new JTextField();
		txtActiveTime.setToolTipText("Active Time");
		txtActiveTime.setText("Active Time");
		txtActiveTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtActiveTime.setColumns(10);
		txtActiveTime.setBounds(5, 63, 124, 20);
		contentPane.add(txtActiveTime);

 		txtColor = new JTextField();
		txtColor.setToolTipText("Color");
		txtColor.setText("Color");
		txtColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtColor.setColumns(10);
		txtColor.setBounds(5, 87, 124, 20);
		contentPane.add(txtColor);

 		txtType = new JTextField();
		txtType.setToolTipText("Type");
		txtType.setText("Type");
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtType.setColumns(10);
		txtType.setBounds(5, 111, 124, 20);
		contentPane.add(txtType);

		DefaultTableModel tableModel = ExSearch(getCommand());
 		JTable table = new JTable(tableModel);
 		table.setBounds(136, 40, 838, 210);
		contentPane.add(table);

 		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

 				//return to login
				Main.SwitchWindows(0);
	            setVisible(false);

 			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(5, 219, 87, 31);
		contentPane.add(button);

 		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				String command = "SELECT * FROM bird_species WHERE";
				//Execute Search
				if(!txtCommonName.getText().equals("") && !txtCommonName.getText().equals("Common Name")) {
					count ++;
					command = command + (" species_common_name = \"" + txtCommonName.getText() + "\"");
				}
				if(!txtActiveTime.getText().equals("") && !txtActiveTime.getText().equals("Active Time")) {
					count ++;
					if(count > 1) {
						command = command + (" AND");
					}


 					command = command + (" species_peak_time = " + txtActiveTime.getText());
				}
				if(!txtColor.getText().equals("") && !txtColor.getText().equals("Color")) {
					count ++;
					if(count > 1) {
						command = command + (" AND");
					}


 					command = command + (" species_female_color = \"" + txtColor.getText() + "\""
					+ " OR species_male_color = \"" + txtColor.getText() + "\"");
				}
				if(!txtType.getText().equals("") && !txtType.getText().equals("Type")) {
					count ++;
					if(count > 1) {
						command = command + (" AND");
					}


 					command = command + (" species_type = \"" + txtType.getText() + "\"");
				} 
			
				setCommand(command);
 				//System.out.println(command + "\ncount = " + count);
				//System.out.println(!txtCommonName.getText().equals("Common Name"));
				if(count > 0) {
					//System.out.println("we made it to execute");
				}

				
				Main.SwitchWindows(2);
	            setVisible(false);
 			}
		});



 		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(5, 137, 85, 31);
		contentPane.add(button_1);

 		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

 				//return to default page
				Main.SwitchWindows(1);
	            setVisible(false);

 			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReturn.setBounds(5, 184, 85, 31);
		contentPane.add(btnReturn);

 		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCommonName.setText("Common Name");
				txtActiveTime.setText("Active Time");
				txtColor.setText("Color");
				txtType.setText("Type");

 			}
		});
		btnClearFields.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClearFields.setBounds(5, 5, 124, 31);
		contentPane.add(btnClearFields);
	}
 	
 	
 	private void setCommand(String newCommand) {
 		command = newCommand;
 	}
 	
 	private String getCommand() {
 		return command;
 	}
}
 