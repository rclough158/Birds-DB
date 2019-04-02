import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class DefaultPage extends JFrame {

	static String speciesAttribute = "id_species";
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultPage frame = new DefaultPage();
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
	public DefaultPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.SwitchWindows(0);
	            setVisible(false);
				
			}
		});
		btnLogout.setBounds(5, 219, 87, 31);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnLogout);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//go to search window
				Main.SwitchWindows(2);
	            setVisible(false);
				
			}
		});
		btnSearch.setBounds(5, 101, 85, 31);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnSearch);
		
		JButton btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//only if LoggedIn = true
				//go to Add Data Window
				if(Main.loggedIn) {
					Main.SwitchWindows(4);
		            setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Unavailable without login");	
				}
				
			}
		});
		btnAddData.setBounds(5, 137, 105, 31);
		btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnAddData);
		
		JButton btnFavorites = new JButton("Favorites");
		btnFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Main.loggedIn) {
					Main.SwitchWindows(3);
		            setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Unavailable without login");	
				}
				
			}
		});
		btnFavorites.setBounds(5, 173, 103, 31);
		btnFavorites.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(btnFavorites);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] {"id_species", "COMMON_NAME", "MALE_COLOR",
						"FEMALE_COLOR", "PEAK_TIME", "MIGRATION", 
						"ENDANGERMENT", "TYPE"}));
		comboBox.setBounds(5, 25, 121, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String speciesAttr = "SPECIES_"+(String)comboBox.getSelectedItem();
				setAttribute(speciesAttr);
				displaySpecies();
				Main.SwitchWindows(1);
	            setVisible(false);
				
			}
		});
		contentPane.add(comboBox);
		
		DefaultTableModel tableModel = displaySpecies();
		
		
		JTable table = new JTable(tableModel);
		

		table.setBounds(136, 40, 838, 210);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(974, 40, -838, 210);
		contentPane.add(scrollPane);
		contentPane.add(table);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setBounds(5, 11, 46, 14);
		contentPane.add(lblSortBy);
		
		JButton btnSearchByStates = new JButton("Sort by states");
		btnSearchByStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SwitchWindows(6);
	            setVisible(false);
			}
		});
		btnSearchByStates.setBounds(5, 56, 121, 23);
		contentPane.add(btnSearchByStates);
		
		
		
		
	}



private DefaultTableModel displaySpecies() {
	String[] columns = {"ID", "Species Name", "Male Color", 
			"Female Color", "Peak Time", "Migration", 
			"Endangerment", "Type"};
	DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
	String[] header = {"ID", "Species Name", "Male Color", 
			"Female Color", "Peak Time", "Migration", 
			"Endangerment", "Type"};
	tableModel.addRow(header);
	
	String speciesAttr;
	if(speciesAttribute.equals("SPECIES_id_species")) {
		speciesAttr = "id_species";
	}
	else {
	speciesAttr = speciesAttribute;
	}
	
	 try{
		 	
		 
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/birdwatchers", Main.user, Main.passwd);
            Statement myStmt = (Statement) connect.createStatement();
            String query = "select * from bird_species order by " + speciesAttr;
            		
            ResultSet rs = myStmt.executeQuery(query);
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
            }
          
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Bypassed.");
        }
	 
	return tableModel;

	 }


public void setAttribute(String speciesAttr) {
	speciesAttribute = speciesAttr;
}
}