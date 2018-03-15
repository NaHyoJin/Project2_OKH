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
		
		if(command.equals("list")) {
			System.out.println("list");
			response.sendRedirect("study_communitybbs.jsp");
			
			
			
		
		}else if(command.equals("write")) {
			response.sendRedirect("study_communitybbswrite.jsp");
		
		}else if(command.equals("ComwriteAF")) {
			String id = request.getParameter("id");
			String title =request.getParameter("title");
			String content = request.getParameter("content");
			String date = request.getParameter("date");
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
			request.setAttribute("comwritedto", dto);
			dispatch("study_communitywriteAF.jsp", request, response);
		}else if(command.equals("detail")) {
			String sseq = request.getParameter("seq");
		
			int seq = Integer.parseInt(sseq);
			ICombbsService service = CombbsService.getInstance();
			List<comment_bbsDto> list = service.detailbbs(seq);
			if(list==null||list.size()==0) {
				List<CombbsDto> list1 = service.commentnull(seq);
				System.out.println("코맨트없는 리스트1");
				request.setAttribute("detail1", list1);
				
			}else {
				request.setAttribute("detail", list);
				System.out.println("코맨트있는 리스트");
				
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
			
			
			System.out.println(id+parent+content);
			ComCommentDto dto = new ComCommentDto( id, content,child);
			System.out.println("dto:"+dto.getId()+dto.getContent());
			request.getSession().setAttribute("comment", dto);
			request.setAttribute("parent", parent);
			dispatch("study_communitybbscommentAF.jsp", request, response);
		}
		
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
}
