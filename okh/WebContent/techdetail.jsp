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
<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.36">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
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
UserDto mem = (UserDto)session.getAttribute("login");
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
<div class="wrap">
	<div class="myinfo"><%=mem.getId() %><%=whatlist.get(0).getWdate() %><br>
		<p class="myinfo_icon">
		<img alt="" src="image/reple.PNG"><%=whatlist.get(0).getCommentcount() %>
		<img alt="" src="image/readcount.PNG"><%=whatlist.get(0).getReadcount() %>
		</p>
	</div>
	<div class="contentareawrap">
	<div class="contentarea">
		<%for(int i=0;i<tagnames.length;i++){
		%>
			<%=tagnames[i] %>
		<%
		}
		%>
		
		<br>
		<h2 class="title">
		<%=whatlist.get(0).getTitle() %>
		</h2>
	
	<article class="content">
		<%=whatlist.get(0).getContent() %>
		
	</article>
	</div>
	<div id="like">
	<!-- 좋아요스크랩기능 -->
		<a href="LikeScrapController?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>" id="changeli"><img src="image/likeoff.PNG" id="likeimg"></a><br>
		<%=whatlist.get(0).getLikecount() %><br>
		<a href="LikeScrapController?command=dislikeimg&dislikeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>"id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="LikeScrapController?command=scrapimg&scrapid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>"><img src="image/scrap.PNG" id="scrapimg"></a><br>
		<%=whatlist.get(0).getScrapcount()%>
		<%
		System.out.println(whatlist.size()+"리스트사이즈");
		if(whatlist.size()==1&&pdsyn==2){
		}
		else if(whatlist.size()>0&&pdsyn==1){
			System.out.println("자료있는리스트");
			for(int i=0;i<whatlist.size();i++){
				TechbbsDto alldto=whatlist.get(i);
				System.out.println(alldto.getFilename()+"11"+alldto.getFilename()+"22"+alldto.getPdsseq());
		%>
		<input type="button" name="btnDown" id="btnDown" value="<%=alldto.getFilename()%>" 
			onclick="location.href='techfiledown?filename=<%=alldto.getFilename()%>&seq=<%=alldto.getPdsseq()%>'">
		<%
			}
		}
		if(mem.getId().equals(whatlist.get(0).getId())){
		%>
		<button type="button" onclick="updatebbs('<%=whatlist.get(0).getSeq() %>')">수정</button>
		<button type="button" onclick="deletebbs('<%=whatlist.get(0).getSeq() %>')">삭제</button>	
		<%
		}
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
	<form action="TechRepbbsController" id="upda">
		<table border="1" class="reptable">

<%
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
	%>
	<tr><td colspan="2"><h5>댓글 <span><%=whatlist.get(0).getCommentcount() %></span></h5></td></tr>
	<%
for(int i = 0;i < replist.size(); i++){
	TechRepbbsDto bbs = replist.get(i);
	%>
	<tr>
		
		<td>
			
			
			
				<%=mem.getId() %>
				<article class="content<%=bbs.getSeq() %>" name="content"><%=bbs.getContent() %></article>
		</td>		
			<td> 
				<%
				if(mem.getId().equals(bbs.getId())){
				%>
				<input type="button" id="edit<%=bbs.getSeq() %>" class="btn btn-primary" onclick="edit('<%=bbs.getSeq() %>')" value="수정하기">
               <input type="hidden" id="save<%=bbs.getSeq() %>" class="btn btn-primary" onclick="save('<%=bbs.getSeq() %>', '<%=bbs.getParent() %>','<%=mem.getId() %>')" value="저장하기">
				<button type="button" onclick="redeletebbs('<%=bbs.getSeq() %>','<%=bbs.getParent()%>','<%=mem.getId() %>')">삭제</button>	
			
			
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
<tr>
	<td class="form">
             <textarea name="content" id="summe" placeholder="댓글 쓰기" class="form-control" ></textarea>
			<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
			<input type="hidden" name="id" value="<%=mem.getId()%>">
			<input type="hidden" name="command" value="write">
		</td>
		<td>
			<button id="write">등록</button>
		</td>
	</tr>	
</table>
</form>
<button id="repcancel" onclick="location.href='TechbbsController?command=techdetail&likeid=<%=mem.getId() %>&seq=<%=whatlist.get(0).getSeq()%>'">댓글취소</button>
	</div>
	<button onclick="location.href='TechbbsController?command=techbbs'" class="gotobbs">기술게시판가기</button>

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
</body>
</html>





