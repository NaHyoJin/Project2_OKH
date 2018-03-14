package lifeBbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeBbsController")
public class LifeBbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		
		String command = request.getParameter("command");
		
		String seq = request.getParameter("seq");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tag = request.getParameter("tag");
		String filename = request.getParameter("filename");
		
		ILifeBbsDao dao = LifeBbsDao.getInstance();
		
		if(command.equals("life")) {
			response.sendRedirect("lifeBbsList.jsp");
		}else if(command.equals("write")) {
			response.sendRedirect("lifeBbsWrite.jsp");
		}else if(command.equals("detail")) {
			response.sendRedirect("lifeBbsDetail.jsp?seq=" + seq);
		}else if(command.equals("answer")) {
			response.sendRedirect("lifeBbsAnswer.jsp?seq=" + seq);
		}else if(command.equals("update")) {
			response.sendRedirect("lifeBbsUpdate.jsp?seq=" + seq);
		}
		
		else if(command.equals("writeAf")) {
			boolean isS = dao.writeBbs(new LifeBbsDto(id, title, content, tag, filename));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("answerAf")) {
			boolean isS = dao.answer(Integer.parseInt(seq), new LifeBbsDto(id, title, content, tag, filename));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("delete")) {
			boolean isS = dao.deleteBbs(Integer.parseInt(seq));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("updateAf")) {
			boolean isS = dao.updateBbs(new LifeBbsDto(Integer.parseInt(seq), title, content, tag, filename));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}
	}

}
