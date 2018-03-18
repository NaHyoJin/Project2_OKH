<%@page import="user.UserDto"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="user.UserDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.CalendarDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calendar</title>
<style type="text/css">
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }
  tr{
  	
  }

</style>
</head>
<body>

<%!
	public boolean nvl(String msg){
	return msg == null || msg.trim().equals("")?true:false;
}

// 날짜를 클릭하면 그날의 일정을 모두 보이게 하는 calllist.jsp 로 이동시키는 함수
	public String calllist(int year, int month, int day){
		String s = "";
		s += String.format("<a href='%s?year=%d&month=%d&day=%d'>",
					"calllist.jsp", year, month, day);
		
		s += String.format("%2d",day);	//1 2 ~ 9 10
		s += "</a>";
		return s;
	}

//일정 기입하기위해서 pen이미지를 클릭하면, calwrite.jsp 로 이동
public String showPen(int year, int month, int day){
	String s = "";
	String url = "calwrite.jsp";
	String image = "<img src='image/pen.gif'>";
	
	s = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", url, year, month, day,image);
	
	return s;
	
}
// 1글자를 2글자로 1 > 01
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();
}
// 내용이 길면 도트3개로 처리 내용 + ...
public String dot3(String msg){
	String s = "";
	if(msg.length() >= 5){
		s = msg.substring(0, 5);
		s += "...";
		
	}else{
		s = msg.trim();
	}
	return s;
}

// 각 날짜별로 테이블을 생성하는 함수
public String makeTable(int year, int month, int day, List<CalendarDto> list){
	String s = "";
	String dates = (year+"")+two(month+"")+two(day+"");// 20180306
	s += "<table>";
	s += "<col width='98'>";
	
	for(CalendarDto dto : list){
		if(dto.getRdate().substring(0,8).equals(dates)){
			s += "<tr bgcolor='yellow'>";
			s += "<td>";
			s += "<a href='caldetail.jsp?seq="+dto.getSeq()+"'>";
			s += "<font style='font-size:8; color:red'>";
			s += dot3(dto.getTitle());
			s += "</font>";
			s += "</a>";
			s += "</td>";
			s += "</tr>";
		}
	}
	s += "</table>";
	return s;
}
%>
<div align="center">
<h1>스케쥴</h1>
<%
	Calendar cal = Calendar.getInstance();
cal.set(Calendar.DATE, 1);
String syear = request.getParameter("year");
String smonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);
if(nvl(syear) == false){
	year = Integer.parseInt(syear);
}

int month = cal.get(Calendar.MONTH)+1;
if(!nvl(smonth)){
	month = Integer.parseInt(smonth);
	
}

if(month <1){
	month = 12;
	year--;
}

if(month>12){
	month = 1;
	year++;
}
cal.set(year, month-1, 1); 	//연 월 일 셋팅 완료

// 요일 1~7 일요일 1 토요일 7

int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

// << 연도
String pp = String.format("<a href='%s?year=%d&month=%d'><img src='image/left.gif'></a>",
							"calendar.jsp",year-1,month);
// < 월
String p = String.format("<a href='%s?year=%d&month=%d'><img src='image/prec.gif'></a>",
							"calendar.jsp",year,month-1);
// > 월
String n = String.format("<a href='%s?year=%d&month=%d'><img src='image/next.gif'></a>",
							"calendar.jsp",year,month+1);
// >> 연도
String nn = String.format("<a href='%s?year=%d&month=%d'><img src='image/last.gif'></a>",
							"calendar.jsp",year+1,month);

UserDto user = (UserDto)session.getAttribute("login");
iCalendar dao = CalendarDao.getInstance();

List<CalendarDto> list = dao.getCalendarList(user.getId(), year+two(month+""));

%>

<table border="1">
	<col width="100"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100">
	<tr height="100" bgcolor="gray">
		<td colspan="7" align="center">
			<%=pp %>&nbsp;&nbsp;<%=p %>&nbsp;
			<font color="black" style="font-size: 50">
				<%=String.format("%d년&nbsp;&nbsp;%s월", year,month) %>
			</font>
		&nbsp;<%=n %>&nbsp;&nbsp; <%=nn %>
		</td>
	</tr> 
	
	<tr height="100">
		<td align="center"> 일 </td>
		<td align="center"> 월 </td>
		<td align="center"> 화 </td>
		<td align="center"> 수 </td>
		<td align="center"> 목 </td>
		<td align="center"> 금 </td>
		<td align="center"> 토 </td>
	</tr>
	<tr height="100" align="left" valign="top">
	<%
	//위쪽의 빈칸
		for(int i=1;i<dayOfWeek;i++){
	%>
				<td bgcolor="#c6c6c6">&nbsp;</td>
	<% 
		}
	
	//실제날짜
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	for(int i = 1;i <= lastDay;i++){
	%>
		<td>
			<%=calllist(year, month, i)%> &nbsp;<%=showPen(year, month, i) %> <%=makeTable(year, month, i, list) %>
		</td>
	<%
		if((i+dayOfWeek-1)%7==0 && i != lastDay){
			%>
				</tr><tr height="80"  align="left" valign="top">
			<%
		}
	}
	
	// 밑칸
	for(int i=0;i<(7-(dayOfWeek + lastDay - 1)%7)%7; i++){
		%>
		
		<td bgcolor="#c6c6c6">&nbsp;</td>
		<%
	}
	
	
	
	%>
	</tr>
</table>
</div>






</body>
</html>