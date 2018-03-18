<%@page import="java.util.Calendar"%>
<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="_main.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
<script src="js/bootstrap-tagsinput.js"></script>
<title>OKH:calendar작성</title>
</head>
<body>
<%
String year = request.getParameter("year");
String month = request.getParameter("month");
String day = request.getParameter("day");
UserDto user = (UserDto)session.getAttribute("login");
iCalendar dao = CalendarDao.getInstance();
Calendar cal = Calendar.getInstance();

int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH);
int tday = cal.get(Calendar.DATE);
int thour = cal.get(Calendar.HOUR);
int tmin = cal.get(Calendar.MINUTE);

%>
<form action="calwriteAf.jsp" method="get">
<div align="center">
<h1> 일정관리 입력 </h1>
	<table border="1">
	
		<col width="50"> <col width="400">
	<tr>
		<td> ID</td> <td> <input type="text" readonly="readonly" value="<%=user.getId() %>" name="id"></td>
	</tr>
	
	
	<tr>
		<td>제목</td>
		<td><input type="text" name="title"> </td>
	</tr>
	
	<tr>
		<td>일정</td>
		<td>
			<select name="year">
				
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
		<td colspan="2">
					<textarea name="content" id="summernote"></textarea>
		</td>
	</tr>
	<tr>
		<td align="left" colspan="2">
			<input type="submit" value="추가">
		</td>
	</tr>
	</table>
	
</div>

</form>

<script type="text/javascript">

setDay();

$("#day").val("<%=day %>");

$(document).ready(function () {
	$("select[name='month']").change(setDay);
});

function setDay() {
	var lastDay = (new Date($("select[name='year']").val()+"", 
							$("select[name='month']").val() + "", 
							0)).getDate();
	
//	alert("lastDay = " + lastDay);
	var str = "";
	for(i = 1;i <= lastDay; i++){
		str += "<option value='" + i + "'>" + i + "</option>";
	}
	
	// 적용
	$("select[name='day']").html(str);	
	
}
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