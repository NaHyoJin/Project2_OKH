<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="Study_like.LikeScrapService"%>
<%@page import="Study_like.LikeScrapServiceImpl"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="studysrc.comment_bbsDto"%>
<%@page import="java.util.List"%>
<%@page import="studysrc.CombbsService"%>
<%@page import="studysrc.ICombbsService"%>
<%@page import="studysrc.CombbsDto"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OKH:스터디모집글</title>
<link rel="stylesheet" type="text/css" href="_main.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_studydetail.css?ver=1.41">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.48">
</head>
<body>
<script type="text/javascript">
function change() {		//좋아요취소		-> 		싫어요활성화
	$("#likeimg").attr('src','image/join1.png');
}
function change1() {	//좋아요할래요		->		싫어요비활성화
	$("#likeimg").attr('src','image/join.png');
}

</script>






<%!
public String toDates(String mdate){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
	
	String s = mdate.substring(0, 4) + "-" 	// yyyy
			+ mdate.substring(4, 6) + "-"	// MM
			+ mdate.substring(6, 8) + " " 	// dd
			+ mdate.substring(8, 10) + ":"	// hh
			+ mdate.substring(10, 12) + ":00"; 
	Timestamp d = Timestamp.valueOf(s);
	
	return sdf.format(d);	
} 
%>

<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	String yn="";
	IUserService uservice = UserService.getInstance();
	String profile = null;
	int mainscore=0;
	String maingetprofile="";
	
	if(ologin != null){
		profile = uservice.getProfile(mem.getId());
		mainscore=uservice.getScore(mem.getId());
		maingetprofile=uservice.getProfile(mem.getId());
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
	%>
<%
List<comment_bbsDto> list= (List<comment_bbsDto>)request.getAttribute("detail");
List<CombbsDto> list1=(List<CombbsDto>)request.getAttribute("detail1");
String sseq = request.getParameter("seq");
int seq=Integer.parseInt(sseq);
ICombbsService service = CombbsService.getInstance();
LikeScrapServiceImpl lsservice=LikeScrapService.getInstance();
String origin=lsservice.getLikeID(seq);


if(list==null||list.size()==0){
	%>
	
	<div class="titlediv"><h2>Study Matching</h2><br>
	<button onclick="location.href='CommunityControl?command=write'" class="create btn btn-success btn-wide pull-right ">새 글 쓰기</button>
	</div>
	<form action="CommunityControl">
	<div class="wrap">
		<div class="myinfo">
		<p class="myinfo_icon">
		<%
		int score=uservice.getScore(list1.get(0).getId());
		String getprofile=uservice.getProfile(list1.get(0).getId());
		%>
		<a onclick="location.href='User?command=otherpage&infoid=<%=list1.get(0).getId() %>'" style="cursor: pointer">
		
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
		<span class="detailid" style="float: left; display: inline-block; margin-left: 15px;">
		<a onclick="location.href='User?command=otherpage&infoid=<%=list1.get(0).getId() %>'" style="cursor: pointer"><%=list1.get(0).getId() %></a>
		<span class="" style="margin-top: 0"><img src="image/actionpoint.PNG" class="pointimg">
		
		<%=score%></span></span>
		 <br><br>
		<span style="float:left; font-size: 12px;"><%=list1.get(0).getWdate()%><br></span>

		
		<img alt="" src="image/repleon.PNG"><span><%=list1.get(0).getCommentcount() %></span>
		<img alt="" src="image/readcounton.PNG"><span><%=list1.get(0).getReadcount()%></span>
		</p>
	</div> 
												
		<input type="hidden" name="parent" value="<%=seq %>"> 
		<div class="contentareawrap">
			<div class="contentarea">		
				<p>
					#<%=seq %>
				<span class="hjhtag"><%=list1.get(0).getTagname() %></span><br>
				</p>
				<h2 class="title"> <%=list1.get(0).getTitle() %></h2> <br>
	<hr/>
				
				<article class="content">
						<%=list1.get(0).getContent()%><br>
					모임날짜 <%=toDates(list1.get(0).getJoindate()) %>
				
				</article>
				
				</div>
				<div id="like">
	<!-- 좋아요스크랩기능 -->
	<%if(list1.get(0).getJoinercount()<5){ %>
	
		<a href="Study_likeLikeScrapController?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=seq%>&tag=<%=list1.get(0).getTagname() %>&content=<%=list1.get(0).getContent() %>&title=<%=list1.get(0).getTitle() %>&joindate=<%=list1.get(0).getJoindate() %>" id="changeli">
			<img src="image/join1.png" id="likeimg"></a>
		<p style="font-size: 25px"><%=list1.get(0).getJoinercount() %></p>
		<br><br>
		<%
	}else if(list1.get(0).getJoinercount()==5){
		%>
		마감 되었습니다.
	
	<%
	}
		if(mem.getId().trim().equals(list1.get(0).getId().trim())){
		
	%>
	<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px;" id="btnPopover2">
	<%
		}
	%>
		
		
		</div>
					
			
	
	
	
	<br>
	
	
	</div>
	
	
<div class="qna">
<input type="text" value="참여자  <%=origin %>" readonly="readonly" class="form-control">
	<input type="text" value="댓글  <%=list1.get(0).getCommentcount() %>" readonly="readonly" class="form-control">

	<table border="1" class="reptable">
		<col width="400"> <col width="100">
		<%if(list==null||list.size()==0){
					
				%><tr>
					<td colspan="2">등록된 댓글이 없습니다.</td>
					</tr>
					
					
					
				<%
		}else{
			for(int i = 0;i<list.size();i++){
				%>
			
		<tr><td>
		<%
		score=uservice.getScore(list.get(i).getCommentid());
		getprofile=uservice.getProfile(list.get(i).getCommentid());
		%>
			<p class="myinfo_icon" style="margin-top: 5px">
			<a onclick="location.href ='User?command=otherpage&infoid=<%=list.get(i).getCommentid() %>'" style="cursor: pointer">
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid">
		<a onclick="location.href ='User?command=otherpage&infoid=<%=list.get(i).getCommentid()%>'" style="cursor: pointer">
		<span style="float: left">
		<%=list.get(i).getCommentid() %>
		</span>
		</a>                                   
		<img src="image/actionpoint.PNG" class="pointimg" style="float: left">
		<span style="float: left;"><%=score%></span><br>
		<span style="font-size: 10px; margin-top:3px; float: left;"><%=list.get(i).getBbswdate() %></span><br><br>
		</span> 
		
		</p><br><br>
		<div style="clear: both;"></div>
		<article class="content<%=list.get(i).getBbsseq() %>" name="content"><%=list.get(i).getBbscontent() %></article>
			</td>
		</tr>

	<%
			}
		}
	%>
	</table>
</div>



<form action="CommunityControl">
	<table>
	<tr>
		<th> 댓 글 </th>
	</tr>
				<col width="100"> <col width="700">
				<tr>
				
					<td colspan="2">
						<input type="text" id="id" readonly="readonly" value="<%=mem.getId() %>" size="100">
						<input type="hidden" name="id" value="<%=mem.getId() %>">
		 				<input type="hidden" name="command" value="commentAF">
		 				<input type="hidden" name="parent" value="<%=seq %>">
		 				
					</td>
				</tr>
				
				
				
				<tr>
					
					<td colspan="2">
						 <textarea name="content" id="summe" placeholder="댓글 쓰기" class="form-control" ></textarea>
						<input type="hidden" name="id" value="<%=mem.getId()%>">
						<input type="hidden" name="mainseq" value="<%=seq%>">
						<input type="hidden" name="command" value="write">
					</td>
				</tr>
				<tr>
			 		<td colspan="2">
			 			<input type="submit" class="btn btn-primary" value="댓글달기" >
			 		</td>
		 		</tr>
				
			</table>
		</div>
	</form>
	
	
	
	
	
	
	
	
<%
}else{
%>
<div class="titlediv"><h2>Study Matching</h2><br>
	<button onclick="location.href='CommunityControl?command=write'" class="create btn btn-success btn-wide pull-right ">새 글 쓰기</button>
	</div>

<form action="CommunityControl">
<%-- <div class="wrap">
	<div class="myinfo"><%= list.get(0).getBbsid() %>
											조회수 <%=list.get(0).getBbsreadcount() %>
											댓글수 <%=list.get(0).getBbscommentcount() %> 
											<input type="hidden" name="parent" value="<%=seq %>"> 
		 					 </div> --%>
<div class="wrap">
	<div class="myinfo"><p class="myinfo_icon">
		<p class="myinfo_icon">
		<%
		int score=uservice.getScore(list.get(0).getBbsid());
		String getprofile=uservice.getProfile(list.get(0).getBbsid());
		%>
		<a onclick="location.href='User?command=otherpage&infoid=<%=list.get(0).getBbsid() %>'" style="cursor: pointer">
		
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
		<span class="detailid" style="float: left; display: inline-block; margin-left: 15px;">
		<a onclick="location.href='User?command=otherpage&infoid=<%=list.get(0).getBbsid() %>'" style="cursor: pointer"><%=list.get(0).getBbsid() %></a>
		<span class="" style="margin-top: 0"><img src="image/actionpoint.PNG" class="pointimg">
		
		<%=score%></span></span>
		 <br><br>
		<span style="float:left; font-size: 12px;"><%=list.get(0).getBbswdate()%><br></span>

		
		<img alt="" src="image/repleon.PNG"><span><%=list.get(0).getBbscommentcount() %></span>
		<img alt="" src="image/readcounton.PNG"><span><%=list.get(0).getBbsreadcount()%></span>
		</p>
	</div>
		 
			
	
			<%-- <h2 class="title"><%=list.get(0).getBbstagname() %> <%=list.get(0).getBbstitle() %> <br>
			<%=list.get(0).getBbswdate() %><hr/></h2>
		
			
				
				<div class="like">
				<img src="image/like1.png" style="cursor: pointer;" onclick="location.href='CommunityControl?command=bbslike&seq=<%=list.get(0).getBbsseq() %>'">
				</div>
				 --%>
<div class="contentareawrap">
	<div class="contentarea">
	<p>
		#<%=seq %>
		
		
			<span class="hjhtag" id="tag"><%=list.get(0).getBbstagname() %></span>
		
	</p>	
		<br>
		<h2 class="title">
		<%=list.get(0).getBbstitle() %>
		</h2>
	<hr>
			
		
			<article class="content">
					<%=list.get(0).getBbscontent()%><br>
				<%=toDates(list.get(0).getBbsjoindate()) %>
					
				</article>
	</div>
	<div id="like">
	<!-- 좋아요스크랩기능 -->
	<%if(list.get(0).getBbsjoinercount()<5){ %>
		<a href="Study_likeLikeScrapController?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=seq%>&tag=<%=list.get(0).getBbstagname() %>&content=<%=list.get(0).getBbscontent() %>&title=<%=list.get(0).getBbstitle() %>&joindate=<%=list.get(0).getBbsjoindate() %>" id="likeimg"><img src="image/join.png" id="likeimg"></a><br>
		<p style="font-size: 25px"><%=list.get(0).getBbsjoinercount() %></p>
		<br><br>
	<%
	}else if(list.get(0).getBbsjoinercount()==5){
	%>
		마감되었습니다
	<%
	}
	%>
	<%
		if(mem.getId().trim().equals(list.get(0).getBbsid().trim())){
		
	%>
	<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px;" id="btnPopover2">
	<%
		}
	%>
	
	
	</div>	
	
	
	<br>
	
	</div>
	
	
	
	
	
	
	
	
	
<br>

<div class="qna">
<input type="text" value="참여자  <%=origin %>" readonly="readonly" class="form-control">
	<input type="text" value="댓글  <%=list.get(0).getBbscommentcount() %>" readonly="readonly" class="form-control">


<table border="1" class="reptable">
	<col width="400"> <col width="100">
	<%if(list==null||list.size()==0){
				
			%><tr>
				<td colspan="2">등록된 댓글이 없습니다.</td>
				</tr>
				
				
				
			<%
	}else{
		
		for(int i = 0;i<list.size();i++){
			
			
			if(list.get(i).getCommentdel()==1){
			%>
				<tr>
					<td colspan="2">이댓글은 삭제되었습니다. </td>
				</tr>
			<%
			}else if(list.get(i).getCommentdel()==0){
			%>
		
	<tr>
		<td>
		<%
		score=uservice.getScore(list.get(i).getCommentid());
		getprofile=uservice.getProfile(list.get(i).getCommentid());
		%>
		<a onclick="location.href ='User?command=otherpage&infoid=<%= list.get(i).getCommentid() %>'" style="cursor: pointer">
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid">
		<a onclick="location.href ='User?command=otherpage&infoid=<%=list.get(i).getCommentid() %>'" style="cursor: pointer">
		<span style="float: left">
		<%=list.get(i).getCommentid() %>
		</span>
		</a>
		<img src="image/actionpoint.PNG" class="pointimg" style="float: left">
		<span style="float: left;"><%=score%></span><br>
		<span style="font-size: 10px; margin-top:3px; float: left;"><%=list.get(i).getCommentwdate() %></span><br><br>
		</span> 
		
		<td rowspan="2">
		<%
		if(mem.getId().trim().equals(list.get(i).getCommentid().trim())){
			
			
		%>
				<input type="button" id="edit<%=list.get(i).getCommentseq() %>" class="btn btn-primary" onclick="edit('<%=list.get(i).getCommentseq() %>')" value="수정하기">
               <input type="hidden" id="save<%=list.get(i).getCommentseq() %>" class="btn btn-primary" onclick="save('<%=list.get(i).getCommentseq() %>', '<%=list.get(0).getBbsparent() %>','<%=mem.getId() %>')" value="저장하기">
				<button type="button" class="btn btn-primary" onclick="delcomment('<%=list.get(i).getCommentseq() %>','<%=list.get(0).getBbsparent()%>','<%=mem.getId() %>')">삭제</button>	
			
				
				
				


		<%
		}else{
			%>
				&nbsp;
			<% 	
		}
		%>
		
		</td>
	</tr>
	<tr>
		<td><div class="content<%=list.get(i).getCommentseq() %>" name="content1"><%=list.get(i).getCommentcontent()%></div>
			
		 </td>
		
		
	</tr>

<%
			}
		}
	}
%>
</table>

</div>


<table>
<tr>
	<th> 댓 글 </th>
</tr>
			<col width="100"> <col width="700">
			<tr>
			
				<td colspan="2">

					<input type="hidden" name="id" value="<%=mem.getId() %>">
	 				<input type="hidden" name="parent" value="<%=list.get(0).getBbsparent() %>">
	 				
	 				<input type="hidden" name="command" value="commentAF">
				</td>
			</tr>
			
			
			
			<tr>
				
				<td colspan="2">
					 <textarea name="content" id="summe" placeholder="댓글 쓰기" class="form-control" ></textarea>
					<input type="hidden" name="id" value="<%=mem.getId()%>">
					<input type="hidden" name="mainseq" value="<%=seq%>">
					<input type="hidden" name="command" value="write">
				</td>
			</tr>
			<tr>
		 		<td colspan="2">
		 			<input type="submit" class="btn btn-primary" value="댓글달기" >
		 			
		 		</td>
	 		</tr>
			
		</table>
		</div>
</form>
<%
}
%>
<script type="text/javascript">

$(document).ready(function() {
     $('#summernote').summernote({
             height: 200,                 // set editor height
             minHeight: null,             // set minimum height of editor
             maxHeight: null,             // set maximum height of editor
             focus: true                  // set focus to editable area after initializing summernote
     });
     
     

});

$('#summe').click(function () {
	$('#summe').attr("id", "summernote");
	 $('#summernote').summernote({
         height: 300,                 // set editor height
         minHeight: null,             // set minimum height of editor
         maxHeight: null,             // set maximum height of editor
         focus: true                  // set focus to editable area after initializing summernote
 });
	 $('#summernote').summernote();
});

</script>

<script type="text/javascript">
var edit = function(seq) {
    $('.content'+seq).summernote({focus: true});
    $('#edit'+seq).attr('type', 'hidden');
    $('#save'+seq).attr('type', 'button');
 };

 var save = function(seq, parentseq, memid) {
    var content = $('.content'+seq).summernote('code');
    $('.content'+seq).summernote('destroy');
    $('#edit'+seq).attr('type', 'button');
    $('#save'+seq).attr('type', 'hidden');
    location.href = "CommunityControl?command=commentup&commentseq=" + seq + "&parentseq=" + parentseq + "&upcontent=" + content+"&memid=" + memid;
 };

 function delcomment( seq,bonseq,memid ) {
		location.href = "CommunityControl?command=delcomment&seq=" + seq+"&bonseq="+bonseq+"&memid="+memid;
	}

</script>
<%
//좋아요랑 안좋아요 아이디유무판단해서 여기서 css만져주기//likeid있으면 1(취소) id없으면2(추가)
CombbsDto li=(CombbsDto)request.getAttribute("likeidyn");
int likeidyn=0;
if(li==null){
	
}else{
	likeidyn=li.getLikeidyn();
	if(likeidyn==1){		//라이크에불끄기 
%>
	<script type="text/javascript">
	
	change();
	
	</script>
<%
}else if(likeidyn==2){	//라이크에불들어오게 dislike버튼비활성화
%>
	<script type="text/javascript">
	change1();
	</script>
<%
}
}
%>

<%
//디테일창처음들어왔을때 id못찾으면2->불꺼줘야함 찾으면1->불켜야함
CombbsDto li3=(CombbsDto)request.getAttribute("flikeidyn");
int flikeidyn=0;
if(li3==null){
	
}else{
	flikeidyn=li3.getLikeidyn();
	if(flikeidyn==2){		//라이크에불끄기 
%>
	<script type="text/javascript">
	
	change();
	
	</script>
<%
}else if(flikeidyn==1){	//라이크에불들어오게 dislike버튼비활성화
%>
	<script type="text/javascript">
	change1();
	</script>
<%
}
}
%>

<script>
      $(function() {
         // initialize popover with dynamic content
         $('#btnPopover2').popover({
            placement: 'bottom',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="updatebbs(<%=seq %>)" type="button" class="btn btn-default popover-dismiss">수정</button><button onclick="deletebbs(<%=seq %>)" type="button" class="btn btn-default popover-dismiss">삭제</button>'
         });
         // prevent popover from being hidden on mouseout.
         // only dismiss when explicity clicked (e.g. has .hide-popover)
         $('#btnPopover2').on('hide.bs.popover', function(evt) {
            if(!$(evt.target).hasClass('hide-popover')) {
               evt.preventDefault();
               evt.stopPropagation();
               evt.cancelBubble = true;
            }
         });
         // reset helper class when dismissed
         $('#btnPopover2').on('hidden.bs.popover', function(evt) {
            $(this).removeClass('hide-popover');
         });
         $('body').on('click', '.popover-dismiss', function() {
            // add helper class to force dismissal
            $('#btnPopover2').addClass('hide-popover');
            // call method to hide popover
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
          
          //set flags when mouse enters the button or the popover.
          //When the mouse leaves unset immediately, wait a second (to allow the mouse to enter again or enter the other) and then test to see if the mouse is no longer over either. If not, close popover.
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
		function updatebbs( seq ) {
			location.href = "CommunityControl?command=update&seq=" + seq;
		}
		function deletebbs( seq ) {
			location.href = "CommunityControl?command=delete&seq=" + seq;
		}
	</script>
	
	

</body>
</html>