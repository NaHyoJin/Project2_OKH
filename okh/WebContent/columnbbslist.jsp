<%@page import="columnBbs.PagingBean"%>
<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="java.util.List"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<%!
// 댓글용
public String arrow(int depth){	
	String rs = "<img src='image/arrow.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	String ts = "";
	
	for(int i = 0;i < depth; i++){
		ts += nbsp;
	}
	return depth == 0?"":ts+rs;
}
%>      


<%
String findWord = request.getParameter("findWord"); 
String choice = request.getParameter("choice"); 
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbslist</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	

<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_techbbs.css?ver=1.64">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.4">
</head>
<body bgcolor="#fcfbfb">
<script type="text/javascript">
function logout() {
	location.href='index.jsp';
}
</script>
<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3"  id="column"><!-- 정재흥 -->		
		<input type="button" class="bbs4">
		<input type="button" class="bbs5" id="life">
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	//  정재흥 column 부분
		$("column").click(function name() {
			location.href="Controller?command=column"
		});

	});
	</script>
	
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


<!-- 텍스트 가로 롤링 -->





 
<%--
iBbsDao dao = BbsDao.getInstance();
List<BbsDto> bbslist = dao.getBbsList(); 
--%>

<!-- 페이징 처리 정보 교환 -->
<%
PagingBean paging = new PagingBean();
if(request.getParameter("nowPage") == null){
	paging.setNowPage(1);
}else{
	paging.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
}
%>

<%
if(findWord == null){
	findWord = "";
}
int cho = 0;

if(choice == null) cho = 0;
else if(choice.equals("title")) cho = 0;
else if(choice.equals("writer")) cho = 1;

iColumnBbsDao dao = ColumnBbsDao.getInstance();
List<ColumnBbsDto> bbslist = dao.getBbsPagingList(paging, findWord, cho);
%>

<div class="wrap">
	<div class="header">
		<h1 style="font-size: 2em; font-family: monospace ">칼럼</h1>
		<button type="button" id="write">칼럼쓰기</button>
	</div>
	<div class="board">
		<table border="1" class="techtable">
		<col width="60"><col width="450"><col width="50">

<%
if(bbslist == null || bbslist.size() == 0){
	%>	
	<tr>
		<th colspan="3">작성된 글이 없습니다</th>
	</tr>	
<%
}
for(int i = 0;i < bbslist.size(); i++){
	ColumnBbsDto bbs = bbslist.get(i);
	
	%>

	<% 
	if(bbs.getDel() == 0){
	%>
	
	<tr>

	
	
		<th>#<%=bbs.getSeq()%></th>
		<td>
			<%=arrow(bbs.getDepth()) %>
			<a href="columnbbsdetail.jsp?seq=<%=bbs.getSeq() %>">
				<%=bbs.getTitle() %>
			</a>
		</td>
		<td><%=bbs.getId() %></td>
	</tr>	
	<% 
	}
	%>
	
	<%
}
%>



</table>
<br>
<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="columnbbslist.jsp"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include>


<br>

<br>

<!-- search -->

<select id="choice">
<option value="title">제목</option>
<option value="writer">작성자</option>
<option value="content">내용</option>
</select>

<input type="text" id="search">
<button name="search" onclick="searchBbs()">검색</button>
	</div>
</div>


<script type="text/javascript">
function searchBbs() {
	var word = document.getElementById("search").value;
	var choice = document.getElementById("choice").value;
	alert("choice = " + choice);
	location.href = "columnbbslist.jsp?findWord=" + word + "&choice=" + choice;	
}

$(function () {
	$("#write").click(function () {
		location.href="Controller?command=write";
	});
	
})



</script>






</body>
</html>





