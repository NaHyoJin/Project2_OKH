<%@page import="studysrc.CalendarDto"%>
<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();
}
%>

<%
UserDto user = (UserDto)session.getAttribute("login");
iCalendar dao = CalendarDao.getInstance();
String id = request.getParameter("id");
String title = request.getParameter("title");
String content = request.getParameter("content");
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");

String rdate = year+two(month)+two(day)+two(hour)+two(min);

boolean isS = dao.writecalendar(new CalendarDto(id,title,content,rdate));
if(isS){
%>
<script type="text/javascript">
alert("글 입력 성공");
location.href="calendar.jsp";
</script>		
<% 
}else{
%>			
<script type="text/javascript">
alert("글 입력을 다시 하십시오");
location.href="calendar.jsp";
</script>	
<%
}
%>

</body>
</html>