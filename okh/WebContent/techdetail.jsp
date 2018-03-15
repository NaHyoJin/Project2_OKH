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
<%!
// 댓글용
public String arrow(int depth){	
	String rs = "<img src='image/arrow1.png' width='20px' height='20px'/>";
	String nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";
	String ts = "";
	
	for(int i = 0;i < depth; i++){
		ts += nbsp;
	}
	return depth == 0?"":ts+rs;
}
%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>techdetail.jsp</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="_detail.css?ver=1.33">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

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
	<div class="like">
	<a href="#"><img src="image/like.PNG"></a><br>
		<%=whatlist.get(0).getLikecount() %><br>
		<a href="#"><img src="image/dislike.PNG"></a><br>
		<a href="#"><img src="image/scrap.PNG"></a><br>
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
	<form action="TechRepbbsController" id="frm">
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
}
for(int i = 0;i < replist.size(); i++){
	TechRepbbsDto bbs = replist.get(i);
	%>
	<tr>
		
		<td colspan="2">
		<div class="replistwrap">
		<div class="replist">
		
		<h5>댓글 <span><%=whatlist.get(0).getCommentcount() %></span></h5>
			<%=mem.getId() %>
			<%=arrow(bbs.getDepth()) %>
			<%=bbs.getContent() %>
			<span id="repup">
			
			</span>
			</div>
			<div class="replike">
			<%=bbs.getLikecount() %>
			<%
			if(mem.getId().equals(bbs.getId())){
			%>
			<button type="button" id="reupdatebbs">수정</button>
			<button type="button" onclick="redeletebbs('<%=whatlist.get(0).getSeq() %>')">삭제</button>	
			<%
			}
			%>
			</div>
			</div>
		</td>
	</tr>
	
<%
}%>

<tr>
	<td class="form">
             <textarea name="content" id="summer" placeholder="댓글 쓰기" class="form-control" ></textarea>
			<input type="hidden" name="mainseq" value="<%=whatlist.get(0).getSeq()%>">
			<input type="hidden" name="id" value="<%=mem.getId()%>">
			<input type="hidden" name="command" value="write">
		</td>
		<td>
			<button id="write">등록</button>
			<button id="cancel">취소</button>
		</td>
	</tr>	
</table>
</form>
	</div>
</div>
<script type="text/javascript">
$('#summer').click(function () {
	$('#summer').attr("id", "summernote");
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
			$("#reupdatebbs").click(function () {
				evenupdate();
			});
			function evenupdate() {
var intag="<textarea type='text' name='upcontent' id='summ' class='form-control'></textarea><button id='btn2'>수정하기</button>"
+"<input type='hidden' name='upcon' value='upcon'>"
+"<input type='hidden' name='repseq' value=<%=whatlist.get(0).getSeq()%>>";
	$("#repup").html(intag);				
						
		}
			$('#btn2').click(function () {
				document.getElementById('frm').submit();
			});
			function reupdatebbs( seq ) {
				location.href = "TechRepbbsController?command=delete&seq=" + seq;
			}
			function redeletebbs( seq ) {
				location.href = "TechRepbbsController?command=delete&seq=" + seq;
			}
			$('#summ').click(function () {
				$('#summ').attr("id", "summernote");
				 $('#summernote').summernote({
			         height: 300,                 // set editor height
			         minHeight: null,             // set minimum height of editor
			         maxHeight: null,             // set maximum height of editor
			         focus: true                  // set focus to editable area after initializing summernote
			 });
			});
			</script>
</body>
</html>





