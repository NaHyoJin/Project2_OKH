package lifeBbs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeBbsController")
public class LifeBbsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 8192;
	private ServletConfig mConfig = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mConfig = config;
	}

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
		
		String seq = request.getParameter("seq");
		String id = (String)request.getSession().getAttribute("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tag = request.getParameter("tag");
		String filename = request.getParameter("filename");
		String upid = request.getParameter("upid");
		
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
		}else if(command.equals("answerAf")) {
			String tagAf = "사는얘기," + tag;
			
			boolean isS = dao.answer(Integer.parseInt(seq), new LifeBbsDto(id, title, content, tagAf, filename));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("delete")) {
			boolean isS = dao.deleteBbs(Integer.parseInt(seq));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("updateAf")) {
			System.out.println("tag in updateAf : " + tag);
			boolean isS = dao.updateBbs(new LifeBbsDto(Integer.parseInt(seq), title, content, tag, filename));
			
			if(isS) {
				response.sendRedirect("lifeBbsList.jsp");
			}
		}else if(command.equals("fileDown")) {
			
			System.out.println("FileDownloader doGet");
			
			filename = new String(request.getParameter("filename").getBytes("UTF-8"), "UTF-8");
			System.out.println("filename = " + filename);
			
			// download 회수를 증가
			String pdsseq = request.getParameter("seq");
			int iseq = Integer.parseInt(pdsseq);
			
			boolean isS = dao.downloadcount(iseq);
			if(!isS) {
				response.sendRedirect("pdslist.jsp");
			}
			
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			String filePath = "";
			
			if(pdsseq != null) {
				filePath = mConfig.getServletContext().getRealPath("/upload");
			}
			
			try {
				filePath = filePath + "\\" + filename;
				
				File f = new File(filePath);
				System.out.println("filePath : " + filePath);
				
				if(f.exists() && f.canRead()) {
					// 다운 로드 window 설정(다운로드창)
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
					response.setHeader("Content-Transfer-Encoding", "binary;");
					response.setHeader("Content-Length", "" + f.length());
					response.setHeader("Pragma", "no-cache;"); 
					response.setHeader("Expires", "-1;");
					
					// 파일 생성, 기입
					BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
					byte buffer[] = new byte[BUFFER_SIZE];
					
					int read = 0;
					
					while((read = fileInput.read(buffer)) != -1) {
						out.write(buffer, 0, read);
					}
					
					fileInput.close();
					out.flush();
				}else {
					System.out.println("파일이 존재하지 않습니다");
				}
			}catch(Exception e) {
				
			}finally {
				if(out != null) {
					out.flush();
					out.close();
				}
			}
			
		}
	}

}
