import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;

public class DefaultPage extends JFrame {

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
		setBounds(100, 100, 450, 300);
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
		btnLogout.setBounds(317, 5, 87, 31);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnSearch);
		
		JButton btnAddData = new JButton("Add Data");
		btnAddData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//only if LoggedIn = true
				//go to Add Data Window
				Main.SwitchWindows(4);
	            setVisible(false);
				
				
				//else
				JOptionPane.showMessageDialog(null, "Unavailable without login");
				
			}
		});
		btnAddData.setBounds(5, 137, 105, 31);
		btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnAddData);
		
		JButton btnFavorites = new JButton("Favorites");
		btnFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//only if LoggedIn = true
				//Go to favorites window
				Main.SwitchWindows(3);
	            setVisible(false);
				
				//else
				JOptionPane.showMessageDialog(null, "Unavailable without login");
				
			}
		});
		btnFavorites.setBounds(5, 173, 103, 31);
		btnFavorites.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnFavorites);
		
		table = new JTable();
		table.setBounds(136, 40, 268, 161);
		contentPane.add(table);
		
		Label label = new Label("Bird Watchers");
		label.setBounds(5, 0, 194, 41);
		contentPane.add(label);
	}
}
