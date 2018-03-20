<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="_lifemain.css?ver-1.62">
	<link rel="stylesheet" type="text/css" href="_lifewrite.css?ver=1.1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>Insert title here</title>
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

</head>
<body>
<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	%>
<!-- 메뉴 -->
	<div class="menu">
		<%
		if(ologin == null){
		%>
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<%
		}else{
		%>
		<div class="actionlogin">
			<span><%=mem.getId() %></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover">	
		</div>
		<%
		}
		%>
		<input type="button" class="bbs1" id="qnabbs">				<!-- 박형태 -->
		<input type="button" class="techbbs_hjh" id="techbbs">		<!-- 황준현 -->
		<input type="button" class="bbs3" >							<!-- 정재흥 -->
		<input type="button" class="bbs4" >							<!-- 장문석 -->
		<input type="button" class="bbs5" id="jobs">				<!-- 나효진 -->
		<input type="button" class="bbs6" id="life">				<!-- 정병찬 -->
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#qnabbs").click(function() {
			location.href="qnaServlet?command=listQna";
		});	
		$("#techbbs").click(function() {
			location.href="TechbbsController?command=techbbs";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	});
	</script>
<!-- modal -->
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
<!-- View -->
	<div class="titlediv"><h2>새 글 쓰기</h2>
	</div>
	<div class="wrap">
		<div class="myinfo">
			<p id="test" align="left"><%=mem.getId() %></p>
		</div>
		<div class="writearea">
		<form action="lifeBbsUpload.jsp" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="<%=mem.getId() %>">
			<input type="text" class="form-control" id="title" name="title" placeholder="제목를 입력해 주세요."><br>
			<input type="text" id="tag" name="tag" value="" placeholder="Tags," class="form-control" data-role="tagsinput" onclick="make_tag()"><br><br>
			<div>
				<input type="file" name="fileload" style="widows: 400px" class="btn btn-default btn-wide">
				<input type="hidden" id="fileload1" name="fileload1" style="widows: 400px" class="btn btn-default btn-wide">
				<input type="hidden" id="fileload2" name="fileload2" style="widows: 400px" class="btn btn-default btn-wide">
				<input type="hidden" id="fileload3" name="fileload3" style="widows: 400px" class="btn btn-default btn-wide">
				<input type="hidden" id="fileload4" name="fileload4" style="widows: 400px" class="btn btn-default btn-wide">
				<input type="button" id="uploadBtn" value="추가" class="btn btn-default btn-wide"><br><br>
			</div>
			<textarea name="content" id="summernote"></textarea><br>
		 	<input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
			<input type="submit" style="float: right" class="btn btn-success btn-wide" value="글추가">
		</form>
		</div>
	</div>
<!-- summernote -->
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,
	             minHeight: null,
	             maxHeight: null,
	             focus: true
	     });
	});
	</script>
<!-- 다중 파일 추가 버튼 -->
	<script type="text/javascript">
	$(document).ready(function() {
		var i = 1;
		$('#uploadBtn').click(function() {
			$('#fileload' + i).attr('type', 'file');
			if(i < 5){
				i++;
			}
		});
	});
	</script>
<!-- 새 글 쓰기 취소 -->
	<script type="text/javascript">
	function gotobbs() {
		location.href = "LifeBbs?command=life";
	}
	</script>
</body>
</html>