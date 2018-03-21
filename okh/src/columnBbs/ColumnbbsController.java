package columnBbs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import columnBbs.ColumnBbsDao;
import columnBbs.PdsDao;
import columnBbs.iColumnBbsDao;
import columnBbs.iPdsDao;
import columnBbs.ColumnBbsDto;
import columnBbs.PdsDto;
@WebServlet("/ColumnbbsController")
public class ColumnbbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		
		String command = request.getParameter("command");
		
		String sseq = request.getParameter("seq");
		String seq = request.getParameter("seq");
		//int intseq = Integer.parseInt(seq);
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String rdate = request.getParameter("rdate");
		String findWord = request.getParameter("findWord");
		String choice = request.getParameter("choice");
		String fiString = request.getParameter("filename");
		
		iColumnBbsDao dao = ColumnBbsDao.getInstance();
		
		
		List<ColumnBbsDto> bbslist = dao.getBbsList();
		
		if(command.equals("column")) {
			response.sendRedirect("columnbbslist.jsp");
		
		}else if(command.equals("detail")) {
				response.sendRedirect("columnbbsdetail.jsp=" + seq);	

		}else if(command.equals("write")) {						//O
			response.sendRedirect("columnbbswrite.jsp");
		}else if(command.equals("writeAf")) {					// O
			boolean isS = dao.writeBbs(new ColumnBbsDto(id, title, content) );
			if(isS) {
				System.out.println("라이트성공");
			}else {
				
				System.out.println("라이트실패");
			}
			response.sendRedirect("columnbbslist.jsp");
		}else if(command.equals("del")) {
			boolean isS = dao.dellBbs(Integer.parseInt(seq));
			if(isS) {
				response.sendRedirect("columnbbslist.jsp");
			}	
		
		}else if(command.equals("update")) {
			response.sendRedirect("columnUpdate.jsp?seq=" + seq);
		}else if(command.equals("updateAf")) {
			System.out.println("seq in updateAf : " + seq);
			boolean isS = dao.updateBbs(new ColumnBbsDto(Integer.parseInt(seq), title, content));
			if(isS) {
				response.sendRedirect("columnbbslist.jsp");
		}	
		}else if(command.equals("answer")) {
			response.sendRedirect("columnanswer.jsp?seq="+ seq);
		}else if(command.equals("answerAf")) {
		boolean isS = dao.answerBbs(Integer.parseInt(seq), new ColumnBbsDto(id, title, content));
			
			if(isS) {
				response.sendRedirect("columnbbslist.jsp");
			}
		}
	}
}


















