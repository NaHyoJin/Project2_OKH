package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lifeBbs.LifeBbsDto;
import techbbs.TechbbsDto;
import user.UserDto;

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
		
		//싱글톤 생성 부분.
		jobsBbs5ModelServiceImpl service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...
		
		List<BbsBoardBeanDtoVO> bbslist = null;
		//root경로
//		String contextPath = req.getContextPath();
		//전체 주소.
//		String reqURI = req.getRequestURI();
		//contextPath의 길이부터 끝까지.
//		String realCommand = reqURI.substring(contextPath.length());
		
		//여기까지 잘 들어온다. 테스트용.
//		System.out.println("Bbs5jobsBoardControllerServlet doProcess realCommand : " + realCommand);

		String command = req.getParameter("command");
		
		System.out.println("받은 command : " + command);
		
//		여기에서 시퀀스 번호 받으면 맨처음 일반 게시판 가는 부분에ㅓ null 오류 생김 seq는 각각 조건에서 만들어줘라.
		
		
		if(command.equals("normalBbs")) {
				    

	    bbslist = service.getBbsNormalBeanDTOList();//전체글 가지고 오는건데...
	    
		//포장하는 부분.
		req.setAttribute("bbslist", bbslist);
		//짐 보내는 부분.
//		dispatch("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp", req, resp);
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
		}//////////////////////////////////////////////////////////////if normalBbs
		else if(command.equals("normalwrite")) {
			
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWrite.jsp");
		}
		else if(command.equals("normalbbswriteAf")) {
			String id = req.getParameter("id");//아이디
			String title = req.getParameter("title");//제목
			String content = req.getParameter("content");//내용
			String tag = req.getParameter("tag");//태그
			String filename = req.getParameter("filename");//파일 이름.
			if(filename.isEmpty()) {
				filename = "filename";//filename 강제로 넣어서 null 방지.
			}
			//실제 글 작성하는 부분.

			//잘 가지고 오나 확인 부분.
			System.out.println("id : " + id);
			System.out.println("title : " + title);
			System.out.println("content : " + content);
			
			//테스트 입력. 나중에 수정해야함. 일단 3개짜리 실험.
			BbsBoardBeanDtoVO dto = new BbsBoardBeanDtoVO(id, title, content, tag, filename);
			
			//포장하는 부분.
			req.setAttribute("normalbbswritedto", dto);
			//짐 보내는 부분.
			dispatch("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWriteAf.jsp", req, resp);
			
		}else if(command.equals("list")) {
			System.out.println("Bbs5jobsBoardControllerServlet doProcess command list");
			resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
		}else if(command.equals("detail")) {/////////////////////////디테일 부분.
			System.out.println("디테일 부분.");
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);//시퀀스
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsDetail.jsp?seq=" + seq);
		}
		else if(command.equals("update")) {
			System.out.println("업데이트 부분.");
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);//시퀀스
			resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsUpdate.jsp?seq=" + seq);
		}
		else if(command.equals("updateAf")) {
			System.out.println("업데이트 이후 부분. command.equals(\"updateAf\"");
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);//시퀀스		
			String id = req.getParameter("id");//아이디
			String title = req.getParameter("title");//제목
			String content = req.getParameter("content");//내용
			String tag = req.getParameter("tag");//태그
			String filename = req.getParameter("filename");//파일 이름.
			
			boolean b = service.updateBbs(new BbsBoardBeanDtoVO(seq, id, title, content, tag, filename));
//			BbsBoardBeanDtoVO dto = new BbsBoardBeanDtoVO(id, title, content, tag, filename);
 			//처리잘 된건가 확인 코드
			System.out.println("service.updateBbs(new BbsBoardBeanDtoVO(seq, id, title, content, tag, filename)); 처리 후에 잘 오는지 확인 부분.");
			System.out.println("boolean b : " + b);
			
			//포장하는 부분.
//			req.setAttribute("normalbbsupdatedto", dto);
			//짐 보내는 부분.
//			dispatch("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWriteAf.jsp", req, resp);
			if (b) {
				System.out.println("업데이트 성공.");
				//그냥 바로 가보자...
				resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
/*				
				List<BbsBoardBeanDtoVO> list = service.getBbsNormalBeanDTOList();
				req.setAttribute("bbslist", list);
				dispatch("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp", req, resp);
*/				
			}
			/*else {
				System.out.println("업데이트 실패");
				request.setAttribute("seq", seq);
				dispatch("techupdate.jsp", request, response);
			}*/
		}else if(command.equals("detele")) {//기본 게시판 삭제 부분.
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);//시퀀스
			boolean isS = service.deleteBbs(seq);
			
			if(isS) {
				resp.sendRedirect("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp");
			}
			
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
