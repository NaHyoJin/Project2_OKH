package techbbs;

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

import likescrap.LikeScrapService;
import likescrap.LikeScrapServiceImpl;
import techpds.PdsService;
import techpds.PdsServiceImpl;
import user.UserDao;

public class TechbbsController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		int parent=0;
		String command = request.getParameter("command");
		String command1=(String)request.getAttribute("command");
		
		TechbbsServiceImpl tservice=TechbbsService.getInstance();
		PdsServiceImpl pservice=PdsService.getInstance();
		if(command.equals("techbbs")) {
			List<TechbbsDto> list=tservice.gettechBbsList();
			request.setAttribute("techbbs", list);
			dispatch("techbbs.jsp", request, response);
		}else if(command.equals("techbbs1")) {
			response.sendRedirect("techwrite.jsp");
		}
		else if(command.equals("techwrite")) {
			parent=pservice.getSeq()+1;
			boolean iss=pservice.pdsdelete(parent);
			if (iss) {
				System.out.println("pds삭제성공");
			}else {
				System.out.println("pds삭제실패");
			}
			response.sendRedirect("techwrite.jsp");
		}else if(command.equals("techwriteAf")) {
			String tagname="-TechTips-";
			String[] tagnames=request.getParameterValues("tagnames");	//span태그안의value값다받아오기
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			System.out.println(title+"여기가문제였군"+content);
			if (title==""||content=="") {
				System.out.println("여기가문제였군2");
				TechbbsDto dto1=null;
				request.setAttribute("return1", dto1);
				dispatch("techwriteAf.jsp", request, response);
			}
			if(tagnames==null) {
				TechbbsDto dto=new TechbbsDto(id, title, tagname, content);
				request.setAttribute("techwritedto", dto);
				dispatch("techwriteAf.jsp", request, response);
			}else {
			for(int i=0;i<tagnames.length;i++){
				tagname+=tagnames[i]+"-";
			}
			TechbbsDto dto=new TechbbsDto(id, title, tagname, content);
			request.setAttribute("techwritedto", dto);
			dispatch("techwriteAf.jsp", request, response);
			}
		}else if(command.equals("techdetail")) {
			
			LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			tservice.readcountplus(seq);
			String likeid = request.getParameter("likeid");
			TechbbsDto dto=null;
			TechbbsDto dto1=null;
			System.out.println(seq+"fdjnfd");
			//좋아요유무 싫어요유무
			boolean likeisS=lsservice.isitlikeid(seq, likeid);
			boolean dislikeisS=lsservice.isitdislikeid(seq, likeid);
			boolean b=tservice.getparent(seq);
			List<TechbbsDto> list=new ArrayList<>();
			if (likeisS) {
				System.out.println("id찾았다");
				dto=new TechbbsDto(1, 0);
			}else {
				System.out.println("id못찾았다");
				dto=new TechbbsDto(2, 0);
			}
			if(dislikeisS) {
				System.out.println("싫어요id찾았다");
				dto1=new TechbbsDto(0, 1);
			}else {
				System.out.println("싫어요id못찾았다");
				dto1=new TechbbsDto(0, 2);
			}
			if (b) {
				list=tservice.getpdsdetail(seq);
				System.out.println("자료있다");
			}else {
				list=tservice.getdetail(seq);
				System.out.println("자료없다");
			}
			
			request.setAttribute("fdislikeidyn", dto1);
			request.setAttribute("flikeidyn", dto);
			request.setAttribute("whatlist", list);
			dispatch("techdetail.jsp", request, response);
		}else if(command.equals("update")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			request.setAttribute("seq", seq);
			dispatch("techupdate.jsp", request, response);
		}else if(command.equals("updateAf")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			boolean b=tservice.update(seq, title, content);
			if (b) {
				System.out.println("업데이트성공");
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}else {
				System.out.println("업데이트실패");
				request.setAttribute("seq", seq);
				dispatch("techupdate.jsp", request, response);
			}
		}
		else if(command.equals("delete")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			boolean b=tservice.delete(seq);
			if (b) {
				System.out.println("게시판지웠다");
				boolean c= tservice.pdsdelete(seq);
				if (c) {
					System.out.println("자료도지웠다");
					tservice.repAlldelete(seq);
					List<TechbbsDto> list=tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("techbbs.jsp", request, response);
				}else {
					List<TechbbsDto> list=tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("techbbs.jsp", request, response);
				}
				
			}else {
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}
		}else if(command.equals("sorthe")) {
			List<TechbbsDto> list=tservice.gettechBbsList();
			String whatsort=request.getParameter("whatthings");
			System.out.println("sort해들어왔나?");
			request.setAttribute("whatsort", whatsort);
			request.setAttribute("sorthe", list);
			dispatch("techbbs.jsp", request, response);
		}
	}
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
