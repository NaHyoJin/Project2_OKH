package techpds;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;


public class FileDownloader extends HttpServlet {

	private static final int BUFFER_SIZE=8192;	//8KB
	private ServletConfig mConfig=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		mConfig=config;		//getRealPath의 경로를 취득하기위해서
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileDownloader doGet");
		
		String filename=new String(req.getParameter("filename").getBytes("8859_1"),"KSC5601");
		
		//download횟수증가
		String pdsseq=req.getParameter("seq");
		int seq=Integer.parseInt(pdsseq);
		
		BufferedOutputStream out=new BufferedOutputStream(resp.getOutputStream());
		String filePath="";
		if(pdsseq!=null) {
			//개인폴더에올렸을때
			//filePath="C:\\tmp";
			//톰캣에올렸을때
			filePath=mConfig.getServletContext().getRealPath("/techupload");
		}
		
		try {
			//경로완성
			filePath=filePath+"\\"+filename;
			
			File f=new File(filePath);
			
			if (f.exists()&&f.canRead()) {
				// 다운 로드 window 설정(다운로드창)
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes(), "utf-8") + "\";");
				resp.setHeader("Content-Transfer-Encoding", "binary;");
				resp.setHeader("Content-Length", "" + f.length());
				resp.setHeader("Pragma", "no-cache;"); 
				resp.setHeader("Expires", "-1;");
				System.out.println(filename+213);
				//받은바이트를 파일로생성
				BufferedInputStream fileInput=new BufferedInputStream(new FileInputStream(f));
				byte buffer[]=new byte[BUFFER_SIZE];
				
				int read=0;
				
				while ((read=fileInput.read(buffer))!=-1) {
					out.write(buffer, 0, read);
				}
				fileInput.close();
				out.flush();
			}else {
				System.out.println("파일이존재안합니다");
			}
		}catch (Exception e) {}
		finally {
			if (out !=null) {
				out.flush();
				out.close();
			}
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	

}
