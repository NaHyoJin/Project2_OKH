<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;

	IUserService service = UserService.getInstance();
	
	String yn="";
	int mainscore=0;
	String maingetprofile="";
	String profile = null;
	if(ologin != null){
		profile = service.getProfile(mem.getId());
		mainscore=service.getScore(mem.getId());
		maingetprofile=service.getProfile(mem.getId());
	}
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String)session.getAttribute("userID");
	}
	if(userID == null){
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
		response.sendRedirect("login.jsp");
		return;
	}

	%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LifeBBSDetail</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="_lifedetail.css?ver=1.1">
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.37">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
</head>
<body>

	<script type="text/javascript">
	function getUnread() {
		$.ajax({
			type: "POST",
			url: "Chat?command=unread",
			data: {
				userID: encodeURIComponent('<%=userID %>')
			},
			success: function(result) {
				if(result >= 1){
					showUnread(result);
				}else{
					showUnread('');
				}
			}
		});
	}
	function getInfiniteUnread() {
		setInterval(function() {
			getUnread();
		}, 4000);
	}
	function showUnread(result) {
		$('#unread').html(result);
	}

	function chatBoxFunction() {
		var userID = '<%=userID %>';
		$.ajax({
			type: "POST",
			url: "Chat?command=box",
			data: {
				userID: encodeURIComponent(userID),
			},
			success: function(data) {
				if(data == "") return;
				$('#boxTable').html('');
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++){
					if(result[i][0].value == userID){
						result[i][0].value = result[i][1].value;
					}else{
						result[i][1].value = result[i][0].value;
					}
					addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value);
				}
			}
		});
	}
	function addBox(lastid, toid, chatcontent, chattime) {
		$('#boxTable').append('<tr onclick="location.href=\'chat.jsp?toid=' + encodeURIComponent(toid) + '\'">'+
				'<td style="width: 150px;"><h5>' + lastid + '</h5></td>' +
				'<td>' +
				'<h5>' + chatcontent + '</h5>' +
				'<div class="pull-right">' + chattime + '</div>' +
				'</td>' +
				'</tr>');
	}
	function getInfiniteBox() {
		setInterval(function() {
			chatBoxFunction();
		}, 3000);
	}
	</script>
<!-- 메뉴 -->
	<div class="menu">
		<%
		if(ologin == null){
			yn="no";
		%>
		<input type="button" class="homebtn" onclick="location.href='index.jsp'">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<%
		}else{
			yn="yes";
		%>
		<input type="button" class="homebtn" id="homebtn">
<div class="actionlogin">
<a onclick="upmydetail()" style="cursor: pointer">
	<img src="<%=maingetprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
</a>		
			<span class="memid"><a onclick="upmydetail()" style="cursor: pointer;color: #fff;"><%=mem.getId() %></a></span> <br>
			<span class="point" style="margin-top: 0"><img src="image/actionpoint.PNG" class="pointimg"><%=mainscore%></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
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
		$("#homebtn").click(function() {
			location.href="main.jsp";
		});
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
		$("#combbs").click(function () {
			if(<%=yn.equals("yes")%>){
				location.href = "CommunityControl?command=list";
	
			}
			else{
				location.href = "User?command=guest";
			}
			
		});
	});
	</script>
<!-- View -->
	<div class="wrap">
		<div class="container">
			<table class="table" style="margin: 0 auto;">
				<thead>
					<tr>
						<th><h4>주고받은 메시지 목록</h4></th>
					</tr>
				</thead>
				<div style="overflow-y: auto; width: 100%; max-height: 450px;">
					<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; margin: 0 auto;">
						<tbody id="boxTable">
						</tbody>
					</table>
				</div>
			</table>
		</div>
	</div>
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
	
<!-- setinterval -->
<%
if(userID != null){
%>
	<script type="text/javascript">
	$(document).ready(function() {
		getUnread();
		getInfiniteUnread();
		chatBoxFunction();
		getInfiniteBox();
	});
	</script>
<%
}
%>
<script type="text/javascript">
	function logout() {
		location.href ="User?command=logout";
	}
	function upmydetail() {
		location.href ="User?command=mypage";
	}
	</script>
</body>
</html>