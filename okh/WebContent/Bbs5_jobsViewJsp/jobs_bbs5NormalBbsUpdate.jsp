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
	<title>jobs_bbs5NormalBbsUpdate.jsp</title>
	<link rel="stylesheet" type="text/css" href="../_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js-->
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
		<input type="button" class="bbs5" id="jobs">
		<input type="button" class="bbs6" id="life">
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		
		//나효진 게시판5.
		$("#jobs").click(function() {
			location.href = "../jobs";
		});
		
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
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
	//인코딩 부분.
	request.setCharacterEncoding("UTF-8");

	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq.trim());
	
	jobsBbs5ModelServiceImpl dao = jobsBbs5ModelService.getInstance();
	
	//디테일 내용 가지고 오는 것.
	BbsBoardBeanDtoVO bbs = dao.detailbbs(seq);
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
	
	<div class="wrap">
		<form action="../BBSboardController" method="POST">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="hidden" name="command" value="updateAf">
					<!-- 히든으로 시퀀스 숨겨서 보내는 부분. -->
					<input type="hidden" name="seq" value="<%=sseq %>">
					<input type="text" id="id" name="id" size="50" 
						readonly="readonly" value="<%=mem.getId() %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" 
					size="50" value="<%=bbs.getTitle() %>">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<!-- name="tag"값이 없어서 null 예외나왔음. -->
					<input type="text" id="tag" name="tag" value="<%=bbs.getTag() %>" 
					data-role="tagsinput">
				</td>
			</tr>
			<tr>
				<td>파일 업로드</td>
				<td>
					<input type="file" name="filename" style="widows: 400px">
				</td>
			</tr>			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" id="summernote" 
					name="content"><%=bbs.getContent() %></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
				</td>
			</tr>
		</table>
		</form>
	</div>

</body>
</html>