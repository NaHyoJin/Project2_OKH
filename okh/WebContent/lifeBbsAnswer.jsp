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
	<link rel="stylesheet" type="text/css" href="_main.css">
	<link rel="stylesheet" type="text/css" href="_lifewrite.css?ver=1.1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>Insert title here</title>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
		<input type="button" class="bbs4">
		<input type="button" class="bbs5" id="life">
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	});
	</script>
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = null;
	if(ologin == null){
		%>
		<script type="text/javascript">
		alert("로그인해 주십시오");
		location.href = "index.jsp";	
		</script>	
		<%
		return;
	}
	mem = (UserDto)ologin;
	%>
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,                 // set editor height
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true                  // set focus to editable area after initializing summernote
	     });
	});
	</script>
	<%
	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq.trim());
	
	ILifeBbsDao dao = LifeBbsDao.getInstance();
	LifeBbsDto bbs = dao.getDetailBbs(seq);
	%>
	
	<div class="wrap">
		<h2>부모글</h2>
		
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
					<textarea rows="10" cols="50" name="content" readonly="readonly"><%=bbs.getContent() %>
					</textarea>
				</td>
			</tr>
		</table>
		<div class="titlediv"><h2>답글 쓰기</h2>
		</div>
		<div class="wrap">
			<div class="myinfo">
				<p id="test" align="left"><%=mem.getId() %></p>
			</div>
			<div class="writearea">
			<form action="lifeBbsUpload.jsp" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="<%=mem.getId() %>">
				<input type="text" class="form-control" id="title" name="title" placeholder="제목를 입력해 주세요."><br>
				<input type="text" id="tag" name="tag" value="" placeholder="Tags," class="form-control" data-role="tagsinput" onclick="make_tag()"><br><br>
				<textarea name="content" id="summernote"></textarea><br>
			 	<input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
				<input type="submit" style="float: right" class="btn btn-success btn-wide" value="글추가">
			</form>
			</div>
			
		</div>
	</div>

</body>
</html>