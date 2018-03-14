package singleton;

import db.DBConnection;
import jobs_BBS5.Bbs5jobsBoardControllerServlet;
import jobs_BBS5.Bbs5jobsHWCodingControllerServlet;
import jobs_BBS5.Bbs5jobsMaterialsControllerServlet;

//싱글톤 부분.
public class Singleton {//싱글톤 만들어주고.
	
	//싱글턴 부분.
	private static Singleton single = null;
	
	//싱글톤 계속 추가해 주면 된다. 180309
	public techbbs.TechbbsController techCtrl;
	public techpds.PdsController pdsCtrl;
	
	
	
	//나효진 게시판 싱글톤 부분.
	public Bbs5jobsHWCodingControllerServlet BBSHWCodingCtrl;//H/W Coding 게시판 부분.
	public Bbs5jobsBoardControllerServlet BBSboardCtrl;//나효진 일반 게시판 부분
	public Bbs5jobsMaterialsControllerServlet BBSmaterialsCtrl;//자료실 게시판 부분.
	
	private Singleton() {
		//싱글톤 부분 계속 추가해 주면 된다. 180309
		DBConnection.initConnection();
		techCtrl = new techbbs.TechbbsController();
		pdsCtrl = new techpds.PdsController();
		
		BBSHWCodingCtrl = new Bbs5jobsHWCodingControllerServlet();
		BBSboardCtrl = new Bbs5jobsBoardControllerServlet();
		BBSmaterialsCtrl = new Bbs5jobsMaterialsControllerServlet();
	}


	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
	
}