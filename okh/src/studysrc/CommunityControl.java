package studysrc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Dispatch;

import user.IUserService;
import user.UserService;



public class CommunityControl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		
		String command = request.getParameter("command");
		IUserService serviceU = UserService.getInstance();
		if(command.equals("list")) {
			System.out.println("list");
			
			
			response.sendRedirect("study_communitybbs.jsp");
		}else if(command.equals("write")) {
			response.sendRedirect("study_communitybbswrite.jsp");
		
		}else if(command.equals("ComwriteAF")) {
			String id = request.getParameter("id");
			String title =request.getParameter("title");
			String content = request.getParameter("content");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String date = request.getParameter("date");
			date += two(hour)+two(min);
			String tag = request.getParameter("tag");
			String filename = request.getParameter("filename");
			
			String tagname="스터디모임-";
			
			String[] tagnames=request.getParameterValues("tagnames");
			for(int i=0; i < tagnames.length; i++){
				tagname += tagnames[i]+"-";
			}
			tagname = tagname.substring(0, tagname.lastIndexOf("-"));
			System.out.println(id+title+content+tagname+date);
			CombbsDto dto = new CombbsDto(id, title, content, tagname, date);
			ICombbsService service = CombbsService.getInstance();
			boolean isS = service.writeBbs(dto);
			int score = serviceU.getScore(id);
	         score += 5;
	         serviceU.updateScore(id, score);
			iCalendar dao = CalendarDao.getInstance();
			if(isS) {
				int child =service.getSeq();
				boolean isCalS = service.writecalendar(dto, child);
				if(isCalS) {
					response.sendRedirect("study_communitybbs.jsp");
				}
			}
			
			
			
		}else if(command.equals("detail")) {
			Study_like.LikeScrapServiceImpl lsservice=Study_like.LikeScrapService.getInstance();
			String sseq = request.getParameter("seq");
		
			int seq = Integer.parseInt(sseq);
			String likeid=request.getParameter("likeid");
			ICombbsService service = CombbsService.getInstance();
			List<comment_bbsDto> list = service.detailbbs(seq);
			
			
			boolean likeisS=lsservice.isitlikeid(seq, likeid);
			System.out.println("컨트롤러seq?" +seq);
			System.out.println("컨트롤러어디로?");
			boolean b=service.getparent(seq);
			if(list==null||list.size()==0) {
				List<CombbsDto> list1 = service.commentnull(seq);
				CombbsDto dto = null;
				if (likeisS) {
					System.out.println("id찾았다");
					dto=new CombbsDto(1);
					request.setAttribute("flikeidyn", dto);
				}else {
					System.out.println("id못찾았다");
					dto=new CombbsDto(2);
					request.setAttribute("flikeidyn", dto);
				}
				request.setAttribute("detail1", list1);
				
			}else {
				if (b) {
					list=service.detailbbs(seq);
					System.out.println("자료있다");
					request.setAttribute("detail", list);
				}else {
					List<CombbsDto> list1 = null;
					list1=service.commentnull(seq);
					System.out.println("자료없다");
					request.setAttribute("detail1", list1);
				}
				
				request.setAttribute("detail", list);
				System.out.println("컨트롤러코맨트있는 리스트");
				System.out.println("컨트롤러여기?코멘트있는리스트");
			}
			service.readcount(seq);
			request.setAttribute("seq", seq);
			dispatch("study_communitybbsdetail.jsp", request, response);
		}else if(command.equals("commentAF")) {
			String id = request.getParameter("id");
			String parents = request.getParameter("parent");
			int parent = Integer.parseInt(parents);
			String content = request.getParameter("content");
			int child = parent;
			/*String childs = request.getParameter("child");
			int child = Integer.parseInt(childs);*/
			int score = serviceU.getScore(id);
	         score += 5;
	         serviceU.updateScore(id, score);
			
			System.out.println(id+parent+content);
			ComCommentDto dto = new ComCommentDto( id, content,child);
			System.out.println("dto:"+dto.getId()+dto.getContent());
			request.getSession().setAttribute("comment", dto);
			request.setAttribute("parent", parent);
			dispatch("study_communitybbscommentAF.jsp", request, response);
		}
		else if(command.equals("update")) {
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			ICombbsService service = CombbsService.getInstance();
			
				List<CombbsDto> list = service.commentnull(seq);
				System.out.println("리스트");
				request.setAttribute("list", list);
				
		
			request.setAttribute("seq", seq);
			dispatch("study_communitybbsupdate.jsp", request, response);
		}
		else if(command.equals("updateAF")) {
			String id = request.getParameter("id");
			String title =request.getParameter("title");
			String content = request.getParameter("content");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String date = request.getParameter("date");
			date += two(hour)+two(min);
			String tag = request.getParameter("tag");
			String filename = request.getParameter("filename");
			String sseq = request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			
			String tagname="스터디모임-";
			
			String[] tagnames=request.getParameterValues("tagnames");
			for(int i=0; i < tagnames.length; i++){
				tagname += tagnames[i]+"-";
			}
			tagname = tagname.substring(0, tagname.lastIndexOf("-"));
			System.out.println(id+title+content+tagname+date);
			CombbsDto dto = new CombbsDto(id, title, content, tagname, date);
			ICombbsService service = CombbsService.getInstance();
			service.updatebbs(dto, seq);
			dispatch("study_communitybbs.jsp", request, response);
			
		}else if(command.equals("delete")) {
			String sseq = request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			System.out.println("seq:                  "+ seq);
			ICombbsService service = CombbsService.getInstance();
			service.delbbs(seq);
			dispatch("study_communitybbs.jsp", request, response);
		}
		else if(command.equals("delcomment")) {
			String sseq = request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			iCommentService service = CommentService.getInstance();
			service.delcomment(seq);
			ICombbsService service1 = CombbsService.getInstance();
			service1.commentdiscount(seq);
			
			dispatch("study_communitybbs.jsp", request, response);
		}else if(command.equals("commentup")) {
			String sseq = request.getParameter("commentseq");
			int seq=Integer.parseInt(sseq);
			String content = request.getParameter("upcontent");
			System.out.println(content);
			iCommentService service = CommentService.getInstance(); 
			service.updatecomment(content, seq);
			dispatch("study_communitybbs.jsp", request, response);
		}
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
	public String two(String msg){
		return msg.trim().length()<2?"0"+msg:msg.trim();
	}
}
