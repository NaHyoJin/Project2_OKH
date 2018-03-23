<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.List"%>
<%@page import="techbbs.TechbbsDto"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	
	<title>index.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<link rel="stylesheet" type="text/css" href="_update.css?ver=1.33">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.32">
</head>
<body>
<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
mem = (UserDto)ologin;
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

	<!-- 인클루드 부분 -->
	<div class="menu">
				<%
if(ologin == null){	//로그인안한상태
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
		$(function() {//좌측 메뉴바 누르는 곳.
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
	
	<%
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
	<%
	request.setCharacterEncoding("UTF-8");

	int seq = (int)request.getAttribute("seq");
	TechbbsServiceImpl tservice=TechbbsService.getInstance();
	List<TechbbsDto> techlist = tservice.getdetail(seq);
	%>
	<script type="text/javascript">
	$(document).ready(function() {
	     $('#summernote').summernote({
	             height: 300,                 // set editor height
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true                  // set focus to editable area after initializing summernote
	     });
	     $('#summernote').summernote();
	});
	</script>
	<div class="titlediv"><h2>글 수정</h2><br>
	</div>
	<div class="wrap">
		<form action="TechbbsController" method="POST">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="hidden" name="command" value="updateAf">
					<input type="hidden" name="seq" value="<%=seq %>">
					<input type="text" class="form-control" id="id" name="id" size="50" readonly="readonly" value="<%=techlist.get(0).getId() %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" class="form-control" id="title" name="title" size="50" value="<%=techlist.get(0).getTitle() %>">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
				<%
				String[] tagnames=tservice.getTagName(techlist.get(0).getTagname());
				
				for(int i=0;i<tagnames.length;i++){
				%>
					<span class="hjhtag" id="tag<%=i%>"><%=tagnames[i] %></span>
				<%
				}
				%>
				</td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="60" id="summernote" name="content"><%=techlist.get(0).getContent() %></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글수정" class="btn btn-success btn-wide" >
				</td>
			</tr>
		</table>
		</form>
	</div>
	
<script>
      $(function() {
         // initialize popover with dynamic content
         $('#btnPopover').popover({
            placement: 'right',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<hr><button onclick="logout()" type="button" class="btn btn-default popover-dismiss">logout</button><button onclick="upmydetail()" type="button" class="btn btn-default popover-dismiss">MY페이지</button>'
         });
         // prevent popover from being hidden on mouseout.
         // only dismiss when explicity clicked (e.g. has .hide-popover)
         $('#btnPopover').on('hide.bs.popover', function(evt) {
            if(!$(evt.target).hasClass('hide-popover')) {
               evt.preventDefault();
               evt.stopPropagation();
               evt.cancelBubble = true;
            }
         });
         // reset helper class when dismissed
         $('#btnPopover').on('hidden.bs.popover', function(evt) {
            $(this).removeClass('hide-popover');
         });
         $('body').on('click', '.popover-dismiss', function() {
            // add helper class to force dismissal
            $('#btnPopover').addClass('hide-popover');
            // call method to hide popover
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
          
          //set flags when mouse enters the button or the popover.
          //When the mouse leaves unset immediately, wait a second (to allow the mouse to enter again or enter the other) and then test to see if the mouse is no longer over either. If not, close popover.
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
	function upmydetail() {
		location.href ="User?command=mypage";
	}
	</script>
</body>
</html>