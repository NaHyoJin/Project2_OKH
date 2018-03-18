<%@page import="studysrc.iCalendar"%>
<%@page import="studysrc.CalendarDao"%>
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
	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq);
	iCalendar dao = CalendarDao.getInstance();
	boolean isS = dao.deletecalendar(seq);
	if(isS){
		%>
		<script type="text/javascript">
		location.href="calendar.jsp";
		</script>		
	<% 
		}else{
	%>			
		<script type="text/javascript">
		location.href="calendar.jsp";
		</script>	
	<%
		}
	%>

</body>
</html>