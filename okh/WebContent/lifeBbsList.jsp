<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="lifeBbs.LifeBbssReplyDao"%>
<%@page import="lifeBbs.ILifeBbssReplyDao"%>
<%@page import="lifeBbs.LifeBbssReplyDto"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="lifeBbs.LifeBbsPagingDto"%>
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
	<title>LifeBBSList</title>
	<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="_lifebbs.css?ver=1.5">
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.62">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body bgcolor="#fcfbfb">

<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	IUserService service = UserService.getInstance();
	String yn="";
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
<!-- 페이징 처리 정보 교환 -->
	<%
	LifeBbsPagingDto paging = new LifeBbsPagingDto();
	if(request.getParameter("nowPage") == null){
		paging.setNowPage(1);
	}else{
		paging.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
	}
	%>
<!-- 검색 -->
	<%
	String findWord = request.getParameter("findWord");
	String choice = request.getParameter("choice");
	if(choice == null){
		choice = "title";
	}
	if(findWord == null || findWord.equals("")){
		choice = "title";
		findWord = "";
	}
	
	if(findWord == null) findWord = "";
	int cho = 0;
	
	if(choice.equals("title")) cho = 0;
	else if(choice.equals("writer")) cho = 1;
	else if(choice.equals("content")) cho = 2;
	%>
<!-- 게시판 데이터 -->
	<%
	ILifeBbsDao dao = LifeBbsDao.getInstance();
	ILifeBbssReplyDao rdao = LifeBbssReplyDao.getInstance();
	
	String sort = request.getParameter("sort");
	List<LifeBbsDto> bbslist = null;
	
	if(sort == null){
		bbslist = dao.getBbsPagingList(paging, findWord, cho);
		System.out.println("null sort");
	}else{
		bbslist = dao.getBbsSortingPagingList(paging, findWord, cho, sort);
		System.out.println("not null sort");
	}
	
	List<LifeBbssReplyDto> replylist = rdao.reply();
	%>

<!-- View -->
<div class="titlediv">
			<span class="titi">사는얘기</span>
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
		<div class="sortingmenu">
			<ul class="list-sort pull-left">
				<li><a onclick="location.href='lifeBbsList.jsp?sort=wdate'" class="category-sort-link active">최신순</a></li>
				<li><a onclick="location.href='lifeBbsList.jsp?sort=up'" class="category-sort-link active">추천순</a></li>
				<li><a onclick="location.href='lifeBbsList.jsp?sort=countreply'" class="category-sort-link active">댓글순</a></li>
				<li><a onclick="location.href='lifeBbsList.jsp?sort=readcount'" class="category-sort-link active">조회순</a></li>
			</ul>
		</div>
		<div class="sercharea">
			<select id="choice" style="height: 30px;">
				<option value="title" <%if(choice.equals("title")) out.println("selected");%>>제목</option>
				<option value="writer" <%if(choice.equals("writer")) out.println("selected");%>>작성자</option>
				<option value="content" <%if(choice.equals("content")) out.println("selected");%>>내용</option>
			</select>
			<input type="text" class="inputField" id="search" value="<%=findWord %>">
			<button name="search" id="serchbtn" class="input-group-btn" onclick="searchBbs()"><img src="image/serchbtn.PNG"></button>
		</div>
		<div class="board">
			<table border="1" class="techtable">
				<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
				<%
				if(bbslist == null || bbslist.size() == 0){
				%>	
				<tr>
					<td colspan="5">작성된 글이 없습니다</td>
				</tr>	
				<%
				}
				for(int i = 0;i < bbslist.size(); i++){
					LifeBbsDto bbs = bbslist.get(i);
					String tagnames[] = bbs.getTag().split(",");
				%>
				<tr>
					<%
					if(bbs.getDel() == 0){
						if(bbs.getCountreply() == 0){
					%>
					<th style="border-left: 5px solid #808080;">#<%=bbs.getSeq() %>
						<%
						}else{
						%>
					<th> #<%=bbs.getSeq() %>
						<%
						}
						for(int j = 0; j < tagnames.length; j++){
						%>
							<span><button class="hjhtag" name="tag<%=j%>" id="tag<%=j%>" onclick="searchBbs1(this)" value="<%=tagnames[j]%>"><%=tagnames[j] %></button></span>
						<%
						}
						%>
						<p style="font-size: 15px">
							<%=arrow(bbs.getDepth()) %>
							<a href="LifeBbs?command=detail&seq=<%=bbs.getSeq() %>">
								<%=bbs.getTitle() %>
							</a>
						</p>
					</th>
						<%
						if(bbs.getCountreply() > 0){
						%>
					<td><img src="image/repleon.PNG"><span class="textalig"> <%=bbs.getCountreply() %></span></td>
						<%
						}else{
						%>
					<td><img src="image/repleoff.png"><span class="textalig"> <%=bbs.getCountreply() %></span></td>
						<%
						}
						if(bbs.getUp() > 0){
						%>
					<td style="padding-top: 3px"><img src="image/likeeon.png"><span class="textalig"> <%=bbs.getUp() %></span></td>
						<%
						}else{
						%>
					<td style="padding-top: 3px"><img src="image/likeeoff.png"><span class="textalig"> <%=bbs.getUp() %></span></td>
						<%
						}
						if(bbs.getReadcount() > 0){
						%>
					<td><img src=image/readcounton.PNG><span class="textalig"> <%=bbs.getReadcount() %></span></td>
						<%
						}else{
						%>
					<td><img src="image/readcountoff.png"><span class="textalig"> <%=bbs.getReadcount() %></span></td>
						<%
						}
						%>
					<td>
						<p class="myinfo_icon" style="margin-bottom: 0">
			<a onclick="location.href ='User?command=otherpage&infoid=<%=bbs.getId() %>'" style="cursor: pointer">
			<%
		IUserService uservice=UserService.getInstance();
		
		int score=uservice.getScore(bbs.getId());
		String getprofile=uservice.getProfile(bbs.getId());
		%>
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid" style="margin-left: 10px;">
		<a onclick="location.href ='User?command=otherpage&infoid=<%=bbs.getId() %>'" style="cursor: pointer"><span style="margin-top: 5px"><%=bbs.getId() %></span></a>
		<span class="" style="margin-top: 10px;"><img src="image/actionpoint.PNG" class="pointimg">
		
		<%=score%></span>
		</span> <br><br>
		</p>
			<p style="font-size: 10px"><%=bbs.getWdate() %></p>
					</td>
				</tr>	
				<%
					}
				}
				%>
			</table>
			<br>
			<jsp:include page="lifeBbsPaging.jsp">
				<jsp:param name="actionPath" value="lifeBbsList.jsp"/>
				<jsp:param name="findWord" value="<%=findWord %>" />
				<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
				<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
				<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
				<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
			</jsp:include>
		</div>
	</div>
<!-- 새 글 쓰기 버튼 -->
	<script type="text/javascript">
	$(function() {
		$("#guestBtn").click(function() {
			location.href = "User?command=guest";
		});
		$("#writeBtn").click(function() {
			location.href = "LifeBbs?command=write";
		});
	});
	</script>
<!-- 검색 버튼 -->				
	<script type="text/javascript">
	function searchBbs() {
		var word = document.getElementById("search").value;
		var choice = document.getElementById("choice").value;
		location.href = "lifeBbsList.jsp?findWord=" + word + "&choice=" + choice;
	}
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