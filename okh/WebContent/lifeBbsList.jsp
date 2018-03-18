<%@page import="lifeBbs.LifeBbssReplyDao"%>
<%@page import="lifeBbs.ILifeBbssReplyDao"%>
<%@page import="lifeBbs.LifeBbssReplyDto"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="lifeBbs.LifeBbsPagingDto"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
if(choice == null){
	choice = "title";
}
if(findWord == null || findWord.equals("")){
	choice = "title";
	findWord = "";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LifeBBSList</title>
	<link rel="stylesheet" type="text/css" href="_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
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
	<div class="wrap">
		<h3>환영합니다 <%=mem.getName() %>님 반갑습니다</h3>
		 
		<a href="calendar.jsp">일정관리</a>
		
		<a href="pdslist.jsp">자료실</a>
		
		<a href="logout.jsp">로그아웃</a>
		
		<%--
		iBbsDao dao = BbsDao.getInstance();
		List<BbsDto> bbslist = dao.getBbsList(); 
		--%>
		
		<!-- 페이징 처리 정보 교환 -->
		<%
		LifeBbsPagingDto paging = new LifeBbsPagingDto();
		if(request.getParameter("nowPage") == null){
			paging.setNowPage(1);
		}else{
			paging.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
		}
		%>
		
		<%
		if(findWord == null) findWord = "";
		int cho = 0;
		
		if(choice.equals("title")) cho = 0;
		else if(choice.equals("writer")) cho = 1;
		else if(choice.equals("content")) cho = 2;
		
		ILifeBbsDao dao = LifeBbsDao.getInstance();
		ILifeBbssReplyDao rdao = LifeBbssReplyDao.getInstance();
		
		List<LifeBbsDto> bbslist = dao.getBbsPagingList(paging, findWord, cho);
		List<LifeBbssReplyDto> replylist = rdao.reply();
		%>
		
		<div>
		
		<table border="1">
			<col width="70"><col width="500"><col width="150">
			
			<tr>
				<th>번호</th><th>제목</th>	<th>작성자</th>
			</tr>
		
		<%
		if(bbslist == null || bbslist.size() == 0){
			%>	
			<tr>
				<td colspan="3">작성된 글이 없습니다</td>
			</tr>	
			<%
		}
		for(int i = 0;i < bbslist.size(); i++){
			LifeBbsDto bbs = bbslist.get(i);
			%>
			<tr>
				<%
				if(bbs.getDel() == 1){
				%>
				<td colspan="3" align="center">삭제된 글입니다.</td>
				<%
				}else if(bbs.getCountreply() == 0){
					System.out.println(bbs.getTitle() + "의 counterreply : " + bbs.getCountreply());
				%>
				<td style="border-left: 5px solid gray;"><%=i+1 %></td>
				<td>
					<%=arrow(bbs.getDepth()) %>
					<a href="LifeBbs?command=detail&seq=<%=bbs.getSeq() %>">
						<%=bbs.getTitle() %>
					</a>
				</td>
				<td><%=bbs.getId() %></td>
				<%
				}else{
					System.out.println(bbs.getTitle() + "의 counterreply : " + bbs.getCountreply());
				%>
				<td style="border-left: 5px solid blue;"><%=i+1 %></td>
				<td>
					<%=arrow(bbs.getDepth()) %>
					<a href="LifeBbs?command=detail&seq=<%=bbs.getSeq() %>">
						<%=bbs.getTitle() %>
					</a>
				</td>
				<td><%=bbs.getId() %></td>
				<%
				}
				%>
			</tr>	
			<%
		}
		%>
		</table>
		<br>
		<jsp:include page="lifeBbsPaging.jsp">
			<jsp:param name="actionPath" value="lifeBbsList.jsp"/>
			<jsp:param name="findWord" value="<%=findWord %>" />
			<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
			<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
			<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
			<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
		</jsp:include>
		
		</div>
		<br>
		<a href="LifeBbs?command=write&id=" + <%=mem.getId() %>>글쓰기</a>
		<br>
		<div align="center">
		<!-- search -->
		
		<select id="choice">
		<option value="title" <%if(choice.equals("title")) out.println("selected");%>>제목</option>
		<option value="writer" <%if(choice.equals("writer")) out.println("selected");%>>작성자</option>
		<option value="content" <%if(choice.equals("content")) out.println("selected");%>>내용</option>
		</select>
		
		<input type="text" id="search" value="<%=findWord %>">
		<button name="search" onclick="searchBbs()">검색</button>
		</div>
		
		<script type="text/javascript">
		function searchBbs() {
			var word = document.getElementById("search").value;
			var choice = document.getElementById("choice").value;
			location.href = "lifeBbsList.jsp?findWord=" + word + "&choice=" + choice;
		}
		</script>
	</div>

</body>
</html>