package techreplebbs;

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

import singleton.Singleton;
import techbbs.TechbbsDto;
import techbbs.TechbbsService;
import techbbs.TechbbsServiceImpl;
import user.UserDao;

public class TechRepbbsController extends HttpServlet {
	
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
		String command1 = request.getParameter("upcon");
		TechRepbbsServiceImpl trservice=TechRepbbsService.getInstance();
		TechbbsServiceImpl tservice=TechbbsService.getInstance();
		if(command1==null) {
			command1="upcon11";
		}
		if(command.equals("write")&&command1.equals("upcon11")) {
			System.out.println("들어왔는가?");
			String id=request.getParameter("id");
			String sseq=request.getParameter("mainseq");
			int parent=Integer.parseInt(sseq);
			String content=request.getParameter("content");
			TechRepbbsDto dto=new TechRepbbsDto(id, content, parent);
			boolean b=trservice.writeBbs(dto);
			if (b) {
				System.out.println("성공"+dto.getContent()+dto.getId()+dto.getParent());
				
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}else {
				System.out.println("실패"+dto.getContent()+dto.getId()+dto.getParent());
				
				request.setAttribute("command", "techdetail1");
				request.setAttribute("seq", parent);
				dispatch("TechbbsController", request, response);
			}
		}else if(command1.equals("upcon")) {
			System.out.println("리플어ㅂ데이트");
			String sseq=request.getParameter("repseq");
			int seq=Integer.parseInt(sseq);
			String content=request.getParameter("upcontent");
			System.out.println(seq+content);
			boolean b=trservice.repupdate(seq, content);
			if (b) {
				System.out.println("rep댓글수정");
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}else {
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}
		}else if(command.equals("delete")) {
			String sseq = request.getParameter("mainseq");
			int seq = Integer.parseInt(sseq);
			boolean b=trservice.repdelete(seq);
			if (b) {
				System.out.println("rep게시판지웠다");
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}else {
				List<TechbbsDto> list=tservice.gettechBbsList();
				request.setAttribute("techbbs", list);
				dispatch("techbbs.jsp", request, response);
			}
		}
	}
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
