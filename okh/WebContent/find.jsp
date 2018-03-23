<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	
	String toid = null;
	if(request.getParameter("toid") != null){
		toid = request.getParameter("toid");
	}
	if(userID == null){
		session.setAttribute("messageType", "오류 메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
		response.sendRedirect("index.jsp");
		return;
	}
	%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css?ver=1.1">
	<title>chat</title>
	<link rel="stylesheet" type="text/css" href="_lifemain.css?ver=1.3">
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
</head>
<body>
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
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							확인 메시지
						</h4>
					</div>
					<div id="checkMessage" class="modal-body">
						<%=messageContent %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 로그아웃, 정보수정 popover -->
	<script type="text/javascript">
	$(function() {
		$('#btnPopover').popover({
			placement: 'right',
			container: 'body',
			html: true,
			trigger: 'hover',
			content: '<p>설정</p><hr><button type="button" class="btn btn-default popover-dismiss" onclick="logout()">로그아웃</button><button type="button" class="btn btn-default popover-dismiss" onclick="mypage()">정보수정</button>'
		});
		$('#btnPopover').on('hide.bs.popover', function(evt) {
			if(!$(evt.target).hasClass('hide-popover')) {
				evt.preventDefault();
				evt.stopPropagation();
				evt.cancelBubble = true;
			}
		});
		$('#btnPopover').on('hidden.bs.popover', function(evt) {
			$(this).removeClass('hide-popover');
		});
		$('body').on('click', '.popover-dismiss', function() {
			$('#btnPopover').addClass('hide-popover');
			$('#btnPopover').popover('hide');
		});
      
      $('#btnPopover').data('overButton', false);
      $('#btnPopover').data('overPopover', false);
      $.fn.closePopover = function(){
        var $this = $(this);
        
        if(!$this.data('overPopover') && !$this.data('overButton')){
          $this.addClass('hide-popover');
          $this.popover('hide');              
        }
      }
      
      $('#btnPopover').on('mouseenter', function(evt){
        $(this).data('overButton', true);
      });
      $('#btnPopover').on('mouseleave', function(evt){
        var $btn = $(this);
        $btn.data('overButton', false);
        
        setTimeout(function() {$btn.closePopover();}, 200);
        
      });
      $('#btnPopover').on('shown.bs.popover', function () {
        var $btn = $(this);
        $('.popover-content').on('mouseenter', function (evt){
          $btn.data('overPopover', true);
        });
        $('.popover-content').on('mouseleave', function (evt){
          $btn.data('overPopover', false);
          
          setTimeout(function() {$btn.closePopover();}, 200);
        });
      });
    });
	</script>
	<script type="text/javascript">
	function logout() {
		location.href ="User?command=logout";
	}
	function mypage() {
		location.href ="User?command=mypage";
	}
	</script>
	
	<div class="wrap">
		<div class="container">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2"><h4>검색으로 친구찾기</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>친구 아이디</h5></td>
						<td><input class="form-control" type="text" id="findid" maxlength="20" placeholder="찾을 아이디를 입력하세요"></td>
					</tr>
					<tr>
						<td colspan="2"><button class="btn btn-primary pull-right" onclick="findFunction();">검색</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="container">
			<table id="friendResult" class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
			</table>
		</div>
	</div>
	
	<script type="text/javascript">
	function findFunction() {
		var userID = $('#findid').val();
		$.ajax({
			type: "post",
			url: 'User?command=check',
			data: {userID: userID},
			success: function(result) {
				if(result == 0){
					$('#checkMessage').html('친구찾기에 성공했습니다.');
					$('#checkType').attr('class', 'modal-content panel-success');
					getFriend(userID);
				}else{
					$('#checkMessage').html('친구를 찾을 수 없습니다.');
					$('#checkType').attr('class', 'modal-content panel-warning');
					failFriend();
				}
				$('#checkModal').modal("show");
			}
		});
	}
	function getFriend(findid) {
		$('#friendResult').html('<thead>' +
				'<tr>' +
				'<th><h4>검색 결과</h4></th>' +
				'</tr>' +
				'<thead>' +
				'<tbody>' +
				'<tr>' +
				'<td style="text-aligh: center;"><h3>' + findid + '</h3><a href="chat.jsp?toid=' + encodeURIComponent(findid) + '" class="btn btn-primary pull-right">' + '메시지 보내기</a></td>' +
				'</tr>' +
				'</tbody>');
	}
	function failFriend() {
		$('#friendResult').html('');
	}
	</script>
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
	</script>
<!-- setinterval -->
<%
if(userID != null){
%>
	<script type="text/javascript">
	$(document).ready(function() {
		getInfiniteUnread();
	});
	</script>
<%
}
%>
</body>
</html>