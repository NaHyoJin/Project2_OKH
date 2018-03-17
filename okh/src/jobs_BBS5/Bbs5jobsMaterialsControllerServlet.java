package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
	
		//싱글톤 생성 부분.
		jobsBbs5ModelServiceImpl service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...
		
		List<BbsMaterialsBeanDtoVO> materialsbbslist = null;
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");
		
		//받는부분. 넘어오는 command로 받는 변수.
		String command = req.getParameter("command");
		
		
		//root경로
//		String contextPath = req.getContextPath();
		//전체 주소.
//		String reqURI = req.getRequestURI();
		
		//contextPath의 길이부터 끝까지.
//		String realCommand = reqURI.substring(contextPath.length());
		
		//여기까지 잘 들어온다.
//		System.out.println("Bbs5jobsMaterialsControllerServlet doProcess realCommand : " + realCommand);

		
		
		
		
		
		
		/*
		//확인후 갈 곳 계속 추가하는 부분.
		//처음 화면 가는 부분.
		if("/mainPDS.BBSmaterialsController".equals(realCommand)) {
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4Materials.jsp");
		} 
		//자료실 올리기.
//		else if("/PdsWrite.BBSmaterialsController".equals(realCommand)) {
///			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5MaterialsWrite.jsp");
//		}
		else if(command.equals("detail")) {
			
				System.out.println(".................디테일 부분으로 넘어오는지 확인 코드.");
				
			}
		
		///PdsDetail.BBSmaterialsController?command=detail&seq=<%=pds.getSeq() %>
		else if("/PdsDetail.BBSmaterialsController".equals(realCommand)) {
			if(command.equals("detail")) {
				System.out.println("디테일 부분으로 넘어오는지 확인 코드.");
				
			}
			
			
		}
		*/
		
//		else if(command.equals("detail")) {
//			req.getParameter(arg0)
//			System.out.println("asdasdasd");

			
	
		}//////////////////////////doProcess
	
	
	//짐 가지고 가는 부분.
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		RequestDispatcher _dispatch = req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
			
}//////////////////////////////////////////////////////Bbs5jobsMaterialsControllerServlet
