<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbsdetail.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.41">
</head>
<body>
<a href="logout.jsp">로그아웃</a>

<h1>상세 글보기</h1>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);

 iColumnBbsDao dao = ColumnBbsDao.getInstance();
 ColumnBbsDto bbs = dao.getBbs(seq);
dao.readcount(seq);
%>

<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;

List<ColumnBbsDto> whatlist=(List<ColumnBbsDto>)request.getAttribute("whatlist");

%>



<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3"  id="column"><!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs"> <!-- 장문석 study -->
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	

	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.

			$("#login").click(function() {
				location.href="User?command=login";
			});
	
			$("#account").click(function() {
				location.href="User?command=join";
			});
			
			//QNA
			$("#qnabbs").click(function() {
				location.href="qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="second.jsp";
			});
	
			$("#techbbs").click(function() {
				location.href="TechbbsController?command=techbbs";
			});
	
			$("#life").click(function() {
				location.href="LifeBbs?command=life";
			});
			/* 장문석  study*/
			$("#combbs").click(function () {
				location.href = "CommunityControl?command=list";
			});
			
	 
			//게시판5 나효진 jobs 부분.
			$("#jobs").click(function () {
				location.href="jobs";
			});
			
			//  정재흥 column 부분
			$("#column").click(function () {
				location.href= "Controller?command=column";
			});

	 
		});
	</script>

<div class="titlediv"><h2>칼럼</h2><br>
<button onclick="location.href='TechbbsController?command=techbbs1'" 
class="create btn btn-success btn-wide pull-right ">새 글 쓰기</button>
</div>

<div class="wrap">
	<div class="myinfo"><%=mem.getId() %><%=whatlist.get(0).getWdate() %><br>
		<p class="myinfo_icon"></p>
</div>
	<div class="contentareawrap">
	<div class="contentarea">
<br>
		<h2 class="title">
		<%=whatlist.get(0).getTitle() %>
		</h2>
	<hr>
	<article class="content">
		<%=whatlist.get(0).getContent() %>
		
	</article>
	
<!-- 답글게시판뿌리기 -->
	<div class="qna">

	<form action="TechRepbbsController" id="upda">
		<table border="1" class="reptable">
		<col width="850"><col width="175">






















<%-- <table border="2">
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
	<td>작성일</td>
	<td><%=bbs.getWdate() %></td>
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
<textarea rows="10" cols="50"
name="content"><%=bbs.getContent() %></textarea>
</td>
</tr>

</table> 

<button type="button" onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>

<% if(bbs.getId().equals(mem.getId())){ %>

<button type="button" 
	onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
<button type="button"
	onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>
<button type="button" 
onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>	
<%
}else if(!(bbs.getId().equals(mem.getId()))){
%>
<button type="button" 
onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>	
<%
}
%>

</div>

<script type="text/javascript">
function updatebbs( seq ) {
	location.href = "Controller?command=update&seq=" + seq;
}

function answerbbs( seq ) {
	location.href = "Controller?command=answer&seq=" + seq; 
}

function deletebbs(seq) {
	location.href= "Controller?command=del&seq=" + seq;
}


</script>
 --%>


</body>
</html>





