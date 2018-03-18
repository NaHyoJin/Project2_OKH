<%@page import="user.UserDto"%>
<%@page import="studysrc.CombbsService"%>
<%@page import="studysrc.ICombbsService"%>
<%@page import="studysrc.CombbsDto"%>
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
	alert("로그인해 주십시오");
	location.href = "index.jsp";	
	</script>	
	<%
	return;
}
mem = (UserDto)ologin;

%>

<%
CombbsDto dto = (CombbsDto)request.getAttribute("comwritedto");
ICombbsService service = CombbsService.getInstance();
boolean isS = service.writeBbs(dto);
if(isS){
%>
<script type="text/javascript">
alert("추가성공");
location.href="CommunityControl?command=list";
</script>
<%
}else{
%>
<script type="text/javascript">
alert("다시입력해주세요");
location.href="CommunityControl?command=list";
</script>
<%
}
%>
</body>
</html>