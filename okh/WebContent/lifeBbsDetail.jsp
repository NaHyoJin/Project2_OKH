<%@page import="java.util.ArrayList"%>
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbssReplyDto"%>
<%@page import="lifeBbs.LifeBbssReplyDao"%>
<%@page import="lifeBbs.ILifeBbssReplyDao"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
// 답글
public String arrow(int depth){	
	String rs = "<img src='image/arrow.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	String ts = "";
	
	for(int i = 0;i < depth; i++){
		ts += nbsp;
	}
	return depth == 0?"":ts+rs;
}
%>  

<!DOCTYPE html>
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
	<link rel="stylesheet" type="text/css" href="_lifedetail.css?ver=1.8">
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.62">
</head>
<body>

<!-- 로그인 세션 -->
	<%
	String yn="";
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
			if(<%=yn.equals("yes")%>){
				location.href = "CommunityControl?command=list";
	
			}
			else{
				location.href = "User?command=guest";
			}
			
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
	
	service = UserService.getInstance();
	ILifeBbsDao dao = LifeBbsDao.getInstance();
	ILifeBbssReplyDao rdao = LifeBbssReplyDao.getInstance();
	
	dao.readcount(Integer.parseInt(sseq.trim()));
	
	LifeBbsDto bbs = dao.getDetailBbs(Integer.parseInt(sseq.trim()));
	List<LifeBbssReplyDto> rbbslist = rdao.getBbsReplyList(Integer.parseInt(sseq.trim()));

	String tagSplit[] = bbs.getTag().split(",");
	String filenameSplit[] = null;
	if(bbs.getFilename() != null){
		filenameSplit = bbs.getFilename().split(",");
	}
	
 	
	String upIdSplit[] = null;
	boolean findUpId = false;
	if(bbs.getUpid() != null && mem != null){
		upIdSplit = bbs.getUpid().split(",");
		for(int i = 0; i < upIdSplit.length; i++){
			if(upIdSplit[i].equals(mem.getId())){
				findUpId = true;
				break;
			}
		}
	}
	String downIdSplit[] = null;
	boolean findDownId = false;
	if(bbs.getDownid() != null && mem != null){
		downIdSplit = bbs.getDownid().split(",");
		for(int i = 0; i < downIdSplit.length; i++){
			if(downIdSplit[i].equals(mem.getId())){
				findDownId = true;
				break;
			}
		}
	}
	System.out.println("findUpId in detail1 : " + findUpId);
	System.out.println("findDownId in detail1 : " + findDownId);
	%>
	<script type="text/javascript">
	function upon() {
		$('#likeimg').attr('src', 'image/likeon.PNG');
	}
	function upoff() {
		$('#likeimg').attr('src', 'image/likeoff.PNG');
	}
	function downon() {
		$('#dislikeimg').attr('src', 'image/dislikeon.PNG');
	}
	function downoff() {
		$('#dislikeimg').attr('src', 'image/dislikeoff.PNG');
	}
<%-- 	
	$(document).ready(function() {
		$('#likeimg').click(function() {
			if(<%=findUpId %>){
				$('#likeimg').attr('src', 'image/likeoff.PNG');
			}else{
				$('#likeimg').attr('src', 'image/likeon.PNG');
			}
		});
		$('#dislikeimg').click(function() {
			if(<%=findDownId %>){
				$('#dislikeimg').attr('src', 'image/dislikeoff.PNG');
			}else{
				$('#dislikeimg').attr('src', 'image/dislikeon.PNG');
			}
		});
	});
	 --%>
	</script>
<!-- View -->
	<div class="titlediv">
		<h2>사는얘기</h2>
		<%
		if(ologin == null){
		%>
		<button class="create btn btn-success btn-wide pull-right " type="button" id="guestBtn" onclick="location.href = 'User?command=guest'">새 글 쓰기</button>
		<%
		}else{
		%>
		<button class="create btn btn-success btn-wide pull-right " type="button" id="writeBtn" onclick="location.href = 'LifeBbs?command=write'">새 글 쓰기</button>
		<%
		}
		%>
	</div>
	<div class="wrap">
		<div class="myinfo">
			<p class="myinfo_icon">
		<%
		IUserService uservice=UserService.getInstance();
		
		int score=uservice.getScore(bbs.getId());
		String getprofile=uservice.getProfile(bbs.getId());
		%>
		<a onclick="location.href='User?command=otherpage&infoid=<%=bbs.getId() %>'" style="cursor: pointer">
		
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
		<span class="detailid" style="float: left; margin-left: 15px;">
		<a onclick="location.href='User?command=otherpage&infoid=<%=bbs.getId() %>'" style="cursor: pointer"><%=bbs.getId() %></a>
		<span class="" style="margin-top: 0"><img src="image/actionpoint.PNG" class="pointimg">
		
		<%=score%></span></span>
		 <br><br>
		<span style="float:left; font-size: 12px;"><%=bbs.getWdate() %><br></span>

		
		<img alt="" src="image/repleon.PNG"><span><%=bbs.getCountreply() %></span>
		<img alt="" src="image/readcounton.PNG"><span><%=bbs.getReadcount() %></span>
		</p>
		</div>
		
		<div class="contentareawrap">
			<div class="contentarea">
				<p>
					#<%=bbs.getSeq() %>
					<%
					for(int i = 0; i < tagSplit.length; i++){
					%>
					<span class="hjhtag" id="tag<%=i%>"><%=tagSplit[i] %></span>
					<%
					}
					%>
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
				if(ologin == null){
				%>
				<a href="javascript:void(0)" id="changeli"><img src="image/likeoff.PNG"></a><br>
				<%
				}else if(findUpId){
				%>
				<button  id="upBtn" style="display: block; padding-top: 20px;"><img src="image/likeon.PNG" id="likeimg"></button><br>
				<%
				}else{
				%>
				<button  id="upBtn" style="display: block; padding-top: 20px;"><img src="image/likeoff.PNG" id="likeimg"></button><br>
				<%
				}
				%>
				<p style="font-size: 25px" id="upVal"><%=bbs.getUp() %></p>
				<%
				if(ologin == null){
				%>
				<a href="javascript:void(0)"id="changedisli"><img src="image/dislikeoff.PNG"></a><br>
				<a href="javascript:void(0)"><img src="image/scrap.PNG" id="scrapimg"></a><br>
				<%
				}else if(findDownId){
				%>
				<button id="downBtn" style="display: block; padding-top: 20px;"><img src="image/dislikeon.PNG" id="dislikeimg"></button><br>
				
				<%
				}else{
				%>
				<button id="downBtn" style="display: block; padding-top: 20px;"><img src="image/dislikeoff.PNG" id="dislikeimg"></button><br>
				
				<%
				}
				%>
				<br><br>
				<%
				if(ologin != null && bbs.getId().equals(mem.getId())){
				%>
				<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px; display: inline-block;" id="btnPopover2">
				<%
				}
				%>
				<br>
				
				<%
				if(filenameSplit != null){
					for(int i = 0; i < filenameSplit.length; i++){
				%>
				<input type="button" name="btnDown" id="btnDown" value="<%=filenameSplit[i] %>" class="btn btn-default btn-wide"
					onclick="location.href='LifeBbs?command=fileDown&filename=<%=filenameSplit[i] %>&seq=<%=bbs.getSeq() %>'"><br>
				<%	
					}
				}
				%>
			</div>
		</div>
		<div class="qna">
			<input type="text" value="댓글  <%=bbs.getCountreply() %>" readonly="readonly" class="form-control">

			<table border="1" class="reptable">
				<col width="850"><col width="175">
				<%
				if(!(rbbslist == null || rbbslist.size() == 0)){
					for(int i = 0;i < rbbslist.size(); i++){
						LifeBbssReplyDto rbbs = rbbslist.get(i);
						if(rbbs.getDel() == 0){
				%>	
				<tr>
					<td>
						<%
		score=uservice.getScore(rbbs.getId());
		getprofile=uservice.getProfile(rbbs.getId());
		%>
			<p class="myinfo_icon" style="margin-top: 5px">
			<a onclick="location.href ='User?command=otherpage&infoid=<%=rbbs.getId() %>'" style="cursor: pointer">
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid">
		<a onclick="location.href ='User?command=otherpage&infoid=<%=rbbs.getId() %>'" style="cursor: pointer">
		<span style="float: left">
		<%=rbbs.getId() %>
		</span>
		 </a>                                  
		<img src="image/actionpoint.PNG" class="pointimg" style="float: left">
		<span style="float: left;"><%=score%></span><br>
		<span style="font-size: 10px; margin-top:3px; float: left;"><%=rbbs.getWdate() %></span><br><br>
		</span> 
		
		</p>
		<div style="clear: both;"></div>
						<article style="margin-top: 0; margin-bottom: 10px;" class="content<%=rbbs.getSeq() %>" name="content"><%=rbbs.getContent() %></article>
					</td>
					<td style="border-left: 1px solid #DDD">
							<%
							if(ologin != null && mem.getId().equals(rbbs.getId())){
							%>
						<div class="buttons">
							<input type="button" id="edit<%=rbbs.getSeq() %>" class="btn btn-primary" onclick="edit('<%=rbbs.getSeq() %>')" value="수정하기">
							<input type="hidden" id="save<%=rbbs.getSeq() %>" class="btn btn-primary" onclick="save('<%=rbbs.getSeq() %>', '<%=rbbs.getBbsseq() %>')" value="저장하기">
							<button type="button" class="btn btn-primary" onclick="deleteReplyBtn('<%=rbbs.getSeq() %>', '<%=rbbs.getBbsseq() %>')">삭제</button> 
						</div>
							<%
							}
							%>
					</td>
				</tr>
					<%
						}
					}
				}
					%>
			</table>
			<%
			if(ologin != null){
			%>
			<div>
				<form action="LifeBbssReply" method="POST" id="frm">
					<table border="1" class="reptable">
						<col width="850"><col width="175">
						<tr>
							<td class="form">
								<input type="hidden" name="command" value="writeReply">
								<input type="hidden" name="id" value="<%=mem.getId() %>">
								<input type="hidden" name="bbsseq" value="<%=bbs.getSeq() %>">
								<textarea id="replyContent" name="content" placeholder="댓글 쓰기" class="form-control"></textarea>
							</td>
							<td>
								<div class="buttons"><input type="submit" class="btn btn-success btn-wide" value="등록"></div>
							</td>
						</tr>
					</table>
				</form>
				<button id="cancle" class="btn btn-default btn-wide" onClick="window.location.reload()">댓글취소</button>
			</div>
			<%
			}else{
			%>
			<div align="center">
				<a href="User?command=login">로그인</a>을 하시면 답변을 등록할 수 있습니다.
			</div>
			<%
			}
			%>
		</div>
	</div>
<!-- 새로고침 -->
	<script type="text/javascript">
	var myVar;
	$(function() {
		$("#btnDown").click(function() {
			myVar = setTimeout(_refrush, 1000);
		});
	});
	
	function _refrush() {
		location.reload();
		clearTimeout(myVar);
	}
	</script>
<!-- 댓글 Content summernote 활성환 -->
	<script type="text/javascript">
	$(document).ready(function() {
		$('#replyContent').click(function() {
			$('#replyContent').attr('id', 'summernote');
			$('#summernote').summernote({
	             height: 300,
	             minHeight: null,
	             maxHeight: null,
	             focus: true
	     	});
		});
	});
	</script>
<!-- 좋아요 버튼 -->
	<script type="text/javascript">
	$(document).ready(function() {
		$('#upBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"lifeBbsup.jsp",
				data:{
					command: "up",
					seq: <%=bbs.getSeq() %>,
					id: "<%=bbs.getId() %>"
				},
				success:function(data){
					$("#upVal").html(data.trim());
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
		$('#upBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"lifeBbsUpImg.jsp",
				data:{
					seq: <%=bbs.getSeq() %>
				},
				success:function(data){
					if(data.trim() == "true"){
						$('#likeimg').attr('src', 'image/likeoff.PNG');
					}else{
						$('#likeimg').attr('src', 'image/likeon.PNG');
					}
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
		$('#downBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"lifeBbsdown.jsp",
				data:{
					command: "up",
					seq: <%=bbs.getSeq() %>,
					id: "<%=bbs.getId() %>"
				},
				success:function(data){
					$("#upVal").html(data.trim());
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
		$('#downBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"lifeBbsDownImg.jsp",
				data:{
					seq: <%=bbs.getSeq() %>
				},
				success:function(data){
					if(data.trim() == "true"){
						$('#dislikeimg').attr('src', 'image/dislikeoff.PNG');
					}else{
						$('#dislikeimg').attr('src', 'image/dislikeon.PNG');
					}			
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
	});
	</script>
<!-- 수정, 답글, 삭제 버튼 -->
	<script type="text/javascript">
	function updatebbs(seq) {
		location.href = "LifeBbs?command=update&seq=" + seq;
	}
	
	function answerbbs(seq) {
		location.href = "LifeBbs?command=answer&seq=" + seq;
	}
	
	function deletebbs(seq) {
		location.href = "LifeBbs?command=delete&seq=" + seq;
	}
	</script>
<!-- 댓글 삭제, 수정 버튼 -->
	<script type="text/javascript">
	function deleteReplyBtn(seq, bbsseq) {
		location.href = "LifeBbssReply?command=deleteReply&seq=" + seq + "&bbsseq=" + bbsseq;
	}
	
	var edit = function(seq) {
		$('.content'+seq).summernote({focus: true});
		$('#edit'+seq).attr('type', 'hidden');
		$('#save'+seq).attr('type', 'button');
	};
	
	var save = function(seq, bbsseq) {
		var content = $('.content'+seq).summernote('code');
		$('.content'+seq).summernote('destroy');
		$('#edit'+seq).attr('type', 'button');
		$('#save'+seq).attr('type', 'hidden');
		location.href = "LifeBbssReply?command=updateReply&seq=" + seq + "&bbsseq=" + bbsseq + "&content=" + content;
	};
	</script>
<!-- 글 수정, 삭제 popover -->
	<script>
      $(function() {
         $('#btnPopover2').popover({
            placement: 'bottom',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="updatebbs(<%=bbs.getSeq() %>)" type="button" class="btn btn-default popover-dismiss">수정</button><button onclick="deletebbs(<%=bbs.getSeq() %>)" type="button" class="btn btn-default popover-dismiss">삭제</button>'
         });
         
         $('#btnPopover2').on('hide.bs.popover', function(evt) {
            if(!$(evt.target).hasClass('hide-popover')) {
               evt.preventDefault();
               evt.stopPropagation();
               evt.cancelBubble = true;
            }
         });

         $('#btnPopover2').on('hidden.bs.popover', function(evt) {
            $(this).removeClass('hide-popover');
         });
         $('body').on('click', '.popover-dismiss', function() {
            $('#btnPopover2').addClass('hide-popover');
            $('#btnPopover2').popover('hide');
         });
          
          $('#btnPopover2').data('overButton', false);
          $('#btnPopover2').data('overPopover', false);
          $.fn.closePopover = function(){
            var $this = $(this);
            
            if(!$this.data('overPopover') && !$this.data('overButton')){
              $this.addClass('hide-popover');
              $this.popover('hide');              
            }
          }
          
          $('#btnPopover2').on('mouseenter', function(evt){
            $(this).data('overButton', true);
          });
          $('#btnPopover2').on('mouseleave', function(evt){
            var $btn = $(this);
            $btn.data('overButton', false);
            
            setTimeout(function() {$btn.closePopover();}, 200);
            
          });
          $('#btnPopover2').on('shown.bs.popover', function () {
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