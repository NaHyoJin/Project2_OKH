<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="_main.css">
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
	
	<script type="text/javascript">
	$(document).ready(function() {
		var i = 1;
		$('#uploadBtn').click(function() {
			$('#fileload' + i).attr('type', 'file');
			if(i < 5){
				i++;
			}
		});
	});
	</script>
	
	<div class="wrap">
		<a href="logout.jsp">로그아웃</a>
		<h1>새 글 쓰기</h1>
		
		<h2><%=mem.getId() %></h2>
	
		<form action="lifeBbsUpload.jsp" method="post" enctype="multipart/form-data">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" id="id" name="id" size="50" readonly="readonly" value="<%=mem.getId() %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" class="form-control" id="title" name="title" size="50" placeholder="제목를 입력해 주세요.">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<input type="text" size="50" id="tag" name="tag" value="" 
						placeholder="Tags," class="form-control" data-role="tagsinput" 
						style="display: none;" onclick="make_tag()">
				</td>
			</tr>
	
			<tr>
				<td>파일 업로드</td>
				<td>
					<input type="file" name="fileload" style="widows: 400px">
					<input type="hidden" id="fileload1" name="fileload1" style="widows: 400px">
					<input type="hidden" id="fileload2" name="fileload2" style="widows: 400px">
					<input type="hidden" id="fileload3" name="fileload3" style="widows: 400px">
					<input type="hidden" id="fileload4" name="fileload4" style="widows: 400px">
					<br>
					<input type="button" id="uploadBtn" value="추가">
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" id="summernote" value=""></textarea>
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