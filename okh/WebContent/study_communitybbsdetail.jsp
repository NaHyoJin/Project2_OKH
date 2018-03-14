<%@page import="studysrc.CombbsService"%>
<%@page import="studysrc.ICombbsService"%>
<%@page import="studysrc.CombbsDto"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="_main.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>
<%
UserDto mem = (UserDto)session.getAttribute("login");
%>
<%
CombbsDto dto = (CombbsDto)request.getAttribute("comwritedto");
ICombbsService service = CombbsService.getInstance();
int seq = (int)request.getAttribute("detail");
dto = service.detailbbs(seq);

%>

<form action="CommunityControl">
<div class="wrap">
	<table border="1">
		<col width="600"> <col width="200">
		<tr>
		<td colspan="2"><div class="myinfo">작성자<%=mem.getId() %>
											조회수 <%=dto.getReadcount() %>
											댓글수 <%=dto.getCommentcount() %> 
											<input type="hidden" name="parent" value="<%=dto.getParent() %>"> 
		 					 </div></td>
		 
			
		</tr>
		<tr>	
			<td><h2 class="title"><%=dto.getTagname() %> <%=dto.getTitle() %> <br>
			<%=dto.getWdate() %><hr/></h2>
			</td>	
			
				<td	rowspan="2">
				<div class="like">
					<button>좋아요</button><br>
					<button>싫어요</button>
				</div>
				</td>
			
		</tr>
		<tr>
			<td><article class="content">
					<%=dto.getContent() %><br>
				모임날짜 <%=dto.getJoindate() %>
				</article>
			</td>
		</tr>
		
		<tr>
			
		</tr>
	</table>
<a href="CommunityControl?command=list">글목록</a>
<a href="CommunityControl?command=update&seq=<%=dto.getSeq() %>">수정</a>
<a href="CommunityControl?command=delet&seq=<%=dto.getSeq() %>">삭제</a>
<br>

<table>
			<col width="100"> <col width="700">
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" id="id" readonly="readonly" value="<%=mem.getId() %>" size="100">
					<input type="hidden" name="id" value="<%=mem.getId() %>">
					
	 				<input type="hidden" name="command" value="commentAF">
				</td>
			</tr>
			
			
			
			<tr>
				<td>내 용</td>
				<td>
					<textarea name="content" id="summernote"></textarea>
				</td>
			</tr>
			<tr>
		 		<td>
		 			<input type="button" id="cancel" value="취소">
		 			 <input type="submit" value="댓글달기" >
		 		</td>
	 		</tr>
			
		</table>
	</div>
</form>

<script type="text/javascript">

$(document).ready(function() {
     $('#summernote').summernote({
             height: 200,                 // set editor height
             minHeight: null,             // set minimum height of editor
             maxHeight: null,             // set maximum height of editor
             focus: true                  // set focus to editable area after initializing summernote
     });
     
});
</script>








</body>
</html>