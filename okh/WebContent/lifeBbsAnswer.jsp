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
	<link rel="stylesheet" href="css/custom.css">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
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
		<h2>답글달기</h2>
		
		<form action="LifeBbs" method="post">
		<input type="hidden" name="seq" value="<%=bbs.getSeq() %>">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="hidden" name="command" value="answerAf">
					<input type="text" name="id" size="50" readonly="readonly" value="<%=mem.getId() %>">
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
			
		<a href="bbslist.jsp">글 목록</a>
	</div>

</body>
</html>