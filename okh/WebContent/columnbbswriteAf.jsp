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
<title>bbswriteAf.jsp</title>
</head>
<body>

<%
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");

 iColumnBbsDao dao =  ColumnBbsDao.getInstance();

 boolean isS = dao.writeBbs(new ColumnBbsDto(id, title, content));
 if(isS){
 %>
	<script type="text/javascript">
	alert("글 입력 성공!");
	location.href = "columnbbslist.jsp";	
	</script>	 
<%
}else{
%>	
	<script type="text/javascript">
	alert("글 입력을 다시 하십시오");
	location.href = "columnbbslist.jsp";
	</script>
 	
<%
}
%>




</body>
</html>








