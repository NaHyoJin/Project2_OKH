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
<title>answer.jsp</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 
<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
input{
	size: 50;
}
</style>
 -->

<link rel="stylesheet" type="text/css" href="style.css"> 


</head>
<body>
<a href="logout.jsp">로그아웃</a>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

 iColumnBbsDao dao = ColumnBbsDao.getInstance();
 ColumnBbsDto bbs = dao.getBbs(seq);
%>

<div class="center">

<h3 align="center">칼럼</h3>

<table border="2" align="center">
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
name="content" readonly="readonly"><%=bbs.getContent() %></textarea>
</td>
</tr>

</table> 

<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>

<h2>답글달기</h2>
<!--location.href = "Controller?command=answer&seq=10";  -->
<form action="Controller" method="post">
<input type="hidden" name="command" value="answerAf">
<input type="hidden" name="seq" value="<%=bbs.getSeq() %>">

<table border="1">
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
		<input type="submit" value="답글">
	</td>
</tr>

</table>
</form>
</div>

<a href="bbslist.jsp">글목록</a>

</body>
</html>





