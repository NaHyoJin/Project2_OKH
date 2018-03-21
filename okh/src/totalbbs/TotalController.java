package totalbbs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import techpds.PdsService;
import techpds.PdsServiceImpl;
import user.UserDao;

public class TotalController extends HttpServlet {
	static int sort=0;
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
		int parent=0;
		String command = request.getParameter("command");
		String command1=(String)request.getAttribute("command");
		
		TechbbsServiceImpl tservice=TechbbsService.getInstance();
		PdsServiceImpl pservice=PdsService.getInstance();
		if(command.equals("totalbbs")) {
			List<totalbbsdto> totallist=new ArrayList<>();
			List<TechbbsDto> techlist=tservice.alltechBbsList();
			List<LifeBbsDto> lifelist=tservice.alllifeBbsList();
			List<newbbs5HWCodingVO> bbs5list=tservice.allbbs5BbsList();
			List<QnaDto> qnalist=tservice.allqnaBbsList();
			List<CombbsDto> comlist=tservice.allcomBbsList();
			
			for (int i = 0; i < techlist.size(); i++) {
				int wdate=0;
				TechbbsDto techdto=techlist.get(i);
				String wdatee=techdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(techdto.getSeq(), "기술게시판", techdto.getId(), 
						techdto.getTitle(),techdto.getContent(), techdto.getReadcount(), techdto.getLikecount(), 
						techdto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < lifelist.size(); i++) {
				int wdate=0;
				LifeBbsDto lifedto=lifelist.get(i);
				String wdatee=lifedto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(lifedto.getSeq(), "사는얘기", lifedto.getId(), 
						lifedto.getTitle(),lifedto.getContent(), lifedto.getReadcount(), lifedto.getUp(), 
						lifedto.getCountreply(), wdate));
			}
			for (int i = 0; i < bbs5list.size(); i++) {
				int wdate=0;
				newbbs5HWCodingVO bbs5dto=bbs5list.get(i);
				String wdatee=bbs5dto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(bbs5dto.getSeq(), "HW게시판", bbs5dto.getId(), 
						bbs5dto.getTitle(),bbs5dto.getContent(), bbs5dto.getReadcount(), bbs5dto.getLikecount(), 
						bbs5dto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < qnalist.size(); i++) {
				int wdate=0;
				QnaDto qnadto=qnalist.get(i);
				String wdatee=qnadto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(qnadto.getSeq(), "QnA게시판", qnadto.getId(), 
						qnadto.getTitle(),qnadto.getContent(), qnadto.getReadcount(), qnadto.getFavor(), 
						qnadto.getAnswercount(), wdate));
			}
			for (int i = 0; i < comlist.size(); i++) {
				int wdate=0;
				CombbsDto comdto=comlist.get(i);
				String wdatee=comdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(comdto.getSeq(), "커뮤니티게시판", comdto.getId(), 
						comdto.getTitle(),comdto.getContent(), comdto.getReadcount(), comdto.getJoinercount(), 
						comdto.getCommentcount(), wdate));
			}
			Collections.sort(totallist,new CompareSeqDesc());
			request.setAttribute("firs", "firs");
			request.setAttribute("totallist", totallist);
			dispatch("totalbbs.jsp", request, response);
		}else if(command.equals("sorthe")) {
			
			List<totalbbsdto> totallist=new ArrayList<>();
			List<TechbbsDto> techlist=tservice.alltechBbsList();
			List<LifeBbsDto> lifelist=tservice.alllifeBbsList();
			List<newbbs5HWCodingVO> bbs5list=tservice.allbbs5BbsList();
			List<QnaDto> qnalist=tservice.allqnaBbsList();
			List<CombbsDto> comlist=tservice.allcomBbsList();
			for (int i = 0; i < techlist.size(); i++) {
				int wdate=0;
				TechbbsDto techdto=techlist.get(i);
				String wdatee=techdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(techdto.getSeq(), "기술게시판", techdto.getId(), 
						techdto.getTitle(),techdto.getContent(), techdto.getReadcount(), techdto.getLikecount(), 
						techdto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < lifelist.size(); i++) {
				int wdate=0;
				LifeBbsDto lifedto=lifelist.get(i);
				String wdatee=lifedto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(lifedto.getSeq(), "사는얘기", lifedto.getId(), 
						lifedto.getTitle(),lifedto.getContent(), lifedto.getReadcount(), lifedto.getUp(), 
						lifedto.getCountreply(), wdate));
			}
			for (int i = 0; i < bbs5list.size(); i++) {
				int wdate=0;
				newbbs5HWCodingVO bbs5dto=bbs5list.get(i);
				String wdatee=bbs5dto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(bbs5dto.getSeq(), "HW게시판", bbs5dto.getId(), 
						bbs5dto.getTitle(),bbs5dto.getContent(), bbs5dto.getReadcount(), bbs5dto.getLikecount(), 
						bbs5dto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < qnalist.size(); i++) {
				int wdate=0;
				QnaDto qnadto=qnalist.get(i);
				String wdatee=qnadto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(qnadto.getSeq(), "QnA게시판", qnadto.getId(), 
						qnadto.getTitle(),qnadto.getContent(), qnadto.getReadcount(), qnadto.getFavor(), 
						qnadto.getAnswercount(), wdate));
			}
			for (int i = 0; i < comlist.size(); i++) {
				int wdate=0;
				CombbsDto comdto=comlist.get(i);
				String wdatee=comdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(comdto.getSeq(), "커뮤니티게시판", comdto.getId(), 
						comdto.getTitle(),comdto.getContent(), comdto.getReadcount(), comdto.getJoinercount(), 
						comdto.getCommentcount(), wdate));
			}
			String whatsort=request.getParameter("whatthings");
			if(whatsort.equals("wdate")) {
				sort=1;
				System.out.println("최신순정렬");
				Collections.sort(totallist,new CompareSeqDesc());
			}else if(whatsort.equals("likecount")) {
				sort=2;
				Collections.sort(totallist,new CompareSeqDesc());
			}else if(whatsort.equals("contentcount")) {
				sort=3;
				Collections.sort(totallist,new CompareSeqDesc());
			}
			else if(whatsort.equals("readcount")) {
				sort=4;
				Collections.sort(totallist,new CompareSeqDesc());
			}
			
			System.out.println("sort해들어왔나?"+sort);
			request.setAttribute("whatsort", whatsort);
			request.setAttribute("sorthe", totallist);
			dispatch("totalbbs.jsp", request, response);
		}else if(command.equals("serch")) {
			
			List<totalbbsdto> totallist=new ArrayList<>();
			List<TechbbsDto> techlist=tservice.alltechBbsList();
			List<LifeBbsDto> lifelist=tservice.alllifeBbsList();
			List<newbbs5HWCodingVO> bbs5list=tservice.allbbs5BbsList();
			List<QnaDto> qnalist=tservice.allqnaBbsList();
			List<CombbsDto> comlist=tservice.allcomBbsList();
			
			for (int i = 0; i < techlist.size(); i++) {
				int wdate=0;
				TechbbsDto techdto=techlist.get(i);
				String wdatee=techdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(techdto.getSeq(), "기술게시판", techdto.getId(), 
						techdto.getTitle(),techdto.getContent(), techdto.getReadcount(), techdto.getLikecount(), 
						techdto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < lifelist.size(); i++) {
				int wdate=0;
				LifeBbsDto lifedto=lifelist.get(i);
				String wdatee=lifedto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(lifedto.getSeq(), "사는얘기", lifedto.getId(), 
						lifedto.getTitle(),lifedto.getContent(), lifedto.getReadcount(), lifedto.getUp(), 
						lifedto.getCountreply(), wdate));
			}
			for (int i = 0; i < bbs5list.size(); i++) {
				int wdate=0;
				newbbs5HWCodingVO bbs5dto=bbs5list.get(i);
				String wdatee=bbs5dto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(bbs5dto.getSeq(), "HW게시판", bbs5dto.getId(), 
						bbs5dto.getTitle(),bbs5dto.getContent(), bbs5dto.getReadcount(), bbs5dto.getLikecount(), 
						bbs5dto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < qnalist.size(); i++) {
				int wdate=0;
				QnaDto qnadto=qnalist.get(i);
				String wdatee=qnadto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(qnadto.getSeq(), "QnA게시판", qnadto.getId(), 
						qnadto.getTitle(),qnadto.getContent(), qnadto.getReadcount(), qnadto.getFavor(), 
						qnadto.getAnswercount(), wdate));
			}
			for (int i = 0; i < comlist.size(); i++) {
				int wdate=0;
				CombbsDto comdto=comlist.get(i);
				String wdatee=comdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(comdto.getSeq(), "커뮤니티게시판", comdto.getId(), 
						comdto.getTitle(),comdto.getContent(), comdto.getReadcount(), comdto.getJoinercount(), 
						comdto.getCommentcount(), wdate));
			}
			String findWord=request.getParameter("findWord");
			String choice=request.getParameter("choice");
			List<totalbbsdto> serchlist=new ArrayList<>();
			if(choice.equals("title")) {
				for (int i = 0; i < totallist.size(); i++) {
					if(totallist.get(i).getTitle().contains(findWord)) {
						serchlist.add(totallist.get(i));
					}else {
					}
				}
				
			}else if(choice.equals("writer")) {
				for (int i = 0; i < totallist.size(); i++) {
					if(totallist.get(i).getId().contains(findWord)) {
						serchlist.add(totallist.get(i));
					}else {
					}
				}
			}else if(choice.equals("content")) {
				for (int i = 0; i < totallist.size(); i++) {
					if(totallist.get(i).getContent().contains(findWord)) {
						serchlist.add(totallist.get(i));
					}else {
					}
				}
			}
			String serchyn="";
			if (serchlist==null||serchlist.size()==0) {
				serchyn="no";
			}else if(serchlist!=null) {
				serchyn="yes";
			}
			System.out.println("serch해들어왔나?");
			request.setAttribute("totallist", totallist);
			request.setAttribute("serchyn", serchyn);
			request.setAttribute("serch", serchlist);
			dispatch("totalbbs.jsp", request, response);
		}else if(command.equals("gettotalbbs")) {
			List<totalbbsdto> totallist=new ArrayList<>();
			List<TechbbsDto> techlist=tservice.alltechBbsList();
			List<LifeBbsDto> lifelist=tservice.alllifeBbsList();
			List<newbbs5HWCodingVO> bbs5list=tservice.allbbs5BbsList();
			List<QnaDto> qnalist=tservice.allqnaBbsList();
			List<CombbsDto> comlist=tservice.allcomBbsList();
			
			for (int i = 0; i < techlist.size(); i++) {
				int wdate=0;
				TechbbsDto techdto=techlist.get(i);
				String wdatee=techdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(techdto.getSeq(), "기술게시판", techdto.getId(), 
						techdto.getTitle(),techdto.getContent(), techdto.getReadcount(), techdto.getLikecount(), 
						techdto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < lifelist.size(); i++) {
				int wdate=0;
				LifeBbsDto lifedto=lifelist.get(i);
				String wdatee=lifedto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(lifedto.getSeq(), "사는얘기", lifedto.getId(), 
						lifedto.getTitle(),lifedto.getContent(), lifedto.getReadcount(), lifedto.getUp(), 
						lifedto.getCountreply(), wdate));
			}
			for (int i = 0; i < bbs5list.size(); i++) {
				int wdate=0;
				newbbs5HWCodingVO bbs5dto=bbs5list.get(i);
				String wdatee=bbs5dto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(bbs5dto.getSeq(), "HW게시판", bbs5dto.getId(), 
						bbs5dto.getTitle(),bbs5dto.getContent(), bbs5dto.getReadcount(), bbs5dto.getLikecount(), 
						bbs5dto.getCommentcount(), wdate));
				
			}
			for (int i = 0; i < qnalist.size(); i++) {
				int wdate=0;
				QnaDto qnadto=qnalist.get(i);
				String wdatee=qnadto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(qnadto.getSeq(), "QnA게시판", qnadto.getId(), 
						qnadto.getTitle(),qnadto.getContent(), qnadto.getReadcount(), qnadto.getFavor(), 
						qnadto.getAnswercount(), wdate));
			}
			for (int i = 0; i < comlist.size(); i++) {
				int wdate=0;
				CombbsDto comdto=comlist.get(i);
				String wdatee=comdto.getWdate();
				String mon=wdatee.substring(5, 7);
				String day=wdatee.substring(8, 10);
				String hou=wdatee.substring(11, 13);
				String min=wdatee.substring(14, 16);
				String sec=wdatee.substring(17, 19);
				String todate=mon+day+hou+min+sec;
				wdate=Integer.parseInt(todate);
				totallist.add(new totalbbsdto(comdto.getSeq(), "커뮤니티게시판", comdto.getId(), 
						comdto.getTitle(),comdto.getContent(), comdto.getReadcount(), comdto.getJoinercount(), 
						comdto.getCommentcount(), wdate));
			}
			Collections.sort(totallist,new CompareSeqDesc());
			request.setAttribute("firs", "firs");
			request.setAttribute("totallist", totallist);
			dispatch("index.jsp", request, response);
		}
	}
	static class CompareSeqDesc implements Comparator<totalbbsdto>{
		 
        @Override
        public int compare(totalbbsdto o1, totalbbsdto o2) {
            if(sort==1) {
            	return o1.getWdate() > o2.getWdate() ? -1 : o1.getWdate() < o2.getWdate() ? 1:0;
            }else  if(sort==2) {
            	return o1.getLikecount() > o2.getLikecount() ? -1 : o1.getLikecount() < o2.getLikecount() ? 1:0;
            }else  if(sort==3) {
            	return o1.getComentcount() > o2.getComentcount() ? -1 : o1.getComentcount() < o2.getComentcount() ? 1:0;
            }else  if(sort==4) {
            	return o1.getReadcount() > o2.getReadcount() ? -1 : o1.getReadcount() < o2.getReadcount() ? 1:0;
            }else {
            	return o1.getWdate() > o2.getWdate() ? -1 : o1.getWdate() < o2.getWdate() ? 1:0;
            }
            
        }  
    }
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher _dispatch=req.getRequestDispatcher(urls);
		_dispatch.forward(req, resp);
	}
	
}
