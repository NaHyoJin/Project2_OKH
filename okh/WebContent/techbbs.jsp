<%@page import="techbbs.PagingBean"%>
<%@page import="user.UserDto"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="techbbs.TechbbsDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
request.setCharacterEncoding("utf-8");
%>
<%
//검색을눌렀을때만넘어옴
String findWord = request.getParameter("findWord"); 
String choice = request.getParameter("choice"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	

<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_techbbs.css?ver=1.58">
</head>
<body bgcolor="#fcfbfb">
<%//로그인한id가져오기
Object ologin = session.getAttribute("login");
UserDto mem = null;
List<TechbbsDto> techlist=(List<TechbbsDto>)request.getAttribute("techbbs");
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

if(choice == null) cho = 3;
else if(choice.equals("title")) cho = 0;
else if(choice.equals("writer")) cho = 1;
else if(choice.equals("content")) cho = 2;
else if(choice.equals("tagname")) cho = 3;

TechbbsServiceImpl tservice=TechbbsService.getInstance();

techlist = tservice.gettechBbsPagingList(paging, findWord, cho);
System.out.println(techlist.size()+"사이즈크기");
%>

	<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" >
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
	<div class="titlediv"><h2 style="font-family: 'Nanum Gothic', sans-serif ">기술게시판</h2><br>
	<button class="create btn btn-success btn-wide pull-right " type="button" id="techwrite">게시글쓰기</button></div>
<div class="wrap">
	<div class="board">
		<table border="1" class="techtable">
		<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
			
			<%if(techlist==null||techlist.size()==0){
				
			%><tr>
				<th colspan="5">리스트가없습니다</th>
				</tr>
			<%
			}
			
			for(int i=0;i<techlist.size();i++){
				TechbbsDto dto=techlist.get(i);
				String[] tagnames=tservice.getTagName(dto.getTagname());	
				
				tservice=TechbbsService.getInstance();
				boolean chekcomment=tservice.checkcomment(techlist.get(i).getSeq());
				if(chekcomment){
			%>
			<tr>
				<th>
				#<%=techlist.get(i).getSeq() %>
			<%
			}else{
			%>
			<tr>
				<th style="border-left: 5px solid #808080">
				#<%=techlist.get(i).getSeq() %>
			<%}
			for(int j=0;j<tagnames.length;j++){//추가시킬때무조건추가시킬거는 -없이해도되고 엔터치면 -그값을넣어준다
			%>
				<span><button class="hjhtag" name="tag<%=j%>" id="tag<%=j%>" onclick="searchBbs1(this)" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
			<%
			}
			%>
			<p style="font-size: 20px"><a href="TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></p>
			</th>
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
			<%if(dto.getLikecount()>0){
			%>
			<td style="padding-top: 3px"><img src="image/likeeon.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
			<%
			}else{
			%>
			<td style="padding-top: 3px"><img src="image/likeeoff.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
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
			%>
		</table>
		<br>
		
		<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="techbbs.jsp"/>
	<jsp:param name="findWord" value="<%=findWord %>"/>
	<jsp:param name="choice" value="<%=choice %>"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include>
<div class="sercharea">
<select id="choice" style="height: 27px; margin-right: 15px;">
		<option value="tagname" <%if(cho==3){ out.println("selected");}%>>선택하세요</option>
		<option value="title" <%if(cho==0){ out.println("selected");}%>>제목</option>
		<option value="writer" <%if(cho==1){ out.println("selected");}%>>작성자</option>
		<option value="content" <%if(cho==2){ out.println("selected");}%>>내용</option>
		</select>
<input type="text" style="width: 304px" id="search" value="<%=findWord %>">
<button name="search" class="btn btn-primary" onclick="searchBbs()">검색</button>
</div>
<script type="text/javascript">
if(document.getElementById("choice").value=="tagname"){
	$("#search").val("");
}
</script>

	</div>
</div>

<script type="text/javascript">
function searchBbs() {
	if(document.getElementById("choice").value=="tagname"){
		alert("선택해주세요");
		$("#search").val("");
		return;
	}
	if(document.getElementById("search").value==""){//빈문자열에서검색시
		location.href = "techbbs.jsp?findWord=TechTips&choice=tagname";	
		return;
	}
	var word = document.getElementById("search").value;
	var choice = document.getElementById("choice").value;
	$("#select_id").val("<%=cho%>").prop("selected", true);
	location.href = "techbbs.jsp?findWord=" + word + "&choice=" + choice;	
}
function searchBbs1(e) {
	$("#search").val("");
	var word = e.value;
	var choice = document.getElementById("choice").value;
	location.href = "techbbs.jsp?findWord=" + word + "&choice=tagname";	
	
}


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