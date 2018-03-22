<%@page import="totalbbs.QnaDto"%>
<%@page import="totalbbs.LifeBbsDto"%>
<%@page import="jobs_BBS5.newbbs5HWCodingVO"%>
<%@page import="jobs_BBS5.newbbs5HWCodingService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingServiceImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="totalbbs.totalbbsdto"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="techbbs.TechbbsDto"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	
	<title>index.jsp</title>

	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.32">
<link rel="stylesheet" type="text/css" href="_totalbbs.css?ver=1.27">
<!-- 쿠키용 -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">

function getCookie(name){    
var wcname = name + '=';
var wcstart, wcend, end;
var i = 0;    

  while(i <= document.cookie.length) {            
  	wcstart = i;  
	wcend   = (i + wcname.length);            
	if(document.cookie.substring(wcstart, wcend) == wcname) {                    
		if((end = document.cookie.indexOf(';', wcend)) == -1)                           
			end = document.cookie.length;                    
		return document.cookie.substring(wcend, end);            
  	}            

	i = document.cookie.indexOf('', i) + 1;            
  
  	if(i == 0)                    
		break;    
  }    
  return '';
} 

if(getCookie('okhpop') != 'rangs') {       
 window.open("popup.jsp",
	        "childForm", "location=0, width=390, height=600, resizable = no, scrollbars = no,top=100,left=400");   
}
</script>

	
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
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover">	
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
			if(<%=ologin %> == null){
				location.href = "User?command=guest";
			}else{
				location.href = "CommunityControl?command=list";
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
	<%
	String findWord = request.getParameter("findWord"); 
	String choice = request.getParameter("choice"); 
if(findWord == null){
	findWord = "";
}
int cho = 0;

if(choice == null) cho = 4;
else if(choice.equals("title")) cho = 0;
else if(choice.equals("writer")) cho = 1;
else if(choice.equals("content")) cho = 2;
else if(choice.equals("tagname")) cho = 3;
%>
	<!-- 황준현 -->
<!-- wrap로 메인페이지 섹션사이즈만들어준거고 그밑에 자식들 partition1~partition4로 테이블뿌리면된니다  -->
	<div class="wrap" id="tableChange">
	<div class="sercharea">
	<button class="btn btn-success btn-wide" onclick="location.href='TotalController?command=totalbbs'">전체게시판</button>
<select id="choice" style="margin-left: 30px">
		<option value="tagname">선택하세요</option>
		<option value="title" >제목</option>
		<option value="writer" >작성자</option>
		<option value="content" >내용</option>
		</select>
<input type="text" placeholder="전체글 검색어" class="inputField" id="search" value="<%=findWord %>">
<button name="search" id="serchbtn" class="input-group-btn" onclick="searchBbs()"><img alt="" src="image/serchbtn.PNG"></button>
</div>
<script type="text/javascript">
function searchBbs() {
	if(document.getElementById("choice").value=="tagname"){
		alert("선택해주세요");
		$("#search").val("");
		return;
	}
	if(document.getElementById("search").value==""){//빈문자열에서검색시
		location.href = "totalbbs.jsp?findWord=TechTips&choice=tagname";	
		return;
	}
	var word = document.getElementById("search").value;
	var choice = document.getElementById("choice").value;
	$("#select_id").val("<%=cho %>").prop("selected", true);
	location.href = "TotalController?command=serch&findWord=" + word + "&choice=" + choice;	
}
function searchBbs1(e) {
	$("#search").val("");
	var word = e.value;
	var choice = document.getElementById("choice").value;
	location.href = "totalbbs.jsp?findWord=" + word + "&choice=tagname";	
	
}
</script>
		<div class="partition1">
			<h4 style="margin-bottom: 15px">Q&A <a href="qnaServlet?command=listQna"><img style="float: right" alt="" src="image/moresee.PNG"></a></h4>
			<%
			TechbbsServiceImpl tservice=TechbbsService.getInstance();
			 tservice=TechbbsService.getInstance();
			List<QnaDto> qnalist=tservice.getqnaBbsList();
			%>
			<table class="techtable">
				<%if(qnalist==null||qnalist.size()==0){
				
			%><tr>
				<th>리스트가없습니다</th>
				</tr>
			<%
			}
			
			for(int i=0;i<qnalist.size();i++){
				QnaDto qna=qnalist.get(i);
				
	
				tservice=TechbbsService.getInstance();
				boolean chekcomment=tservice.checkqnacomment(qnalist.get(i).getSeq());
				if(chekcomment){
			%>
			<tr>
				<th style="padding-left:10px;">
				<%
		if(ologin == null){	//로그인안한상태
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=&seq=<%=qna.getSeq()%>"><%=qna.getTitle() %></a></p>
				<p style="text-align: right"><%=qna.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=<%=mem.getId() %>&seq=<%=qna.getSeq()%>"><%=qna.getTitle() %></a></p>
				<p style="text-align: right"><%=qna.getId() %></p>
		<%
		}
			}else{
			%>
			
			<tr>
				<th style="padding-left:10px; border-left: 5px solid #808080">
					<%
		if(ologin == null){	//로그인안한상태
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=&seq=<%=qna.getSeq()%>"><%=qna.getTitle() %></a></p>
				<p style="text-align: right"><%=qna.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="qnaServlet?command=qnaBbsDetail&action=detail&likeid=<%=mem.getId() %>&seq=<%=qna.getSeq()%>"><%=qna.getTitle() %></a></p>
				<p style="text-align: right"><%=qna.getId() %></p>
			<%}
			}}
			%>
		</table>
			
			
		</div>
		<div class="partition2">
		<h4 style="margin-bottom: 15px">사는얘기 <a href="LifeBbs?command=life"><img style="float: right" alt="" src="image/moresee.PNG"></a></h4>
			<%
			
			 tservice=TechbbsService.getInstance();
			List<LifeBbsDto> lifelist=tservice.getlifeBbsList();
			%>
			<table border="1" class="techtable">
				<%if(lifelist==null||lifelist.size()==0){
				
			%><tr>
				<th>리스트가없습니다</th>
				</tr>
			<%
			}
			
			for(int i=0;i<lifelist.size();i++){
				LifeBbsDto dto=lifelist.get(i);
				
	
				tservice=TechbbsService.getInstance();
				boolean chekcomment=tservice.checklifecomment(lifelist.get(i).getSeq());
				if(chekcomment){
			%>
			<tr>
				<th style="padding-left:10px;">
				<%
		if(ologin == null){	//로그인안한상태
			%>
		<p style="font-size: 15px; padding-left: 10px;">
                     <%=arrow(dto.getDepth()) %>
                     <a href="LifeBbs?command=detail&seq=<%=dto.getSeq() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
				<p style="text-align: right"><%=dto.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px; padding-left: 10px;">
                     <%=arrow(dto.getDepth()) %>
                     <a href="LifeBbs?command=detail&seq=<%=dto.getSeq() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
				<p style="text-align: right"><%=dto.getId() %></p>
		<%
		}
			}else{
			%>
			
			<tr>
				<th style="padding-left:10px; border-left: 5px solid #808080">
					<%
		if(ologin == null){	//로그인안한상태
			%>
			<p style="font-size: 15px">
                     <%=arrow(dto.getDepth()) %>
                     <a href="LifeBbs?command=detail&seq=<%=dto.getSeq() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
				<p style="text-align: right"><%=dto.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px">
                     <%=arrow(dto.getDepth()) %>
                     <a href="LifeBbs?command=detail&seq=<%=dto.getSeq() %>">
                        <%=dto.getTitle() %>
                     </a>
                  </p>
				<p style="text-align: right"><%=dto.getId() %></p>
			<%}
			}}
			%>
		</table>
		</div>
		<div class="partition3">
			<h4 style="margin-bottom: 15px">기술게시판 <a href="TechbbsController?command=techbbs"><img style="float: right" alt="" src="image/moresee.PNG"></a></h4>
			<%
			 tservice=TechbbsService.getInstance();
			List<TechbbsDto> techlist=tservice.gettechBbsList();
			%>
			<table border="1" class="techtable">
				<%if(techlist==null||techlist.size()==0){
				
			%><tr>
				<th>리스트가없습니다</th>
				</tr>
			<%
			}
			
			for(int i=0;i<techlist.size();i++){
				TechbbsDto dto=techlist.get(i);
				
	
				tservice=TechbbsService.getInstance();
				boolean chekcomment=tservice.checkcomment(techlist.get(i).getSeq());
				if(chekcomment){
			%>
			<tr>
				<th style="padding-left:10px;">
				<%
		if(ologin == null){	//로그인안한상태
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></p>
				<p style="text-align: right"><%=dto.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></p>
				<p style="text-align: right"><%=dto.getId() %></p>
		<%
		}
			}else{
			%>
			
			<tr>
				<th style="padding-left:10px; border-left: 5px solid #808080">
					<%
		if(ologin == null){	//로그인안한상태
			%>
			<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></p>
				<p style="text-align: right"><%=dto.getId() %></p>
		
				<%
		}else{
			
		%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></p>
				<p style="text-align: right"><%=dto.getId() %></p>
			<%}
			}}
			%>
		</table>
		</div>
	
		<div class="partition4">
			<h4 style="margin-bottom: 15px">H/W & Coding 게시판 <a href="BBSHWCodingController?command=main"><img style="float: right" alt="" src="image/moresee.PNG"></a></h4>
			<%
			newbbs5HWCodingServiceImpl hwservice = newbbs5HWCodingService.getInstance();
			List<newbbs5HWCodingVO> hwlist = hwservice.gettechBbsList();
			%>
			<table border="1" class="techtable">
				<%
				if(hwlist == null || hwlist.size() == 0){
				
			%><tr>
				<th>H/W & Coding 글이 없습니다.</th>
				</tr>
			<%
			}
			
			for(int i = 0; i <hwlist.size(); i++){
				newbbs5HWCodingVO hwdto = hwlist.get(i);
				hwservice = newbbs5HWCodingService.getInstance();
				boolean chekcomment = hwservice.checkcomment(hwlist.get(i).getSeq());
				if(chekcomment){
			%>
			<tr>
				<th style="padding-left:10px;">
						<%      
						//로그인 정보 확인 부분. //로그인한id가져오기
						 	if(ologin != null){
								mem = (UserDto)ologin;
								//로그인 정보 가지고 오나 확인 부분.
								System.out.println("mem : " + mem.toString());
							}else{
								System.out.println("로그인한 정보 없음.");
							}
							%>
							<%
							//로그인 안하고 글 볼때 null 값으로.
								if(mem == null){
									String memNull = null;
							%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=techdetail&likeid=<%=memNull %>&seq=<%=hwdto.getSeq()%>"><%=hwdto.getTitle() %></a></p>
						<%
							}else{//로그인 하고 글 볼때.
						%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=hwdto.getSeq()%>"><%=hwdto.getTitle() %></a></p>		
						<%
							}
			 			%>
				<p style="text-align: right"><%=hwdto.getId() %></p>
			<%
			}else{
			%>
			<tr>
				<th style="padding-left:10px; border-left: 5px solid #808080">
				<%      
				//로그인 정보 확인 부분. //로그인한id가져오기
						 	if(ologin != null){
								mem = (UserDto)ologin;
								//로그인 정보 가지고 오나 확인 부분.
								System.out.println("mem : " + mem.toString());
							}else{
								System.out.println("로그인한 정보 없음.");
							}
							%>
				<%
							//로그인 안하고 글 볼때 null 값으로.
								if(mem == null){
									String memNull = null;
							%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=hwdetail&likeid=<%=memNull %>&seq=<%=hwdto.getSeq()%>"><%=hwdto.getTitle() %></a></p>
						<%
							}else{//로그인 하고 글 볼때.
						%>
				<p style="font-size: 15px; margin-top: 5px;"><a href="BBSHWCodingController?command=hwdetail&likeid=<%=mem.getId() %>&seq=<%=hwdto.getSeq()%>"><%=hwdto.getTitle() %></a></p>		
						<%
							}
			 			%>
				<p style="text-align: right"><%=hwdto.getId() %></p>
	
			<%
			}
			}
			%>
		</table>
		</div>
	</div>
	
</body>
</html>