package studysrc;

import db.DBConnection;

public class Singleton {
	private static Singleton single = null;
	public CommunityControl commCtrl;
	
	private Singleton() {
		DBConnection.initConnection();
		commCtrl = new CommunityControl();
	}
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
	
}
