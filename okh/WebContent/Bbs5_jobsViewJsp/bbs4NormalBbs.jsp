<%@page import="jobs_BBS5.BbsBoardBeanDtoVO"%>
<%@page import="java.util.List"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5Dao"%>
<%@page import="jobs_BBS5.jobsBbs5DaoImpl"%>
<%@page import="user.UserDto"%>
<%@page import="jobs_BBS5.PagingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
		//검색을 하기위한 변수 값 받는 부분.
		String findWord = request.getParameter("findWord"); 
		String choice = request.getParameter("choice"); 
	%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbs4NormalBbs.jsp</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script src="./js/jquery-3.3.1.min.js"></script>

<!-- 스타일 부분 -->
<style type="text/css">

	a{
		text-decoration: none;
	}

</style>

</head>
<body>


<%

	//로그인 정보 확인 부분.
	Object ologin = session.getAttribute("login");
    
    UserDto mem = null;
    
	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}
	
	
%>
	
<!--  
	<a href="../BBSHWCodingController">H/W & Coding NEWS;</a>../현재 폴더의 윗 폴더
	<a href="../BBSboardController">자유 게시판;</a>/최상위 폴더
	<a href="../BBSmaterialsController">자료실;</a>
 -->	
 
 <div class="">
	<jsp:include page="BBS5TopMenuinclude.jsp" flush="false" />
</div>

<%
		//인간 로그인 안하면 안보이게 하는 부분.
		if(mem != null){
	%>
	
	<br>
	<!-- 일반 글 쓰기 로 가는것 -->
	<a href="../NormalWrite.BBSboardController">새 글 쓰기</a>
	 <!-- <a href="../PdsWrite.BBSmaterialsController?command=detail">새 글 쓰기</a>  -->
		<!-- <a href="../PdsWrite.BBSmaterialsController" 
		style="text-decoration: none; text-align: right">자료올리기</a> -->
		
	<%
		}
	%>
	<br><br>
	

	<a href="">최신순</a>
	<a href="">추천순</a>
	<a href="">조회순</a>
	
	<%
		if(findWord == null){
			findWord = "";
		}
	
		int cho = 0;
		
		if(choice == null) cho = 0;
		else if(choice.equals("title")) cho = 0;
		else if(choice.equals("writer")) cho = 1;
		else if(choice.equals("content")) cho = 2;
		
//싱글톤 생성 부분.
jobsBbs5ModelServiceImpl service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...

List<BbsBoardBeanDtoVO> bbslist = service.getBbsNormalBeanDTOList();
	%>
	<div align="center">

		<!-- search -->
		
		<select id="choice">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" id="search">
		<button name="search" onclick="searchBbs()">检索 bbs4NormalBbs</button>
	</div>
	
	
	<br>

<table border="1">
<col width="50"><col width="100"><col width="400"><col width="100">
<col width="100"><col width="100"><col width="100">

<tr bgcolor="#09bbaa">
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th>
</tr>

	<%
	for(int i = 0; i < bbslist.size(); i++){
		BbsBoardBeanDtoVO bbs = bbslist.get(i);
		String bgcolor = "";
		
		if(i%2 == 0){
			bgcolor = "#ddeebb";
		}else{
			bgcolor = "#ddddbb";
		}
	%>
	
 

	<tr bgcolor="<%=bgcolor %>" align="center" height="5">
		<!-- 글 번호 부분 -->
		<td><%=i+1 %> </td>
		
		<!-- 아이디 부분 -->
		<td><%=bbs.getId() %></td>
		
		<!-- 제목 부분 -->
		<td align="left">
		<!-- 컨트롤러로 시퀀스 번호 넘겨줘야하는 부분인데??? -->
			<a hre="../PdsDetail.BBSmaterialsController?command=detail&seq=<%=bbs.getSeq() %>">
			<%-- <a href="pdsdetail.jsp?seq=<%=pds.getSeq() %>"> --%>
			<%-- <a href="pdsdetail_180308.jsp?seq=<%=pds.getSeq() %>"> --%>
				<%=bbs.getTitle() %>
			</a>
		</td>
		<!-- 다운로드 부분. -->
		<td>
			<input type="button" name="btnDown" id="btnDown" value="파일"
			onclick="location.href='filedown?filename=<%=bbs.getFilename() %>&seq=<%=bbs.getSeq() %>'">			
		</td>
		
		<td><%=bbs.getReadcount() %></td>
		<td><%=bbs.getDowncount() %></td>
		
		<!-- 글작성일. -->
		<td><%=bbs.getWdate() %></td>
	</tr>
	
	
	
	<%	
		}
	%>

</table>

	<jsp:include page="paging.jsp">
		<jsp:param name="actionPath" value="pdslist.jsp"/>
		<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
		<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
		<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
		<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
	</jsp:include>

	

<br><br>

<a href="../mainJSP">Home</a>



	


<script type="text/javascript">

	var myVar;
	
	$(function () {
		$("#btnDown").click(function () {
			myVar = setTimeout(_refrush, 1000);		
		});
	});
	
	function _refrush() {
		location.reload();
		clearTimeout(myVar);
	}
	
	
	//검색 기능 부분.
	function searchPds() {
		var word = document.getElementById("search").value;
		var choice = document.getElementById("choice").value;
		
		//제목, 작성자 등등 뭐가 넘어 오나 확인 코드
//		alert("choice = " + choice);
		
		location.href = "pdslist.jsp?findWord=" + word + "&choice=" + choice;	
	}

</script>
</body>
</html>