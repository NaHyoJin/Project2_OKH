<%@page import="user.UserDto"%>
<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbsdetail.jsp</title>

<style type="text/css">
.center{
	margin: auto;
	width: 60%;
	border: 3px solid #8ac007;
	padding: 10px;
}
</style>

</head>
<body>
<a href="logout.jsp">로그아웃</a>

<h1>상세 글보기</h1>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);

 iColumnBbsDao dao = ColumnBbsDao.getInstance();
 ColumnBbsDto bbs = dao.getBbs(seq);
dao.readcount(seq);
%>

<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>

<div class="center">

<table border="2">
<col width="200"><col width="500">

<tr>
	<td>작성자</td>
	<td><%=bbs.getId() %></td>
</tr>

<tr>
	<td>제목</td>
	<td><%=bbs.getTitle() %></td>
</tr>

<tr>
	<td>작성일</td>
	<td><%=bbs.getWdate() %></td>
</tr>

<tr>
	<td>조회수</td>
	<td><%=bbs.getReadcount() %></td>
</tr>

<tr>
	<td>정보</td>
	<td><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
</tr>

<tr>
<td>내용</td>
<td>
<textarea rows="10" cols="50"
name="content"><%=bbs.getContent() %></textarea>
</td>
</tr>

</table> 

<%-- <button type="button" onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button> --%>

<% if(bbs.getId().equals(mem.getId())){ %>

<button type="button" 
	onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
<button type="button"
	onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>
<button type="button" 
onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>	
<%
}else if(!(bbs.getId().equals(mem.getId()))){
%>
<button type="button" 
onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>	
<%
}
%>

</div>

<script type="text/javascript">
function updatebbs( seq ) {
	location.href = "Controller?command=update&seq=" + seq;
}

function answerbbs( seq ) {
	location.href = "Controller?command=answer&seq=" + seq; 
}

function deletebbs(seq) {
	location.href= "Controller?command=del&seq=" + seq;
}


</script>



</body>
</html>





