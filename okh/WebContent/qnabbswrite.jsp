
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeQna.jsp</title>
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> 
	
	<!-- 태그 이용하기위한-->
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<script src="js/bootstrap-tagsinput.js"></script>
	
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_write.css?ver=1.48">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.36">
<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
</head>
<body bgcolor="#fcfbfb">

<%-- <div class="menu">
<jsp:include page="menuinclude.jsp">
<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>
 --%>
<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>
<!-- 인클루드 부분 -->
	<div class="menu">
			<%
if(ologin == null){	//로그인안한상태
	%>
	<input type="button" class="homebtn" onclick="location.href='index.jsp'">
	<input type="button" class="login" id="login">
	<input type="button" class="account" id="account">

		<%
}else{
	
%>
<input type="button" class="homebtn" id="homebtn">
<div class="actionlogin">

	<span><%=mem.getId() %></span>
	<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
</div>
<%
}
%>
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" >
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	

	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.
			$("#homebtn").click(function() {
				location.href="main.jsp";
			});
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
<div class="wrap">



<div class="border" align="center">
<form action="qnaServlet" method="get">
<table>

<tr>새 글 쓰기</tr>
<br><br>

<tr>
	<td>
		<input type="hidden" name="command" value="writeQna">
		<!-- <input type="hidden" name="command" value="qnawrite"> -->
		<input type="hidden" name="iD" value="<%=mem.getId() %>">
		
	</td>
</tr>

<tr>
	<td>
		<input type="text" name="tItle"  placeholder="제목을 입력하세요" style="width: 800px"><br><br>
	</td>	
</tr>

<tr>
	<td>
		<input type="text" name="tAg" id="tAg" data-role="tagsinput" style="width: 800px" value="QnA" ><br><br>
	</td>
</tr>	

<tr>
	<td>
		<textarea rows="10" cols="50" name="cOntent" id="summernote" ></textarea>
		
		
	</td>
</tr>

<tr>
	<td>
		<input type="reset" value="취소" onclick="location.href='qnaServlet?command=listQna'" >
		<input type="submit" value="등록">
	</td>
</tr>

</table>
</form>
</div>
</div>

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


</body>
</html>