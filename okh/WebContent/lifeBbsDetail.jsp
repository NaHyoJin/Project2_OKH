<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbssReplyDto"%>
<%@page import="lifeBbs.LifeBbssReplyDao"%>
<%@page import="lifeBbs.ILifeBbssReplyDao"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
// 댓글용
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
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="_main.css?ver=1.1">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/bootstrap-tagsinput.js"></script>
	
		<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js -->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>
	<div class="menu">
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<input type="button" class="bbs1">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3">
		<input type="button" class="bbs4">
		<input type="button" class="bbs5" id="life">
	</div>
	<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
	});
	</script>
	
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = null;
	if(ologin == null){
		%>
		<script type="text/javascript">
		alert("로그인해 주십시오");
		location.href = "index.jsp";	
		</script>	
		<%
		return;
	}
	mem = (UserDto)ologin;
	%>
	<%
	request.setCharacterEncoding("UTF-8");
	
	String sseq = request.getParameter("seq");
	
	ILifeBbsDao dao = LifeBbsDao.getInstance();
	ILifeBbssReplyDao rdao = LifeBbssReplyDao.getInstance();
	
	dao.readcount(Integer.parseInt(sseq.trim()));
	
	LifeBbsDto bbs = dao.getDetailBbs(Integer.parseInt(sseq.trim()));
	List<LifeBbssReplyDto> rbbslist = rdao.getBbsReplyList(Integer.parseInt(sseq.trim()));
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
	
	<%
	String tagSplit[] = bbs.getTag().split(",");
	String filenameSplit[] = null;
	if(bbs.getFilename() != null){
		filenameSplit = bbs.getFilename().split(",");
	}
	%>

	<script type="text/javascript">
	$(document).ready(function() {
		$('#replyContent').click(function() {
			$('#replyContent').attr('id', 'summernote');
			$('#summernote').summernote({
	             height: 300,                 // set editor height
	             minHeight: null,             // set minimum height of editor
	             maxHeight: null,             // set maximum height of editor
	             focus: true                  // set focus to editable area after initializing summernote
	     	});
		});
		$('#replyBtn').click(function() {
			location.href = "";
		});
	});
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('#upBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"up.jsp",
				data:{
					command: "up",
					seq: <%=bbs.getSeq() %>,
					id: "<%=mem.getId() %>"
				},
				success:function(data){
					$("#upVal").html(data.trim());
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
		
		$('#downBtn').click(function() {
			$.ajax({
				type:"POST",
				url:"down.jsp",
				data:{
					command: "up",
					seq: <%=bbs.getSeq() %>,
					id: "<%=mem.getId() %>"
				},
				success:function(data){
					$("#upVal").html(data.trim());
				},
				error:function(){
					alert("좋아요 실패");
				}
			});
		});
	});
	</script>

	<div class="wrap">
		<table border="1">
			<col width="200"><col width="500">
			<tr>
				<td>작성자</td>
				<td><%=bbs.getId() %></td>
				<td rowspan="8">
					<button id="upBtn">UP</button><br>
					<div id="upVal"><%=bbs.getUp() %></div><br>
					<button id="downBtn">DOWN</button>
				<td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><%=bbs.getTitle() %></td>
			</tr>
			
			<tr>
				<td>Tag</td>
				<td>
					<%
					if(tagSplit != null){
						for(int i = 0; i < tagSplit.length; i++){
					%>
					<a href="#" class="tag"><%=tagSplit[i] %></a>
					<%
						}
					}
					%>
				</td>
			</tr>
			
			<tr>
				<td>작성일</td>
				<td><%=bbs.getWdate() %></td>
			</tr>
			
			<tr>
				<td>다운로드</td>
				<td>
					<%
					if(filenameSplit != null){
						for(int i = 0; i < filenameSplit.length; i++){
					%>
					<input type="button" name="btnDown" id="btnDown" value="파일" 
						onclick="location.href='LifeBbs?command=fileDown&filename=<%=filenameSplit[i] %>&seq=<%=bbs.getSeq() %>'">
					<%	
						}
					}
					%>
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
					<article name="content"><%=bbs.getContent() %></article>
				</td>
			</tr>
		</table>

		<button type="button" 
			onclick="answerbbs('<%=bbs.getSeq() %>')">답글</button>
		
		<% if(bbs.getId().equals(mem.getId())){ %>
		<button type="button" 
			onclick="updatebbs('<%=bbs.getSeq() %>')">수정</button>
		<button type="button" 
			onclick="deletebbs('<%=bbs.getSeq() %>')">삭제</button>
		<%} %>
		
		<table border="1">
			<col width="500"><col width="150">
			
			<tr>
				<th>내용</th><th>작성자</th>
			</tr>
		
		<%
		if(rbbslist == null || rbbslist.size() == 0){
			%>	
			<tr>
				<td colspan="3">작성된 글이 없습니다</td>
			</tr>	
			<%
		}
		for(int i = 0;i < rbbslist.size(); i++){
			LifeBbssReplyDto rbbs = rbbslist.get(i);
			%>
			<tr>
				<%
				if(rbbs.getDel() == 1){
				%>
				<td colspan="3" align="center">삭제된 글입니다.</td>
				<%
				}else{
				%>
				<td>
					<article class="content<%=rbbs.getSeq() %>" name="content"><%=rbbs.getContent() %></article>
				</td>
				<td><%=rbbs.getId() %></td>
				<td>
					<% if(rbbs.getId().equals(mem.getId())){ %>
					<button type="button" onclick="deleteReplyBtn('<%=rbbs.getSeq() %>', '<%=rbbs.getBbsseq() %>')">삭제</button> 
					<input type="button" id="updateReplyBtn" value="수정" class="btn btn-primary">
					<input type="button" id="edit<%=rbbs.getSeq() %>" class="btn btn-primary" onclick="edit('<%=rbbs.getSeq() %>')" value="수정하기">
					<input type="hidden" id="save<%=rbbs.getSeq() %>" class="btn btn-primary" onclick="save('<%=rbbs.getSeq() %>', '<%=rbbs.getBbsseq() %>')" value="저장하기">
					<%} %>
				</td>
				<%
				}
				%>
			</tr>	
			<%
		}
		%>
		</table>
		<% if(bbs.getId().equals(mem.getId())){ %>
		<form action="LifeBbssReply" method="get">
		<input type="hidden" name="command" value="writeReply">
		<input type="hidden" name="id" value="<%=bbs.getId() %>">
		<input type="hidden" name="bbsseq" value="<%=bbs.getSeq() %>">
		<textarea rows="10" cols="50" id="replyContent" name="content" placeholder="댓글 쓰기"></textarea>
		<button id="cancle" onClick="window.location.reload()">취소</button>
		<input type="submit" value="등록">
		<%} %>
		</form>

	</div>
	
	<script type="text/javascript">
	function updatebbs(seq) {
		location.href = "LifeBbs?command=update&seq=" + seq;
	}
	
	function answerbbs(seq) {
		location.href = "LifeBbs?command=answer&seq=" + seq;
	}
	
	function deletebbs(seq) {
		location.href = "LifeBbs?command=delete&seq=" + seq;
	}
	</script>
	
	<script type="text/javascript">
	function deleteReplyBtn(seq, bbsseq) {
		location.href = "LifeBbssReply?command=deleteReply&seq=" + seq + "&bbsseq=" + bbsseq;
	}
	
	var edit = function(seq) {
		$('.content'+seq).summernote({focus: true});
		$('#edit'+seq).attr('type', 'hidden');
		$('#save'+seq).attr('type', 'button');
	};

	var save = function(seq, bbsseq) {
		var content = $('.content'+seq).summernote('code');
		$('.content'+seq).summernote('destroy');
		$('#edit'+seq).attr('type', 'button');
		$('#save'+seq).attr('type', 'hidden');
		location.href = "LifeBbssReply?command=updateReply&seq=" + seq + "&bbsseq=" + bbsseq + "&content=" + content;
	};
	</script>

</body>
</html>