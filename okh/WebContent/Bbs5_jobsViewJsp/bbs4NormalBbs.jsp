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
   
    <%!
		// 댓글용
		public String arrow(int depth){
			String rs = "<img src='../image/arrow.png' width='20px' height='20px'/>";
			String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
			String ts = "";
			
			for(int i = 0;i < depth; i++){
				ts += nbsp;
			}
			return depth == 0?"":ts+rs;
		}
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
		//검색을 하기위한 변수 값 받는 부분.
		String findWord = request.getParameter("findWord"); 
		String choice = request.getParameter("choice"); 

		
		System.out.println("IT News & 정보");
List<BbsBoardBeanDtoVO> bbslist = (List<BbsBoardBeanDtoVO>)request.getAttribute("bbslist");
		
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
//싱글톤 생성 부분.
jobsBbs5ModelServiceImpl service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...

	//로그인 정보 확인 부분.
	Object ologin = null;
	ologin = session.getAttribute("login");
    
//로그인 안하고 들어올수 있는 경우.
    UserDto mem = null;
    
	//로그인을 안해도 일단 글 보여야되는데...
	bbslist = service.getBbsNormalBeanDTOList();//전체글 가지고 오는건데...
     
	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}
	else{
		System.out.println("일반 게시판 로그인한 정보 없음.");
		System.out.println("로그인한 정보 없는때도 전체 글 다 가지고 와야되는데");
		service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...
		
		//로그인을 안해도 일단 글 보여야되는데...
		bbslist = service.getBbsNormalBeanDTOList();
	}
	
	//싱글톤 생성 부분.
	service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...

	//로그인을 안해도 일단 글 보여야되는데...
	bbslist = service.getBbsNormalBeanDTOList();
	
%>
	
<!--  
	<a href="../BBSHWCodingController">H/W & Coding NEWS;</a>../현재 폴더의 윗 폴더
	<a href="../BBSboardController">자유 게시판;</a>/최상위 폴더
	<a href="../BBSmaterialsController">자료실;</a>
 -->	
 
 <div >
	<jsp:include page="BBS5TopMenuinclude.jsp" flush="false" />
</div>

	<%
		//인간 로그인 안하면 안보이게 하는 부분.
		if(mem != null){
	%>
	<p align="center">환영합니다. <%=mem.getName() %>님 반갑습니다.</p>
	<br>
	<!-- 일반 글 쓰기 로 가는것 -->
	<a href="../BBSboardController?command=normalwrite">새 게시판 글 쓰기</a><!-- 경로 문제였음....십할.. -->
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
		service = jobsBbs5ModelService.getInstance();//먼저 서비스를 불러야지...

		//로그인을 안해도 일단 글 보여야되는데...
		bbslist = service.getBbsNormalBeanDTOList();
	%>
	
	<div align="center">

		<!-- search -->
		
		<select id="choice">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" id="search">
		<button name="search" onclick="searchBbs()" autofocus>检索 bbs4NormalBbs</button>
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
<%-- 		
		<%
			if(bbs.getDel() != 0){//삭제된건 안보여주게 하는것.
		%>
		<h4 align="center" >이 글은 삭제되었습니다.</h5>
					
				<%
					}else{
				%>
 --%>				
<%-- 		
			<a href="../PdsDetail.BBSmaterialsController?command=detail&seq=<%=bbs.getSeq() %>">
 --%>			
			<a href="../BBSboardController?command=detail&seq=<%=bbs.getSeq() %>"> 
			<%-- <a href="pdsdetail_180308.jsp?seq=<%=pds.getSeq() %>"> --%>
				<%=bbs.getTitle() %>
			</a>
<%-- 			
			<%
			}
			%>
			 --%>
		</td>
		<!-- 다운로드 부분. -->
		<td>
			<input type="button" name="btnDown" id="btnDown" value="Download"
			onclick="location.href='filedown?filename=<%=bbs.getFilename() %>&seq=<%=bbs.getSeq() %>'">			
		</td>
		
		<td><%=bbs.getReadcount() %></td>
		<td><%=bbs.getDowncount() %></td>
		
		<!-- 글작성일. -->
		<td><%=bbs.getWdate() %></td>
		
	</tr>
	
	
	
	<%	
		}//////////전체 for문.
	%>

</table>


<%-- 
	<jsp:include page="paging.jsp" flush="false">
		<jsp:param name="actionPath" value="bbs4NormalBbs.jsp"/>
		<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
		<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
		<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
		<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
	</jsp:include>
 --%>

	

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