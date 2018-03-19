package jobs_BBS5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mainJSPServlet extends HttpServlet {

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
	
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		
		resp.setContentType("text/html; charset=utf-8");
//		PrintWriter out = resp.getWriter();
		
		req.setCharacterEncoding("utf-8");

		//Home누르면 그냥 메인 화면으로 가게 한것.
		resp.sendRedirect("index.jsp");
	}

	
}
