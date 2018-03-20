package jobs_BBS5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileDownloader extends HttpServlet {

	//설정 부분.
	private static final int BUFFER_SIZE = 819822;	//파일 크기
	private ServletConfig mConfig = null;
	
	
	//중요한. 경로 얻어오기 위해서 init 필요하다.
	@Override
	public void init(ServletConfig config) throws ServletException {//자동 호출.
		
		mConfig = config;//경로 얻기 위해 하는 작업. getRealPath의 경로를 취득하기 위한 작업.
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	
	
	//모든것 처리하기 위한 것.
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//잘 들어오는지 확인 부분.
				System.out.println("FileDownloader doGet");
				
				String filename = new String(request.getParameter("filename").getBytes("8859_1"), "KSC5601");//파일 이름 넘어오는것 받는 작업.
				System.out.println("doGet filename : " + filename);
				
				
				//download 횟수 증가
				String pdsseq = request.getParameter("seq");
				int seq = Integer.parseInt(pdsseq);//문자를 숫자로 변형하는 부분.

		/*	
		 		//다운로드 수 증가 부분. 일단 패스
				newbbs5HWCodingPDSServiceImpl dao = newbbs5HWCodingPDSService.getInstance();
				boolean isS = dao.downloadcount(seq);
		*/	
		/*		
				if(!isS) {
					
					 보내는 방법.
					form action, <a>, 
					
					sendRedirect
					pageContent 소속 foword
					
					resp.sendRedirect("pdslist.jsp");
				}
		*/		
		//받아 오는 부분.
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		String filePath = "";
				
				if(pdsseq != null) {//pdsseq안넘어 왔다는건 파일 없다.
					
					//tomcat
//					filePath = mConfig.getServletContext().getRealPath("/upload");//업로드 부분에서와 매치 시켜야 한다.
					
					//개인 폴더, 드라이브
					filePath = "E:\\tmp";//프로젝트 진행시에는 폴더로 저장해놓자.			
				}
				
				try {
					
					filePath = filePath + "\\" + filename;//경로하고 파일이름 합치는 부분.
					
					File f = new File(filePath);//파일 생성하는 부분.
					System.out.println("filePath : " + filePath);//잘 만들어졌나 확인 코드
				
					if(f.exists() && f.canRead()) {//존재하냐? 읽기 가능하냐?
						
						// 다운 로드 window 설정(다운로드창)
						response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
						response.setHeader("Content-Transfer-Encoding", "binary;");//인코딩 방식. 이진수
						response.setHeader("Content-Length", "" + f.length());
						response.setHeader("Pragma", "no-cache;"); //기억을 하겠는가? 부분
						response.setHeader("Expires", "-1;");//유효기간 -1 이면 설정 안하겠다.
						
						//파일 생성. 기입하는 부분.
						BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
						
						byte buffer[] = new byte[BUFFER_SIZE];
						
						int read = 0;
						
						while ((read = fileInput.read(buffer)) != -1) {
							out.write(buffer, 0, read);//파일 쓰는 부분.
						}
						
						fileInput.close();
						out.flush();//반드시 중요한 부분. 넣어야 한다.
					}else {
						System.out.println("파일이 존재하지 않습니다.");
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				finally {
					if(out != null) {//살아있는 경우
						out.flush();
						out.close();//뒷처리 끝나는 부분.
					}
				}
	}

}