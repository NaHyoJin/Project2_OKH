<%@page import="qna.PagingBean"%>
<%@page import="java.util.List"%>
<%@page import="qna.QnaAnswerDto"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Remove"%>
<%@page import="user.UserDto"%>
<%@page import="qna.QnaDto"%>
<%@page import="qna.QnaService"%>
<%@page import="qna.QnaServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% request.setCharacterEncoding("utf-8");%>
<fmt:requestEncoding value="utf-8" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>qnabbsdetail.jsp</title>
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
// 로그인한 정보
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>

<!-- 인클루드 부분 -->
	<div class="menu">
		<%
		if(ologin == null){	
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
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover">	
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
	</div>	<!-- .menu end -->

	
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
<div class="titlediv"><h2>Q&A</h2><br>
<%
	if(ologin == null){
			%>
	
	<button class="create btn btn-success btn-wide pull-right " type="button" id="loginhe">게시글쓰기</button></div>
	
	<%
		}else{
	%>
	<button class="create btn btn-success btn-wide pull-right " type="button" id="qnawrite">게시글쓰기</button></div>
	
	<%
	}
	%>
	


	
<script type="text/javascript">
$(function() {	
	$("#qnawrite").click(function() {
		
		location.href="qnaServlet?command=qnabbswrite";		
	});
	$("#loginhe").click(function() {
		alert("로그인해주세요");
		location.href="qnaServlet?command=listQna";
	});
});
</script>

<%
// 게시글 정보 detailDto에서 받아옴
QnaDto bbslist = (QnaDto)session.getAttribute("detailDto");
System.out.println("bbs="+bbslist);

String Sseq = request.getParameter("seq");
int seq = Integer.parseInt(Sseq);
QnaServiceImpl service = QnaService.getInstance();
List<QnaAnswerDto> rplist = service.getCommentList(seq);

// tag 검색
String[] tagnames = service.getTagName(bbslist.getTagname());
// 자료
//List<QnaDto> whatlist=(List<QnaDto>)request.getAttribute("whatlist");
int pdsyn=0;
/* if(whatlist.get(0).getPdsys()==2){		//자료없으면
	pdsyn=2;
}else if(whatlist.get(0).getPdsys()==1){	//자료있으면
	pdsyn=1;
} */
%>	

<div class="wrap">
	<div class="myinfo"><%=bbslist.getId() %><%=bbslist.getWdate() %><br>
	<p class="myinfo_icon">
		<img alt="" src="image/repleon.PNG"><span><%=bbslist.getCommentcount() %></span>
		<img alt="" src="image/readcounton.PNG"><span><%=bbslist.getReadcount() %></span>
	</p>
</div>
<div class="contentareawrap">
<div class="contentarea">
<p>
	#<%=bbslist.getSeq() %>
	
	<%for(int i=0;i<tagnames.length;i++){
		%>
			<span class="hjhtag" id="tag<%=i%>"><%=tagnames[i] %></span>
	<%
	}
	%>
</p>
	<br>
	<h2 class="title">
		<%=bbslist.getTitle() %>
	</h2>
<hr>
<article class="content">
	<%=bbslist.getContent() %>		
</article>
</div> <!-- .contentarea end -->
<div id="like">
<%
if(ologin == null){	//로그인안한상태
	%>
<a href="javascript:void(0)" id="changeli"><img src="image/likeoff.PNG" id="likeimg"></a><br>
		<%
}else{
%>
<a href="qnaServlet?command=likeimg&likeid=<%=mem.getId() %>&seq=<%=bbslist.getSeq()%>" id="changeli"><img src="image/likeoff.PNG" id="likeimg"></a><br>
<%
}
%>
	<p style="font-size: 25px"><%=bbslist.getLikecount() %></p>
<%
if(ologin == null){	//로그인안한상태
	%>
<a href="javascript:void(0)"id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="javascript:void(0)"><img src="image/scrap.PNG" id="scrapimg"></a><br>
		
		<%
}else{
	
%>
<a href="qnaServlet?command=dislikeimg&dislikeid=<%=mem.getId() %>&seq=<%=bbslist.getSeq()%>"id="changedisli"><img src="image/dislikeoff.PNG" id="dislikeimg"></a><br>
		<a href="qnaServlet?command=scrapimg&scrapid=<%=mem.getId() %>&seq=<%=bbslist.getSeq()%>"><img src="image/scrap.PNG" id="scrapimg"></a><br>	
<%
}
%>	
	<span id="cocoun"><%=bbslist.getScrapcount()%></span>
		<br><br>
<%-- <%
		System.out.println(whatlist.size()+"리스트사이즈");
		if(whatlist.size()==1&&pdsyn==2){
		}
		else if(whatlist.size()>0&&pdsyn==1){
			System.out.println("자료있는리스트");
		%>
		<input type="text" value="첨부된 파일" readonly="readonly" class="form-control">
		<%
		for(int i=0;i<whatlist.size();i++){
			QnaDto alldto=whatlist.get(i);
			System.out.println(alldto.getFilename()+"11"+alldto.getFilename()+"22"+alldto.getPdsseq());
		%> --%>
	<%-- 	<div class="downbtn">
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
		
		</div> --%>
<%
//	}
//}
	if(ologin == null){}
	else{
		//if(mem.getId().equals(whatlist.get(0).getId())){
	%>
	<img alt="" src="image/settingbtn.PNG" style="cursor: pointer; padding-bottom: 20px; padding-top: 20px;" id="btnPopover2">
	<%
	} //}
	%>


</div> <!-- #like end -->
	<script type="text/javascript">
		function updatebbs( seq ) {
			location.href = "qnaServlet?command=qnaBbsDetail&action=update&seq=" + seq;
		}
		function deletebbs( seq ) {
			location.href = "qnaServlet?command=delete&seq=" + seq;
		}
	</script>


</div> <!-- .contentareawrap end -->



	
<%--

<div align="center">
<h3>여기는 qnabbsdetail.jsp</h3>
</div>

<div class="border" align="center">

<table border="1">
<col width="100"><col width="400"><col width="150">
<tr>
	<td colspan="2" >작성자: <%=bbs.getId() %> </td>
	<td >답변수:<%=bbs.getCommentcount() %>/조회수:<%=bbs.getReadcount() %></td>
</tr>
<tr>
	<td colspan="2">번호:<%= %></td>
	<td rowspan="3">좋아요 구현</td>
</tr>
<tr>
	<td colspan="2">
		<input type="text" name="tAg" id="tAg" data-role="tagsinput" value="<%=bbs.getTagname() %>" ><br><br>
	</td>	
</tr>
<tr>
	<td colspan="2" >
		<textarea rows="10" cols="50" name="cOntent" id="summernote" >
			<%=bbs.getContent() %>
		</textarea>		
		<textarea  rows="30" cols="50" id="summernote" readonly="readonly" style="width: 100%;" >		
		<%=bbs.getContent() %> 
		</textarea>		
		<br><br><br>
		<article>
		<%=bbs.getContent() %>
		</article> 
	</td>
</tr>
</table> 

<% if(bbs.getId().equals(mem.getId())){ %>
<button type="button" 
	onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
<button type="button"
	onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>	
<%} %>

</div>
<br><br><br><br><br><br>

<!-- 답변 시작 -->
<%
//페이징 처리
PagingBean paging = new PagingBean();
if(request.getParameter("nowPage") == null){
	paging.setNowPage(1);		// 처음에는 1페이지로 셋팅
}else{
	paging.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
}
QnaServiceImpl service = QnaService.getInstance();
List<QnaDto> qnalist = service.getBbsPagingList(paging);

%>

<!-- 리스트 보여주기 -->
<div align="center">
<table border="1">
<col width="500"><col width="150">
	
	
	<%
	if(qnalist == null || qnalist.size() == 0){
	
	%>	
	<tr>
		<td colspan="5">작성된 글이 없습니다.</td>
	</tr>
	<%
	}
	%>
	<tr>
		<td align="left">	</td>		
	</tr>
	
	<%
	for(int i = 0; i<qnalist.size(); i++){
		QnaDto qna = qnalist.get(i);	
		if(bbs.getSeq()==qna.getParent()){
	%>
	
	<tr>
		<td>
			답변작성자<%=qna.getId() %>
		</td>		
		
	</tr>	
	<tr>
		<td>
		<article>
			<%=qna.getContent() %>
		</article>
		</td>
	</tr>
	
	<%
	}}
	%>


</table>
</div>
<br><br>


<div id="comment">
<form action="qnaServlet">
<table border="1" align="center">
<col width="500"><col width="150">
<tr>
	<td>
		<input type="hidden" name="command" value="writeAnswer" >
		<input type="hidden" name="iD" value="<%=mem.getId() %>">	<!-- 답변 작성 아이디 -->		
		<input type="hidden" name="aNswerCount" value="<%=bbs.getCommentcount() %>"> <!-- 현재의 답변 카운트를 넘겨준다 -->
		<input type="hidden" name="seq" value="<%=bbs.getSeq() %>">
	</td>
</tr>
<tr>
	<td> 이글에 대한 답변수:<%=qnalist.get(qnalist.size()-1).getCommentcount() %> </td>
	<td rowspan="2"> 
		<input type="submit" value="등록">
	</td> 
</tr>
<tr>
	<td>
		<textarea id="summernote" name="cOntent"></textarea>
	</td>
</tr>	
</table>
</form>
</div>

<br><br><br><br><br><br>


<!-- 답변 끝 -->



<script type="text/javascript">
// 업데이트와 델리트를 위한 함수설정
function updatebbs( seq ) {
	//location.href = "qnaServlet?command=updateQna&seq=" +seq;
	location.href = "qnaServlet?command=qnaBbsDetail&action=update&seq="+seq;
}
function deletebbs(seq) {	
	// 추후 추가
}

</script>



<script type="text/javascript">
$(document).ready(function() {
    $('#summernote').summernote({
            height: 150,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
    });
    //$('#summernote').summernote('disable');
   // $(".note-editable").attr("contenteditable","false")
});
</script>

 --%>

</body>
</html>