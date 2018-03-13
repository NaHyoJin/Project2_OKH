package qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class QnaServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		myFunc(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		myFunc(req, resp);
	}
	
	protected void dispatch(String urls, 
			HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		
		RequestDispatcher _dispatch = req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);		
	}

	protected void myFunc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("html/text; charset=utf-8");
		
		String command =req.getParameter("command");
		
		QnaServiceImpl service = QnaService.getInstance();
		
		if(command.equals("writeQna")) {
			System.out.println("여기는 writeQna입니다");
		
			QnaDto dto = new QnaDto();
			
			dto.setId(req.getParameter("iD"));
			dto.setTitle(req.getParameter("tItle"));
			dto.setTag(req.getParameter("tAg"));
			dto.setContent(req.getParameter("cOntent"));
			
		//	qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
		//	dao.writeQnaBbs(dto);
			
			service.writeQnaBbs(dto);			
			
			//RequestDispatcher rd = req.getRequestDispatcher("/qnaServlet?command=listQna");						
            //rd.forward(req, resp);
			resp.sendRedirect("qnaServlet?command=listQna");
			
		}else if(command.equals("listQna")) {
			System.out.println("여기는 listQna");
			
			
			
		//	qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
		//	dao.getQnaList();			
			
			service.getQnaList();
			
			RequestDispatcher rd = req.getRequestDispatcher("qnabbslist.jsp");

            rd.forward(req, resp);

			
		}
		
		
		
	}
	
	
	
	
	
}
