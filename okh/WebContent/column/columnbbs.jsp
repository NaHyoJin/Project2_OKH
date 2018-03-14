<%@page import="columnbbs.ColumnbbsService"%>
<%@page import="columnbbs.ColumnbbsServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="columnbbs.ColumnbbsDto"%>
<%@page import="user.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="_column.css?ver=1">
</head>
<body>
<%
UserDao mem = (UserDao)session.getAttribute("login");
List<ColumnbbsDto> columnlist=(List<ColumnbbsDto>)request.getAttribute("columnbbs");

%>

<<div class="menu">
<jsp:include page="menuinclude.jsp">
<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>
<div class="cover">
	<div class="header">
		<h3>칼럼</h3>
		<button type="button" id="columnwrite">새 글 쓰기</button>
	</div>
	<div class="board">
		<table border="1">
		<col width="450"><col width="50"><col width="50">
		<col width="50"><col width="200">
		
			<%if(columnlist==null || columnlist.size()==0){
			
			%><tr>
				<td colspan="5">입력된 게시물이 없습니다</td>
				</tr>
			<%	
			}else{
				for(int i=0; i < columnlist.size();i++){
					ColumnbbsDto dto=columnlist.get(i);
					ColumnbbsServiceImpl service=ColumnbbsService.getInstance();
					String[] tagnames=service.getTagName(dto.getTagname());
			%><tr>
				<td>
					<span id="tag"><%=i+1 %></span>
			<%
			for(int j= 0; j < tagnames.length;j++){//추가시킬때 무조건 추가시킬거는 - 없이해도되고 엔터치면 -그 값을 넣어준다
			%>
				<span><button name="tag<%=j%>" id="tag<%=j%>" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
			<%
			}
			%>	
			<p><a href="ColumnbbsController?command=columndetail&iid=<%=mem.getId %>"><%=dto.getTitle() %></a></p>
			</td>
			<td><%=dto.getCommentcount() %></td>
			<td><%=dto.getLikecount() %></td>
			<td><%=dto.getReadcount() %></td>
			<td><%=mem.getId() %></td>
			</tr>
			<% 	
			}	
			}
			%>
		
		</table>

	</div>
	
</div>







</body>
</html>