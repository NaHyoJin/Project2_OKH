<%@page import="jobs_BBS5.HWRepbbsDto"%>
<%@page import="jobs_BBS5.HWRepbbsService"%>
<%@page import="jobs_BBS5.HWRepbbsServiceImpl"%>
<%@page import="jobs_BBS5.newbbs5HWCodingService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingServiceImpl"%>
<%@page import="jobs_BBS5.newbbs5HWCodingVO"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //한글 인코딩 관련 부분.
		request.setCharacterEncoding("utf-8");
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobs_bbs5HWCodingDetail.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" 
rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" 
rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.41">
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
UserDto mem = null;//null로 초기화.
if(ologin != null){
	mem = (UserDto)ologin;
	//로그인 정보 가지고 오나 확인 부분.
	System.out.println("mem : " + mem.toString());
}else{
	System.out.println("로그인한 정보 없음.");
	String Unknown = "Unknown";
	request.setAttribute("Unknown", Unknown);
}

List<newbbs5HWCodingVO> whatlist = (List<newbbs5HWCodingVO>)request.getAttribute("whatlist");
newbbs5HWCodingServiceImpl tservice = newbbs5HWCodingService.getInstance();
String[] tagnames = tservice.getTagName(whatlist.get(0).getTagname());
int pdsyn=0;
if(whatlist.get(0).getPdsys() == 2){		//자료없으면
	pdsyn=2;
}else if(whatlist.get(0).getPdsys() == 1){	//자료있으면
	pdsyn=1;
}

//답글 게시판 리스트불러오는 함수 작성
HWRepbbsServiceImpl trservice = HWRepbbsService.getInstance();
List<HWRepbbsDto> replist = trservice.getRepBbsList(whatlist.get(0).getSeq());
%>


	<!-- 인클루드 부분 -->
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" >
		<input type="button" class="bbs5" id="jobs"><!-- 나효진 -->
		<input type="button" class="bbs6" id="life"><!-- 병찬 사는얘기 -->
	</div>	

	
	<script type="text/javascript">
		$(function() {//좌측 메뉴바 누르는 곳.

			$("#login").click(function() {
				location.href="User?command=login";
			});
	
			$("#account").click(function() {
				location.href="../User?command=join";
			});
			
			//QNA
			$("#qnabbs").click(function() {
				location.href="../qnaServlet?command=listQna";
			});
			
			$("#second").click(function() {
				location.href="../second.jsp";
			});
	
			$("#techbbs").click(function() {
				location.href="../TechbbsController?command=techbbs";
			});
	
			$("#life").click(function() {
				location.href="../LifeBbs?command=life";
			});
			
			//게시판5 나효진 jobs 부분.
			$("#jobs").click(function name() {
				location.href="jobs";
			});

		});
	</script>
	<div class="titlediv"><h2>HW & Coding Detail</h2><br>
<!-- 	<button onclick="location.href='../BBSHWCodingController?command=techbbs1'"  -->
	<button onclick="newWrite()" 
	class="create btn btn-success btn-wide pull-right ">새 글 쓰기</button>
	</div>
	
	<script type="text/javascript">
	<%
	ologin = session.getAttribute("login");
	mem = null;//null로 초기화.
	if(ologin != null){
		mem = (UserDto)ologin;
		//로그인 정보 가지고 오나 확인 부분.
		System.out.println("mem : " + mem.toString());
	}else{
		System.out.println("새 글 쓰기 부분 로그인한 정보 없음.");
		String Unknown = "Unknown";
		request.setAttribute("Unknown", Unknown);
	}
	%>
	
	function newWrite() {
		if(<%=mem %> == null){
			alert("로그인을 해주세요.");
			return;
		}else{
			location.href="../BBSHWCodingController?command=techbbs1";
		}
		
	}
	
	</script>
	
<div class="wrap">
<%
	if(mem == null){
		String unlogin = "Unknown";
%>
	<div class="myinfo"><%=unlogin %>님 <%=whatlist.get(0).getWdate() %><br>
<%
	}else{
%>
	<div class="myinfo"><%=mem.getId() %>님 <%=whatlist.get(0).getWdate() %><br>
<%
	}
%>	
		<p class="myinfo_icon">
		<img alt="image/reple.PNG 이미지 없음" src="image/reple.PNG"><%=whatlist.get(0).getCommentcount() %>
		<img alt="image/readcount.PNG 이미지 없음." src="image/readcount.PNG"><%=whatlist.get(0).getReadcount() %>
		</p>
	</div>
	<div class="contentareawrap">
	<div class="contentarea">
	<p>
		#<%=whatlist.get(0).getSeq() %>
		
		<%for(int i=0; i < tagnames.length; i++){
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
		if(mem == null){
			String Unknown = "null";
	%>
	<a href="HWLikeScrapController?command=likeimg&likeid=<%=Unknown %>&seq=<%=whatlist.get(0).getSeq()%>" id="changeli">
	<img src="image/likeoff.PNG" id="likeimg"></a><br>	
	<%	
		}else{
	%>
		<a href="HWLikeScrapController?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>" id="changeli">
		<img src="image/likeoff.PNG" id="likeimg"></a><br>
	<%
		}
	%>		
		<p style="font-size: 25px"><%=whatlist.get(0).getLikecount() %></p>
	
	<%
		if(mem == null){
			String unlogin = "null";
	%>
<a href="HWLikeScrapController?command=dislikeimg&dislikeid=<%=unlogin %>&seq=<%=whatlist.get(0).getSeq()%>" id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
<a href="HWLikeScrapController?command=scrapimg&scrapid=<%=unlogin %>&seq=<%=whatlist.get(0).getSeq()%>"><img src="image/scrap.PNG" id="scrapimg"></a><br>	
	<%
		} else{
	%>
	
		<a href="HWLikeScrapController?command=dislikeimg&dislikeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>" id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="HWLikeScrapController?command=scrapimg&scrapid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>"><img src="image/scrap.PNG" id="scrapimg"></a><br>
<%
		}
%>
		<span id="cocoun"><%=whatlist.get(0).getScrapcount()%></span>
		<br><br>
		<%
		System.out.println(whatlist.size() + "리스트 사이즈");
		if(whatlist.size() == 1 && pdsyn == 2){
		}
		else if(whatlist.size() > 0 && pdsyn == 1){
			System.out.println("자료있는 리스트");
			for(int i = 0; i < whatlist.size(); i++){
				newbbs5HWCodingVO alldto = whatlist.get(i);
				System.out.println(alldto.getFilename() + "11" + alldto.getFilename() + "22"+alldto.getPdsseq());
		%>
		
		<!-- 파일 다운로드 부분. -->
		<input type="button" name="btnDown" id="btnDown" value="<%=alldto.getFilename()%>" 
			onclick="location.href='hwfiledown?filename=<%=alldto.getFilename()%>&seq=<%=alldto.getPdsseq()%>'">
		<!-- onclick 부분 ../ 경로 빼줘야 한다. -->
		<%
			}
		}
		if(mem != null && mem.getId().equals(whatlist.get(0).getId()) ){
		%>
		
		<img alt="image/settingbtn.PNG 이미지 없음." src="image/settingbtn.PNG" style="cursor: pointer; 
		padding-bottom: 20px;" id="btnPopover">
		
		<%
		}
		%>
	</div>
	
		<script type="text/javascript">
			function updatebbs( seq ) {
				location.href = "BBSHWCodingController?command=update&seq=" + seq;
			}
			
			//삭제
			function deletebbs(seq) {
					 
			        var msg = "정말 삭제 하시겠습니까?";
			        
			        var delseq = seq;
//			        var delid = <%=mem.getId() %>;
			        if (confirm(msg)) {//confirm 이거 함수 사용해서 작동 완료.
			              
			        
			  /////////////////////////////////////////////// 글삭제 부분에서 시작 180227 

			         <%
			       //글 삭제하는 부분 실질적으로는 DB에 남아있고 보이지만 않게 한다.
//			       response.sendRedirect("../BBSboardController?command=detele&seq="+seq);
//			       location.href = "../BBSboardController?command=detele&seq=" + seq;
//			        	boolean b = dao.bbsDelete(seq);

//						if(b == true) {
//							out.println("이 글은 성공적으로 삭제되었습니다.");
					%>

			            alert("성공적으로 글 삭제.");
			            location.href = "BBSHWCodingController?command=delete&seq=" + seq;

						
					<%
//						}else {
					%>
		/* 					alert("글 삭제 실패.");
							location.href = "bbslist.jsp";
		 */				
						<%
//							}
			        	%>

						
			        } else {// 취소 누른 부분
			            // no click
//			         alert("글 삭제 취소");
			            return;
					}		
			}// deletebbs
			
		</script>
	
	</div>
	
	<!-- 답글게시판뿌리기 -->
	<div class="qna">
	<input type="text" value="댓글  <%=whatlist.get(0).getCommentcount() %>" 
	readonly="readonly" class="form-control">

	<form action="../HWRepbbsController" id="upda">
		<table border="1" class="reptable">
		<col width="850"><col width="175">
<%
if(replist == null || replist.size() == 0){
	%>	
	<tr>
		<td class="form">
			<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
			<%
				if(mem == null){
					String unlogin = "null";
			%>
			<input type="hidden" name="id" value="<%=unlogin %>">
			<%
				}else {
			%>
			<input type="hidden" name="id" value="<%=mem.getId()%>">
			<%
				}
			%>
			<input type="hidden" name="command" value="write">
		</td>
	</tr>	
	<%
}else{
	%>
	
	<%
for(int i = 0;i < replist.size(); i++){
	HWRepbbsDto bbs = replist.get(i);
	%>
	<tr>
		
		<td>
		<%
			if(mem == null){
				String Unknown = "Unknown";
		%>
			<%=Unknown %>
		<%
			}else{
		%>
			<%=mem.getId() %>
		<%
			}
		%>
			<article class="content<%=bbs.getSeq() %>" name="content"><%=bbs.getContent() %></article>
			
		</td>
		<td style="border-left: 1px solid #DDD">	
				<%
				if(mem != null && mem.getId().equals(bbs.getId())){
				%>
				<div class="buttons">
				<input type="button" id="edit<%=bbs.getSeq() %>" 
				class="btn btn-primary" onclick="edit('<%=bbs.getSeq() %>')" value="수정하기">
               <input type="hidden" id="save<%=bbs.getSeq() %>" 
               class="btn btn-primary" onclick="save('<%=bbs.getSeq() %>', '<%=bbs.getParent() %>','<%=mem.getId() %>')" value="저장하기">
				<button type="button" class="btn btn-primary" 
				onclick="redeletebbs('<%=bbs.getSeq() %>','<%=bbs.getParent()%>','<%=mem.getId() %>')">삭제</button>	
				</div>
			
				<%
				System.out.println(replist.get(i).getSeq());
				}
				%>
		</td>
	</tr>

	
<%
}}%>
	</table>
</form>
<script type="text/javascript">
var edit = function(seq) {
    $('.content' + seq).summernote({focus: true});
    $('#edit' + seq).attr('type', 'hidden');
    $('#save' + seq).attr('type', 'button');
 };

 var save = function(seq, bbsseq, memid) {
    var content = $('.content' + seq).summernote('code');
    $('.content' + seq).summernote('destroy');
    $('#edit' + seq).attr('type', 'button');
    $('#save' + seq).attr('type', 'hidden');
    location.href = "HWRepbbsController?command1=upcon&repseq=" + seq + "&seq=" + bbsseq + "&upcontent=" + content+"&memid=" + memid;
 };
</script>
<form action="HWRepbbsController" id="frm">
	<table border="1" class="reptable">
	<col width="850"><col width="175">
	<tr>
		<td class="form">
	             <textarea name="content" id="summe" placeholder="댓글 쓰기" 
	             class="form-control" ></textarea>
				<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
				<%
					if(mem ==null){
						String unlonin = "null";
				%>
				<input type="hidden" name="id" value="<%=unlonin %>">
				<%
					}else{
				%>
				<input type="hidden" name="id" value="<%=mem.getId()%>">
				<%
					}
				%>
				<input type="hidden" name="command" value="write">
			</td>
			<td>
				<div class="buttons">
				
				<%	//null 아닐때만 보여주는 부분.
					if(mem != null){
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
	if(mem == null){
		String un = "null";
%>
<button id="repcancel" class="btn btn-default btn-wide" 
onclick="location.href='BBSHWCodingController?command=techdetail&likeid=<%=un %>&seq=<%=whatlist.get(0).getSeq()%>'">댓글취소</button>		
<%	
	}else{
%>
	<button id="repcancel" class="btn btn-default btn-wide" 
	onclick="location.href='BBSHWCodingController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>'">댓글취소</button>
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

// 글 작성 하는 부분 스크립트
$(function() {
	$('#write').click(function () {
		document.getElementById('frm').submit();
	});
});


</script>


<script type="text/javascript">	

		function redeletebbs( seq,bonseq,memid ) {
			location.href = "HWRepbbsController?command=delete&seq=" + seq + "&bonseq=" + bonseq + "&memid="+memid;
		}
		
		function reuptebbs( seq ) {
			
		}
		
		function hjh(elem,seq) {
			
			 
			var sum1 = "summernote";
			var nnam = $(elem).attr('name');	//0,1,2...버튼의 네임가져오기
			var iid = $('#'+nnam).attr('id');		//0,1,2...textarea id 가져오기
			$('#cont' + nnam).hide();
			$("#" + nnam).show();
			alert(iid);
			
			sum1 += iid;
			alert(sum1);
					$('#' + iid).attr("id", sum1);
					 $('#' + sum1).summernote({
				         height: 300,                 // set editor height
				         minHeight: null,             // set minimum height of editor
				         maxHeight: null,             // set maximum height of editor
				         focus: true                  // set focus to editable area after initializing summernote
				 });
					 $('#' + sum1).summernote();
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
newbbs5HWCodingVO li = (newbbs5HWCodingVO)request.getAttribute("likeidyn");
int likeidyn = 0;

if(li == null){
	
}else{
	likeidyn = li.getLikeidyn();
	if(likeidyn == 1){		//라이크에 불끄기 
%>
	<script type="text/javascript">
	
	change();
	
	</script>
<%
}else if(likeidyn == 2){	//라이크에 불들어오게 dislike버튼비활성화
%>
	<script type="text/javascript">
	change1();
	</script>
<%
}
}
%>
<%
//좋아요랑 안좋아요 아이디 유무 판단해서 여기서 css만져주기//likeid있으면 1(취소) id없으면2(추가)
newbbs5HWCodingVO li2 = (newbbs5HWCodingVO)request.getAttribute("dislikeidyn");
int dislikeidyn = 0;
if(li2 == null){
	
}else{
	dislikeidyn = li2.getDislikeidyn();
	if(dislikeidyn == 1){		//라이크에 불끄기 
%>
	<script type="text/javascript">
	
	change2();
	
	</script>
<%
}else if(dislikeidyn == 2){	//라이크에불들어오게 dislike버튼비활성화
%>

	<script type="text/javascript">
	change3();
	</script>
	
<%
}
}
%>

<%
//디테일창 처음들어왔을때 id못 찾으면2->불꺼줘야함 찾으면1->불켜야함
newbbs5HWCodingVO li3 = (newbbs5HWCodingVO)request.getAttribute("flikeidyn");
int flikeidyn = 0;
if(li3 == null){
	
}else{
	flikeidyn = li3.getLikeidyn();
	if(flikeidyn == 2){		//라이크에 불끄기 
%>
	<script type="text/javascript">
	
	change();
	
	</script>
<%
}else if(flikeidyn == 1){	//라이크에불들어오게 dislike버튼비활성화
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
newbbs5HWCodingVO li4 = (newbbs5HWCodingVO)request.getAttribute("fdislikeidyn");
int fdislikeidyn = 0;

if(li4 == null){
	
}else{
	fdislikeidyn = li4.getDislikeidyn();
	if(fdislikeidyn == 2){		//라이크에불끄기 
%>
	<script type="text/javascript">
	
	change2();
	
	</script>
<%
}else if(fdislikeidyn == 1){	//라이크에불들어오게 dislike버튼비활성화
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
       //content: '<button onclick="logout()" type="button" class="btn btn-default popover-dismiss">로그 아웃</button><button onclick="upmydetail()" type="button" class="btn btn-default popover-dismiss">마이 페이지</button>'
       content: '<button onclick="updatebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">내 글 수정</button><button onclick="deletebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">내 글 삭제</button>'
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
         $('#btnPopover1').popover({
            placement: 'right',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="updatebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">수정</button><button onclick="deletebbs(<%=whatlist.get(0).getSeq() %>)" type="button" class="btn btn-default popover-dismiss">삭제</button>'
         });
         // prevent popover from being hidden on mouseout.
         // only dismiss when explicity clicked (e.g. has .hide-popover)
         $('#btnPopover1').on('hide.bs.popover', function(evt) {
            if(!$(evt.target).hasClass('hide-popover')) {
               evt.preventDefault();
               evt.stopPropagation();
               evt.cancelBubble = true;
            }
         });
         // reset helper class when dismissed
         $('#btnPopover1').on('hidden.bs.popover', function(evt) {
            $(this).removeClass('hide-popover');
         });
         $('body').on('click', '.popover-dismiss', function() {
            // add helper class to force dismissal
            $('#btnPopover').addClass('hide-popover');
            // call method to hide popover
            $('#btnPopover').popover('hide');
         });
          
          $('#btnPopover1').data('overButton', false);
          $('#btnPopover1').data('overPopover', false);
          $.fn.closePopover = function(){
            var $this = $(this);
            
            if(!$this.data('overPopover') && !$this.data('overButton')){
              $this.addClass('hide-popover');
              $this.popover('hide');              
            }
          }
          
          //set flags when mouse enters the button or the popover.
          //When the mouse leaves unset immediately, wait a second (to allow the mouse to enter again or enter the other) and then test to see if the mouse is no longer over either. If not, close popover.
          $('#btnPopover1').on('mouseenter', function(evt){
            $(this).data('overButton', true);
          });
          $('#btnPopover1').on('mouseleave', function(evt){
            var $btn = $(this);
            $btn.data('overButton', false);
            
            setTimeout(function() {$btn.closePopover();}, 200);
            
          });
          $('#btnPopover1').on('shown.bs.popover', function () {
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
</body>
</html>




























<%-- <%@page import="jobs_BBS5.BbsHWCodingBeanDtoVO"%>
<%@page import="jobs_BBS5.BbsBoardBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5ModelService"%>
<%@page import="jobs_BBS5.jobsBbs5ModelServiceImpl"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>jobs_bbs5HWCodingDetail.jsp</title>
	<link rel="stylesheet" type="text/css" href="../_main.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
</head>
<body>
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
		<input type="button" class="bbs4">
		<input type="button" class="bbs5" id="jobs">
		<input type="button" class="bbs6" id="life">
	</div>
	
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "../User?command=login";
		});
		$("#account").click(function() {
			location.href = "../User?command=join";
		});
		
		$("#jobs").click(function() {
			location.href = "../jobs";
		});
		
		$("#life").click(function() {
			location.href = "../LifeBbs?command=life";
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
	//한글 인코딩 관련 부분.
	request.setCharacterEncoding("UTF-8");
	
	//시퀀스 번호 받는 부분.
	String sseq = request.getParameter("seq");
	int seq = Integer.parseInt(sseq.trim());
	
	jobsBbs5ModelServiceImpl dao = jobsBbs5ModelService.getInstance();
	
	//누르면 조회수 올라가는 부분.
	dao.readcounthwbbs(seq);
	
	//dao 에서 가지고 오는 부분.
	BbsHWCodingBeanDtoVO bbs = dao.detailHWbbs(seq);
	System.out.println("BbsHWCodingBeanDtoVO 가지고온 글 : " + bbs.toString());
	%>
	
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
	
	<div class="wrap">
		<table border="2">
			<col width="200"><col width="500">
			<tr>
				<td>작성자</td>
				<td><%=bbs.getId() %></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><%=bbs.getTitle() %></td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<input type="text" value="<%=bbs.getTag() %>" data-role="tagsinput">
				</td>
			</tr>
			
			<tr>
				<td>작성일</td>
				<td><%=bbs.getWdate() %></td>
			</tr>
			
			<tr>
				<td>다운로드</td>
				<td>
					<input type="button" name="btnDown" id="btnDown" value="파일" 
				onclick="location.href='LifeBbs?command=fileDown&filename=<%=bbs.getFilename() %>&seq=<%=bbs.getSeq() %>'">
				</td>
			</tr>
			
			<tr>
				<td>조회수</td>
				<td><%=bbs.getReadcount() %></td>
			</tr>
			
			<tr>
				<td>정보</td>
				<td><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="50" name="content" readonly="readonly"><%=bbs.getContent() %>
					</textarea>
				</td>
			</tr>
		</table>

<%
	if(mem != null){
		
	
%>
		<button type="button" 
			onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>
		
		<% if(bbs.getId().equals(mem.getId())){ 
		%>
		<button type="button" 
			onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
		<button type="button" 
			onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>
		<%
		} 
		%>
<%
	}
%>
<br><br>
	<button type="button" onclick="normalbbshome()">home</button>
	</div>
	
	
	<script type="text/javascript">
	//수정.
	function updatebbs(seq) {
		location.href = "../BBSHWCodingController?command=update&seq=" + seq;
	}
	
	function answerbbs(seq) {
		location.href = "LifeBbs?command=answer&seq=" + seq;
	}
	
	//삭제
	function deletebbs(seq) {
			 
	        var msg = "정말 삭제 하시겠습니까?";
	        
	        var delseq = seq;
	        
	        if (confirm(msg)) {//confirm 이거 함수 사용해서 작동 완료.
	              
	        
	  /////////////////////////////////////////////// 글삭제 부분에서 시작 180227 

	         <%
	       //글 삭제하는 부분 실질적으로는 DB에 남아있고 보이지만 않게 한다.
//	       response.sendRedirect("../BBSboardController?command=detele&seq="+seq);
//	       location.href = "../BBSboardController?command=detele&seq=" + seq;
//	        	boolean b = dao.bbsDelete(seq);

//				if(b == true) {
//					out.println("이 글은 성공적으로 삭제되었습니다.");
			%>

	            alert("성공적으로 글 삭제.");
				location.href = "../BBSHWCodingController?command=detele&seq=" + seq;

				
			<%
//				}else {
			%>
/* 					alert("글 삭제 실패.");
					location.href = "bbslist.jsp";
 */				
				<%
//					}
	        	%>

				
	        } else {// 취소 누른 부분
	            // no click
//	         alert("글 삭제 취소");
	            return;
			}		
	}// deletebbs
	
	
	// home버튼
	function normalbbshome() {
		location.href = "../BBSHWCodingController?command=HWBbs";
	}
	</script>

</body>
</html> --%>