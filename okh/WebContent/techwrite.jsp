<%@page import="user.UserDto"%>
<%@page import="techpds.PdsService"%>
<%@page import="techpds.PdsServiceImpl"%>
<%@page import="techpds.PdsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="_write.css?ver=1">
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
PdsDto pdsdto=null;
List<PdsDto> pdslist=null;
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
			
	 
			//게시판5 나효진 jobs 부분.			
			$("#jobs").click(function name() {
				location.href="jobs";
			});

		});
	</script>
<div class="wrap">
	<h2>새글쓰기</h2>
	<div class="writeform">
	 <form action="TechbbsController" method="post">
	 	<table class="techinput">
	 		<tr>
	 		<td>
	 			 <p id="test" align="left"><%=mem.getId() %></p>
	 			 <input type="hidden" name="id" value="<%=mem.getId()%>">
	 			 <input type="hidden" name="command" value="techwriteAf">
	 		</td>
	 		</tr>
	 		<tr>
	 		<td>
	 			 <input type="text" name="title" value="" placeholder="제목을 입력해 주세요." class="form-control" id="title" />
	 		</td>
	 		</tr>
	 		<tr>
	 		<td>
	 			 <input type="text" name="tagString" value="" placeholder="Tags," class="form-control" id="tagString" onblur="eventonblur()" />
	 		<div id="tagdiv">
	 		</div>
	 		</td>
	 		</tr>
	 		<tr>
	 		<td>
	 			<textarea name="content" id="summernote"></textarea>
	 		</td>
	 		</tr>
	    	<tr>
	 		<td>
	 			<input type="button" id="cancel" value="취소">
	 			 <input type="submit" value="글올리기" >
	 		</td>
	 		</tr>
			
	     
	     </table>
	 </form>
	 <form action="pdsupload.jsp" method="post" enctype="multipart/form-data">
	 <input type="text" id="addya" readonly="readonly" value="첨부파일" class="form-control">
	 	<input type="file" name="fileload" style="width: 400px">
	 	  <input type="hidden" name="id2" value="<%=mem.getId()%>">
	 	 <input type="submit" id="btn" value="파일올리기" >
	 </form>
	 </div>

</div>  
<%
PdsServiceImpl pservice=PdsService.getInstance();

PdsDto dto11= (PdsDto)request.getAttribute("pdsdto11");
String command11=request.getParameter("command11");
if(dto11!=null&&command11!=null&&command11.equals("addafter")){
	System.out.println(command11+"황준현"+dto11.getParent());
	System.out.println("리스트생성");
	 pdslist=pservice.getpdsList(dto11.getParent());
}
%>

<%
if(dto11==null||pdslist==null){
	System.out.println("같은번호없음");
}else if(pdslist.size()<1&&dto11!=null&&pdslist!=null){
	System.out.println("황준현11");
%>
<script type="text/javascript">
var aa=document.getElementById("addya").value;
$("#addya").val(aa+"<%=dto11.getFilename()%>");	
</script>   
<%
}else if(pdslist.size()>=1&&dto11!=null&&pdslist!=null){
	 
	 for(int i=0;i<pdslist.size();i++){
		 PdsDto dto22=pdslist.get(i);
		 System.out.println("황준현22"+dto22.getFilename());
%>
<script type="text/javascript">
var aa=document.getElementById("addya").value;
$("#addya").val(aa+"<%=dto22.getFilename()%>");	
</script>  
<%
}
}
%> 
<script type="text/javascript">

		// 뒤로가기 버튼 방지 
		history.pushState(null, null, "#noback");
		$(window).bind("hashchange", function(){
  		history.pushState(null, null, "#noback");
		});
		
		// F5, ctrl + F5, ctrl + r 새로고침 막기
		$(document).keydown(function (e) {
		     
		            if (e.which === 116) {
		                if (typeof event == "object") {
		                    event.keyCode = 0;
		                }
		                return false;
		            } else if (e.which === 82 && e.ctrlKey) {
		                return false;
		            }
		});  
$(document).ready(function(){
    $("#tagString").keypress(function (e) {
     if (e.which == 13){
    	 eventonblur();  // 실행할 이벤트
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
	button.name="can"
	span.appendChild( jbBtnText );
	span.appendChild( inputNode );
	span.appendChild( button );
	button.appendChild( BtnText );
	$("#tagdiv").append(span);						
	
  }
  
$(document).ready(function() {
    $('#summernote').summernote({
            height: 300,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
    });
});
$(document).ready(function() {
  $('#summernote').summernote();
});
</script>  
<!-- var formData = new FormData(); 
	formData.append("id", $("input[name=id2]").val()); 
	formData.append("fileload", $("input[name=fileload]")[0].files[0]); 
	$.ajax({ 
		url: 'pdsupload.jsp', 
		data: formData, 
		processData: false, 
		contentType: false, 
		type: 'POST', 
		success: function(data){ 
			alert("추가되었습니다"); 
			
			}
		}); 
	});  -->
</body>
</html>