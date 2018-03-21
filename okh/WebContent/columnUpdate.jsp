<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@page import="columnBbs.PdsDto"%>
<%@page import="columnBbs.PdsDao"%>
<%@page import="columnBbs.iPdsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
if(ologin == null){
	%>
	<script type="text/javascript">
	alert("로그인해 주세요");
	location.href = "index.jsp";
	</script>
	<%
	return;
}
mem = (UserDto)ologin;
	%>
<%
request.setCharacterEncoding("UTF-8");

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

iColumnBbsDao dao = ColumnBbsDao.getInstance();
ColumnBbsDto dto = dao.getBbs(seq);
%>

<div class="wrap">
	<form action="Controller" method="GET">
	<table border="1">
		<col width="200"><col width="500">	
	  	<tr>
	  		<td>아이디</td>
	  		<td>
	  			<input type="hidden" name="command" value="updateAf">
	  			<input type="hidden" name="seq" value="<%=sseq%>">
	  			<input type="text" id="id" name="id" size="50" readonly="readonly" value="<%=dto.getId() %>">	
	  		</td>
	  	</tr>
		
		<tr>
			<td>제목</td>
			<td>
				<input type="text" id="titel" name="title" size="50" value="<%=dto.getTitle() %>">
			</td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="10" cols="50" id="summernote" name="content"><%=dto.getContent() %></textarea>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="submit" value="수정하기">
			</td>
		</tr>
	</table>
	</form>
</div>






</body>
</html>