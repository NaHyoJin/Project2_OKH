<%@page import="user.UserDto"%>
<%@page import="qna.QnaDto"%>
<%@page import="qna.QnaService"%>
<%@page import="qna.QnaServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>qnabbsdetail.jsp</title>
	<!-- include summernote libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> 
	
	<!-- include summernote-ko-KR -->
	<script src="lang/summernote-ko-KR.js"></script>


	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_main.css">
	
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
<div class="menu">
<jsp:include page="menuinclude.jsp">
<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>



<%
String sseq = request.getParameter("seq").trim();
int seq = Integer.parseInt(sseq);


QnaServiceImpl service = QnaService.getInstance();
QnaDto bbs = service.getBbs(seq);

//UserDto mem = (UserDto)session.getAttribute("login");

/* String str = service.RemoveHTMLTag(bbs.getContent());

String content = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
System.out.println("str:"+content); */
%>






<div align="center">
<h3>여기는 qnabbsdetail.jsp</h3>
</div>

<div class="wrap">

<table style="border: 1px solid; width: 100%">
<col width="100"><col width="400"><col width="150">
<tr>
	<td colspan="2" >작성자: <%=bbs.getId() %> </td>
	<td style="border: 1px solid;">답변수:<%=bbs.getAnswercount() %>/조회수:<%=bbs.getReadcount() %></td>
</tr>
<tr>
	<td>번호:<%=bbs.getRef() %></td>
	<td>테그:<%=bbs.getTag() %></td>
	<td rowspan="2" style="border: 1px solid;">좋아요 구현</td>
</tr>
<tr>
	<td colspan="2" >
		<%-- <textarea  rows="30" cols="50" id="summernote" readonly="readonly" style="width: 100%;" >		
		<%=bbs.getContent() %> 
		</textarea> --%>		
		<br><br><br>
		<article>
		<%=bbs.getContent() %>
		</article> 
	</td>
</tr>

</table>




</div>


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