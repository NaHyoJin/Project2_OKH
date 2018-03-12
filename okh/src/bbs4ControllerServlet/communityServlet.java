package bbs4ControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class communityServlet extends HttpServlet {

	/* 
	넘어가는 종류.
	----------------jsp, html
	form action
	a
	
	----------------javascript
	location.href
	
	----------------java에서 이동하는것. 다른곳으로 넘어가는것.
	forward		가장 많이 사용한다.
	sendRedirect
	setheader
	*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	
	//어떤 방식으로와도 처리하는 과정 //어떤 방법으로도 작동 되게끔.
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		//여기까지 잘 들어온다
//		System.out.println("communityServlet으로 잘 들어오나 확인 코드");
		
		//커뮤니티 기본 화면이동 부분.
		resp.sendRedirect("Bbs4_communityViewJsp/communityMain.jsp");

/*		//forward 부분.
		String disp = "forwardTest";
		RequestDispatcher dispatch = req.getRequestDispatcher(disp);
		dispatch.forward(req, resp);//forward자료를 가지고 간다.
*/		
	}
}
