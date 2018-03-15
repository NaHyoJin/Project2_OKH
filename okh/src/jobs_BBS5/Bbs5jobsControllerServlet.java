package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bbs5jobsControllerServlet extends HttpServlet {

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	
	//어떤 방식으로와도 처리하는 과정 //어떤 방법으로도 작동 되게끔.
	//인덱스 에서 처음 그냥 Bbs5jobsControllerServlet 곳으로 오는것 나중은 사용 안한다.
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		
		String command = req.getParameter("command");
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
//		resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWrite.jsp");
/*		
		if(command.equals("normalwrite")) {
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWrite.jsp");
		}
		*/
		
/*		//root경로
		String contextPath = req.getContextPath();
		System.out.println("contextPath : " + contextPath);
		//전체 주소.
		String reqURI = req.getRequestURI();
		//contextPath의 길이부터 끝까지.
		String realCommand = reqURI.substring(contextPath.length());
		
		//여기까지 잘 들어온다.
		System.out.println("Bbs5jobsControllerServlet doProcess realCommand : " + realCommand);

		//여기까지 잘 들어온다
		System.out.println("Bbs5jobsControllerServlet doProcess 으로 잘 들어오나 확인 코드");
		if("/jobs".equals(realCommand)) {
			resp.sendRedirect("../index.jsp");
		}else
*/
		//jobs 기본 화면이동 부분. HW 부분으로 가게 했다.
		resp.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");

/*		//forward 부분.
		String disp = "forwardTest";
		RequestDispatcher dispatch = req.getRequestDispatcher(disp);
		dispatch.forward(req, resp);//forward자료를 가지고 간다.
*/		
	}
}
