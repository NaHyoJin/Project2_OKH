<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- include용 -->
    
	<%
	String actionPath = request.getParameter("actionPath"); // bbslist.jsp
	
	String sNowPage = request.getParameter("nowPage"); // 현재 페이지. 누른 현재 페이지. 어떤거 누를지 모른다.
	String sTotalCount = request.getParameter("totalCount"); // 총 글수
	String sCountPerPage =  request.getParameter("countPerPage"); // 10. 한 화면에 보여줄 글 갯수.
	String sBlockCount = request.getParameter("blockCount");	// 10개로 지금 설정해놨다.
	
	//확인 코드 잘 넘어오나 확인 부분.
	System.out.println("paging.jsp sNowPage:" + sNowPage);
	System.out.println("paging.jsp sTotalCount:" + sTotalCount);
	System.out.println("paging.jsp sCountPerPage:" + sCountPerPage);
	System.out.println("paging.jsp sBlockCount:" + sBlockCount);
	
	int nowPage = (sNowPage==null||sNowPage.trim().equals(""))?1:Integer.parseInt(sNowPage);
	int totalCount = (sTotalCount==null||sTotalCount.trim().equals(""))?0:Integer.parseInt(sTotalCount);
	int countPerPage = (sCountPerPage==null||sCountPerPage.trim().equals(""))?1:Integer.parseInt(sCountPerPage);
	int countPerBlock = (sBlockCount==null||sBlockCount.trim().equals(""))?1:Integer.parseInt(sBlockCount);
	
	// 페이지 수를 취득
	int totalPage = (int)((totalCount - 1) / countPerPage) + 1;
	/*
								23
								(23 - 1) / 10
								 22 / 10 -> 2 + 1
			3					 
	*/
	/*
		만약에 글이 23개라면.
				(23 - 1) / 10
					22 / 10		--> 2 + 1
					총 페이지 수 3.
	*/
	System.out.println("totalPage:" + totalPage);
	
	// block의 갯수를 취득 ([1][2][3][4][5]~[10]) == 0 ([11][12]) == 1	버튼 누르는것.
	int totalBlock = (int)((totalPage - 1) / countPerBlock);//totalBlock 사용처 맨 마지막의 숫자를 표현하기 위한 변수
	/*						[1][2][3]
				0				3 - 1 / 10
							[1] ~ [14]
				1				14 - 1 / 10	
							[1] ~ [26]
				2				26 - 1 / 10								
				
				14		[1] ~ [10]		[11] ~ [14] 
	*/
	int nowBlock = (int)((nowPage - 1) / countPerBlock);
	/*
			 ([1][2][3][4][5]~[10]) == 0
			 ([11][12][13][14][15]~[20]) == 1 
			 
			1 =				(11	- 1) /	 10
			[11] ~ [14]
	*/
	
	int firstPage = 0;//맨처음 페이지 0값
	int prevPage = 0;
	int nextPage = 0;
	int lastPage = 0;
	
	// [첫번째 페이지][이전 페이지][1][2][3][다음 페이지][끝 페이지]
	
	if(nowBlock > 0){	// [첫번째 페이지]
		firstPage = 1;
	}
	if(nowPage > 1){	// [이전 페이지]
		prevPage = nowPage - 1;
	}
	if(nextPage == 1){
		prevPage = 1;
	}
	
	
	int startPage = nowBlock * countPerBlock + 1;
	//		11	 =		1	* 10 + 1
	int endPage = countPerBlock * (nowBlock + 1);
	//		20	 =		10			 1	
	//	([1][2][3][4][5]~[10]) == 0	<- nowBlock
	//	([11][12][13][14][15]~[20]) == 1 <- nowBlock
	
	/*
		nowBlock ([1] [2] [3] [4] ~ [10]) == 0 nowBlock의 수치. 이런식으로 구하는것이다.
		
		
		totalBlock 과 nowBlock 혼동 주의.
	*/
	
	if(endPage > totalPage){	// endPage == 20
		endPage = totalPage;	// ([1][2][3][4][5]~[14]) 14  
		//맨 끝 페이지 숫자 endPage 넣는 부분. 다나와 페이지 오류 부분.
	}
	
	if(nowPage < totalPage){	// [1][2][3][4][5]
		nextPage = nowPage + 1;
	}
	
	if(nowBlock < totalBlock){	// 0(1 ~ 10) 1(11 ~ 20) 2
		lastPage = totalPage;
	}
	// 14
	// [첫번째페이지][이전페이지][1][2]~[10][다음페이지][끝페이지]
	//		10 -> 4 -> 10
	//			  4 -> 14	
	
	System.out.println("totalBlock:" + totalBlock);
	System.out.println("nowBlock:" + nowBlock);	// 0, 1, 2
	
	%>


<script>
	function gotoPage(pageNum) {
		var objForm = document.frmPaging;
		objForm.nowPage.value = pageNum;
		objForm.submit();	// form의 Action이 실행되도록한다.
		//클릭했을때 들어오는것. form의 Action이 실행되도록 한다.
	}
</script>

<form name="frmPaging" method="get" action="<%=actionPath %>">
	<input type="hidden" name="nowPage"><!-- 숨겨서 보내는 부분. -->

	<div align="center">
<%-- 		
		<a href="#" onclick="gotoPage('<%=firstPage %>')" style="text-decoration: none">[처음페이지]</a>
	
		<%if(prevPage > 0){ %>
			<a href="#" onclick="gotoPage('<%=prevPage %>')">[이전]</a>
		<%
			}else{
		%>
			[이전]
		<%
			}
		%>
윤필용 코드--%>

		
		<%
			if(nowPage != 1){ //맨처음 페이지면 처음 페이징하고 이전 페이징 안보이게 하는 부분.
		%>
		
	<a href="#" onclick="gotoPage('<%=firstPage %>')" style="text-decoration: none">[처음 페이지]</a>
		
		
	<a style="text-decoration: none" href="#" onclick="gotoPage('<%=prevPage %>')">[이전]</a>
		<%
			}
		%>
		
		
		<%	// [1] 2 [3]
		for(int i = startPage; i <= endPage; i++){			
			if(i == nowPage){
				%>
				<%=i %>
				<%
			}else{
				%>
				<a href="#" onclick="gotoPage('<%=i %>')">[<%=i %>]</a>
				<%
			}			
		}		
		%>
		<a href="#" onclick="gotoPage('<%=nextPage %>')">[다음]</a>
		
		<a href="#" onclick="gotoPage('<%=endPage %>')">[끝페이지]</a>
										<!-- totalPage -->
	</div>

</form>