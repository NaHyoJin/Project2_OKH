<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%
	request.setCharacterEncoding("utf-8");
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
	
	/* qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
	List<QnaDto> qnalist = dao.getQnaList(); */	
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
	<link rel="stylesheet" type="text/css" href="../_main.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>jobs_bbs5NormalBbsWrite.jsp</title>
	
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

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
			/* 장문석  study*/
			$("#combbs").click(function () {
				location.href = "CommunityControl?command=list";
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
	
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,                 // set editor height
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true                  // set focus to editable area after initializing summernote
	     });
	});
	</script>
 
	<%
		//인간 로그인 안하면 안보이게 하는 부분.
//		if(mem != null){
	%>
<!-- 	
		<a href="../HwWrite.BBSHWCodingController" style="margin-left: 800px; color: green">새 글 쓰기</a>
	<br>
 -->	
	<%
//		}
	%>

	<%-- 
	<a href="" style="margin-left: 320px">최신순</a>
	<a href="">추천순</a>
	<a href="">조회순</a>
	<br>
	
	
	<div >
	<form action="" style="">
		<input type="text" border="1" style="margin-left: 320px" placeholder="검색" autofocus>
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
//		if(bbslist == null || bbslist.size() == 0){
	%>	
		<tr>
			<td colspan="6" align="center">작성된 글이 없습니다.</td>
		</tr>
	<%
//		}
//		for(int i = 0;i < bbslist.size(); i++){
//			BbsDto bbs = bbslist.get(i);
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
				<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>">
					<%=bbs.getTitle() %>
				
				<%
					}
				%>
			
				</a>
			</td>
			<td><%=bbs.getId() %></td>
		</tr>	
		
	<%
//		}
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
 --%>

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
 
<div class="wrap">
		<a href="../logout.jsp">로그아웃</a>
				
		<h2 align="center"><%=mem.getId() %>님 게시글 작성</h2><br>
	
		<form action="../BBSboardController" method="get" 
			enctype="multipart/form-data">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" id="id" name="id" size="50" 
						readonly="readonly" value="<%=mem.getId() %>">
					<input type="hidden" name="id" value="<%=mem.getId() %>">
					<input type="hidden" name="command" value="normalbbswriteAf">
					
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input autofocus type="text" class="form-control" id="title" 
					name="title" size="50" placeholder="제목를 입력해 주세요." >
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<input type="text" size="50" id="tag" name="tag" value="IT News & 정보" 
						placeholder="Tags," class="form-control" data-role="tagsinput" 
						style="display: none;" onclick="make_tag()">
				</td>
			</tr>
	
			<tr>
				<td>파일 업로드</td>
				<td>
					<input type="file" name="filename" style="widows: 400px">
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" id="summernote" 
					value=""></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="reset" id="cancel" value="취소"
					onclick="location.href='../BBSboardController?command=normalBbs'">
				</td>
			</tr>
		</table>
		</form>
	</div> 

</body>
</html>