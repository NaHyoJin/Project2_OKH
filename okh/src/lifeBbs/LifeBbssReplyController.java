package lifeBbs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.IUserService;
import user.UserService;

@WebServlet("/LifeBbssReplyController")
public class LifeBbssReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String command = request.getParameter("command");

		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String bbsseq = request.getParameter("bbsseq");
		String seq = request.getParameter("seq");
		
		ILifeBbssReplyDao dao = LifeBbssReplyDao.getInstance();
		ILifeBbsDao bbsDao = LifeBbsDao.getInstance();
		IUserService service = UserService.getInstance();
		
		if(command.equals("writeReply")) {
			boolean isS = dao.writeBbsReply(new LifeBbssReplyDto(id, content, Integer.parseInt(bbsseq)));
			
			int countreply = dao.getCountReply(Integer.parseInt(bbsseq));
			System.out.println("countreply in writeReply : " + countreply);
			bbsDao.writeCountReply(Integer.parseInt(bbsseq), countreply);
			
			if(isS) {
				// score +2
				int score = service.getScore(id);
				score += 2;
				boolean SS = service.updateScore(id, score);
				
				if(SS) {
					response.sendRedirect("lifeBbsDetail.jsp?seq=" + bbsseq);
				}
			}
		}else if(command.equals("deleteReply")) {
			boolean isS = dao.deleteBbsReply(Integer.parseInt(seq));
			
			int countreply = dao.getCountReply(Integer.parseInt(bbsseq));
			System.out.println("countreply in deleteReply : " + countreply);
			bbsDao.writeCountReply(Integer.parseInt(bbsseq), countreply);
			if(isS) {
				response.sendRedirect("lifeBbsDetail.jsp?seq=" + bbsseq);
			}
		}else if(command.equals("updateReply")) {
			boolean isS = dao.updateBbsReply(new LifeBbssReplyDto(Integer.parseInt(seq), content));
			if(isS) {
				response.sendRedirect("lifeBbsDetail.jsp?seq=" + bbsseq);
			}
		}else if(command.equals("reply")) {
			List<LifeBbssReplyDto> list = dao.reply();
		}
	}

}
