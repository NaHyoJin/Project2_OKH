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

import likescrap.LikeScrapService;
import likescrap.LikeScrapServiceImpl;
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
		String command1 = request.getParameter("command1");
		//두번째댓글수정이나추가시 write랑 upcon이나온다
		String secondreple="";
		//두번째댓글은 두가지값이다넘어온다
		System.out.println(command+"command?"+command1+"command1");
		TechRepbbsServiceImpl trservice=TechRepbbsService.getInstance();
		TechbbsServiceImpl tservice=TechbbsService.getInstance();
		if(command1==null) {
			command1="upcon11";
		}
		if(command==null) {
			command="";
		}
		
		if(command.equals("write")&&command1.equals("upcon11")) {
			System.out.println("글쓰기들어왔는가?");
			//글쓰기후 디테일창가기위한초기화
			LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
			TechbbsDto dto=null;
			TechbbsDto dto1=null;
			List<TechbbsDto> list=new ArrayList<>();
			//
			String memid=request.getParameter("id");
			String sseq=request.getParameter("mainseq");
			int parent=Integer.parseInt(sseq);
			String content=request.getParameter("content");
			//좋아요,싫어요유무
			boolean likeisS=lsservice.isitlikeid(parent, memid);
			boolean dislikeisS=lsservice.isitdislikeid(parent, memid);
			
			if (likeisS) {
				System.out.println("id찾았다");
				dto=new TechbbsDto(1, 0);
			}else {
				System.out.println("id못찾았다");
				dto=new TechbbsDto(2, 0);
			}
			if(dislikeisS) {
				System.out.println("싫어요id찾았다");
				dto1=new TechbbsDto(0, 1);
			}else {
				System.out.println("싫어요id못찾았다");
				dto1=new TechbbsDto(0, 2);
			}
			
			
			TechRepbbsDto dto2=new TechRepbbsDto(memid, content, parent);
			boolean b=trservice.writeBbs(dto2);
			if (b) {
				System.out.println("성공"+dto.getContent()+dto.getId()+dto.getParent());
				tservice.commentcountplus(parent);
				boolean is=tservice.getparent(parent);
				
				if (is) {
					list=tservice.getpdsdetail(parent);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(parent);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}else {
				System.out.println("글쓰기실패"+dto.getContent()+dto.getId()+dto.getParent());
				
				boolean is=tservice.getparent(parent);
				
				if (is) {
					list=tservice.getpdsdetail(parent);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(parent);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}
		}else if(command1.equals("upcon")) {
			System.out.println("리플어ㅂ데이트");
			//업데이트후 디테일창가기위한초기화
			LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
			TechbbsDto dto=null;
			TechbbsDto dto1=null;
			List<TechbbsDto> list=new ArrayList<>();
			//필요한거받아오기
			String memid=request.getParameter("memid");
			String sseq=request.getParameter("repseq");
			int seq=Integer.parseInt(sseq);
			String upcontent=request.getParameter("upcontent");
			System.out.println(seq+upcontent);
			String bonseqq=request.getParameter("seq");
			int bonseq=Integer.parseInt(bonseqq);
			//memid=좋아요유무, seq=리플단번호, upcontent=수정할내용, bonseq=본게시판seq
			
			//좋아요,싫어요유무
			boolean likeisS=lsservice.isitlikeid(bonseq, memid);
			boolean dislikeisS=lsservice.isitdislikeid(bonseq, memid);
			
			if (likeisS) {
				System.out.println("id찾았다");
				dto=new TechbbsDto(1, 0);
			}else {
				System.out.println("id못찾았다");
				dto=new TechbbsDto(2, 0);
			}
			if(dislikeisS) {
				System.out.println("싫어요id찾았다");
				dto1=new TechbbsDto(0, 1);
			}else {
				System.out.println("싫어요id못찾았다");
				dto1=new TechbbsDto(0, 2);
			}
			//업데이트유무
			boolean b=trservice.repupdate(seq, upcontent);
			if (b) {
				System.out.println("rep댓글수정");
				boolean is=tservice.getparent(bonseq);
				
				if (is) {
					list=tservice.getpdsdetail(bonseq);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(bonseq);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}else {
				System.out.println("rep댓글수정실패");
				boolean is=tservice.getparent(bonseq);
				
				if (is) {
					list=tservice.getpdsdetail(bonseq);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(bonseq);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}
		}else if(command.equals("delete")) {
			//삭제후 디테일창가기위한초기화
			LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
			TechbbsDto dto=null;
			TechbbsDto dto1=null;
			List<TechbbsDto> list=new ArrayList<>();
			//필요한거받아오기
			String memid=request.getParameter("memid");
			String sseq=request.getParameter("seq");
			int seq=Integer.parseInt(sseq);
			String bonseqq=request.getParameter("bonseq");
			int bonseq=Integer.parseInt(bonseqq);
			//memid=좋아요유무, seq=리플단번호, upcontent=수정할내용, bonseq=본게시판seq

			//좋아요,싫어요유무
			boolean likeisS=lsservice.isitlikeid(bonseq, memid);
			boolean dislikeisS=lsservice.isitdislikeid(bonseq, memid);
			
			if (likeisS) {
				System.out.println("id찾았다");
				dto=new TechbbsDto(1, 0);
			}else {
				System.out.println("id못찾았다");
				dto=new TechbbsDto(2, 0);
			}
			if(dislikeisS) {
				System.out.println("싫어요id찾았다");
				dto1=new TechbbsDto(0, 1);
			}else {
				System.out.println("싫어요id못찾았다");
				dto1=new TechbbsDto(0, 2);
			}
		
			//삭제유무
			boolean b=trservice.repdelete(seq);
			if (b) {
				System.out.println(seq+"rep댓글삭제");
				tservice.commentcountminus(bonseq);
				boolean is=tservice.getparent(bonseq);
				
				if (is) {
					list=tservice.getpdsdetail(bonseq);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(bonseq);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}else {
				System.out.println("rep댓글삭제실패");
				boolean is=tservice.getparent(bonseq);
				
				if (is) {
					list=tservice.getpdsdetail(bonseq);
					System.out.println("자료있다");
				}else {
					list=tservice.getdetail(bonseq);
					System.out.println("자료없다");
				}
				//값들보내주기 좋아요싫어요유무, 어떤리스트인지,
				request.setAttribute("fdislikeidyn", dto1);
				request.setAttribute("flikeidyn", dto);
				request.setAttribute("whatlist", list);
				dispatch("techdetail.jsp", request, response);
			}
		}
	}
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
