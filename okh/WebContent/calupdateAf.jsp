<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.iCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
String sseq = request.getParameter("seq");
String title = request.getParameter("title");
String content = request.getParameter("content");
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
String hour = request.getParameter("hour");
String min = request.getParameter("min");
System.out.println(title+content+year+month+day+hour+min+"seq:"+sseq);
String rdate = year+two(month)+two(day)+two(hour)+two(min);
int seq = Integer.parseInt(sseq);
iCalendar dao = CalendarDao.getInstance();
boolean isS = dao.updatecalendar(title, content, rdate, seq);

if(isS){
%>
<script type="text/javascript">
alert("글 수정 성공");
location.href="calendar.jsp";
</script>		
<% 
}else{
%>			
<script type="text/javascript">
alert("글 수정을 다시 하십시오");
location.href="calendar.jsp";
</script>	
<%
}
%>



</body>
</html>