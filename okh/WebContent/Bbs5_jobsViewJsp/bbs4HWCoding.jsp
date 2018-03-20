<%@page import="jobs_BBS5.PagingBean"%>
<%@page import="jobs_BBS5.newbbs5HWCodingVO"%>
<%@page import="jobs_BBS5.newbbs5HWCodingService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingServiceImpl"%>
<%@page import="jobs_BBS5.BbsHWCodingBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="user.UserDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--     
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
 --%>	
    <% //한글 깨짐 인코딩.
	request.setCharacterEncoding("utf-8");
	%>
	<%
		//검색을 하기위한 변수 값 받는 부분. //검색을 눌렀을때만넘어옴
		String findWord = request.getParameter("findWord"); 
		String choice = request.getParameter("choice"); 
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<title>bbs4HWCoding.jsp</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" 
rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" 
rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	

<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../_jobsbbs.css?ver=1.59"><!-- ../경로 설정 유념-->
</head>
<body bgcolor="#fcfbfb">


<%      
//로그인 정보 확인 부분. //로그인한id가져오기
	Object ologin = session.getAttribute("login");

 	UserDto mem = null;//null로 초기화.
 	List<newbbs5HWCodingVO> techlist = (List<newbbs5HWCodingVO>)request.getAttribute("techbbs");
	
 	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}else{
		System.out.println("로그인한 정보 없음.");
	}
	
	newbbs5HWCodingServiceImpl dao = newbbs5HWCodingService.getInstance();
	List<newbbs5HWCodingVO> hwlist = dao.gettechBbsList();
	%>


<%-- 
 <%//로그인한id가져오기
//로그인 정보 확인 부분. //로그인한id가져오기
	Object ologin = session.getAttribute("login");

 	UserDto mem = null;//null로 초기화.
	List<newbbs5HWCodingVO> techlist = (List<newbbs5HWCodingVO>)request.getAttribute("techbbs");
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
 --%>

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

	dao = newbbs5HWCodingService.getInstance();

hwlist = dao.gettechBbsPagingList(paging, findWord, cho);

System.out.println(hwlist.size() + "사이즈크기");
%>

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
	<div class="titlediv"><span class="titi">H/W & Coding NEWS;</span>
<%-- 	
	<div style="margin-left: 320px" >
		<jsp:include page="BBS5TopMenuinclude.jsp" flush="false" />
	</div>
 --%>
<button class="create btn btn-success btn-wide pull-right " 
type="button" id="techwrite">게시글 쓰기</button></div>
	
	
<div class="wrap">
	<div class="sercharea">
		<select id="choice" style="height: 30px">
			<option value="tagname" <%if(cho == 3){ out.println("selected");}%>>선택하세요</option>
			<option value="title" <%if(cho == 0){ out.println("selected");}%>>제목</option>
			<option value="writer" <%if(cho == 1){ out.println("selected");}%>>작성자</option>
			<option value="content" <%if(cho == 2){ out.println("selected");}%>>내용</option>
		</select>
		<input type="text" class="inputField" id="search" value="<%=findWord %>">
		<button name="search" id="serchbtn" class="input-group-btn" onclick="searchBbs()">
	
	<img alt="" src="../image/serchbtn.PNG"></button>
	</div>
		<div class="board">
			<table border="1" class="techtable">
			<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
				
				<%
					if(hwlist == null || hwlist.size() == 0) {
					
				%>
				<tr>
					<th colspan="5">리스트가없습니다</th>
					</tr>
					
				<%
				}
				
				for(int i=0; i < hwlist.size(); i++){
					newbbs5HWCodingVO dto = hwlist.get(i);
					String[] tagnames = dao.getTagName(dto.getTagname());	
					
					dao = newbbs5HWCodingService.getInstance();
					boolean chekcomment = dao.checkcomment(hwlist.get(i).getSeq());
					if(chekcomment){
				%>
				<tr>
					<th>
					#<%=hwlist.get(i).getSeq() %>
				<%
				}else{
				%>
				<tr>
					<th style="border-left: 5px solid #808080">
					#<%=hwlist.get(i).getSeq() %>
				<%}
				for(int j=0; j < tagnames.length; j++){//추가시킬때무조건추가시킬거는 -없이해도되고 엔터치면 -그값을넣어준다
				%>
					<span><button class="hjhtag" name="tag<%=j%>" id="tag<%=j%>" 
					onclick="searchBbs1(this)" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
				<%
				}
				%>
				
				<p style="font-size: 20px">
				
				<!-- 컨트롤러 부분 like 부분 일단 주석 처리 하고 해보자. -->
				<%
				//로그인 안하고 글 볼때 null 값으로.
					if(mem == null){
						String memNull = null;
						%>
	<a href="../BBSHWCodingController?command=techdetail&likeid=<%=memNull %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a>			
				<%
					}else{
				%>
				<%-- <a href="../BBSHWCodingController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a> --%>
	<a href="../BBSHWCodingController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a>
	 			<%
					}
	 			%>
	 			
	 			</p>
				</th>
				<%if(dto.getCommentcount() > 0){
				%>
				<td><img src="../image/repleon.PNG"><span class="textalig"> <%=dto.getCommentcount() %></span></td>
				<%
				}else{
				%>
				<td><img src="../image/repleoff.png"><span class="textalig"> <%=dto.getCommentcount() %></span></td>
				<%
				}
				%>
				<%if(dto.getLikecount() > 0){
				%>
				<td style="padding-top: 3px"><img src="../image/likeeon.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
				<%
				}else{
				%>
				<td style="padding-top: 3px"><img src="../image/likeeoff.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
				<%
				}
				%>
				<%if(dto.getReadcount() > 0){
				%>
				
				<td><img src="../image/readcounton.PNG"><span class="textalig"> <%=dto.getReadcount() %></span></td>
				
				<%
				}else{
				%>
				<td><img src="../image/readcountoff.png"><span class="textalig"> <%=dto.getReadcount() %></span></td>
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
			
	<%-- 	페이징 부분 어디가 문제인지 --%>
	<jsp:include page="paging.jsp">
		<jsp:param name="actionPath" value="bbs4HWCoding.jsp"/>
		<jsp:param name="findWord" value="<%=findWord %>"/>
		<jsp:param name="choice" value="<%=choice %>"/>
		<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
		<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
		<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
		<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
	</jsp:include>
	
	
	<script type="text/javascript">
	if(document.getElementById("choice").value == "tagname"){
		$("#search").val("");
	}
	</script>
	
		</div>
</div><!-- 게시판 전체 다이브 -->


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
	
	

<script type="text/javascript">
function searchBbs() {
	
	if(document.getElementById("choice").value == "tagname"){
		alert("선택해주세요");
		$("#search").val("");
		return;
	}
	
	if(document.getElementById("search").value==""){//빈문자열에서 검색시
		location.href = "bbs4HWCoding.jsp?findWord=TechTips&choice=tagname";	
		return;
	}
	var word = document.getElementById("search").value;
	var choice = document.getElementById("choice").value;
	$("#select_id").val("<%=cho%>").prop("selected", true);
	location.href = "bbs4HWCoding.jsp?findWord=" + word + "&choice=" + choice;	
}

function searchBbs1(e) {
	$("#search").val("");
	var word = e.value;
	var choice = document.getElementById("choice").value;
	location.href = "bbs4HWCoding.jsp?findWord=" + word + "&choice=tagname";	
	
}


$(function() {
	
	
	$("#techbbs").click(function() {
		location.href = "TechbbsController?command=techbbs";
	});//////////////techbbs
	
	
	//글 작성 부분.
	$("#techwrite").click(function() {
<%-- 		
		<%
		ologin = session.getAttribute("login");
		mem = null;//null로 초기화.
		if(ologin != null){//로그인 한 부분이다.
			mem = (UserDto)ologin;
			//로그인 정보 가지고 오나 확인 부분.
			System.out.println("mem : " + mem.toString());
		%>
		
			location.href = "BBSHWCodingController?command=techwrite";
		
		<%
		}else{
			System.out.println("게시글 부분 로그인한 정보 없음.");
			String Unknown = "Unknown";
			request.setAttribute("Unknown", Unknown);
		%>
		
		alert("로그인을 해주세요.");
		
		<%
		return;
		}
		%>
 --%>
		
		
<%-- 		
		if(<%=mem %> == null){
			alert("로그인을 해주세요.");
			return;
		}else{
			location.href = "BBSHWCodingController?command=techwrite";
		}
 --%>		
		
<%
		//로그인 정보 확인 부분.
/* 		
		ologin = null;
		ologin = session.getAttribute("login");
	    
		//로그인 안하고 들어올수 있는 경우.
	    mem = null;
	     */ 
		if(ologin != null){
			mem = (UserDto)ologin;
			//로그인 정보 가지고 오나 확인 부분.
			System.out.println("mem : " + mem.toString());
		%>
//		alert("로그인 오케이.");

		location.href="../BBSHWCodingController?command=techwrite";
		
<%
		} else{
%>

	alert("로그인을 해주십시오.");
	location.href="../jobs";
	
<%
		}
%>

 
 
 
<%-- 		
		ologin = session.getAttribute("login");
	    
	    mem = null;//null로 초기화.
		%>
		if(<%=ologin %> == null){
			alert("로그인을 해주십시오.");
		}else{
//			alert("로그인 오케이.");
			location.href="../BBSHWCodingController?command=techwrite";
		}
 --%>	
 		
		//로그인 정보 확인 부분. //로그인한id가져오기
/* 		Object ologin = session.getAttribute("login");
	    System.out.ptintln("ologin : " + ologin);
	    
	    UserDto mem = null;//null로 초기화.
	    
		if(ologin != null){
			mem = (UserDto)ologin;
			//로그인 정보 가지고 오나 확인 부분.
			System.out.println("mem : " + mem.toString());
			location.href="TechbbsController?command=techwrite";
		}else{
			System.out.println("로그인한 정보 없음.");
			alert("로그인을 해주십시오.");
			location.href="../BBSHWCodingController?command=list";
		}
 */
	});//////////techwrite
	
});
</script>
</body>
</html>

<%-- <%@page import="jobs_BBS5.BbsHWCodingBeanDtoVO"%>
<%@page import="java.util.List"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
	
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">

	<title>bbs4HWCoding.jsp</title>
	
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

	

	
	
	
<!--  
	<a href="../mainHW.BBSHWCodingController"></a>../현재 폴더의 윗 폴더
	<a href="../BBSboardController">자유 게시판;</a>/최상위 폴더
	<a href="../BBSmaterialsController">자료실;</a>
-->
	
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
<col width="70"><col width="500"><col width="50"><col width="50"><col width="70"><col width="150">

<tr>
	<th>번호</th><th>제목</th><th>답글</th><th>추천</th><th>조회수</th><th>작성자</th>
</tr>

	<%
		if(hwlist == null || hwlist.size() == 0){
	%>	
		<tr>
			<td colspan="6" align="center">작성된 글이 없습니다.</td>
		</tr>
	<%
		}
		for(int i = 0;i < hwlist.size(); i++){
			BbsHWCodingBeanDtoVO bbs = hwlist.get(i);
	%>

	
 	<tr>
			<td><%=i+1 %></td>
			<td>
				<%=arrow(bbs.getDepth()) %>


				<%
					if(bbs.getDel()==1) { 
				%>
				
					<h5 align="center" >이 글은 삭제되었습니다.</h5>
					
				<%
					}else{
				%>

<!-- 디테일로 가는 부분 -->				
				<a href="../BBSHWCodingController?command=detail&seq=<%=bbs.getSeq() %>">
					<%=bbs.getTitle() %>
				
				<%
					}
				%>
			
				</a>
			</td>
			<td><%=bbs.getId() %></td>
		</tr>
		
	<%
		}
	%>
</table>
<br>


페이징 처리 일단 보류
<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="bbslist.jsp"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include>



</div>


	<!-- 황준현 -->
<!-- wrap로 메인페이지 섹션사이즈만들어준거고 그밑에 자식들 partition1~partition4로 테이블뿌리면된니다  -->
	<div class="wrap" id="tableChange">
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
	

</body>
</html> --%>