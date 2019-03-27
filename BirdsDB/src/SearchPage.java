import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSpeciesName;
	private JTextField txtActiveTime;
	private JTextField txtColor;
	private JTextField txtType;

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

	/**
	 * Create the frame.
	 */
	public SearchPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSpeciesName = new JTextField();
		txtSpeciesName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpeciesName.setText("Species Name");
		txtSpeciesName.setBounds(5, 39, 124, 20);
		contentPane.add(txtSpeciesName);
		txtSpeciesName.setColumns(10);
		
		txtActiveTime = new JTextField();
		txtActiveTime.setText("Active Time");
		txtActiveTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtActiveTime.setColumns(10);
		txtActiveTime.setBounds(5, 63, 124, 20);
		contentPane.add(txtActiveTime);
		
		txtColor = new JTextField();
		txtColor.setText("Color");
		txtColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtColor.setColumns(10);
		txtColor.setBounds(5, 87, 124, 20);
		contentPane.add(txtColor);
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtType.setColumns(10);
		txtType.setBounds(5, 111, 124, 20);
		contentPane.add(txtType);
		
		table = new JTable();
		table.setBounds(136, 40, 268, 161);
		contentPane.add(table);
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//return to login
				Main.SwitchWindows(0);
	            setVisible(false);
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(317, 5, 87, 31);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Execute Search
				
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(5, 135, 85, 31);
		contentPane.add(button_1);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//return to default page
				Main.SwitchWindows(1);
	            setVisible(false);
				
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReturn.setBounds(5, 170, 100, 31);
		contentPane.add(btnReturn);
		
		JButton btnClearFields = new JButton("Clear Fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//clears txtfields
				
			}
		});
		btnClearFields.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClearFields.setBounds(5, 5, 124, 31);
		contentPane.add(btnClearFields);
	}
}
