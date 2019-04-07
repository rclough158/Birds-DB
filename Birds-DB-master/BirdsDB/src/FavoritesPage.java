import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FavoritesPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSpeciesId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FavoritesPage frame = new FavoritesPage();
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
	public FavoritesPage() {
	
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//		TABLE TO DISPLAY BIRD FAVES		//
	    DefaultTableModel tableModel = displayFaves();
		
		JTable table = new JTable(tableModel);
		table.setBounds(136, 40, 268, 161);
		contentPane.add(table);
		
		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(1);
	            setVisible(false);
				//Return to default window
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(5, 170, 100, 31);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Logout");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(0);
	            setVisible(false);
				//return to login
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(317, 5, 87, 31);
		contentPane.add(button_1);
		
		txtSpeciesId = new JTextField();
		txtSpeciesId.setText("Species ID #");
		txtSpeciesId.setBounds(10, 37, 87, 20);
		contentPane.add(txtSpeciesId);
		txtSpeciesId.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String speciesCode = txtSpeciesId.getText();
				if(txtSpeciesId.getText().equals("Species ID #") | txtSpeciesId.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(null, "Please insert a valid species code. \n(Check species table to find what you are looking for.)");
				}
				else {
					insertFav(Main.userID, speciesCode);
				}
			}
		});
		btnAdd.setBounds(8, 68, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String speciesCode = txtSpeciesId.getText();
				if(txtSpeciesId.getText().equals("Species ID #") | txtSpeciesId.getText().length() == 0 ) {
					JOptionPane.showMessageDialog(null, "Please insert a species code already in your favorites.");
				}
				else {
					removeFav(Main.userID, speciesCode);
				}
			}
		});
		btnRemove.setBounds(8, 102, 89, 23);
		contentPane.add(btnRemove);
	}
	
	
	private void insertFav(int id, String code) {
		
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
	            Statement myStmt = connect.createStatement();
	            myStmt.executeUpdate("INSERT INTO Favorites(id_user, id_species)VALUES('"+id+"','"+code+"')");
				JOptionPane.showMessageDialog(null, "Success!");
				Main.SwitchWindows(3);
				setVisible(false);
	         
	        } catch(Exception e){
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Unable to insert species into favorites.");
				  }
		
	}
	private void removeFav(int id, String code) {
		
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
	            Statement myStmt = connect.createStatement();
	            myStmt.executeUpdate("DELETE FROM Favorites WHERE id_user = "+id+" AND id_species = "+code);
				JOptionPane.showMessageDialog(null, "Success!");
				Main.SwitchWindows(3);
				setVisible(false);
	           
	         
	        } catch(Exception e){
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Unable to remove species from favorites.");
				
	        }
		
	}
	
	
	private DefaultTableModel displayFaves() {
		String[] columns = {"ID", "Species Name"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
		  
		 try{
			 
			 
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
	            Statement myStmt = (Statement) connect.createStatement();
	            String query = "select * from bird_species where id_species IN "
	            		+ "(select id_species from favorites where id_user = "
	            		+ Main.userID + ")";
	            		
	            ResultSet rs = myStmt.executeQuery(query);
	            rs.beforeFirst();
	           while (rs.next()) {
	                String id_species = (String)rs.getString("id_species");
	                String species_common_name = (String)rs.getString("SPECIES_COMMON_NAME");
	              
	                String[] data = {id_species, species_common_name};
	             	tableModel.addRow(data);
	            }
	          
	        } catch(Exception e){
	            e.printStackTrace();
	            System.out.println("Bypassed.");
	        }
		 
		return tableModel;
	
		 }
	}

