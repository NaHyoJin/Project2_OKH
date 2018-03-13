package controller;

import java.io.File;
import java.io.IOException;
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

import dto.PagingBean;
import dto.PdsDto;
import dto.TechbbsDto;
import service.TechbbsService;
import service.TechbbsServiceImpl;
import singleton.Singleton;
import user.UserDTO;
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
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		String command = request.getParameter("command");
		
		if(command.equals("techbbs")) {
			TechbbsServiceImpl service=TechbbsService.getInstance();
			List<TechbbsDto> list=service.gettechBbsList();
			request.setAttribute("techbbs", list);
			dispatch("techbbs.jsp", request, response);
		}else if(command.equals("techwrite")) {
			response.sendRedirect("techwrite.jsp");
		}else if(command.equals("techwriteAf")) {
			String tagname="TechTips&강좌-";
			String tagString=request.getParameter("tagString");
			String[] tagnames=request.getParameterValues("tagnames");	//span태그안의value값다받아오기
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			for(int i=0;i<tagnames.length;i++){
				tagname+=tagnames[i]+"-";
			}
			tagname=tagname.substring(0, tagname.lastIndexOf("-"));
			TechbbsDto dto=new TechbbsDto(id, title, tagname, content);
			request.setAttribute("techwritedto", dto);
			dispatch("techwriteAf.jsp", request, response);
		}
	}
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
