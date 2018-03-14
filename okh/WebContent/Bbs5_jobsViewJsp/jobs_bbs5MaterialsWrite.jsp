<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobs_bbs5MaterialsWrite.jsp</title>
</head>
<body>
	jobs_bbs5MaterialsWrite.jsp 자료실 글 작성 화면 부분.
	
	<%

	//로그인 정보 확인 부분.
	Object ologin = session.getAttribute("login");
    
    UserDto mem = null;
    
	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}
	
%>

<h3>자료 올리기</h3>

<form action="pdsupload.jsp" method="post" 
	enctype="multipart/form-data">
	
	<table border="1" bgcolor="gray">
	<col width="200"><col width="500">
	
	<tr>
		<td>아이디</td>
		<td>
			<%=mem.getId() %>
			<input type="hidden" name="id" value="<%=mem.getId() %>">
		</td>
	</tr>
	
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="title" size="50">
		</td>
	</tr>
	
	<tr>
		<td>파일 업로드</td>
		<td>
			<input type="file" name="fileload" style="width:400px">
		</td>
	</tr>
	
	<tr>
		<td>내용</td>
		<td>
			<textarea rows="20" cols="50" name="content"></textarea>
		</td>
	</tr>
	
	<tr align="center">
		<td colspan="2">
			<input type="submit" value="올리기">
		</td>
	</tr>
	
	</table>

</form>
	
</body>
</html>