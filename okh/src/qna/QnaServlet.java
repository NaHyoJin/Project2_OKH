package qna;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
		int parent = 0;
		String command =req.getParameter("command");
		
		QnaServiceImpl service = QnaService.getInstance();
		
		if(command.equals("writeQna")) {
			System.out.println("여기는 writeQna입니다");
		
			
			
		
			QnaDto dto = new QnaDto();
			
			dto.setId(req.getParameter("iD"));
			dto.setTitle(req.getParameter("tItle"));
			dto.setTagname(req.getParameter("tAg"));
			dto.setContent(req.getParameter("cOntent"));
			
		//	qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
		//	dao.writeQnaBbs(dto);
			
			System.out.println(dto.toString());
			
			service.writeQnaBbs(dto);			
			
		//	RequestDispatcher rd = req.getRequestDispatcher("/qnaServlet?command=listQna");						
        //   rd.forward(req, resp);
			resp.sendRedirect("qnaServlet?command=listQna");
			
		}else if(command.equals("listQna")) {
			System.out.println("여기는 listQna");			
			
		//	qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
		//	dao.getQnaList();			
			
		//	List<QnaDto> list = service.getQnaList();
		//	List<QnaDto> list = service.getBbsPagingList(paging);
		//	req.setAttribute("listQna", list);
			
			dispatch("qnabbslist.jsp", req, resp);			
			
						
		}else if(command.equals("qnaBbsDetail")) {
			System.out.println("여기는 qnadetail");
			String Sseq= req.getParameter("seq");
			int seq = Integer.parseInt(Sseq);	
			String likeid = req.getParameter("likeid");
			
			QnaDto dto = service.getBbs(seq);
						
		//	req.getSession().setAttribute("detailDto", dto);
		//	RequestDispatcher rd = req.getRequestDispatcher("qnabbsdetail.jsp");
			
			String action = req.getParameter("action").trim();
			req.getSession().setAttribute("detailDto", dto);
			RequestDispatcher rd = null;
			if(action.equals("update")) {
				rd = req.getRequestDispatcher("qnabbsupdate.jsp");
				System.out.println("update="+rd);
			}else if(action.equals("detail")){				
				rd = req.getRequestDispatcher("qnabbsdetail.jsp");	
				System.out.println("detail="+dto);
			}
			
			rd.forward(req, resp);
			
			
			
		
		}else if(command.equals("updateQnaAf")) {
			System.out.println("여기는 qnaUpdateAf Servlet");
			String Sseq= req.getParameter("seq");
			int seq = Integer.parseInt(Sseq);	
						
			QnaDto dto = new QnaDto();
			
			dto.setSeq(seq);
			dto.setTitle(req.getParameter("tItle").trim());			
			dto.setContent(req.getParameter("cOntent").trim());
			dto.setTagname(req.getParameter("tAg").trim());		
			boolean isS = service.qnaupdate(dto);			
			System.out.println("isS is " +isS);
			
			if(isS) {
				req.getSession().setAttribute("txtAlert", "수정 되었습니다.");
			}else {
				req.getSession().setAttribute("txtAlert", "수정이 안되었습니다.");
			}
			
			/*RequestDispatcher rd = req.getRequestDispatcher("qnabbslist.jsp");
			rd.forward(req, resp);*/
			resp.sendRedirect("qnaServlet?command=listQna");
			
		}else if(command.equals("updateQna")) {
			//detail과 합침
		}else if(command.equals("writeAnswer")) {
			//답변 내용 적용
			/*
			QnaDto dto = new QnaDto();
			String id = req.getParameter("iD").trim();
			String title = req.getParameter("tItle").trim();			
			String Sanswercount = req.getParameter("aNswerCount").trim();
			int answercount = Integer.parseInt(Sanswercount);
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			String tag = "tag";
			
			dto.setId(id);
			dto.setTitle(title);			
			dto.setAnswercount(answercount);
			dto.setSeq(seq);
			dto.setTag(tag);
			boolean isS = service.answer(seq, dto);
			resp.sendRedirect("qnaServlet?command=listQna");
			//resp.sendRedirect("qnabbswrite.jsp");
			*/
			System.out.println("writeanswer servlet");
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			
			//QnaAnswerDto dto = new QnaAnswerDto();
			QnaDto dto = new QnaDto();
			
			dto.setId(req.getParameter("iD").trim());
			//dto.setComment_num(comment_num);
			dto.setContent(req.getParameter("cOntent").trim());
			dto.setCommentcount(Integer.parseInt(req.getParameter("aNswerCount").trim()));
			
			service.writeAnswer(dto, seq);
			
		//	RequestDispatcher rd = req.getRequestDispatcher("qnabbsdetail.jsp");
			RequestDispatcher rd = req.getRequestDispatcher("qnabbslist.jsp");
            rd.forward(req, resp);
			
		}else if(command.equals("sorthe")) {
			List<QnaDto> list=service.getQnaList();
			String whatsort=req.getParameter("whatthings");
			System.out.println("sort해들어왔나?");
			req.setAttribute("whatsort", whatsort);
			req.setAttribute("sorthe", list);
			dispatch("qnabbslist.jsp", req, resp);
		}else if(command.equals("qnabbswrite")) {
			resp.sendRedirect("qnabbswrite.jsp");
		}
		// like
		else if(command.equals("likeimg")) {
			QnaDto dto=null;
			//처음받아오는값 본게시판 seq랑 좋아요누른아이디
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String likeid = req.getParameter("likeid");
			//아이디유무확인
			
			boolean b=service.isitlikeid(seq, likeid);
			if (b) {	//id있다 -> 좋아요취소
				//카운트-1
				service.dislikecount(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto=new QnaDto(1, 0);
				//저장되있는 id찾아서 삭제하기
				
				boolean isS=service.deleteLikeID(likeid, seq);
				
				if (isS) {
					System.out.println("likeid아이디삭제성공");
					//boolean is=service.getparent(seq);
					List<QnaDto> list=new ArrayList<>();
					/*if (is) {
						list=service.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=service.getdetail(seq);
						System.out.println("자료없다");
					}*/
					req.setAttribute("likeidyn", dto);
					req.setAttribute("whatlist", list);
					dispatch("techdetail.jsp", req, resp);
				}else {
					System.out.println("아이디삭제fail");
					List<QnaDto> list=service.getQnaList();
					req.setAttribute("qnabbslist", list);
					dispatch("qnabbslist.jsp", req, resp);
				}
			}else {		//id없다 -> 좋아요올려주기
				
				//저장되있는 id찾아서 거기에추가하기
				String origin=service.getLikeID(seq);
				likeid=origin+"-"+likeid+"-";
				//카운트+1
				service.likecountplus(seq);
				//likeid있으면 1(취소) id없으면2(추가)
				dto=new QnaDto(2, 0);//아이디추가
				boolean isS=service.addLikeID(new QnaDto(seq, likeid, ""));
				
				if (isS) {
					System.out.println("likeid아이디추가성공");
					System.out.println(seq+"fdjnfd");
					//boolean is=service.getparent(seq);
					List<QnaDto> list=new ArrayList<>();
					/*if (is) {
						list=service.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=service.getdetail(seq);
						System.out.println("자료없다");
					}*/
					req.setAttribute("likeidyn", dto);
					req.setAttribute("whatlist", list);
					dispatch("techdetail.jsp", req, resp);
				}else {
					System.out.println("아이디추가fail");
					List<QnaDto> list=service.getQnaList();
					req.setAttribute("techbbs", list);
					dispatch("techbbs.jsp", req, resp);
				}
			}
		}else if(command.equals("dislikeimg")) {
			QnaDto dto=null;
			//처음받아오는값 본게시판 seq랑 좋아요누른아이디
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String dislikeid = req.getParameter("dislikeid");
			//아이디유무확인
			boolean b=service.isitdislikeid(seq, dislikeid);
			if (b) {	//id있다 -> 싫어요취소
				//카운트==
				service.likecountplus(seq);
				//dislikeid있으면 1(취소) id없으면2(추가)
				dto=new QnaDto(0, 1);
				//저장되있는 id찾아서 삭제하기
				boolean isS=service.deleteaddDisLikeID(dislikeid, seq);
				
				if (isS) {
					System.out.println("dislikeid아이디삭제성공");
				//	boolean is=service.getparent(seq);
					List<QnaDto> list=new ArrayList<>();
					/*if (is) {
						list=service.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=service.getdetail(seq);
						System.out.println("자료없다");
					}*/
					req.setAttribute("dislikeidyn", dto);
					req.setAttribute("whatlist", list);
					dispatch("techdetail.jsp", req, resp);
				}else {
					System.out.println("아이디삭제fail");
					List<QnaDto> list=service.getQnaList();
					req.setAttribute("techbbs", list);
					dispatch("techbbs.jsp", req, resp);
				}
			}else {		//id없다 -> 좋아요올려주기
				
				//저장되있는 id찾아서 거기에추가하기
				String origin=service.getDisLikeID(seq);
				dislikeid=origin+"-"+dislikeid+"-";
				//카운트-1
				service.dislikecount(seq);
				//dislikeid있으면 1(취소) id없으면2(추가)
				dto=new QnaDto(0, 2);//아이디추가
				boolean isS=service.addDisLikeID(new QnaDto(seq, "", dislikeid));
				
				if (isS) {
					System.out.println("dislikeid아이디추가성공");
				//	boolean is=service.getparent(seq);
					List<QnaDto> list=new ArrayList<>();
					/*if (is) {
						list=service.getpdsdetail(seq);
						System.out.println("자료있다");
					}else {
						list=service.getdetail(seq);
						System.out.println("자료없다");
					}*/
					req.setAttribute("dislikeidyn", dto);
					req.setAttribute("whatlist", list);
					dispatch("techdetail.jsp", req, resp);
				}else {
					System.out.println("아이디추가fail");
					List<QnaDto> list=service.getQnaList();
					req.setAttribute("techbbs", list);
					dispatch("techbbs.jsp", req, resp);
				}
			}
		}else if(command.equals("scrapimg")) {
			
		}
		
		
		
	}
	
	
	
	
	
}
