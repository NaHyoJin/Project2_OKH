<%@page import="studysrc.CombbsService"%>
<%@page import="studysrc.ICombbsService"%>
<%@page import="studysrc.CommentService"%>
<%@page import="studysrc.iCommentService"%>
<%@page import="studysrc.ComCommentDto"%>
<%@page import="user.UserDto"%>
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
ComCommentDto dto = (ComCommentDto)session.getAttribute("comment");
String parents = request.getParameter("parent");
int parent = Integer.parseInt(parents);
ICombbsService service1 = CombbsService.getInstance();

iCommentService service = CommentService.getInstance();
service1.commentcount(parent);
boolean isS =service.writecomment(dto);
if(isS){
%>
<script type="text/javascript">



location.href="CommunityControl?command=list";

</script>
<%
}else{
%>
<script type="text/javascript">

location.href="CommunityControl?command=list";
</script>
<%
}
%>
</body>
</html>