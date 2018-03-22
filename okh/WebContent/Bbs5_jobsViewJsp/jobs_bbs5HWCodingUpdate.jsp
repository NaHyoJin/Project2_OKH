<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingVO"%>
<%@page import="jobs_BBS5.newbbs5HWCodingService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	
	<title>jobs_bbs5HWCodingUpdate.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" 
rel="stylesheet">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" 
rel="stylesheet">
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
if(ologin != null){
	profile = service.getProfile(mem.getId());
}
%>

	<!-- 인클루드 부분 -->
	<div class="menu">
				<%
if(ologin == null){	//로그인안한상태
	%>
	<input type="button" class="homebtn" onclick="location.gref='index.jsp'">
	<input type="button" class="login" id="login">
	<input type="button" class="account" id="account">

		<%
}else{
	
%>
<input type="button" class="homebtn" id="homebtn">
<div class="actionlogin">
	<a onclick="upmydetail()" style="cursor: pointer">
			<img src="<%=profile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
			<span class="memid"><a onclick="upmydetail()" style="cursor: pointer;color: #fff;"><%=mem.getId() %></a></span> <br>
				<span class="point"><img src="image/actionpoint.PNG" style="margin-top: 0" class="pointimg"><%=mem.getScore()%></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
				
</div>
<%
}
%>
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" id="column"><!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs"><!-- 장문석 -->
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	

	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.
			$("#homebtn").click(function() {
				location.href = "main.jsp";
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
	newbbs5HWCodingServiceImpl tservice = newbbs5HWCodingService.getInstance();
	List<newbbs5HWCodingVO> techlist = tservice.getdetail(seq);
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
	<div class="titlediv"><h2>H/W 글 수정</h2><br>
	</div>
	<div class="wrap">
		<form action="BBSHWCodingController" method="POST">
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
					<input type="text" class="form-control" id="title" name="title" 
					size="50" value="<%=techlist.get(0).getTitle() %>">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
				<%
				String[] tagnames = tservice.getTagName(techlist.get(0).getTagname());
				
				for(int i = 0; i < tagnames.length; i++){
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
					<textarea rows="10" cols="60" 
					id="summernote" name="content"><%=techlist.get(0).getContent() %></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글 수정" class="btn btn-success btn-wide" >
				</td>
			</tr>
		</table>
		</form>
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
   
   <!-- 로그아웃 내 정보 수정 부분. -->
   <script type="text/javascript">
	function logout() {
		location.href = "User?command=logout";
	}
	function mypage() {
		location.href = "User?command=mypage";
	}
	</script>
</body>
</html>





<%-- 
		});
		
		//나효진 게시판5.
		$("#jobs").click(function() {
			location.href = "../jobs";
		});
		
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	});
	</script>
	
	<%
		Object ologin = null;
		UserDto mem = null;
		ologin = session.getAttribute("login");
		
		if(ologin != null){
	%>
<!--  		
		<script type="text/javascript">
		alert("로그인해 주십시오");
		location.href = "../index.jsp";	
		</script>
 -->		
	<%
//		return;
	mem = (UserDto)ologin;
	}
		
	%>
	
	<%
	//인코딩 부분.
	request.setCharacterEncoding("UTF-8");

	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq.trim());
	
	newbbs5HWCodingServiceImpl dao = newbbs5HWCodingService.getInstance();
	
	//디테일 내용 가지고 오는 것.
	List<newbbs5HWCodingVO> bbs = dao.getdetail(seq);//리스트로 가지고 와야한다.
	%>
	
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
	
	<div class="wrap">
		<form action="../BBSHWCodingController" method="POST">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>아이디</td>
				<td>
					<input type="hidden" name="command" value="updateAf">
					<!-- 히든으로 시퀀스 숨겨서 보내는 부분. -->
					<input type="hidden" name="seq" value="<%=sseq %>">
					<input type="text" id="id" name="id" size="50" 
						readonly="readonly" value="<%=mem.getId() %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" 
					size="50" value="<%=bbs.get(0).getTitle() %>">
				</td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<!-- name="tag"값이 없어서 null 예외나왔음. -->
					<input type="text" id="tag" name="tag" value="<%=bbs.get(0).getTagname() %>" 
					data-role="tagsinput">
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
					<textarea rows="10" cols="50" id="summernote" 
					name="content"><%=bbs.get(0).getContent() %></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
				</td>
			</tr>
		</table>
		</form>
	</div>

</body>
</html> --%>