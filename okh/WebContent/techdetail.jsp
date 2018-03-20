<%@page import="techreplebbs.TechRepbbsService"%>
<%@page import="techreplebbs.TechRepbbsServiceImpl"%>
<%@page import="techreplebbs.TechRepbbsDto"%>
<%@page import="java.util.List"%>
<%@page import="techbbs.TechbbsService"%>
<%@page import="techbbs.TechbbsServiceImpl"%>
<%@page import="techbbs.TechbbsDto"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>techdetail.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.44">

<link rel="stylesheet" type="text/css" href="_main.css?ver=1.31">
</head>
<body>
<script type="text/javascript">
function change() {		//좋아요취소		-> 		싫어요활성화
	$("#likeimg").attr('src','image/likeoff.PNG');
}
function change1() {	//좋아요할래요		->		싫어요비활성화
	$("#likeimg").attr('src','image/likeon.PNG');
	$("#changedisli").css({ 'pointer-events': 'none' });
}
function change2() {	//싫어요취소		->		좋아요활성화
	$("#dislikeimg").attr('src','image/dislikeoff.PNG');
}
function change3() {	//싫어요할래요		->		좋아요비활성화
	$("#dislikeimg").attr('src','image/dislikeon.PNG');
	$("#changeli").css({ 'pointer-events': 'none' });
}
var myVar;
$(function() {
	$("#repcancel").click(function() {
		myVar=setTimeout(_refresh,1000);
	});
});
function _refresh() {
	location.reload;		
	clearTimeout(myVar);
}
</script>

<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
mem = (UserDto)ologin;
List<TechbbsDto> whatlist=(List<TechbbsDto>)request.getAttribute("whatlist");
TechbbsServiceImpl tservice=TechbbsService.getInstance();
String[] tagnames=tservice.getTagName(whatlist.get(0).getTagname());
int pdsyn=0;
if(whatlist.get(0).getPdsys()==2){		//자료없으면
	pdsyn=2;
}else if(whatlist.get(0).getPdsys()==1){	//자료있으면
	pdsyn=1;
}
//답글게시판리스트불러오는함수작성
TechRepbbsServiceImpl trservice=TechRepbbsService.getInstance();
List<TechRepbbsDto> replist=trservice.getRepBbsList(whatlist.get(0).getSeq());

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
	<span><%=mem.getId() %></span>
	<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
</div>
<%
}
%>
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" >
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	

	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.
			$("#homebtn").click(function() {
				location.href="main.jsp";
			});
			$("#login").click(function() {
				location.href="User?command=login";
			});
	
			$("#account").click(function() {
				location.href="User?command=join";
			});
			
			//QNA
			$("#qnabbs").click(function() {
				location.href="qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="second.jsp";
			});
	
			$("#techbbs").click(function() {
				location.href="TechbbsController?command=techbbs";
			});
	
			$("#life").click(function() {
				location.href="LifeBbs?command=life";
			});
			
			
	/* 
			//columns
			$("#").click(function() {
				location.href="";
			});
	 */
	 
			//게시판5 나효진 jobs 부분.
/* 			$("#jobs").click(function() {
				location.href="main.BBSHWCodingController";
			});
 */	 
			
			$("#jobs").click(function name() {
				location.href="jobs";
			});

		});
	</script>
	<div class="titlediv"><h2>TechTips</h2><br>
	<%
if(ologin == null){	//로그인안한상태
	%>
<button class="create btn btn-success btn-wide pull-right" onclick="loginhe()">새 글 쓰기</button>
		<%
}else{
	
%>
<button onclick="location.href='TechbbsController?command=techbbs1'" class="create btn btn-success btn-wide pull-right">새 글 쓰기</button>
<%
}
%>
	
	</div>
<div class="wrap">
	<div class="myinfo"><%=whatlist.get(0).getId() %><%=whatlist.get(0).getWdate() %><br>
		<p class="myinfo_icon">
		<img alt="" src="image/repleon.PNG"><span><%=whatlist.get(0).getCommentcount() %></span>
		<img alt="" src="image/readcounton.PNG"><span><%=whatlist.get(0).getReadcount() %></span>
		</p>
	</div>
	<div class="contentareawrap">
	<div class="contentarea">
	<p>
		#<%=whatlist.get(0).getSeq() %>
		
		<%for(int i=0;i<tagnames.length;i++){
		%>
			<span class="hjhtag" id="tag<%=i%>"><%=tagnames[i] %></span>
		<%
		}
		%>
	</p>	
		<br>
		<h2 class="title">
		<%=whatlist.get(0).getTitle() %>
		</h2>
	<hr>
	<article class="content">
		<%=whatlist.get(0).getContent() %>
		
	</article>
	</div>
	<div id="like">
	<!-- 좋아요스크랩기능 -->
			<%
if(ologin == null){	//로그인안한상태
	%>
<a href="javascript:void(0)" id="changeli"><img src="image/likeoff.PNG" id="likeimg"></a><br>
		<%
}else{
%>
<a href="LikeScrapController?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>" id="changeli"><img src="image/likeoff.PNG" id="likeimg"></a><br>
<%
}
%>
	<p style="font-size: 25px"><%=whatlist.get(0).getLikecount() %></p>
<%
if(ologin == null){	//로그인안한상태
	%>
<a href="javascript:void(0)"id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="javascript:void(0)"><img src="image/scrap.PNG" id="scrapimg"></a><br>
		
		<%
}else{
	
%>
<a href="LikeScrapController?command=dislikeimg&dislikeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>"id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="LikeScrapController?command=scrapimg&scrapid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>"><img src="image/scrap.PNG" id="scrapimg"></a><br>	
<%
}
%>	
		<span id="cocoun"><%=whatlist.get(0).getScrapcount()%></span>
		<br><br>
		<%
		System.out.println(whatlist.size()+"리스트사이즈");
		if(whatlist.size()==1&&pdsyn==2){
		}
		else if(whatlist.size()>0&&pdsyn==1){
			System.out.println("자료있는리스트");
		%>
		<input type="text" value="첨부된 파일" readonly="readonly" class="form-control">
		<%
		for(int i=0;i<whatlist.size();i++){
			TechbbsDto alldto=whatlist.get(i);
			System.out.println(alldto.getFilename()+"11"+alldto.getFilename()+"22"+alldto.getPdsseq());
		%>
		<div class="downbtn">
		<%
if(ologin == null){	//로그인안한상태
	%>
<input type="button" name="btnDown" style="cursor: pointer" disabled="disabled" id="btnDown" value="<%=alldto.getFilename()%>" class="btn btn-default btn-wide"
			onclick="location.href='techfiledown?filename=<%=alldto.getFilename()%>&seq=<%=alldto.getPdsseq()%>'">		
		<%
}else{
	
%>
<input type="button" name="btnDown" id="btnDown" value="<%=alldto.getFilename()%>" class="btn btn-default btn-wide"
			onclick="location.href='techfiledown?filename=<%=alldto.getFilename()%>&seq=<%=alldto.getPdsseq()%>'"><%
}
%>	
		
		</div>
<%
	}
}
	if(ologin == null){}
	else{
		if(mem.getId().equals(whatlist.get(0).getId())){
	%>
	<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px; padding-top: 20px;" id="btnPopover2">
	<%
	}}
	%>
	</div>
	<script type="text/javascript">
		function updatebbs( seq ) {
			location.href = "TechbbsController?command=update&seq=" + seq;
		}
		function deletebbs( seq ) {
			location.href = "TechbbsController?command=delete&seq=" + seq;
		}
	</script>
	</div>
	<!-- 답글게시판뿌리기 -->
	<div class="qna">
	<input type="text" value="댓글  <%=whatlist.get(0).getCommentcount() %>" readonly="readonly" class="form-control">

	<form action="TechRepbbsController" id="upda">
		<table border="1" class="reptable">
		<col width="850"><col width="175">
	<%
	if(ologin == null){}
	else{
		if(replist == null || replist.size() == 0){
	%>
	<tr>
		<td class="form">
			<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
			<input type="hidden" name="id" value="<%=mem.getId()%>">
			<input type="hidden" name="command" value="write">
		</td>
	</tr>
	<%
	}else{
		for(int i = 0;i < replist.size(); i++){
			TechRepbbsDto bbs = replist.get(i);
	%>
	<tr>
		<td>
			<%=mem.getId() %>
			<article class="content<%=bbs.getSeq() %>" name="content"><%=bbs.getContent() %></article>
		</td>
		<td style="border-left: 1px solid #DDD">	
	<%
	if(ologin == null){}
	else{
		if(mem.getId().equals(bbs.getId())){
	%>
	<div class="buttons">
	<input type="button" id="edit<%=bbs.getSeq() %>" class="btn btn-primary" onclick="edit('<%=bbs.getSeq() %>')" value="수정하기">
    <input type="hidden" id="save<%=bbs.getSeq() %>" class="btn btn-primary" onclick="save('<%=bbs.getSeq() %>', '<%=bbs.getParent() %>','<%=mem.getId() %>')" value="저장하기">
	<button type="button" class="btn btn-primary" onclick="redeletebbs('<%=bbs.getSeq() %>','<%=bbs.getParent()%>','<%=mem.getId() %>')">삭제</button>	
	</div>
	<%
	}}
	%>			
		</td>
	</tr>
<%
}}}%>
	</table>
</form>
<script type="text/javascript">
var edit = function(seq) {
    $('.content'+seq).summernote({focus: true});
    $('#edit'+seq).attr('type', 'hidden');
    $('#save'+seq).attr('type', 'button');
 };

 var save = function(seq, bbsseq, memid) {
    var content = $('.content'+seq).summernote('code');
    $('.content'+seq).summernote('destroy');
    $('#edit'+seq).attr('type', 'button');
    $('#save'+seq).attr('type', 'hidden');
    location.href = "TechRepbbsController?command1=upcon&repseq=" + seq + "&seq=" + bbsseq + "&upcontent=" + content+"&memid=" + memid;
 };
</script>
<form action="TechRepbbsController" id="frm">
<table border="1" class="reptable">
<col width="850"><col width="175">
<tr>
	<td class="form">
           
	
					<%
	if(ologin == null){
			%>
	  <textarea name="content" readonly="readonly" placeholder="로그인해야 이용가능합니다" class="form-control" ></textarea>
	<%
		}else{
	%>
	  <textarea name="content" id="summe" placeholder="댓글 쓰기" class="form-control" ></textarea>
	<input type="hidden" name="id" value="<%=mem.getId()%>">
		<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
		<input type="hidden" name="command" value="write">
	<%
	}
	%>
		</td>
		<td>
			<div class="buttons">
			<%
	if(ologin == null){
			%>
	<button class="btn btn-success btn-wide" style="cursor: pointer" disabled="disabled">등록</button>
	<%
		}else{
	%>
	<button class="btn btn-success btn-wide" id="write">등록</button>
	<%
	}
	%>
			
			</div>
		</td>
	</tr>	
</table>
</form>
	<%
	if(ologin == null){
		}else{
	%>
	<button id="repcancel" class="btn btn-default btn-wide" onclick="location.href='TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>'">댓글취소</button>
	<%
	}
	%>
	
	
	</div>
	

</div>
<script type="text/javascript">
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

$(function() {
	$('#write').click(function () {
		document.getElementById('frm').submit();
	});
});


</script>  
<script type="text/javascript">	
			function redeletebbs( seq,bonseq,memid ) {
				location.href = "TechRepbbsController?command=delete&seq=" + seq+"&bonseq="+bonseq+"&memid="+memid;
			}
			function reuptebbs( seq ) {
				
			}
		function hjh(elem,seq) {
			
			 
			var sum1="summernote";
			var nnam=$(elem).attr('name');	//0,1,2...버튼의 네임가져오기
			var iid=$('#'+nnam).attr('id');		//0,1,2...textarea id 가져오기
			$('#cont'+nnam).hide();
			$("#"+nnam).show();
			alert(iid);
			
			sum1+=iid;
			alert(sum1);
					$('#'+iid).attr("id", sum1);
					 $('#'+sum1).summernote({
				         height: 300,                 // set editor height
				         minHeight: null,             // set minimum height of editor
				         maxHeight: null,             // set maximum height of editor
				         focus: true                  // set focus to editable area after initializing summernote
				 });
					 $('#'+sum1).summernote();
					 $(elem).hide();
					 $('.uploadbtn:eq('+nnam+')').show();	
					 $("#seqq").attr("value",seq);
		}
		$(".uploadbtn").click(function () {
			document.getElementById('upda').submit();
		});
		$(document).ready(function () {
			$("[name=upcontent]").hide();
			 $('.uploadbtn').hide();
		});
			
			</script>
		<!-- /* 계속리프래쉬하는거 다운누르면 바로바로올라가게하는거 */ -->	
<script type="text/javascript">
$("#loginhe").click(function() {
	alert("로그인해주세요");
	location.href="login.jsp";
});

$(document).keydown(function (e) {
    
    if (e.which === 116) {
        if (typeof event == "object") {
            event.keyCode = 0;
        }
        return false;
    } else if (e.which === 82 && e.ctrlKey) {
        return false;
    }
});  
</script>
<%
//좋아요랑 안좋아요 아이디유무판단해서 여기서 css만져주기//likeid있으면 1(취소) id없으면2(추가)
TechbbsDto li=(TechbbsDto)request.getAttribute("likeidyn");
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
//좋아요랑 안좋아요 아이디유무판단해서 여기서 css만져주기//likeid있으면 1(취소) id없으면2(추가)
TechbbsDto li2=(TechbbsDto)request.getAttribute("dislikeidyn");
int dislikeidyn=0;
if(li2==null){
	
}else{
	dislikeidyn=li2.getDislikeidyn();
	if(dislikeidyn==1){		//라이크에불끄기 
%>
	<script type="text/javascript">
	
	change2();
	
	</script>
<%
}else if(dislikeidyn==2){	//라이크에불들어오게 dislike버튼비활성화
%>
	<script type="text/javascript">
	change3();
	</script>
<%
}
}
%>
<%
//디테일창처음들어왔을때 id못찾으면2->불꺼줘야함 찾으면1->불켜야함
TechbbsDto li3=(TechbbsDto)request.getAttribute("flikeidyn");
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
<%
//디테일창처음들어왔을때 id못찾으면2->불꺼줘야함 찾으면1->불켜야함
TechbbsDto li4=(TechbbsDto)request.getAttribute("fdislikeidyn");
int fdislikeidyn=0;

if(li4==null){
	
}else{
	fdislikeidyn=li4.getDislikeidyn();
	if(fdislikeidyn==2){		//라이크에불끄기 
%>
	<script type="text/javascript">
	
	change2();
	
	</script>
<%
}else if(fdislikeidyn==1){	//라이크에불들어오게 dislike버튼비활성화
%>
	<script type="text/javascript">
	change3();
	</script>
<%
}
}
%>
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
         // initialize popover with dynamic content
         $('#btnPopover2').popover({
            placement: 'bottom',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="updatebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">수정</button><button onclick="deletebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">삭제</button>'
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
function loginhe() {
	alert("로그인해야 가능합니다");
	location.href='TechbbsController?command=techdetail&likeid=&seq=<%=whatlist.get(0).getSeq()%>'
}
</script>		

</body>
</html>





