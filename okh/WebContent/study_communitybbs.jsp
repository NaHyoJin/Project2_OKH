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
<title>Insert title here</title>
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


UserDto mem = (UserDto)session.getAttribute("login");

List<CombbsDto> comlist=(List<CombbsDto>)request.getAttribute("communitybbs");
%>
<a>최신순</a> <a>조회순</a> <a>댓글순</a> <a>참여도순</a>
	<table border="1">
		<col width="5"><col width="500"><col width="70"><col width="70"><col width="70"><col width="200">
		<%if(comlist==null||comlist.size()==0){
				
			%><tr>
				<td colspan="6">리스트가없습니다</td>
				</tr>
				
				
				
			<%
			}else{
			%>
			<tr>
			<td/> <td>제목</td> <td>참여자수</td> <td>댓글수</td> <td>작성자</td> <td>작성일</td> 
		</tr>
			<% 	
				
				for(int i=0;i<comlist.size();i++){
					CombbsDto dto = comlist.get(i);
					ICombbsService service = CombbsService.getInstance();
					String[] tagnames=service.getTagName(dto.getTagname
							());
				
		
		%>
		
		
		<tr>
		
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
			<td><%=dto.getJoinercount() %></td>
			<td><%=dto.getCommentcount()%></td>
			<td><%=dto.getId() %></td>
			<td><%=dto.getWdate() %></td>
			
			</tr>
			<%
			}
			}
			%>
	</table>
</div>


</body>
</html>