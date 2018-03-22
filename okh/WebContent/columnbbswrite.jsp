<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="_lifemain.css?ver-1.62">
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
<!-- 로그인 세션 -->
<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>
<!-- 메뉴 -->
	<div class="menu">
		<%
		if(ologin == null){
		%>
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<%
		}else{
		%>
		<div class="actionlogin">
			<span><%=mem.getId() %></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover">	
		</div>
		<%
		}
		%>
		<input type="button" class="bbs1" id="qnabbs">				<!-- 박형태 -->
		<input type="button" class="techbbs_hjh" id="techbbs">		<!-- 황준현 -->
		<input type="button" class="bbs3"  id="column">				<!-- 정재흥 -->		
		<input type="button" class="bbs4" >							<!-- 장문석 -->
		<input type="button" class="bbs5" id="jobs">				<!-- 나효진 -->
		<input type="button" class="bbs6" id="life">				<!-- 정병찬 -->
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#qnabbs").click(function() {
			location.href="qnaServlet?command=listQna";
		});	
		$("#techbbs").click(function() {
			location.href="TechbbsController?command=techbbs";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	//  정재흥 column 부분
		$("column").click(function name() {
			location.href="Controller?command=column"
		});
	});
	</script>
<a href="logout.jsp">로그아웃</a>
<!-- View -->
<div class="titlediv">
	<h2>새 글 쓰기</h2>
</div>
<div class="wrap">
		<div class="myinfo">
			<p id="test" align="left"><%=mem.getId() %></p>
		</div>
	<div class="writearea">
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="writeAf">
			<input type="hidden" name="id" value="<%=mem.getId() %>">
			<input type="text" class="form-control" id="title" name="title" placeholder="제목를 입력해 주세요."><br>
			
			<textarea name="content" id="summernote"></textarea><br>
		 	<input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
			<input type="submit" style="float: right" class="btn btn-success btn-wide" value="글추가">
		</form>
	</div>
</div>
	<!-- 새 글 쓰기 취소 -->
	<script type="text/javascript">
	function gotobbs() {
		location.href = "Controller?command=column";
	}
	</script>
	<!-- summernote -->
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,
	             minHeight: null,
	             maxHeight: null,
	             focus: true
	     });
	});
	</script>
</body>
</html>
		
		
		