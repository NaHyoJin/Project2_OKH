<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@page import="columnBbs.PdsDto"%>
<%@page import="columnBbs.PdsDao"%>
<%@page import="columnBbs.iPdsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>columnUpdate.jsp</title>
	<link rel="stylesheet" type="text/css" href="_main.css?ver-1.62">
	<link rel="stylesheet" type="text/css" href="_lifewrite.css?ver=1.1">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>

<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	IUserService service = UserService.getInstance();

	String profile = null;
	int mainscore=0;
	String maingetprofile="";
	
	if(ologin != null){
		profile = service.getProfile(mem.getId());
		mainscore=service.getScore(mem.getId());
		maingetprofile=service.getProfile(mem.getId());
	}
	String userID = null;
	   if(session.getAttribute("userID") != null){
	      userID = (String)session.getAttribute("userID");
	   }
	%>
<!-- 메뉴 -->
	<div class="menu">
		<%
		if(ologin == null){
		%>
		<input type="button" class="homebtn" onclick="location.href='index.jsp'">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<%
		}else{
		%>
		<input type="button" class="homebtn" id="homebtn">
<div class="actionlogin">
	<a onclick="upmydetail()" style="cursor: pointer">
			<img src="<%=maingetprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
			<span class="memid"><a onclick="upmydetail()" style="cursor: pointer;color: #fff;"><%=mem.getId() %></a></span> <br>
				<span class="point"><img src="image/actionpoint.PNG" style="margin-top: 0" class="pointimg"><%=mainscore%></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
				
</div>
		<%
		}
		%>
		<input type="button" class="bbs1" id="qnabbs">				<!-- 박형태 -->
		<input type="button" class="techbbs_hjh" id="techbbs">		<!-- 황준현 -->
		<input type="button" class="bbs3" id="column">				<!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs">				<!-- 장문석 -->
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
		$("#column").click(function name() {
			location.href="Controller?command=column";
		});
		$("#jobs").click(function () {
			location.href = "jobs";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
		$("#combbs").click(function () {
			location.href = "CommunityControl?command=list";
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
<!-- 게시판 데이터 -->
<%
request.setCharacterEncoding("UTF-8");

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

iColumnBbsDao dao = ColumnBbsDao.getInstance();
ColumnBbsDto bbs = dao.getBbs(seq);
%>
<!-- View -->
	<div class="titlediv"><h2>글 수정</h2>
	</div>
	
	<div class="wrap">
		<div class="writearea">
			<form action="Controller" method="POST">
			<input type="text" style="margin-bottom: 10px;" class="form-control" id="id" name="id" size="50" readonly="readonly" value="아이디   <%=bbs.getId() %>">
				<input type="hidden" name="command" value="updateAf">
				<input type="hidden" name="seq" value="<%=sseq %>">
				<input type="text" class="form-control" id="title" name="title" value="<%=bbs.getTitle() %>" placeholder="제목를 입력해 주세요."><br>
				<textarea id="summernote" name="content"><%=bbs.getContent() %></textarea><br>
		 		<input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
				<input type="submit" style="float: right" class="btn btn-success btn-wide" value="저장">
			</form>
		</div>
	</div>
<!-- summernote -->
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
<!-- 글 수정 취소 -->
	<script type="text/javascript">
	function gotobbs() {
		location.href = "LifeBbs?command=detail&seq=" + <%=bbs.getSeq() %>;
	}
	</script>





</body>
</html>