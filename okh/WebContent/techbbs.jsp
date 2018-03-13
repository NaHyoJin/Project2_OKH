<%@page import="service.TechbbsService"%>
<%@page import="service.TechbbsServiceImpl"%>
<%@page import="user.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.TechbbsDto"%>
<%@page import="java.util.List"%>
<%@page import="singleton.Singleton"%>
<%@page import="controller.TechbbsController"%>
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
<link rel="stylesheet" type="text/css" href="_techbbs.css?ver=1">
</head>
<body>
<%


UserDTO mem = (UserDTO)session.getAttribute("login");

List<TechbbsDto> techlist=(List<TechbbsDto>)request.getAttribute("techbbs");
%>
<div class="menu">

	<jsp:include page="menuinclude.jsp">
	<jsp:param name="actionPath" value="index.jsp"/>
	</jsp:include>

</div>

<div class="wrap">
	<div class="header">
		<h3>Tech</h3>
		<button type="button" id="techwrite">게시글쓰기</button>
	</div>
	<div class="board">
		<table border="1">
		<col width="450"><col width="50"><col width="50"><col width="50"><col width="200">
			
			<%if(techlist==null||techlist.size()==0){
				
			%><tr>
				<td colspan="5">리스트가없습니다</td>
				</tr>
			<%
			}else{
			
				for(int i=0;i<techlist.size();i++){
					TechbbsDto dto=techlist.get(i);
					TechbbsServiceImpl service=TechbbsService.getInstance();
					String[] tagnames=service.getTagName(dto.getTagname());
					
			%><tr>
				<td>
					<span id="tag"><%=i+1 %></span>
				
			<%
			for(int j=0;j<tagnames.length;j++){//추가시킬때무조건추가시킬거는 -없이해도되고 엔터치면 -그값을넣어준다
			%>
				<span><button name="tag<%=j%>" id="tag<%=j%>" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
			<%
			}
			%>
			<p><a href="techbbsdetail.jsp"><%=dto.getTitle() %></a></p>
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
<script type="text/javascript">


$(function() {
	$("#techbbs").click(function() {
		location.href="TechbbsController?command=techbbs";
	});
	$("#techwrite").click(function() {
		location.href="TechbbsController?command=techwrite";
	});
});
</script>
</body>
</html>