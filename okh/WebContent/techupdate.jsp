<%@page import="java.util.List"%>
<%@page import="techbbs.TechbbsDto"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	
	<title>index.jsp</title>
	
	<link rel="stylesheet" type="text/css" href="_main.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.33">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>


	<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" >
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
			
			
	/* 
			//columns
			$("#").click(function() {
				location.href="";
			});
	 */
	 
			//게시판5 나효진 jobs 부분.
/* 			$("#jobs").click(function() {
				location.href="main.BBSHWCodingController";
			});
 */	 
			
			$("#jobs").click(function name() {
				location.href="jobs";
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
	<%
	request.setCharacterEncoding("UTF-8");

	int seq = (int)request.getAttribute("seq");
	TechbbsServiceImpl tservice=TechbbsService.getInstance();
	List<TechbbsDto> techlist = tservice.getdetail(seq);
	%>
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,                 // set editor height
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true                  // set focus to editable area after initializing summernote
	     });
	     $('#summernote').summernote();
	});
	</script>
	
	<div class="wrap">
		<form action="TechbbsController" method="POST">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="hidden" name="command" value="updateAf">
					<input type="hidden" name="seq" value="<%=seq %>">
					<input type="text" id="id" name="id" size="50" readonly="readonly" value="<%=techlist.get(0).getId() %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" size="50" value="<%=techlist.get(0).getTitle() %>">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
				<%
				String[] tagnames=tservice.getTagName(techlist.get(0).getTagname());
				
				for(int i=0;i<tagnames.length;i++){
				%>
					<%=tagnames[i] %>
				<%
				}
				%>
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" id="summernote" name="content"><%=techlist.get(0).getContent() %></textarea>
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