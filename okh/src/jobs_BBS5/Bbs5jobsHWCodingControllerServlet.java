package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bbs5jobsHWCodingControllerServlet extends HttpServlet {//공지사항 게시판 컨트롤

	
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
	
		//컨트롤러에서 각각 따로 만들어보자.
		jobsBbs5ModelServiceImpl bbs5Service = new jobsBbs5ModelService();
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		//root경로
		String contextPath = req.getContextPath();
		//전체 주소.
		String reqURI = req.getRequestURI();
		//contextPath의 길이부터 끝까지.
		String realCommand = reqURI.substring(contextPath.length());
		
		//여기까지 잘 들어온다.
		System.out.println("BBSHWCodingController doProcess realCommand : " + realCommand);

		//확인후 갈 곳 계속 추가하는 부분.
		if("/mainHW.BBSHWCodingController".equals(realCommand)) {
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
		} 
		//HW글쓰는 분기
		else if("/HwWrite.BBSHWCodingController".equals(realCommand)) {
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWrite.jsp");
		}
		
		//H/W 화면이동 부분.
//		resp.sendRedirect("Bbs4_communityViewJsp/bbs4HWCoding.jsp");
		
	}
}
