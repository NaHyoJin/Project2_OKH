package singleton;

import bbs4ControllerServlet.BBSHWCodingController;
import bbs4ControllerServlet.BBSboardController;
import bbs4ControllerServlet.BBSmaterialsController;
import controller.PdsController;
import controller.TechbbsController;
import db.DBConnection;

//싱글톤 부분.
public class Singleton {//싱글톤 만들어주고.
	
	//싱글턴 부분.
	private static Singleton single = null;
	
	//싱글톤 계속 추가해 주면 된다. 180309
	public TechbbsController techCtrl;
	public PdsController pdsCtrl;
	
	
	
	//나효진 게시판 싱글톤 부분.
	public BBSHWCodingController BBSHWCodingCtrl;//H/W Coding 게시판 부분.
	public BBSboardController BBSboardCtrl;//나효진 일반 게시판 부분
	public BBSmaterialsController BBSmaterialsCtrl;//자료실 게시판 부분.
	
	private Singleton() {
		//싱글톤 부분 계속 추가해 주면 된다. 180309
		DBConnection.initConnection();
		techCtrl = new TechbbsController();
		pdsCtrl=new PdsController();
	}
	
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
	
}