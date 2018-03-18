<%@page import="studysrc.PagingBean"%>
<%@page import="studysrc.ComDao"%>
<%@page import="studysrc.CombbsService"%>
<%@page import="studysrc.ICombbsService"%>
<%@page import="studysrc.CombbsDto"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OKH:스터디모집</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_main.css">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
		<input type="button" class="bbs4" id="combbs">
		<input type="button" class="bbs5">
</div>
<div align="center">
<a href="calendar.jsp">일정관리</a>
<p align="right"><a href="CommunityControl?command=write" >게시글쓰기</a><br></p>

<%

String findWord = request.getParameter("findWord");
String choice = request.getParameter("choice");
if(choice == null){
	choice = "title";
}
if(findWord == null || findWord.equals("")){
	choice = "title";
}




/* List<CombbsDto> comlist=(List<CombbsDto>)request.getAttribute("communitybbs"); */

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

ICombbsService service = CombbsService.getInstance();
List<CombbsDto> bbslist = service.getpagingComList(paging, findWord, cho);


%>
<a>최신순</a> <a>조회순</a> <a>댓글순</a> <a>참여도순</a>
	<table border="1">
		<col width="5"><col width="500"><col width="70"><col width="70"><col width="70"><col width="200">
		<%if(bbslist==null||bbslist.size()==0){
				
			%><tr>
				<td colspan="6">리스트가없습니다</td>
				</tr>
				
				
				
			<%
			}else{
			%>
			<tr>
			<td/> <td>제목</td> <td>조회수</td> <td>댓글수</td> <td>작성자</td> <td>작성일</td> 
		</tr>
			<% 	
				
				for(int i=0;i<bbslist.size();i++){
					CombbsDto dto = bbslist.get(i);
					service = CombbsService.getInstance();
					String[] tagnames=service.getTagName(dto.getTagname
							());
				
		
		%>
		
		
		<tr>
		<%
		if(dto.getDel()==1){
		%>
			<td colspan="6">삭제된 글입니다</td>
			
		<%
		}else if(dto.getDel()==0){
		%>
		
		<td bgcolor="blue"/>
		
				<td>
					<span id="tag"><%=i+1 %></span>
				
			<%
			for(int j=0;j<tagnames.length;j++){//추가시킬때무조건추가시킬거는 -없이해도되고 엔터치면 -그값을넣어준다
				
			%>
				<span><button name="tag<%=j%>" id="tag<%=j%>" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
			<%
			}
			%>
			<p><b style="font-size: 24px"><a href="CommunityControl?command=detail&seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></b></p>
			</td>
			<td><%=dto.getReadcount() %></td>
			<td><%=dto.getCommentcount()%></td>
			<td><%=dto.getId() %></td>
			<td><%=dto.getWdate() %></td>
			
			</tr>
			<%
		}
	}
}
			%>
	</table>
	<br>
		<jsp:include page="paging.jsp">
			<jsp:param name="actionPath" value="study_communitybbs.jsp"/>
			<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
			<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
			<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
			<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
		</jsp:include>
</div>

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
			var content = document.getElementById("content").value;
			location.href = "study_communitybbs.jsp?findWord=" + word + "&choice=" + choice + "&content=" +content;
		}
</script>
	
<script type="text/javascript">



</script>
</body>
</html>