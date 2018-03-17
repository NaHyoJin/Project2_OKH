<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="jobs_BBS5.BbsBoardBeanDtoVO"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobs_bbs5NormalBbsWriteAf.jsp</title>
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
BbsBoardBeanDtoVO dto = (BbsBoardBeanDtoVO)request.getAttribute("normalbbswritedto");
//싱글톤 호출 부분.
jobsBbs5ModelServiceImpl service = jobsBbs5ModelService.getInstance();

boolean isS = service.writenormalBbs(dto);

if(isS){
%>
	<script type="text/javascript">
	alert("글 입력 완료");
	location.href="BBSboardController?command=list";
	</script>
<%
}else{
%>
	<script type="text/javascript">
	alert("다시 입력해 주세요.");
	location.href="BBSboardController?command=list";
	</script>
<%
}
%>
</body>
</html>