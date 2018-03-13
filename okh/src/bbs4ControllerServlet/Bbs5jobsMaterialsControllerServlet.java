package bbs4ControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelService.jobsBbs5ModelService;
import modelService.jobsBbs5ModelServiceImpl;

public class Bbs5jobsMaterialsControllerServlet extends HttpServlet {//자료실 게시판

	
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
		jobsBbs5ModelServiceImpl bbs4Service = new jobsBbs5ModelService();
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		//여기까지 잘 들어온다
		System.out.println("BBSmaterialsController으로 잘 들어오나 확인 코드");
		
		//자료실 게시판 화면이동 부분.
		resp.sendRedirect("Bbs4_communityViewJsp/bbs4Materials.jsp");
		
	}
	
}
