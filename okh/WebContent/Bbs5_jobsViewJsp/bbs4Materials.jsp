<%@page import="jobs_BBS5.BbsMaterialsBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsService"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsServiceImpl"%>
<%@page import="jobs_BBS5.BbsHWCodingBeanDtoVO"%>
<%@page import="java.util.List"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="user.UserDto"%>
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
    <%

	//로그인 정보 확인 부분.
	Object ologin = session.getAttribute("login");
    
    UserDto mem = null;//null로 초기화.
    
	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}else{
		System.out.println("로그인한 정보 없음.");
	}
	
	jobsBbs5MaterialsServiceImpl dao = jobsBbs5MaterialsService.getInstance();
	List<BbsMaterialsBeanDtoVO> materialslist = dao.getPdsList();
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
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">

	<title>bbs4Materials.jsp</title>
	
	<link rel="stylesheet" type="text/css" href="../_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<!-- 스타일 부분 -->
<style type="text/css">

	a{
		text-decoration: none;
		/* margin-left: 320px; */
	}

</style>
	
</head>
<body>

	<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs"> <!-- 장문석 study -->
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 HW코딩 부분으로 가는것.-->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	


	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.

			$("#login").click(function() {
				location.href="../User?command=login";
			});
	
			$("#account").click(function() {
				location.href="../User?command=join";
			});
			
			//QNA
			$("#qnabbs").click(function() {
				location.href="../qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="../second.jsp";
			});
	
			$("#techbbs").click(function() {
				location.href="../TechbbsController?command=techbbs";
			});
	
			//병찬 사는 이야기.
			$("#life").click(function() {
				location.href="../LifeBbs?command=life";
			});
			
			/* 장문석  study*/
			$("#combbs").click(function () {
				location.href = "../CommunityControl?command=list";
			});			
	 
			//게시판5 나효진 jobs 부분.
			$("#jobs").click(function () {
				location.href="../jobs";
			});

	 
		});
	</script>
<!--  
	<a href="../mainHW.BBSHWCodingController">H/W & Coding NEWS;</a>../현재 폴더의 윗 폴더
	<a href="../BBSboardController">자유 게시판;</a>/최상위 폴더
	<a href="../BBSmaterialsController">자료실;</a>
-->
	<div style="margin-left: 320px" >
		<jsp:include page="BBS5TopMenuinclude.jsp" flush="false" />
	</div>

	<%
		//인간 로그인 안하면 안보이게 하는 부분.
		if(mem != null){
	%>
		<!-- <a href="../HwWrite.BBSHWCodingController"  기존 코드..망한것.-->
		<a href="../BBSmaterialsController?command=Materialswrite" 
		style="margin-left: 800px; color: green">새 글 쓰기</a>
	<br>
	<%
		}
	%>
	<a href="" style="margin-left: 320px">최신순</a>
	<a href="">추천순</a>
	<a href="">조회순</a>
	<br>
	
	
	<div >
	<form action="" style="">
		<input type="text" border="1" style="margin-left: 320px" 
			placeholder="검색" autofocus>
		<button onclick="" >检索 bbs4HWCoding</button>
	</form>
	</div>
	<br>
<div align="center" style="margin-left: 280px">

<table border="1">
<col width="50"><col width="100"><col width="400"><col width="100">
<col width="100"><col width="100"><col width="100">

<tr bgcolor="#09bbaa">
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th>
</tr>

	<%
	for(int i = 0; i < materialslist.size(); i++){
		BbsMaterialsBeanDtoVO pds = materialslist.get(i);
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
		<td><%=pds.getId() %></td>
		
		<!-- 제목 부분 -->
		<td align="left">
			<%-- <a href="pdsdetail.jsp?seq=<%=pds.getSeq() %>"> --%>
			<a href="../BBSmaterialsController?command=detail&seq=<%=pds.getSeq() %>">
				<%=pds.getTitle() %>
			</a>
		</td>
		<td>
			<input type="button" name="btnDown" id="btnDown" value="파일"
			onclick="location.href='filedown?filename=<%=pds.getFilename() %>&seq=<%=pds.getSeq() %>'">			
		</td>
		
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegdate() %></td>
	</tr>
	
	<%	
		}
	%>

</table>
<br>

<%-- 
페이징 처리 일단 보류
<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="bbslist.jsp"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include>
 --%>


</div>


	<!-- 황준현 -->
<!-- wrap로 메인페이지 섹션사이즈만들어준거고 그밑에 자식들 partition1~partition4로 테이블뿌리면된니다  -->
<%-- 	<div class="wrap" id="tableChange">
		<div class="partition1">
			게시판뿌려주기1
			<table border="1">
				<tr>
					<td>황</td>
					<td>준</td>
					<td>현</td>
				</tr>
				<tr>
					<td>황</td>
					<td>준</td>
					<td>현</td>
				</tr>
				<tr>
					<td>황</td>
					<td>준</td>
					<td>현</td>
				</tr>
				<tr>
					<td>황</td>
					<td>준</td>
					<td>현</td>
				</tr>
				<tr>
					<td>황</td>
					<td>준</td>
					<td>현</td>
				</tr>
			</table>
		</div>
		<div class="partition2">
			게시판뿌려주기2
		</div>
		<div class="partition3">
			게시판뿌려주기3
		</div>
		<div class="partition4">
			게시판뿌려주기4
		</div>
	</div>
	<%
	String messageContent = null;
	if(session.getAttribute("messageContent") != null){
		messageContent = (String)session.getAttribute("messageContent");
	}
	String messageType = null;
	if(session.getAttribute("messageType") != null){
		messageType = (String)session.getAttribute("messageType");
	}
	if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.println("panel-success"); %> ">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType.trim() %>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent.trim() %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#messageModal').modal("show");
	</script>
	
	<%
			session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
 --%>	

</body>
</html>

<%-- 
<%@page import="user.UserDto"%>
<%@page import="jobs_BBS5.BbsMaterialsBeanDtoVO"%>
<%@page import="java.util.List"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDao"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDaoImpl"%>
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
<title>bbs4Materials.jsp</title>

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
 <!-- include 부분. 상단 이동 부분. -->
 <div class="">
	<jsp:include page="BBS5TopMenuinclude.jsp" flush="false" />
</div>

<%
		//인간 로그인 안하면 안보이게 하는 부분.
		if(mem != null){
	%>
	
	<br>
	 <a href="../PdsWrite.BBSmaterialsController?command=detail">새 글 쓰기</a> 
		<!-- <a href="../PdsWrite.BBSmaterialsController" 
		style="text-decoration: none; text-align: right">자료올리기</a> -->
		
	<%
		}
	%>
	<br><br>
	
	<a href="../Pdslatest.BBSmaterialsController">최신순</a>
	<a href="../PdsUp.BBSmaterialsController">추천순</a>
	<a href="../PdsRead.BBSmaterialsController">조회순</a>
	

	
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
jobsBbs5MaterialsDaoImpl dao = jobsBbs5MaterialsDao.getInstance();
List<BbsMaterialsBeanDtoVO> pdslist = dao.getPdsPagingList(paging, findWord, cho);
	%>
	
	
	<div align="center">

		<!-- search -->
		
		<select id="choice">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" id="search">
		<button name="search" onclick="searchPds()">检索 bbs4Materials</button>
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
	for(int i = 0; i < pdslist.size(); i++){
		BbsMaterialsBeanDtoVO pds = pdslist.get(i);
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
		<td><%=pds.getId() %></td>
		
		<!-- 제목 부분 -->
		<td align="left">
		<!-- 컨트롤러로 시퀀스 번호 넘겨줘야하는 부분인데??? -->
			<a hre="../PdsDetail.BBSmaterialsController?command=detail&seq=<%=pds.getSeq() %>">
			<a href="pdsdetail.jsp?seq=<%=pds.getSeq() %>">
			<a href="pdsdetail_180308.jsp?seq=<%=pds.getSeq() %>">
				<%=pds.getTitle() %>
			</a>
		</td>
		<td>
			<input type="button" name="btnDown" id="btnDown" value="파일"
			onclick="location.href='filedown?filename=<%=pds.getFilename() %>&seq=<%=pds.getSeq() %>'">			
		</td>
		
		<td><%=pds.getReadcount() %></td>
		<td><%=pds.getDowncount() %></td>
		<td><%=pds.getRegdate() %></td>
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
</html> --%>