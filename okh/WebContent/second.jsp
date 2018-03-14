<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="_main.css">
<title>second.jsp</title>
</head>
<body>
<!-- div 클래스면 menu로만들고 그안에 include코드넣어주면된다  (actionPath안붙이니까안불러지길래넣었는데왜있는지모르겠어요)-->
<div class="menu">
	<jsp:include page="include.jsp">
	<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>
<script type="text/javascript">
$(function() {
	$("#second").click(function() {
		location.href="index.jsp";
	});
	$("#techbbs").click(function() {
		location.href="techbbs.jsp";
	});
});
</script>
</body>
</html>