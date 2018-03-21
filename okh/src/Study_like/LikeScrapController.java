package Study_like;

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

import studysrc.CalendarDao;
import studysrc.CalendarDto;
import studysrc.CombbsDto;
import studysrc.CombbsService;
import studysrc.ICombbsService;
import studysrc.comment_bbsDto;
import studysrc.iCalendar;
import user.UserDao;

public class LikeScrapController extends HttpServlet {
	
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
		
		String command = request.getParameter("command");
		
		LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
		ICombbsService cservice=CombbsService.getInstance();
		
		
		
		if(command.equals("likeimg")) {
			CombbsDto dto=null;
			//처음받아오는값 본게시판 seq랑 좋아요누른아이디
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			String tag = request.getParameter("tag");
			String joindate = request.getParameter("joindate");
			
			String likeid = request.getParameter("likeid");
			
			
			
			boolean b=lsservice.isitlikeid(seq, likeid);
			if (b) {	//id있다 -> 좋아요취소
				//카운트-1
				boolean delcal = cservice.deletecalendar(seq);
				if(delcal) {
				cservice.dislikecount(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto=new CombbsDto(1);
				//저장되있는 id찾아서 삭제하기
				
				boolean isS=lsservice.deleteLikeID(likeid, seq);
				
				if (isS) {
					System.out.println("likeid아이디삭제성공");
					boolean is=cservice.getparent(seq);
					List<CombbsDto> list1=new ArrayList<>();
					List<comment_bbsDto> list = new ArrayList<>();
					if (is) {
						list=cservice.detailbbs(seq);
						request.setAttribute("detail", list);
						System.out.println("자료있다");
					}else {
						list1=cservice.commentnull(seq);
						request.setAttribute("detail1", list1);
					
						System.out.println("자료없다");
					}
					request.setAttribute("likeidyn", dto);
					request.setAttribute("seq", seq);
					
					dispatch("study_communitybbsdetail.jsp", request, response);
				}else {
					System.out.println("아이디삭제fail");
					dispatch("study_communitybbs.jsp", request, response);
				}
			}
		}
				else {		//id없다 -> 좋아요올려주기
				
				CombbsDto dto1 = new CombbsDto(likeid,title,content,tag,joindate);
				//아이디유무확인
				boolean calwrite =cservice.writecalendar(dto1, seq);
				if(calwrite) {
				/*
				
				String origin=lsservice.getLikeID(seq);
            String addlikeid="";
            addlikeid=origin+likeid+"-";
            //카운트+1
            tservice.likecountplus(seq);
            //likeid있으면 1(취소) id없으면2(추가)
            dto=new TechbbsDto(2, 0);//아이디추가
            boolean isS=lsservice.addLikeID(new TechbbsDto(seq, addlikeid, ""));
				
				*/
				//저장되있는 id찾아서 거기에추가하기
				String origin=lsservice.getLikeID(seq);
				String addlikeid="";
				addlikeid=origin+","+likeid+",";
				//카운트+1
				cservice.likecountplus(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto=new CombbsDto(2);//아이디추가
				boolean isS=lsservice.addLikeID(new CombbsDto(seq,addlikeid));
				
				if (isS) {
					System.out.println("likeid아이디추가성공");
					System.out.println(seq+"fdjnfd");
					boolean is=cservice.getparent(seq);
					List<CombbsDto> list1=new ArrayList<>();
					List<comment_bbsDto> list = new ArrayList<>();
					if (is) {
						list=cservice.detailbbs(seq);
						request.setAttribute("detail", list);
						System.out.println("자료있다");
					}else {
						list1=cservice.commentnull(seq);
						request.setAttribute("detail1", list1);
					
						System.out.println("자료없다");
					}
					request.setAttribute("seq", seq);
					request.setAttribute("likeidyn", dto);
					dispatch("study_communitybbsdetail.jsp", request, response);
				}
				else {
					System.out.println("아이디추가fail");
					dispatch("study_communitybbs.jsp", request, response);
					}
				}
			}
		}
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
