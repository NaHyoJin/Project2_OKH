package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿이 컨트롤러다.
public class Bbs5jobsBoardControllerServlet extends HttpServlet {//일반 게시판 컨트롤
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	
	//어떤 방식으로와도 처리하는 과정 //어떤 방법으로도 작동 되게끔.
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		resp.setContentType("text/html; charset=utf-8");
//		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		//root경로
//		String contextPath = req.getContextPath();
		//전체 주소.
//		String reqURI = req.getRequestURI();
		//contextPath의 길이부터 끝까지.
//		String realCommand = reqURI.substring(contextPath.length());
		
		//여기까지 잘 들어온다.
//		System.out.println("Bbs5jobsBoardControllerServlet doProcess realCommand : " + realCommand);

//		normalBbs
		String command = req.getParameter("command");
		
		System.out.println("받은 command : " + command);
		
		if(command.equals("normalBbs")) {
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
		}
		else if(command.equals("normalwrite")) {
			
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWrite.jsp");
		}
		else if(command.equals("normalbbswriteAf")) {
			//실제 글 작성하는 부분.
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
		
			String fileload;
			//잘 가지고 오나 확인 부분.
			System.out.println("id : " + id);
			System.out.println("title : " + title);
			System.out.println("content : " + content);
			
			//테스트 입력. 나중에 수정해야함. 일단 3개짜리 실험.
			BbsBoardBeanDtoVO dto = new BbsBoardBeanDtoVO(id, title, content, "tag", "filename");
			
			//포장하는 부분.
			req.setAttribute("normalbbswritedto", dto);
			//짐 보내는 부분.
			dispatch("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWriteAf.jsp", req, resp);
		}else if(command.equals("list")) {
			System.out.println("Bbs5jobsBoardControllerServlet doProcess command list");
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
		}
		
		//확인 후 갈 곳 계속 추가하는 부분. 하다가 망한 코드.
		//처음 화면 가는 부분.
/*		if("/mainNormalBbs.BBSboardController".equals(realCommand)) {
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
		} 
		//normal 글쓰는 분기
		else if("/NormalWrite.BBSboardController".equals(realCommand)) {
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWrite.jsp");
		}
*/		
		//일반 게시판 화면이동 부분.
//		resp.sendRedirect("Bbs4_communityViewJsp/bbs4HWCoding.jsp");
	}
	
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		RequestDispatcher _dispatch = req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
}
