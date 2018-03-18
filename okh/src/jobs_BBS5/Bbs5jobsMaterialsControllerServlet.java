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
	
		//싱글톤 생성 부분. 자료실 서비스 부분은 따로 만들었다.
		jobsBbs5MaterialsServiceImpl service = jobsBbs5MaterialsService.getInstance();//먼저 서비스를 불러야지...
		
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

		
		//자료실 게시판 메인 화면 부분.
		if(command.equals("MaterialsBbs")) {
		    

			materialsbbslist = service.getPdsList();//전체글 가지고 오는건데...
		    
			//포장하는 부분.
			req.setAttribute("materialsbbslist", materialsbbslist);
			//짐 보내는 부분.
//					dispatch("Bbs5_jobsViewJsp/bbs4NormalBbs.jsp", req, resp);
				resp.sendRedirect("Bbs5_jobsViewJsp/bbs4Materials.jsp");
			}//////////////////////////////////////////////////////////////if normalBbs
		
		//새 글 쓰기 부분.
			else if(command.equals("Materialswrite")) {
				
				resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5MaterialsWrite.jsp");
			}
		//글 작성 후 처리 부분. 실질적으로 글 작성되는 부분.
			else if(command.equals("MaterialswriteAf")) {
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
				System.out.println("tag : " + tag);
				System.out.println("filename : " + filename);
				
				//테스트 입력. 나중에 수정해야함. 일단 3개짜리 실험.
				BbsMaterialsBeanDtoVO dto = new BbsMaterialsBeanDtoVO(id, title, content, tag, filename);
				
				//포장하는 부분.
				req.setAttribute("Materialswritedto", dto);
				//짐 보내는 부분.
				dispatch("Bbs5_jobsViewJsp/jobs_bbs5MaterialsWriteAf.jsp", req, resp);
				
		//리스트 부분. 처음 화면 부분.
			}else if(command.equals("list")) {
				System.out.println("Bbs5jobsMaterialsControllerServlet doProcess command list");
				resp.sendRedirect("Bbs5_jobsViewJsp/bbs4Materials.jsp");
			}else if(command.equals("detail")) {/////////////////////////디테일 부분.
				System.out.println("디테일 부분.");
				String sseq = req.getParameter("seq");
				int seq = Integer.parseInt(sseq);//시퀀스
				resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5MaterialsDetail.jsp?seq=" + seq);
			}
			else if(command.equals("update")) {
				System.out.println("업데이트 부분.");
				String sseq = req.getParameter("seq");
				int seq = Integer.parseInt(sseq);//시퀀스
				resp.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5MaterialsUpdate.jsp?seq=" + seq);
			}
			else if(command.equals("updateAf")) {
				System.out.println("업데이트 이후 부분. command.equals(\"updateAf\" jobs_bbs5MaterialsUpdate");
				String sseq = req.getParameter("seq");
				int seq = Integer.parseInt(sseq);//시퀀스		
				String id = req.getParameter("id");//아이디
				String title = req.getParameter("title");//제목
				String content = req.getParameter("content");//내용
				String tag = req.getParameter("tag");//태그
				String filename = req.getParameter("filename");//파일 이름.
				
				boolean b = service.updateBbs(new BbsMaterialsBeanDtoVO(seq, id, title, content, tag, filename));
//						BbsBoardBeanDtoVO dto = new BbsBoardBeanDtoVO(id, title, content, tag, filename);
	 			//처리잘 된건가 확인 코드
				System.out.println("service.updateBbs(new BbsMaterialsBeanDtoVO(seq, id, title, content, tag, filename)); 처리 후에 잘 오는지 확인 부분.");
				System.out.println("boolean b : " + b);
				
				//포장하는 부분.
//						req.setAttribute("normalbbsupdatedto", dto);
				//짐 보내는 부분.
//						dispatch("Bbs5_jobsViewJsp/jobs_bbs5NormalBbsWriteAf.jsp", req, resp);
				if (b) {
					System.out.println("업데이트 성공.");
					//그냥 바로 가보자...
					resp.sendRedirect("Bbs5_jobsViewJsp/bbs4Materials.jsp");
			
				}
				/*else {
					System.out.println("업데이트 실패");
					request.setAttribute("seq", seq);
					dispatch("techupdate.jsp", request, response);
				}*/
			}else if(command.equals("detele")) {//자료실 삭제 부분.
				String sseq = req.getParameter("seq");
				int seq = Integer.parseInt(sseq);//시퀀스
				boolean isS = service.deleteMaterials(seq);
				
				if(isS) {
					resp.sendRedirect("Bbs5_jobsViewJsp/bbs4Materials.jsp");
				}
				
			}
		
		
		
		
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
