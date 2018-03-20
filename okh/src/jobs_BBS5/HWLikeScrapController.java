package jobs_BBS5;

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

import techbbs.TechbbsDto;
import techbbs.TechbbsService;
import techbbs.TechbbsServiceImpl;
import user.UserDao;

public class HWLikeScrapController extends HttpServlet {
	
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=uTF-8");
		
		String command = request.getParameter("command");
		
		HWLikeScrapServiceImpl lsservice = HWLikeScrapService.getInstance();
		newbbs5HWCodingServiceImpl tservice = newbbs5HWCodingService.getInstance();
		if(command.equals("likeimg")) {
			newbbs5HWCodingVO dto = null;
			//처음받아오는값 본게시판 seq랑 좋아요누른아이디
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String likeid = request.getParameter("likeid");
			//아이디유무확인
			
			boolean b = lsservice.isitlikeid(seq, likeid);
			if (b) {	//id있다 -> 좋아요 취소
				//카운트-1
				tservice.dislikecount(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto = new newbbs5HWCodingVO(1, 0);
				//저장되있는 id찾아서 삭제하기
				
				boolean isS = lsservice.deleteLikeID(likeid, seq);
				
				if (isS) {
					System.out.println("likeid아이디 삭제 성공");
					boolean is = tservice.getparent(seq);
					List<newbbs5HWCodingVO> list = new ArrayList<>();
					if (is) {
						list = tservice.getpdsdetail(seq);
						System.out.println("자료 있다.");
					}else {
						list =tservice.getdetail(seq);
						System.out.println("자료 없다.");
					}
					request.setAttribute("likeidyn", dto);
					request.setAttribute("whatlist", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}else {
					System.out.println("아이디삭제fail");
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}
			}else {		//id없다 -> 좋아요올려주기
				
				//저장되있는 id찾아서 거기에추가하기
				String origin = lsservice.getLikeID(seq);
				likeid = origin+"-"+likeid+"-";
				//카운트+1
				tservice.likecountplus(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto = new newbbs5HWCodingVO(2, 0);//아이디추가
				boolean isS = lsservice.addLikeID(new newbbs5HWCodingVO(seq, likeid, ""));
				
				if (isS) {
					System.out.println("likeid아이디추가성공");
					System.out.println(seq+"fdjnfd");
					boolean is = tservice.getparent(seq);
					List<newbbs5HWCodingVO> list = new ArrayList<>();
					if (is) {
						list = tservice.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list = tservice.getdetail(seq);
						System.out.println("자료없다");
					}
					request.setAttribute("likeidyn", dto);
					request.setAttribute("whatlist", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}else {
					System.out.println("아이디 추가 fail");
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}
			}
		}else if(command.equals("dislikeimg")) {
			newbbs5HWCodingVO dto = null;
			//처음받아오는값 본게시판 seq랑 좋아요누른아이디
			String sseq = request.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String dislikeid = request.getParameter("dislikeid");
			//아이디유무확인
			boolean b = lsservice.isitdislikeid(seq, dislikeid);
			if (b) {	//id있다 -> 싫어요취소
				//카운트==
				tservice.likecountplus(seq);
				//dislikeid있으면 1(취소) id없으면2(추가)
				dto = new newbbs5HWCodingVO(0, 1);
				//저장되있는 id찾아서 삭제하기
				boolean isS = lsservice.deleteaddDisLikeID(dislikeid, seq);
				
				if (isS) {
					System.out.println("dislikeid아이디삭제성공");
					boolean is = tservice.getparent(seq);
					List<newbbs5HWCodingVO> list = new ArrayList<>();
					if (is) {
						list=tservice.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=tservice.getdetail(seq);
						System.out.println("자료없다");
					}
					request.setAttribute("dislikeidyn", dto);
					request.setAttribute("whatlist", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}else {
					System.out.println("아이디삭제fail");
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}
			}else {		//id없다 -> 좋아요올려주기
				
				//저장되있는 id찾아서 거기에추가하기
				String origin=lsservice.getDisLikeID(seq);
				dislikeid = origin+"-"+dislikeid+"-";
				//카운트-1
				tservice.dislikecount(seq);
				//dislikeid있으면 1(취소) id없으면2(추가)
				dto=new newbbs5HWCodingVO(0, 2);//아이디추가
				boolean isS = lsservice.addDisLikeID(new newbbs5HWCodingVO(seq, "", dislikeid));
				
				if (isS) {
					System.out.println("dislikeid아이디추가성공");
					boolean is = tservice.getparent(seq);
					List<newbbs5HWCodingVO> list=new ArrayList<>();
					if (is) {
						list=tservice.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=tservice.getdetail(seq);
						System.out.println("자료없다");
					}
					request.setAttribute("dislikeidyn", dto);
					request.setAttribute("whatlist", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}else {
					System.out.println("아이디추가fail");
					List<newbbs5HWCodingVO> list = tservice.gettechBbsList();
					request.setAttribute("techbbs", list);
					dispatch("Bbs5_jobsViewJsp/jobs_bbs5HWCodingDetail.jsp", request, response);
				}
			}
		}else if(command.equals("scrapimg")) {
			
		}
	}
	
	//이동 부분. dispatch.
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}