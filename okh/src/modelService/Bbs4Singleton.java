package modelService;

import bbs4ControllerServlet.BBSHWCodingController;
import bbs4ControllerServlet.BBSboardController;
import bbs4ControllerServlet.BBSmaterialsController;
import db.DBConnection;

//싱글톤 부분.
public class Bbs4Singleton {//싱글톤 만들어주고.
	
	//싱글턴 부분.
	private static Bbs4Singleton single = null;
	
	//싱글톤 계속 추가해 주면 된다. 180309

//	public MemberController memCtrl;//인간 회원 부분
	public BBSHWCodingController BBSHWCodingCtrl;//H/W Coding 게시판 부분.
	public BBSboardController BBSboardCtrl;//나효진 일반 게시판 부분
	public BBSmaterialsController BBSmaterialsCtrl;//자료실 게시판 부분.
	
	
	private Bbs4Singleton() {
		//싱글톤 부분 계속 추가해 주면 된다. 180309
		
		DBConnection.initConnection();
//		memCtrl = new MemberController();
		
		BBSHWCodingCtrl = new BBSHWCodingController();//H/W Coding 게시판 부분.
		BBSboardCtrl = new BBSboardController();//나효진 일반 게시판 부분
		BBSmaterialsCtrl = new BBSmaterialsController();//자료실 부분.
		
	}
	
	
	public static Bbs4Singleton getInstance() {
		if(single == null) {
			single = new Bbs4Singleton();
		}
		return single;
	}

}
