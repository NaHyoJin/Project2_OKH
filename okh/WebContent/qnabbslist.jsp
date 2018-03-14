

<%@page import="qna.PagingBean"%>
<%@page import="qna.QnaDto"%>
<%@page import="qna.QnaService"%>
<%@page import="qna.QnaServiceImpl"%>
<%@page import="user.UserDto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
String findWord = request.getParameter("findWord"); 
String choice = request.getParameter("choice"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listQna.jsp</title>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_main.css">
	
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
<style type="text/css">
table {
	width: 100%;
	border: 1px solid #444444;
}
tr,td {
	border: 1px ;
	
}
</style>		
</head>
<body>

<div class="menu">
<jsp:include page="menuinclude.jsp">
<jsp:param name="actionPath" value="index.jsp"/>
</jsp:include>
</div>

<%

//로그인정보
Object ologin = session.getAttribute("login");

UserDto mem = (UserDto)ologin;
/* qnaBbsDaoImpl dao = qnaBbsDao.getInstance();
List<QnaDto> qnalist = dao.getQnaList(); */


//QnaServiceImpl service = QnaService.getInstance();
//List<QnaDto> qnalist = service.getQnaList();
%>

<%
//페이징 처리
PagingBean paging = new PagingBean();
if(request.getParameter("nowPage") == null){
	paging.setNowPage(1);		// 처음에는 1페이지로 셋팅
}else{
	paging.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
}
%>
<%
QnaServiceImpl service = QnaService.getInstance();
List<QnaDto> qnalist = service.getBbsPagingList(paging);

%>


<%--
if(findWord == null){
	findWord = "";
}
int cho = 0;

if(choice == null) cho = 0;
else if(choice.equals("title")) cho = 0;
else if(choice.equals("writer")) cho = 1;

QnaServiceImpl service = QnaService.getInstance();
List<QnaDto> qnalist = service.getQnaPagingList(paging, findWord, cho);

--%>
<div class="wrap">

<div align="center">

<h3>여기는 Q&A 게시판</h3>
<a href="qnabbswrite.jsp">새 글쓰기</a>
<!-- 
<button onclick="location.href='qnaAdd?command=addQna'">새 글쓰기</button>
-->

</div>
<br><br>

<div align="center">
<!-- 관리자 공지 위치 -->
<table>
<!-- (게시물번호)(태그)(좋아요)(답변수)(작성자정보) -->
<col width="50"><col width="300"><col width="50"><col width="50"><col width="100">
	<tr>
		<td>번호</td>
		<td>테그</td>
		<td rowspan="2">좋아요</td>
		<td rowspan="2">답변수</td>
		<td rowspan="2">작성자정보 </td>		
	</tr>
	<tr>
		<td colspan="2">게시물 타이틀</td>		
	</tr>	
</table>
</div>
<br><br>
	


<!-- 실질적인 qna게시판  -->
<div align="center">
<table >
<!-- (게시물번호)(태그)(좋아요)(답변수)(작성자정보) -->
<col width="50"><col width="300"><col width="50"><col width="50"><col width="100">
	<%
	if(qnalist == null || qnalist.size() == 0){
	
	%>	
	<tr>
		<td colspan="5">작성된 글이 없습니다.</td>
	</tr>
	<%
	}
	for(int i = 0; i<qnalist.size(); i++){
		QnaDto qna = qnalist.get(i);		
	%>
	
	<tr>
		<td>번호 :<%=qna.getRef() %></td>
		<td>테그 : <%=qna.getTag() %></td>
		<td rowspan="2" style="border-bottom: 1px solid #444444; ">좋아요 : <%=qna.getFavor() %></td>
		<td rowspan="2" style="border-bottom: 1px solid #444444; ">답변수 : <%=qna.getAnswercount() %></td>
		<td rowspan="2" style="border-bottom: 1px solid #444444; ">작성자정보 : <%=qna.getId() %> </td>		
	</tr>
	<tr>
		<td colspan="2" style="border-bottom: 1px solid #444444; ">
			게시물 타이틀 : 
			<%-- <a href="bbsdetail.jsp?seq=<%=qna.getSeq() %>"> --%>
			<a href="qnabbsdetail.jsp?seq=<%=qna.getSeq() %>">
			 <%=qna.getTitle() %>
			</a>
		</td>		
	</tr>	
	
	<%
	}
	%>
	
	
</table>
 
<jsp:include page="paging.jsp">
	<jsp:param name="actionPath" value="qnabbslist.jsp"/>
	<jsp:param name="nowPage" value="<%=String.valueOf(paging.getNowPage()) %>" />
	<jsp:param name="totalCount" value="<%=String.valueOf(paging.getTotalCount()) %>" />
	<jsp:param name="countPerPage" value="<%=String.valueOf(paging.getCountPerPage()) %>" />
	<jsp:param name="blockCount" value="<%=String.valueOf(paging.getBlockCount()) %>" />
</jsp:include> 


</div>

</div>



</body>
</html>