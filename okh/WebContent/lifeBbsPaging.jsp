<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
    
<%
String findWord = request.getParameter("findWord");
String choice = request.getParameter("choice");
System.out.println("findWord in paging = " + findWord);
%>
<%
String actionPath = request.getParameter("actionPath");			// bbslist.jsp

String sNowPage = request.getParameter("nowPage");				// 현재 페이지
String sTotalCount = request.getParameter("totalCount");		// 총 글의 갯수
String sCountPerPage = request.getParameter("countPerPage");	// 10
String sBlockCount = request.getParameter("blockCount");		// 10

System.out.println("actionPath : " + actionPath);
System.out.println("sNowPage : " + sNowPage);
System.out.println("sTotalCount : " + sTotalCount);
System.out.println("sCountPerPage : " + sCountPerPage);
System.out.println("sBlockCount : " + sBlockCount);

int nowPage = (sNowPage == null || sNowPage.trim().equals(""))?1:Integer.parseInt(sNowPage);
int TotalCount = (sTotalCount == null || sTotalCount.trim().equals(""))?0:Integer.parseInt(sTotalCount);
int CountPerPage = (sCountPerPage == null || sCountPerPage.trim().equals(""))?1:Integer.parseInt(sCountPerPage);
int countPerBlock = (sBlockCount == null || sBlockCount.trim().equals(""))?1:Integer.parseInt(sBlockCount);

// 페이지 수를 취득
int totalPage = (int)((TotalCount - 1) / CountPerPage) + 1;
/*
						총 글의 숫자  :	23
									(23 - 1) / 10
									 22 / 10  --> 2 + 1 --> 현재 페이지 : 3
*/

System.out.println("totalPage : " + totalPage);

// block의 갯수를 취득 ([1] [2] [3] [4] [5] ~ [10]) == 0 ([11] [12]) == 1
int totalBlock = (int)((totalPage - 1) / countPerBlock);
/*
						[1] [2] [3]
								 3 - 1 / 10				==	0
						[1] ~ [14]
								14 - 1 / 10				==	1
						[1] ~ [26]
								26 - 1 / 10				==	2
*/

int nowBlock = (int)((nowPage - 1) / countPerBlock);
/*
						11	  - 1  /		10			==	1
	[11] ~ [14]
*/

int firstPage = 0;
int prevPage = 0;
int nextPage = 0;
int lastPage = 0;

// [첫번째페이지][이전페이지][1][2][3][다음페이지][끝페이지]

if(nowBlock > 0){		// 첫번째페이지
	firstPage = 1;
}
if(nowPage > 1){		// 이전페이지
	prevPage = nowPage - 1;
}

int startPage = (nowBlock * countPerBlock) + 1;
//		11	  =		1	  *		10		   + 1

int endPage = countPerBlock * (nowBlock + 1);
//		20	=		10		*	1		+ 1
// ([1][2][3][4][5] ~ [10]) == 0	<-	nowBlock
// ([11][12][13][14] ~ [20]) == 1	<- nowBlock

if(endPage > totalPage){						// endPage == 20
	endPage = totalPage;						// ([1][2][3][4][5] ~ [14]) 14
}

if(nowPage < totalPage){						// [1][2][3][4][5]
	nextPage = nowPage + 1;
}

if(nowBlock < totalBlock){						// 0(1~10) 1(11~20) 2(21~30)
	lastPage = totalPage;						// 끝페이지
}
// 총 14페이지
// [첫번째페이지][이전페이지][1][2][3]~[14][다음페이지][끝페이지]
// 끝페이지를 누르면 10으로 가도록 하거나 14페이지로 가는 두 가지 경우가 존재함
// 		10 -> 4 -> 10  지금은 이 방법
//			  4 -> 14

System.out.println("totalBlock : " + totalBlock);
System.out.println("nowBlock : " + nowBlock);	// 0, 1, 2
%>

<script>
function gotoPage(pageNum) {
	var objForm = document.frmPaging;
	objForm.nowPage.value = pageNum;
	objForm.submit();							// form의 Action이 실행되도록 한다.
}
</script>

<form name="frmPaging" method="get" action="<%=actionPath %>">
	<input type="hidden" name="findWord" value="<%=findWord %>">
	<input type="hidden" name="choice" value="<%=choice %>">
	<input type="hidden" name="nowPage">
	
	<div align="center">
		<ul class="pagination pagination-sm">
			<li class="step">
				<a href="#" onclick="gotoPage('<%=firstPage %>')">첫페이지</a>
			</li>
			<%
			if(prevPage > 0){
			%>
			<li class="step">
				<a href="#" onclick="gotoPage('<%=prevPage %>')">&lt;</a>
			</li>
			<%
			}else{
			%>
			<li class="step">
				<a href="javascript:void(0)" >&lt;</a>
			</li>
			<%
			}
			%>	
			<%	// [1][2][3]
			for(int i = startPage; i <= endPage; i++){
				if(i == nowPage){
			%>
			<li class="active">
				<a href="#" onclick="gotoPage('<%=i %>')"><span><%=i %></span></a>
			</li>
				<%	
				}else{
				%>
			<li class="step">
				<a href="#" onclick="gotoPage('<%=i %>')"><span><%=i %></span></a>
			</li>
			<%
				}
			}
			if(nowPage != endPage){
			%>
			<li class="step">
				<a href="#" onclick="gotoPage('<%=nextPage %>')">&gt;</a>
			</li>
			<%
			}else{
			%>
				<li class="step">
					<a href="javascript:void(0)" >&gt;</a>
				</li>
			<%
			}
			%>
			<%-- <a href="#" onclick="gotoPage('<%=endPage %>')">끝페이지</a> --%>			<!-- 10페이지 -->
			<li class="step">
		 		<a href="#" onclick="gotoPage('<%=totalPage %>')">끝페이지</a>		<!-- 14페이지 -->
		 	</li>
		</ul>
	</div>
</form>