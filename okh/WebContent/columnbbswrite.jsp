<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_write.css?ver=1.45">
<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
</head>
<body bgcolor="#fcfbfb">

<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
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

<div class="titlediv"><h2>새글쓰기</h2><br>
	</div>
<div class="wrap">
	
<form action="Controller?command=writeAf" method="post">
<div class="myinfo">

	<p id="test" align="left"><%=mem.getId() %></p>
	 <input type="hidden" name="id" value="<%=mem.getId()%>">
	 <input type="hidden" name="command" value="columnbbswriteAf">
</div>

<div class="writearea">
	 	<input type="text" name="title" value="" placeholder="제목을 입력해 주세요." class="form-control" id="title" /><br>
	 
	 	<textarea name="content" id="summernote"></textarea><br>
		 <input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
	<button id="write" style="float: right" class="btn btn-success btn-wide" onclick="write1();">글추가</button>
		</div>
</div>


<%-- <table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td>
		<input type="text" name="id" size="50" readonly="readonly"
			value="<%=mem.getId() %>">
	</td>
</tr>
<tr>
	<td>제목</td>
	<td>
		<input type="text" name="title" size="50">
	</td>
</tr>

<tr>
	<td>내용</td>
	<td>
		<textarea rows="10" cols="50" name="content"></textarea>
	</td>
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="글쓰기">
	</td>
</tr>

</table> --%>
</form>	


<a href="columnbbslist.jsp">글목록</a>

</body>
</html>










