<%@page import="studysrc.iCalendar"%>
<%@page import="user.UserDto"%>
<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.CalendarDto"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calllist.jsp</title>
</head>
<body>

<%!
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();	// 1 ~ 9 -> 01
}

// yyyy-mm-dd hh:mm:ss	Timestemp <- String
// yyyy-mm-dd 	Date <- String
public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	String s = mdate.substring(0, 4) + "-" 	// yyyy
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " " 	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00"; 
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);	
} 

%>

<%
UserDto user = (UserDto)session.getAttribute("login");

String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");

 
String rdate = year + two(month) + two(day);

iCalendar dao = CalendarDao.getInstance();
List<CalendarDto> list = dao.getDayList(user.getId(), rdate);
System.out.println(list.toString());

%>

<table border="1">
<col width="100"><col width="200"><col width="450"><col width="50">

<tr bgcolor="#09bbaa">
<td>번호</td><td>시간</td><td>제목</td><td>삭제</td>
</tr>

<%
for(int i = 0;i < list.size(); i++){
	CalendarDto caldto = list.get(i);
	%>
	<tr>
		<td><%=i + 1 %></td>
		<td> <%= toDates(caldto.getRdate())  %>  </td>
		<td>
			<a href="caldetail.jsp?seq=<%=caldto.getSeq() %>"><%=caldto.getTitle() %></a>
		</td>	
		<td>
			<form action="caldel.jsp" method="post">
				<input type="hidden" name="seq" value="<%=caldto.getSeq() %>">
				<input type="submit" value="일정삭제">
			</form>
		</td>
	</tr>	
	<%
}
%>
</table>

<%
String url = String.format("%s?year=%s&month=%s", 
						"calendar.jsp", year, month);
%>
<a href="<%=url %>">일정보기</a>


</body>
</html>









