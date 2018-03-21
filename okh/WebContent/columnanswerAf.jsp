<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>answerAf.jsp</title>
</head>
<body>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

iColumnBbsDao dao = ColumnBbsDao.getInstance();
boolean isS = dao.answerBbs(seq, new ColumnBbsDto(id, title, content));

if(isS){ 
	%>
	<script type="text/javascript">
	alert("답글 입력 성공!");
	location.href = "columnbbslist.jsp";
	</script>
	<%
}else{
	%>
	<script type="text/javascript">
	alert("답글을 등록하지 못했습니다");
	location.href = "columnbbslist.jsp";
	</script>	
<%
}
%>

</body>
</html>








