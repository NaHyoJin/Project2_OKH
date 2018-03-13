<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bbs4HWCoding.jsp</title>
<!-- 스타일 부분 -->
<style type="text/css">

	a{
		text-decoration: none;
	}

</style>
</head>
<body>

<!-- 
	bbs4HWCoding<br>
 -->
	<a href="../mainpage.BBSHWCodingController">H/W & Coding NEWS;</a><!-- ../현재 폴더의 윗 폴더 -->
	<a href="../BBSboardController">자유 게시판;</a><!-- /최상위 폴더 -->
	<a href="../BBSmaterialsController">자료실;</a>
	<br>
	<a href="../HWCodingWrite.BBSHWCodingController">새 글 쓰기</a>
	<a href="">최신순</a>
	<a href="">추천순</a>
	<a href="">조회순</a>
	<br>
	<form action="">
		<input type="text" border="1" align="right">
		<button onclick="" >检索 bbs4HWCoding</button>
	</form>
	
	<br>
	<div align="center">

<table border="1">
<col width="70"><col width="500"><col width="50"><col width="50"><col width="50"><col width="150">

<tr>
	<th>번호</th><th>제목</th><th>답글</th><th>추천</th><th>조회수</th><th>작성자</th>
</tr>

	<%
//		if(bbslist == null || bbslist.size() == 0){
	%>	
		<tr>
			<td colspan="6">작성된 글이 없습니다</td>
		</tr>	
	<%
//		}
//		for(int i = 0;i < bbslist.size(); i++){
//			BbsDto bbs = bbslist.get(i);
	%>

<%-- 	
		<tr>
			<td><%=i+1 %></td>
			<td>
				<%=arrow(bbs.getDepth()) %>


				<%
					if(bbs.getDel()==1) { 
				%>
				
					<h5 align="center" >이 글은 삭제되었습니다.</h5>
					
				<%
					}else{
				%>

<!-- 디테일로 가는 부분 -->				
				<a href="bbsdetail.jsp?seq=<%=bbs.getSeq() %>">
					<%=bbs.getTitle() %>
				
				<%
					}
				%>
			
				</a>
			</td>
			<td><%=bbs.getId() %></td>
		</tr>	
 --%>		
	<%
//		}
	%>
</table>
<br>
<%-- 페이징 처리 일단 보류
<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="bbslist.jsp"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include>
 --%>
</div>

</body>
</html>