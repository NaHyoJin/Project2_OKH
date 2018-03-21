<%@page import="user.UserDto"%>
<%@page import="studysrc.CombbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="_studymain.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

<link rel="stylesheet" href="css/bootstrap-tagsinput.css">
<script src="js/bootstrap-tagsinput.js"></script>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="_Studybbs.css?ver=1.59">
<link rel="stylesheet" type="text/css" href="_studyupdate.css?ver=1.33">
<link rel="stylesheet" type="text/css" href="_studymain.css?ver=1.3">



</head>
<body>
<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
mem = (UserDto)ologin;
%>

	<!-- 인클루드 부분 -->
	<div class="menu">
				<%
if(ologin == null){	//로그인안한상태
	%>
	<input type="button" class="login" id="login">
	<input type="button" class="account" id="account">

		<%
}else{
	
%>
<div class="actionlogin">
	<span><%=mem.getId() %></span>
	<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
	<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover1">	
</div>
<%
}
%>
		<input type="button" class="bbs1" id="qnabbs">
		<input type="button" class="techbbs_hjh" id="techbbs">
		<input type="button" class="bbs3" ><!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs">
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
			$("#combbs").click(function() {
				location.href = "CommunityControl?command=list";
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
	
	<%
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
String sseq = request.getParameter("seq");
int seq=Integer.parseInt(sseq);
List<CombbsDto> list=(List<CombbsDto>)request.getAttribute("list");

System.out.println(list.get(0).getId());

%>
<div class="titlediv"><h2>글 수정</h2><br>
	</div>
	<div class="wrap">
	<form action="CommunityControl" method="post">
		<table>
			<col width="200"> <col width="500">
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" class="form-control" id="id" readonly="readonly" value="<%=mem.getId() %>" size="100">
					<input type="hidden" name="id" value="<%=mem.getId() %>">
	 				<input type="hidden" name="command" value="updateAF">
	 				<input type="hidden" name="seq" value="<%=seq %>">
				</td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td>
					<input type="text" class="form-control" placeholder="제목을 입력해 주세요." id="title" name="title" size="100" value="<%=list.get(0).getTitle() %>">
				</td>
			</tr>
			<tr>
				<td>날짜</td>
				<td>
					<input type="button" value="날짜선택 호출" onclick="opendate();" />
					<input type="text" id="pdate" name="date" readonly="readonly" value="<%=list.get(0).getJoindate() %>">
					<select name="hour">
				<option value="12"> 12
				<%
				
				for(int i = 0; i<24;i++){
					
				%>
					<option value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>시
			<select name="min">
				<option value="30"> 30
				<%
				
				for(int i = 0; i<60;i++){
					
				%>
					<option value="<%=i %>"><%=i %></option>
				<%
				}
				%>
			</select>분
				</td>
			</tr>
			<tr>
				<td>테그</td>
				<td>
					<input type="text" value="" data-role="tagsinput" name="tagnames" >
					
				</td>
			</tr>
			
			<tr>
				<td>내 용</td>
				<td>
					<textarea rows="10" cols="60" name="content" id="summernote" ><%=list.get(0).getContent() %></textarea>
				</td>
			</tr>
			<tr>
		 		<td> 
		 		<input type="submit" value="글올리기" class="btn btn-success btn-wide">
		 			<input type="button" id="cancel" class="btn btn-success btn-wide" onclick="cancel(<%=seq %>)" value="취소">
		 			
		 		</td>
	 		</tr>
			
		</table>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
     $('#summernote').summernote({
             height: 300,                 // set editor height
             minHeight: null,             // set minimum height of editor
             maxHeight: null,             // set maximum height of editor
             focus: true                  // set focus to editable area after initializing summernote
     });
   

    
   
     
     
});



$(document).ready(function(){
    $("#tagString").keypress(function (e) {
     if (e.which == 13){	//아스키코드 13 엔터 엔터를 누르면 eventonblur실행
    	 eventonblur()  // 실행할 이벤트
     }
 });
    
});
function createInputElement(type,name,value){
	 if(!type){type='';}
	 if(!name){name='';}
	 if(!value){value='';}
	 
	 var obj = null;
	 try{
	  obj = document.createElement('<input type="'+type+'" name="'+name+'" value="'+value+'" />');
	 }catch(e){
	  obj = document.createElement('input');
	  obj.type = type;
	  obj.name = name;
	  obj.value = value;
	 }
	 return obj
	}
function eventonblur() {
    var obj=$("#tagString").val();					//input tag text field에 값가져오기
	$("#tagString").val("");						//input tag text field비워주기
	var span = document.createElement("span")		//input 밑에 span(id=입력한이름)태그생성
	var jbBtnText = document.createTextNode( obj );
	var inputNode = createInputElement('hidden','tagnames',obj);
	span.style.background = "#ddeffb";
	span.style.display = "inline-block";
	span.id = obj;
	
	var button = document.createElement("button")	//span태그옆에 취소버튼(name=cancel)
	var BtnText = document.createTextNode( 'X' );
	button.name="cancel"
	span.appendChild( jbBtnText );
	span.appendChild( inputNode );
	span.appendChild( button );
	button.appendChild( BtnText );
	$("#tagdiv").append(span);						
	
	
	


	
	
  }
</script>

  <script type="text/javascript">
    
        var openWin;
    
        function opendate()
        {
            // window.name = "부모창 이름"; 
            window.name = "날짜선택";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("datepic.jsp",
                    "childForm", "width=570, height=350, resizable = no, scrollbars = no");    
        }
 
        
        
   </script>

<script type="text/javascript">
$(function () {
	$("#cancel").click(function () {
		location.href = "CommunityControl?command=detail&seq=<%=seq %>";
	});
	
});
</script>


</body>
</html>