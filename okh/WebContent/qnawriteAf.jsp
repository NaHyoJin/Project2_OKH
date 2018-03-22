<%@page import="qna.QnaService"%>
<%@page import="qna.QnaServiceImpl"%>
<%@page import="qna.QnaDto"%>
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
QnaDto techwritedto=(QnaDto)request.getAttribute("techwritedto");
QnaDto return1=(QnaDto)request.getAttribute("return1");
if(return1==null&&techwritedto==null){

%>
<script type="text/javascript">
alert("제목과 내용을 입력해주세요");
location.href="qnaServlet?command=qnabbswrite";
</script>
<%
}else{
QnaServiceImpl service = QnaService.getInstance();

boolean isS=service.writeBbs(techwritedto);
if(isS){
%>
<script type="text/javascript">
alert("추가성공");
location.href="qnaServlet?command=qnabbs";
</script>
<%
}else{
%>
<script type="text/javascript">
alert("다시입력해주세요");
location.href="qnaServlet?command=qnabbs";
</script>
<%
}
}
%>
<script type="text/javascript">

</script>
</body>
</html>