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
	<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_main.css">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="_Studybbs.css?ver=1.60"> -->
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	

<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_Studybbs.css?ver=1.61">
</head>
<body>
<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs">
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	
<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.

			$("#login").click(function() {
				location.href="User?command=login";
			});
	
			$("#account").click(function() {
				location.href="User?command=join";
			});
			
			//QNA
			$("#qnabbs").click(function() {
				location.href="qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="second.jsp";
			});
	
			$("#techbbs").click(function() {
				location.href="TechbbsController?command=techbbs";
			});
	
			$("#life").click(function() {
				location.href="LifeBbs?command=life";
			});
			$("#combbs").click(function() {
				location.href="CommunityControl?command=list";
			});
			
	/* 
			//columns
			$("#").click(function() {
				location.href="";
			});
	 */
	 
			//게시판5 나효진 jobs 부분.
/* 			$("#jobs").click(function() {
				location.href="main.BBSHWCodingController";
			});
 */	 
			
			$("#jobs").click(function name() {
				location.href="jobs";
			});

		});
	</script>
	<div class="titlediv"><span class="titi">스터디모임 게시판</span>
	<button class="create btn btn-success btn-wide pull-right " type="button" id="write">게시글쓰기</button></div>




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
<div class="wrap">
<div class="sercharea">
		<!-- search -->
		
		<select id="choice">
		<option value="title" <%if(choice.equals("title")) out.println("selected");%>>제목</option>
		<option value="writer" <%if(choice.equals("writer")) out.println("selected");%>>작성자</option>
		<option value="content" <%if(choice.equals("content")) out.println("selected");%>>내용</option>
		</select>
		
		<input type="text" id="search" class="inputField" value="<%=findWord %>">
		<button name="search" id="serchbtn" class="input-group-btn" onclick="searchBbs()"><img alt="" src="image/serchbtn.PNG"></button>
</div>
	<div class="board">
	<a href="calendar.jsp">일정관리</a>
	<table border="1" class="techtable">
		<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
		<%if(bbslist==null||bbslist.size()==0){
				
			%><tr>
				<td colspan="5">리스트가없습니다</td>
				</tr>
				
				
				
			<%
			}else{
			%>
			
			<% 	
				
				for(int i=0;i<bbslist.size();i++){
					CombbsDto dto = bbslist.get(i);
					service = CombbsService.getInstance();
					String[] tagnames=service.getTagName(dto.getTagname
							());
					
					boolean checkjoiner=service.checkjoiner(bbslist.get(i).getSeq());
					
			
		
		%>
		
		
		
		<%
		if(dto.getJoinercount()==0){
		%>
		<tr>
			<th style="border-left: 5px solid #808080">
			#<%=bbslist.get(i).getSeq() %>
		<%
		}else if(dto.getJoinercount()>0 && dto.getJoinercount()<5){
		%>
	
			
				<th style="border-left: 5px solid #00ff00">
				#<%=bbslist.get(i).getSeq() %>
			<%}else{
				
				%>
				<th style="border-left: 5px solid #ff00ff">
				#<%=bbslist.get(i).getSeq() %>
				<%
			}
			for(int j=0;j<tagnames.length;j++){//추가시킬때무조건추가시킬거는 -없이해도되고 엔터치면 -그값을넣어준다
				
			%>
			<span><button class="hjhtag" name="tag<%=j%>" id="tag<%=j%>" onclick="searchBbs1(this)" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
			<%
			}
			%>
			
			<p style="font-size: 20px"><a href="CommunityControl?command=detail&seq=<%=dto.getSeq() %>&likeid=<%=mem.getId() %>"><%=dto.getTitle() %></a></b></p>
			
			<%if(dto.getCommentcount()>0){
			%>
			<td><img src="image/repleon.PNG"><span class="textalig"> <%=dto.getCommentcount() %></span></td>
			<%
			}else{
			%>
			<td><img src="image/repleoff.png"><span class="textalig"> <%=dto.getCommentcount() %></span></td>
			<%
			}
			%>
			<%if(dto.getJoinercount()>0){
			%>
			<td style="padding-top: 3px"><img src="image/likeeon.png"><span class="textalig"> <%=dto.getJoinercount() %></span></td>
			<%
			}else{
			%>
			<td style="padding-top: 3px"><img src="image/likeeoff.png"><span class="textalig"> <%=dto.getJoinercount() %></span></td>
			<%
			}
			%>
			<%if(dto.getReadcount()>0){
			%>
			<td><img src="image/readcounton.PNG"><span class="textalig"> <%=dto.getReadcount() %></span></td>
			<%
			}else{
			%>
			<td><img src="image/readcountoff.png"><span class="textalig"> <%=dto.getReadcount() %></span></td>
			<%
			}
			%>
			
			<td>
			<%=dto.getId() %>
			<p style="font-size: 10px"><%=dto.getWdate() %></p>
			</td>
			
			
			</tr>
			<%
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
			</div>
<script type="text/javascript">
		function searchBbs() {
			var word = document.getElementById("search").value;
			var choice = document.getElementById("choice").value;
			location.href = "study_communitybbs.jsp?findWord=" + word + "&choice=" + choice;
		}
</script>

	
	
<script type="text/javascript">

$(function() {
	
	$("#write").click(function() {
		location.href="CommunityControl?command=write";
	});
});

</script>
</body>
</html>