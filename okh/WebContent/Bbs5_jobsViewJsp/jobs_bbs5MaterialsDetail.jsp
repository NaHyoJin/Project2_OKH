<%@page import="user.UserDto"%>
<%@page import="jobs_BBS5.BbsMaterialsBeanDtoVO"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDao"%>
<%@page import="jobs_BBS5.jobsBbs5MaterialsDaoImpl"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	//디테일 쪽으로 넘어온 시퀀스 번호 받는 부분.
    	String sseq = request.getParameter("seq");
    	int seq = Integer.parseInt(sseq);//문자열을 int형으로 변환 부분.
    	
    	jobsBbs5MaterialsDaoImpl dao = jobsBbs5MaterialsDao.getInstance();
    	BbsMaterialsBeanDtoVO pdsdto = dao.getPds(seq);//가지고 오는 부분.
    	
    	dao.readcount(seq);//조회수 부분.
    %>
    
    <%
    	//인간 정보 관련 세션 부분.
		Object ologin = session.getAttribute("login");
    UserDto mem = (UserDto)ologin;
  자료실 디테일 부분 작업해야함.
	%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jobs_bbs5MaterialsDetail.jsp</title>

	<style type="text/css">
		.center{
			margin: auto;
			width: 60%;
			border: 3px solid #8ac007;
			padding: 10px;
		}
		
		.logout{
			text-align: right;
			
		}
	</style>
	
</head>
<body>

<%--
	디테일로 넘어온 seq 번호 확인 부분 : <%=seq %>
 --%>
 
 	<a href="logout.jsp" style="text-decoration: none" class="logout">로그아웃</a>

	<h1>상세 파일 보기</h1>

	<div class="center">

		<table border="2">
		<col width="200"><col width="500">

		<tr>
			<td>작성자</td>
			<td><%=mem.getId() %></td>
		</tr>

		<tr>
			<td>제목</td>
			<td><%=pdsdto.getTitle() %></td>
		</tr>

		<tr>
			<td>작성일</td>
			<td><%=pdsdto.getRegdate() %></td>
		</tr>

		<tr>
			<td>조회수</td>
			<td><%=pdsdto.getReadcount() %></td>
		</tr>
<%-- 
		<tr>
			<td>정보</td>
			<td><%=bbs.getRef() %>-<%=bbs.getStep() %>-<%=bbs.getDepth() %></td>
		</tr>
 --%>
		<tr>
			<td>내용</td>
			<td>
			<textarea rows="10" cols="50"
			name="content"><%=pdsdto.getContent() %></textarea>
			</td>
		</tr>

		</table> 
<br>
<button type="button" onclick="mainpds()">자료실 처음으로</button>

<% 
	if(pdsdto.getId().equals(mem.getId())){ 
%>
<button type="button" 
	onclick="updatebbs('<%=pdsdto.getSeq() %>')">수정</button>
<button type="button"
	onclick="deletebbs('<%=pdsdto.getSeq() %>')">삭제</button>	
<%
	} 
%>

	</div>



<script type="text/javascript">

	//수정
	function updatebbs( seq ) {
		location.href = "pdsupdate.jsp?seq=" + seq;
	}
	
	
	function mainpds() {
		location.href = "pdslist.jsp";
	}

 	
 	//삭제
	function deletebbs( seq ) {
		location.href = "pdsdelete.jsp?seq=" + seq;
	}


</script> 	
 
 	
</body>
</html>