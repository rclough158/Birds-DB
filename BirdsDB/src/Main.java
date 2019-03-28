import java.awt.EventQueue;

public class Main {
	static Login frame;
	static DefaultPage defPage; // 1
	static SearchPage serPage; // 2
	static FavoritesPage favPage; // 3
	static AddDataPage addPage; // 4
	static NewLogin nLogPage;
	
	static Boolean loggedIn;
	static int userID;
	
	// IF YOUR MYSQL USERNAME AND PASSWORD ARE DIFFERENT, THIS IS THE ONLY PLACE YOU NEED TO CHANGE IT
	// If you have the birdwatchers database correctly set up, everything else should work
	static public String user = "root";
	static public String passwd = "newPassword";
	
	
	public static void main(String[] args) throws Exception {
		//MySQLAccess dao = new MySQLAccess();
	    //dao.readDataBase();
	    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login(); // 0
					frame.setVisible(true);
					
					defPage = new DefaultPage(); // 1
					serPage = new SearchPage(); // 2
					favPage = new FavoritesPage(); // 3
					addPage = new AddDataPage(); // 4
					nLogPage = new NewLogin(); // 5
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void SwitchWindows(int SwitchCode) {
		
		switch(SwitchCode) {
		case 0:
			loggedIn = false;
			userID = 0;
			frame.setVisible(true);
			break;
		case 1:
			defPage.setVisible(true);
			break;
		case 2:
			serPage.setVisible(true);
			break;
		case 3:
			favPage.setVisible(true);
			break;
		case 4:
			addPage.setVisible(true);
			break;
		case 5:
			nLogPage.setVisible(true);
			break;
			
		}
		
	}

}
