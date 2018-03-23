<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="totalbbs.totalbbsdto"%>
<%@page import="java.util.List"%>
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>mypage.jsp</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="_lifedetail.css?ver=1.25">
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.36">
</head>
<body>
<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	String yn="";
	IUserService service = UserService.getInstance();
	
	String profile = null;
	if(ologin != null){
		profile = service.getProfile(mem.getId());
	}
	int mainscore=service.getScore(mem.getId());
	String maingetprofile=service.getProfile(mem.getId());
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
<!-- View -->
	<div class="titlediv">
		<h2>내 정보</h2>
	</div>
	<div class="wrap">
		<div class="myinfo">
			<br>
				<div>
					<img src="<%=maingetprofile %>" class="media-object img-circle" style="max-width: 100px; float:left; max-height: 100px; margin: 0 auto;"><span style="font-size: 30px; margin-left: 30px;">아이디 <%=mem.getId() %>  활동점수 <%=mainscore %></span>
					
					<button style="margin-left:30px" class="create btn btn-success btn-wide pull-right" onclick="location.href='loginProfileUpdate.jsp'">프로필 수정</button>
					<button  class="create btn btn-success btn-wide pull-right" onclick="location.href='loginUpdate.jsp'">회원 정보 수정</button>
				</div>
			
		</div>
		<div class="listarea">
			<%
			List<totalbbsdto> totallist=new ArrayList<>();
			List<totalbbsdto> serchlist=(List<totalbbsdto>)request.getAttribute("writeserchlist");
			List<totalbbsdto> likelist=(List<totalbbsdto>)request.getAttribute("likelist");
			String ynynyn="";
			if(serchlist==null||serchlist.size()==0){	//sort안했다
				System.out.println("내가쓴글리스트");
				ynynyn="작성한 글이 없습니다";
			}else{
				System.out.println("쓴글을안눌렀다");
				totallist=serchlist;
			}
			if(likelist==null||likelist.size()==0){	//sort안했다
				System.out.println("좋아요없다");
				ynynyn="좋아요한 글이 없습니다";
			}else{
				System.out.println("좋아요리스트");
				totallist=likelist;
			}
			%>
			<table border="1" class="techtable">
		<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
			
			<%if(totallist==null||totallist.size()==0){
				
			%><tr>
				<td colspan="5"></td>
				</tr>
			<%
			}else{
			
			for(int i=0;i<totallist.size();i++){
				totalbbsdto dto=totallist.get(i);
				if(dto.getComentcount()>0){
			%>
			<tr>
				<th>
				<%=totallist.get(i).getWhatbbs() %>
			<%
			}else{
			%>
			<tr>
				<th style="border-left: 5px solid #808080">
				<%=totallist.get(i).getWhatbbs() %>
			<%
			}
			if(ologin == null){
			if(dto.getWhatbbs().equals("기술게시판")){
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("사는얘기")){
			%>
			<!-- 병찬이컨트롤러  -->
			<p style="font-size: 15px">
                     <a href="LifeBbs?command=detail&seq=<%=dto.getParent() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
			<%
			}else if(dto.getWhatbbs().equals("QnA게시판")){
			%>
			<!-- 형태형컨트롤러  -->
			<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("커뮤니티게시판")){
			%>
			<!-- 문석이컨트롤러  -->
			<p style="font-size: 20px"><a href="CommunityControl?command=detail&seq=<%=dto.getParent() %>&likeid="><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("HW게시판")){
			%>
			<!-- 효진형컨트롤러  -->
			<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=hwdetail&likeid=&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("컬럼")){
			%>
			<!-- 재흥컨트롤러  -->
			<p style="font-size: 15px">
			<a href="columnbbsdetail.jsp?seq=<%=dto.getParent() %>">
				<%=dto.getTitle() %>
			</a></p>
			<%
			}
				}else{	//로그인한상태
			%>
			<%//컨트롤러다따로 디테일 뿌려줘야한다
			if(dto.getWhatbbs().equals("기술게시판")){
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("사는얘기")){
			%>
			<!-- 병찬이컨트롤러  -->
			<p style="font-size: 15px">
                     <a href="LifeBbs?command=detail&seq=<%=dto.getParent() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
			<%
			}else if(dto.getWhatbbs().equals("QnA게시판")){
			%>
			<!-- 형태형컨트롤러  -->
			<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=<%=mem.getId() %>&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("커뮤니티게시판")){
			%>
			<!-- 문석이컨트롤러  -->
			<p style="font-size: 20px"><a href="CommunityControl?command=detail&seq=<%=dto.getParent() %>&likeid=<%=mem.getId() %>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("HW게시판")){
			%>
			<!-- 효진형컨트롤러  -->
			<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=hwdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getParent()%>"><%=dto.getTitle() %></a></p>
			<%
			}else if(dto.getWhatbbs().equals("컬럼")){
			%>
			<!-- 재흥컨트롤러  -->
			<p style="font-size: 15px">
			<a href="columnbbsdetail.jsp?seq=<%=dto.getParent() %>">
				<%=dto.getTitle() %>
			</a></p>
			<%
			}
			%>
			</th>
			<%if(dto.getComentcount()>0){
			%>
			<td><img src="image/repleon.PNG"><span class="textalig"> <%=dto.getComentcount() %></span></td>
			<%
			}else{
			%>
			<td><img src="image/repleoff.png"><span class="textalig"> <%=dto.getComentcount() %></span></td>
			<%
			}
			%>
			<%if(dto.getLikecount()>0){
			%>
			<td style="padding-top: 3px"><img src="image/likeeon.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
			<%
			}else{
			%>
			<td style="padding-top: 3px"><img src="image/likeeoff.png"><span class="textalig"> <%=dto.getLikecount() %></span></td>
			<%
			}
			%>
			<%if(dto.getReadcount()>0){
			%>
			<td><img src="image/readcounton.PNG"><span class="textalig"> <%=dto.getReadcount() %></span></td>
			<%
			}else{
			%>
			<td><img src="image/readcountoff.png"><span class="textalig"> <%=dto.getReadcount() %></span></td>
			<%
			}
			%>
			
			<td>
			
			<h4>
			<%
			TechbbsServiceImpl tservice=TechbbsService.getInstance();
			String[] getid=tservice.getTagName(dto.getId());
			%>
			<%=getid[0] %>
			</h4>
			</td>
			</tr>
			<%
			}}}
			%>
		</table>
		</div>
		<div class="buttonarea">
			<div class="col-sm-2 user-info-nav pull-right">
                <ul class="nav">
                    <li class="active"><a style="cursor: pointer;" onclick="location.href='TotalController?command=givelikelist&memid=<%=mem.getId()%>'">내가 좋아요한 글</a> </li>
                    <li class=""><a style="cursor: pointer;" onclick="location.href='TotalController?command=givewritelist&memid=<%=mem.getId()%>'">내가 쓴글 </a></li>
                
                </ul>
            </div>
	
		</div>
	</div>

</body>
</html>