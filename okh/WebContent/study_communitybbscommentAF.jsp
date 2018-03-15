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
UserDto mem = (UserDto)session.getAttribute("login");
ComCommentDto dto = (ComCommentDto)session.getAttribute("comment");
iCommentService service = CommentService.getInstance();
boolean isS =service.writecomment(dto);
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