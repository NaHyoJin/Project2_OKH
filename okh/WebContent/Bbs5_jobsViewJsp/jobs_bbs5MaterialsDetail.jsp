<%@page import="jobs_BBS5.jobsBbs5MaterialsService"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsServiceImpl"%>
<%@page import="jobs_BBS5.BbsMaterialsBeanDtoVO"%>
<%@page import="jobs_BBS5.BbsBoardBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>jobs_bbs5MaterialsDetail.jsp</title>
	<link rel="stylesheet" type="text/css" href="../_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
</head>
<body>
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
		<input type="button" class="bbs4">
		<input type="button" class="bbs5" id="jobs">
		<input type="button" class="bbs6" id="life">
	</div>
	
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "../User?command=login";
		});
		$("#account").click(function() {
			location.href = "../User?command=join";
		});
		
		$("#jobs").click(function() {
			location.href = "../jobs";
		});
		
		$("#life").click(function() {
			location.href = "../LifeBbs?command=life";
		});
	});
	</script>
	<%
		Object ologin = null;
	UserDto mem = null;
		ologin = session.getAttribute("login");
		
		if(ologin != null){
	%>
<!--  		
		<script type="text/javascript">
		alert("로그인해 주십시오");
		location.href = "../index.jsp";	
		</script>
 -->		
	<%
//		return;
		mem = (UserDto)ologin;
		}
		
	%>
	
	<%
	//한글 인코딩 관련 부분.
	request.setCharacterEncoding("UTF-8");
	
	//시퀀스 번호 받는 부분.
	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq.trim());
	
	jobsBbs5MaterialsServiceImpl dao = jobsBbs5MaterialsService.getInstance();
	
	//누르면 조회수 올라가는 부분. 일반 게시판 글 조회수. 3개를 다 따로 만들어야 한다.
	dao.readcount(seq);
	
	//dao 에서 가지고 오는 부분.
	BbsMaterialsBeanDtoVO bbs = dao.getPds(seq);
	System.out.println("가지고온 글 : " + bbs.toString());
	%>
	
	<script type="text/javascript">
	var myVar;
	$(function() {
		$("#btnDown").click(function() {
			myVar = setTimeout(_refrush, 1000);
		});
	});
	
	function _refrush() {
		location.reload();
		clearTimeout(myVar);
	}
	</script>
	
	<div class="wrap">
		<table border="2">
			<col width="200"><col width="500">
			<tr>
				<td>작성자</td>
				<td><%=bbs.getId() %></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><%=bbs.getTitle() %></td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<input type="text" value="<%=bbs.getTag() %>" data-role="tagsinput">
				</td>
			</tr>
			
			<tr>
				<td>작성일</td>
				<td><%=bbs.getWdate() %></td>
			</tr>
			
			<tr>
				<td>다운로드</td>
				<td>
					<input type="button" name="btnDown" id="btnDown" value="파일" 
				onclick="location.href='LifeBbs?command=fileDown&filename=<%=bbs.getFilename() %>&seq=<%=bbs.getSeq() %>'">
				</td>
			</tr>
			
			<tr>
				<td>조회수</td>
				<td><%=bbs.getReadcount() %></td>
			</tr>
			
			<tr>
				<td>정보</td>
				<td><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" readonly="readonly"><%=bbs.getContent() %>
					</textarea>
				</td>
			</tr>
		</table>

<%
	if(mem != null){
		
	
%>
		<button type="button" 
			onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>
		
		<% if(bbs.getId().equals(mem.getId())){ 
		%>
		<button type="button" 
			onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
		<button type="button" 
			onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>
		<%
		} 
		%>
<%
	}
%>
<br><br>
	<button type="button" onclick="normalbbshome()">home</button>
	</div>
	
	
	<script type="text/javascript">
	//수정.
	function updatebbs(seq) {
		location.href = "../BBSmaterialsController?command=update&seq=" + seq;
	}
	
	function answerbbs(seq) {
		location.href = "LifeBbs?command=answer&seq=" + seq;
	}
	
	//삭제
	function deletebbs(seq) {
			 
	        var msg = "정말 삭제 하시겠습니까?";
	        
	        var delseq = seq;
	        
	        if (confirm(msg)) {//confirm 이거 함수 사용해서 작동 완료.
	              
	        
	  /////////////////////////////////////////////// 글삭제 부분에서 시작 180227 

	         <%
	       //글 삭제하는 부분 실질적으로는 DB에 남아있고 보이지만 않게 한다.
//	       response.sendRedirect("../BBSboardController?command=detele&seq="+seq);
//	       location.href = "../BBSboardController?command=detele&seq=" + seq;
//	        	boolean b = dao.bbsDelete(seq);

//				if(b == true) {
//					out.println("이 글은 성공적으로 삭제되었습니다.");
			%>

	            alert("성공적으로 글 삭제.");
				location.href = "../BBSmaterialsController?command=detele&seq=" + seq;

				
			<%
//				}else {
			%>
/* 					alert("글 삭제 실패.");
					location.href = "bbslist.jsp";
 */				
				<%
//					}
	        	%>

				 

				
	        } else {// 취소 누른 부분
	            // no click
//	         alert("글 삭제 취소");
	            return;
			}		
	}// deletebbs
	
	
	// home버튼
	function normalbbshome() {
		location.href = "../BBSmaterialsController?command=list";
	}
	</script>

</body>
</html>



<%-- <%@page import="jobs_BBS5.jobsBbs5MaterialsService"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsServiceImpl"%>
<%@page import="user.UserDto"%>
<%@page import="jobs_BBS5.BbsMaterialsBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDao"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDaoImpl"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	//디테일 쪽으로 넘어온 시퀀스 번호 받는 부분.
    	String sseq = request.getParameter("seq");
    	int seq = Integer.parseInt(sseq);//문자열을 int형으로 변환 부분.
    	
    	jobsBbs5MaterialsServiceImpl dao = jobsBbs5MaterialsService.getInstance();
    	BbsMaterialsBeanDtoVO pdsdto = dao.getPds(seq);//가지고 오는 부분.
    	
    	dao.readcount(seq);//조회수 부분.
    %>
    
    <%
  //인간 정보 관련 세션 부분. 로그인 안하고 그냥 들어와서 볼 수 있으니.
		Object ologin = null;
		UserDto mem = null;
		ologin = session.getAttribute("login");
		
		if(ologin != null){
	%>
<!--  		
		<script type="text/javascript">
		alert("로그인해 주십시오");
		location.href = "../index.jsp";	
		</script>
 -->		
	<%
//		return;
		mem = (UserDto)ologin;
		}
		
	%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobs_bbs5MaterialsDetail.jsp</title>

	<style type="text/css">
		.center{
			margin: auto;
			width: 60%;
			border: 3px solid #8ac007;
			padding: 10px;
		}
		
		.logout{
			text-align: right;
			
		}
	</style>
	
</head>
<body>


	디테일로 넘어온 seq 번호 확인 부분 : <%=seq %>

 
 	<a href="../logoutAction.jsp" style="text-decoration: none" class="logout">로그아웃</a>

	<h1>H1 태그 자료실 상세 파일 보기</h1>

	<div class="center">

		<table border="2">
		<col width="200"><col width="500">

		<tr>
			<td>작성자</td>
			<td><%=pdsdto.getId() %></td>
		</tr>

		<tr>
			<td>제목</td>
			<td><%=pdsdto.getTitle() %></td>
		</tr>

		<tr>
			<td>작성일</td>
			<td><%=pdsdto.getRegdate() %></td>
		</tr>

		<tr>
			<td>조회수</td>
			<td><%=pdsdto.getReadcount() %></td>
		</tr>

		<tr>
			<td>정보</td>
			<td><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
		</tr>

		<tr>
			<td>내용</td>
			<td>
			<textarea rows="10" cols="50"
			name="content"><%=pdsdto.getContent() %></textarea>
			</td>
		</tr>

		</table> 
<br>
<button type="button" onclick="mainpds()">자료실 처음으로</button>

<% 
	if(pdsdto.getId().equals(mem.getId())){
%>
<button type="button" 
	onclick="updatebbs('<%=pdsdto.getSeq() %>')">수정</button>
<button type="button"
	onclick="deletebbs('<%=pdsdto.getSeq() %>')">삭제</button>	
<%
	} 
%>

	</div>



<script type="text/javascript">

	//수정
	function updatebbs( seq ) {
		location.href = "pdsupdate.jsp?seq=" + seq;
	}
	
	
	function mainpds() {
		location.href = "../BBSmaterialsController?command=list";
	}

 	
 	//삭제
	function deletebbs( seq ) {
		location.href = "../BBSmaterialsController?command=detele&seq=" + seq;
	}


</script> 	
 
 	
</body>
</html> --%>