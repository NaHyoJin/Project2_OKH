package jobs_BBS5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.swing.JOptionPane;
import javax.xml.ws.Dispatch;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import user.UserDao;

public class newbbs5HWCodingControllerServlet extends HttpServlet {
	
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
	
	//모든것 다 처리하기 위한 메소드.
	public void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//인코딩 관련
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		int parent = 0;
		String command = request.getParameter("command");
		String command1=(String)request.getAttribute("command");
		
		newbbs5HWCodingServiceImpl tservice = newbbs5HWCodingService.getInstance();
		
		
		if(command.equals("techbbs")) {
			
			List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
			//포장하는 부분.
			request.setAttribute("techbbs", list);
			dispatch("Bbs5_jobsViewJsp/bbs4HWCoding.jsp", request, response);
			
			//메인 화면으로 가는것.
		}else if(command.equals("main")) {
			System.out.println("쓰기후 main 으로 들어왔나?");
			response.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
		}		
		else if(command.equals("techbbs1")) {
			response.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWrite.jsp");
		}
		//글 작성 폼 부분. write
		else if(command.equals("techwrite")) {
			response.sendRedirect("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWrite.jsp");
			System.out.println("글 작성 까지 잘 들어오나? 수정중.");
			
		//실제 글 작성 부분.
		}else if(command.equals("techwriteAf")) {
			String tagname = "HW & Coding";
			String[] tagnames = request.getParameterValues("tagnames");	//span태그안의value값다받아오기
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(title+"여기가 문제였군"+content);
			
			//제목 내용 공백일 경우.
			if (title == "" || content == "") {
				System.out.println("여기가 문제였군2");
				newbbs5HWCodingVO dto1 = null;
				//포장하는 부분.
				request.setAttribute("return1", dto1);
				dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWriteAf.jsp", request, response);
			}
			
			if(tagnames == null) {
				newbbs5HWCodingVO dto = new newbbs5HWCodingVO(id, title, tagname, content);
				request.setAttribute("techwritedto", dto);
				dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWriteAf.jsp", request, response);
			}else {
			for(int i = 0; i<tagnames.length; i++){
				tagname+=tagnames[i]+"-";
			}
			
			tagname = tagname.substring(0, tagname.lastIndexOf("-"));
			newbbs5HWCodingVO dto = new newbbs5HWCodingVO(id, title, tagname, content);
			request.setAttribute("techwritedto", dto);
			dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingWriteAf.jsp", request, response);
			}
			
		//디테일 부분.
		}else if(command.equals("techdetail")) {
			
			HWLikeScrapServiceImpl lsservice = HWLikeScrapService.getInstance();
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			tservice.readcountplus(seq);
			String likeid = request.getParameter("likeid");
			
			//확인 코드
			System.out.println("likeid : " + likeid);
			
			newbbs5HWCodingVO dto = null;
			newbbs5HWCodingVO dto1 = null;
			System.out.println(seq + "fdjnfd");
			
			//좋아요유무 싫어요유무
			boolean likeisS = lsservice.isitlikeid(seq, likeid);
			boolean dislikeisS = lsservice.isitdislikeid(seq, likeid);
			boolean b = tservice.getparent(seq);
			List<newbbs5HWCodingVO> list = new ArrayList<>();
			if (likeisS) {
				System.out.println("id 찾았다");
				dto = new newbbs5HWCodingVO(1, 0);
			}else {
				System.out.println("id 못찾았다");
				dto = new newbbs5HWCodingVO(2, 0);
			}
			if(dislikeisS) {
				System.out.println("싫어요 id찾았다");
				dto1 = new newbbs5HWCodingVO(0, 1);
			}else {
				System.out.println("싫어요 id못찾았다");
				dto1 = new newbbs5HWCodingVO(0, 2);
			}
			if (b) {
				list = tservice.getpdsdetail(seq);
				System.out.println("자료 있다.");
			}else {
				list = tservice.getdetail(seq);
				System.out.println("자료 없다.");
			}
			
			request.setAttribute("fdislikeidyn", dto1);
			request.setAttribute("flikeidyn", dto);
			request.setAttribute("whatlist", list);
			dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
			
		//글 수정 부분.
		}else if(command.equals("update")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			request.setAttribute("seq", seq);
			dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingUpdate.jsp", request, response);
			
		//글 수정 완료 하는 부분.
		}else if(command.equals("updateAf")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			boolean b = tservice.update(seq, title, content);
			if (b) {
				System.out.println("업데이트 성공");
				List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
//				request.setAttribute("techbbs", list);
//				dispatch("Bbs5_jobsViewJsp/bbs4HWCoding.jsp", request, response);
				response.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
			}else {
				System.out.println("업데이트실패");
				request.setAttribute("seq", seq);
				dispatch("techupdate.jsp", request, response);
			}
		}
		else if(command.equals("delete")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			boolean b = tservice.delete(seq);
			
			if (b) {
				System.out.println("HW 게시판 지웠다");
				boolean c = tservice.pdsdelete(seq);
				if (c) {
					System.out.println("자료도 지웠다");
					tservice.repAlldelete(seq);
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					
					//글 삭제하면 점수 - 부분.
					byte score = 10;
//					String writescoreid = request.getParameter("id");//아이디 못가지고 오니 그냥 seq로 찾아보자.
					tservice.deleteBbsMemSCORE(score, seq);
					
					response.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
				}else {
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					
					//글 삭제하면 점수 - 부분.
					byte score = 10;
//					String writescoreid = request.getParameter("id");
					tservice.deleteBbsMemSCORE(score, seq);
					
					response.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
				}
				
			}else {
				List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
				response.sendRedirect("Bbs5_jobsViewJsp/bbs4HWCoding.jsp");
			}
		}
		/*if(command1==null) {
			
		}
			
		else if(command1!=null&&command1.equals("pdswrite")) { 
			String tagname="TechTips&강좌-";
			String[] tagnames=request.getParameterValues("tagnames");	//span태그안의value값다받아오기
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			for(int i=0;i<tagnames.length;i++){
				tagname+=tagnames[i]+"-";
			}
			tagname=tagname.substring(0, tagname.lastIndexOf("-"));
			System.out.println(tagname+id+title+command1+content);
		}*/
	}
	
	//이동하는 메소드.
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
