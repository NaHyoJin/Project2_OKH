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
	
	<title>index.jsp</title>
	
	<link rel="stylesheet" type="text/css" href="_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
</head>
<body>


	<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3"><!-- 정재흥 -->
		<input type="button" class="bbs4" >
		<input type="button" class="bbs5" id="jobs">
	</div>	
	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.
	
			$("#login").click(function() {
				location.href="User?command=login";
			});
	
			$("#account").click(function() {
				location.href="User?command=join";
			});
			$("#qnabbs").click(function() {
				location.href="qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="second.jsp";
			});
	
			
			$("#techbbs").click(function() {
				location.href="TechbbsController?command=techbbs";
			});
			
			
	/* 
			//columns
			$("#").click(function() {
				location.href="";
			});
	 */		
			//게시판5 나효진 jobs 부분.
			$("#jobs").click(function() {
				location.href="community";
			});
	
		});
	</script>
	
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
</html>