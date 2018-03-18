<%@page import="studysrc.CalendarDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="studysrc.CalendarDto"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>caldetail.jsp</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
<script src="js/bootstrap-tagsinput.js"></script>
</head>
<body>

<%!

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

public String toOne(String msg){	// 08 -> 8
	return msg.charAt(0)=='0'?msg.charAt(1) + "": msg.trim();
}
%>

<%
Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH);
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR);
int tmin = cal.get(Calendar.MINUTE);



String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
iCalendar dao = CalendarDao.getInstance();

CalendarDto dto = new CalendarDto();
dto = dao.getDay(seq);

%>
<form action="calupdateAf.jsp">
<table border="1">
<col width="200"><col width="500">

<tr>
	<td>아이디</td>
	<td><%=dto.getId() %></td>
</tr>

<tr>
	<td>제목</td>
	<td><input type="text" value="<%=dto.getTitle() %>" name="title"></td>
</tr>

<tr>
	<td>일정</td>
	<td><select name="year">
				
				<%
				for(int i = tyear -10; i<tyear+10;i++){
				%>
					<option <%=year.equals(i+"")?"selected='selected'":"" %> value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>년
			<select name="month" id="_month">
				<option value="월"> 월
				<%
				for(int i = 1; i<=12;i++){
					%>
						<option <%=month.equals(i+"")?"selected='selected'":"" %> value="<%=i %>"><%=i %></option>
					<%
				}
				%>
			</select>월
			<select name="day" id="_day">
				<option value="일"> 일
				<%
				int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for(int i = 1; i<=lastDay;i++){
					
				%>
					<option <%=day.equals(i+"")?"selected='selected'":"" %> value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>일
			
			<select name="hour">
				<option value="시"> 시
				<%
				
				for(int i = 0; i<24;i++){
					
				%>
					<option <%=(thour+"").equals(i+"")?"selected='selected'":"" %> value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>시
			<select name="min">
				<option value="분"> 분
				<%
				
				for(int i = 0; i<60;i++){
					
				%>
					<option <%=(tmin+"").equals(i+"")?"selected='selected'":"" %> value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>분
			
			
		</td>
</tr>

<tr>
	<td>내용</td>
	<td>
		<textarea name="content" id="summernote"><%=dto.getContent() %></textarea>
		<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="submit" value="수정">
	</td>
</tr>

</table>
</form>

<script type="text/javascript">
$(document).ready(function() {
    $('#summernote').summernote({
            height: 300,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
    });
});
</script>





</body>
</html>



