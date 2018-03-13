
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

	
	<!-- <link rel="stylesheet" type="text/css" href="/main/_qnahbbs.css"> -->
	<link rel="stylesheet" type="text/css" href="/main/_main.css">
</head>
<body>

<div class="menu">
<jsp:include page="menuinclude.jsp">
<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>


<div class="wrap">
<div align="center">


<h3>여기는 writeQna입니다.</h3>

<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>
</div>


<div class="border" align="center">
<form action="qnaServlet" method="get">
<table>

<tr>현제 접속 아이디는:<%=mem.getId() %> </tr>

<tr>
	<td>
		<input type="hidden" name="command" value="writeQna">
		<input type="hidden" name="iD" value="<%=mem.getId() %>">
		
	</td>
</tr>

<tr>
	<td>
		<input type="text" name="tItle" placeholder="제목을 입력하세요">
	</td>	
</tr>

<tr>
	<td>
		<input type="text" name="tAg" placeholder="tag를 입력하세요">
	</td>
</tr>	

<tr>
	<td>
		<textarea rows="10" cols="50" name="cOntent" id="summernote" style="resize: none;"></textarea>
		
		
	</td>
</tr>

<tr>
	<td>
		<input type="reset" value="취소" onclick="location.href='qnaServlet?command=listQna'">
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