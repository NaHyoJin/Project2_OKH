<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.PagingBean"%>
<%@page import="totalbbs.totalbbsdto"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
request.setCharacterEncoding("utf-8");
%>
<%
//검색을눌렀을때만넘어옴
String findWord = request.getParameter("findWord"); 
String choice = request.getParameter("choice"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	

<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_techbbs.css?ver=1.64">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.4">
</head>
<body>
<script type="text/javascript">
function logout() {
	location.href='index.jsp';
}
</script>

<%//로그인한id가져오기
Object ologin = session.getAttribute("login");
UserDto mem = null;

mem = (UserDto)ologin;
IUserService service = UserService.getInstance();
String yn="";
String profile = null;
if(ologin != null){
	profile = service.getProfile(mem.getId());
}
%>
<!-- 인클루드 부분 -->
	<div class="menu">
		<%
		if(ologin == null){	//로그인안한상태
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
			<img src="<%=profile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
			<span class="memid"><a onclick="upmydetail()" style="cursor: pointer;color: #fff;"><%=mem.getId() %></a></span> <br>
			<span class="point"><img src="image/actionpoint.PNG" style="margin-top: 0" class="pointimg"><%=mem.getScore()%></span>
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
				if(<%=yn.equals("yes")%>){
					location.href = "CommunityControl?command=list";
		
				}
				else{
					location.href = "User?command=guest";
				}
				
			});


		
		});
	</script>

<%
if(findWord == null){
	findWord = "";
}
int cho = 0;

if(choice == null) cho = 4;
else if(choice.equals("title")) cho = 0;
else if(choice.equals("writer")) cho = 1;
else if(choice.equals("content")) cho = 2;
else if(choice.equals("tagname")) cho = 3;
List<totalbbsdto> totallist=new ArrayList<>();
TechbbsServiceImpl tservice=TechbbsService.getInstance();
List<totalbbsdto> sortlist=(List<totalbbsdto>)request.getAttribute("sorthe");
String whatsort=(String)request.getAttribute("whatsort");
List<totalbbsdto> serchlist=(List<totalbbsdto>)request.getAttribute("serch");
if(sortlist==null||whatsort==null||sortlist.size()==0){	//sort안했다
	System.out.println("기본페이지");
	totallist=(List<totalbbsdto>)request.getAttribute("totallist");
}else{
	System.out.println("sort페이지");
	totallist=sortlist;
}
String serchyn=(String)request.getAttribute("serchyn");
if(serchyn==null){
	
}
else if(serchlist==null||serchlist.size()==0){	//검색된결과가없을때인데 처음들어올때도비워있으니문제가된다
	
%>
<script type="text/javascript">
alert("검색된결과가없습니다");
</script>
<%
}else if(serchlist!=null){
	totallist=serchlist;
}
%>
<div class="titlediv"><span class="titi">전체게시판</span></div>
	
<div class="wrap">
<div class="sortingmenu">
	 <ul class="list-sort pull-left">
         <li><a onclick="location.href='TotalController?command=sorthe&whatthings=wdate'"  class="category-sort-link active">최신순</a></li>
         <li><a onclick="location.href='TotalController?command=sorthe&whatthings=likecount'" class="category-sort-link">좋아요순</a></li>
         <li><a onclick="location.href='TotalController?command=sorthe&whatthings=contentcount'" class="category-sort-link">댓글순</a></li>
         <li><a onclick="location.href='TotalController?command=sorthe&whatthings=readcount'" class="category-sort-link">조회순</a></li>
     </ul>
	
</div>
<div class="sercharea">
<select id="choice" style="height: 30px">
		<option value="tagname" <%if(cho==3){ out.println("selected");}%>>선택하세요</option>
		<option value="title" <%if(cho==0){ out.println("selected");}%>>제목</option>
		<option value="writer" <%if(cho==1){ out.println("selected");}%>>작성자</option>
		<option value="content" <%if(cho==2){ out.println("selected");}%>>내용</option>
		</select>
<input type="text" class="inputField" id="search" value="<%=findWord %>">
<button name="search" id="serchbtn" class="input-group-btn" onclick="searchBbs()"><img alt="" src="image/serchbtn.PNG"></button>
</div>
	<div class="board">
		<table border="1" class="techtable">
		<col width="450"><col width="80"><col width="80"><col width="80"><col width="150">
			
			<%if(totallist==null||totallist.size()==0){
				
			%><tr>
				<th colspan="5">리스트가없습니다</th>
				</tr>
			<%
			}
			
			for(int i=0;i<totallist.size();i++){
				totalbbsdto dto=totallist.get(i);
				tservice=TechbbsService.getInstance();
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
			if(ologin == null){	//로그인안했을때
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
			<%
		IUserService uservice=UserService.getInstance();
		
		int score=uservice.getScore(dto.getId());
		String getprofile=uservice.getProfile(dto.getId());
		%>
			<p class="myinfo_icon" style="margin-bottom: 0">
			<a onclick="location.href='User?command=otherpage&infoid=<%=dto.getId() %>'" style="cursor: pointer">
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid" style="margin-left: 10px;">
		<a onclick="location.href='User?command=otherpage&infoid=<%=dto.getId() %>'" style="cursor: pointer"><span style="margin-top: 5px"><%=dto.getId() %></span></a>
		</span> <br><br>
		</p>
			<p style="font-size: 10px">20180<%=dto.getWdate() %></p>
		
			</td>
			<%
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
				<%
		IUserService uservice=UserService.getInstance();
		
		int score=uservice.getScore(dto.getId());
		String getprofile=uservice.getProfile(dto.getId());
		%>
			<td>
			<p class="myinfo_icon" style="margin-bottom: 0">
			<a onclick="location.href ='User?command=otherpage&infoid=<%=dto.getId() %>'" style="cursor: pointer">
		
		<img src="<%=getprofile %>" class="media-object img-circle" style="max-width: 30px; float:left; max-height: 30px; margin: 0 auto;">
		</a>
		<span class="detailid" style="margin-left: 10px;">
		<a onclick="location.href ='User?command=otherpage&infoid=<%=dto.getId() %>'" style="cursor: pointer"><span style="margin-top: 5px"><%=dto.getId() %></span></a>
		<span class="" style="margin-top: 10px;"><img src="image/actionpoint.PNG" class="pointimg">
		<%=score%></span>
		</span> <br><br>
		</p>
			<p style="font-size: 10px">20180<%=dto.getWdate() %></p>
		
			</td>
			</tr>
			<%
			}}
			%>
		</table>
		<br>
		

<script type="text/javascript">
if(document.getElementById("choice").value=="tagname"){
	$("#search").val("");
}
</script>

	</div>
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


$(function() {
	$("#techbbs").click(function() {
		location.href="TechbbsController?command=techbbs";
	});
	$("#techwrite").click(function() {
		location.href="TechbbsController?command=techwrite";
	});
	$("#loginhe").click(function() {
		alert("로그인해주세요");
		location.href="TechbbsController?command=techbbs";
	});
});
</script>

<script>
      $(function() {
         // initialize popover with dynamic content
         $('#btnPopover').popover({
            placement: 'right',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="logout()" type="button" class="btn btn-default popover-dismiss">logout</button><button onclick="upmydetail()" type="button" class="btn btn-default popover-dismiss">MY페이지</button>'
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
 <script>
            $(function() {
                $('.category-sort-link').click(function(e) {
                    $('#category-sort-input').val($(this).data('sort'));
                    $('#category-order-input').val($(this).data('order'));
                    e.preventDefault();
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