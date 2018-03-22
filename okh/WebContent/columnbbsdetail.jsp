<%@page import="user.UserDto"%>
<%@page import="columnBbs.ColumnBbsDto"%>
<%@page import="columnBbs.ColumnBbsDao"%>
<%@page import="columnBbs.iColumnBbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<link rel="stylesheet" type="text/css" href="_lifedetail.css?ver=1.5">
	<link rel="stylesheet" type="text/css" href="_lifemain.css?ver-1.62">
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
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="membtnPopover">
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="alarmbtnPopover">
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
<h1>상세 글보기</h1>

<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq);

iColumnBbsDao dao = ColumnBbsDao.getInstance();
ColumnBbsDto bbs = dao.getBbs(seq);
dao.readcount(seq);
%>
<!-- View -->

<div class="titlediv">
	<h2>칼럼</h2>
		<%
		if(ologin == null){
		%>
		<button class="create btn btn-success btn-wide pull-right " type="button" id="guestBtn" onclick="location.href = 'User?command=guest'">새 글 쓰기</button>
		<%
		}else{
		%>
		<button class="create btn btn-success btn-wide pull-right " type="button" id="writeBtn" onclick="location.href = 'Controller?command=write'">새 글 쓰기</button>
		<%
		}
		%>
</div>
<div class="wrap">
	<div class="myinfo">
		<%=bbs.getId() %>
		<%=bbs.getWdate() %>
		<br>
		<p class="myinfo_icon">
			<img alt="" src="image/readcount.PNG"><span><%=bbs.getReadcount() %></span>
		</p>
	</div>
	
	<div class="contentareawrap">
		<div class="contentarea">
			<p>
				#<%=bbs.getSeq() %>
			</p>
			<br>
			<h2 class="title">
				<%=bbs.getTitle() %>
			</h2>
			<hr>
			<article class="content">
				<%=bbs.getContent() %>
			</article>
		</div>
		<div id="like">
			<%
			if(ologin != null && bbs.getId().equals(mem.getId())){
			%>
			<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px; display: inline-block;" id="btnPopover">
			<%
			}
			%>
		</div>
	</div>
	
</div>


	<script type="text/javascript">
	function updatebbs( seq ) {
		location.href = "Controller?command=update&seq=" + seq;
	}
	
	function answerbbs( seq ) {
		location.href = "Controller?command=answer&seq=" + seq; 
	}
	
	function deletebbs(seq) {
		location.href= "Controller?command=del&seq=" + seq;
	}
	
	
	</script>
	
	
	<!-- 글 수정, 삭제 popover -->
	<script>
      $(function() {
         $('#btnPopover').popover({
            placement: 'bottom',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="updatebbs(<%=bbs.getSeq() %>)" type="button" class="btn btn-default popover-dismiss">수정</button><button onclick="deletebbs(<%=bbs.getSeq() %>)" type="button" class="btn btn-default popover-dismiss">삭제</button>'
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

</body>
</html>





