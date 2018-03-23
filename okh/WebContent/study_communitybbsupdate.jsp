<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="user.UserDto"%>
<%@page import="studysrc.CombbsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>study_communitybbsupdate.jsp</title>
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
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.34">



</head>
<body>
<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
mem = (UserDto)ologin;
IUserService service = UserService.getInstance();

String profile = null;
if(ologin != null){
	profile = service.getProfile(mem.getId());
}
int mainscore=service.getScore(mem.getId());
String maingetprofile=service.getProfile(mem.getId());
%>



<!-- 메뉴 -->
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
	<a onclick="upmydetail()" style="cursor: pointer">
			<img src="<%=maingetprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
		</a>
			<span class="memid"><a onclick="upmydetail()" style="cursor: pointer;color: #fff;"><%=mem.getId() %></a></span> <br>
				<span class="point"><img src="image/actionpoint.PNG" style="margin-top: 0" class="pointimg"><%=mainscore%></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
				
</div>
		<%
		}
		%>
		<input type="button" class="bbs1" id="qnabbs">				<!-- 박형태 -->
		<input type="button" class="techbbs_hjh" id="techbbs">		<!-- 황준현 -->
		<input type="button" class="bbs3" id="column">				<!-- 정재흥 -->
		<input type="button" class="bbs4" id="combbs">				<!-- 장문석 -->
		<input type="button" class="bbs5" id="jobs">				<!-- 나효진 -->
		<input type="button" class="bbs6" id="life">				<!-- 정병찬 -->
	</div>
	<script type="text/javascript">
	$(function() {
		$("#homebtn").click(function() {
			location.href="main.jsp";
		});
		$("#login").click(function() {
			location.href = "User?command=login";
		});
		$("#account").click(function() {
			location.href = "User?command=join";
		});
		$("#qnabbs").click(function() {
			location.href="qnaServlet?command=listQna";
		});	
		$("#techbbs").click(function() {
			location.href="TechbbsController?command=techbbs";
		});
		$("#column").click(function name() {
			location.href="Controller?command=column";
		});
		$("#jobs").click(function () {
			location.href = "jobs";
		});
		$("#life").click(function() {
			location.href = "LifeBbs?command=life";
		});
		$("#combbs").click(function () {
			location.href = "CommunityControl?command=list";
		});
	});
	</script>
<!-- modal -->
	<%
	String messageContent = null;
	if(session.getAttribute("messageContent") != null){
		messageContent = (String)session.getAttribute("messageContent");
	}
	String messageType = null;
	if(session.getAttribute("messageType") != null){
		messageType = (String)session.getAttribute("messageType");
	}
	if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div class="modal-content <% if(messageType.equals("오류 메시지")) out.println("panel-warning"); else out.println("panel-success"); %> ">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType.trim() %>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent.trim() %>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#messageModal').modal("show");
	</script>
	<%
	session.removeAttribute("messageContent");
	session.removeAttribute("messageType");
	}
	%>
<!-- 로그아웃, 정보수정 popover -->
	<script type="text/javascript">
	$(function() {
		$('#btnPopover').popover({
			placement: 'right',
			container: 'body',
			html: true,
			trigger: 'hover',
			content: '<p>설정</p><hr><button type="button" class="btn btn-default popover-dismiss" onclick="logout()">로그아웃</button><button type="button" class="btn btn-default popover-dismiss" onclick="mypage()">정보수정</button>'
		});
		$('#btnPopover').on('hide.bs.popover', function(evt) {
			if(!$(evt.target).hasClass('hide-popover')) {
				evt.preventDefault();
				evt.stopPropagation();
				evt.cancelBubble = true;
			}
		});
		$('#btnPopover').on('hidden.bs.popover', function(evt) {
			$(this).removeClass('hide-popover');
		});
		$('body').on('click', '.popover-dismiss', function() {
			$('#btnPopover').addClass('hide-popover');
			$('#btnPopover').popover('hide');
		});
      
      $('#btnPopover').data('overButton', false);
      $('#btnPopover').data('overPopover', false);
      $.fn.closePopover = function(){
        var $this = $(this);
        
        if(!$this.data('overPopover') && !$this.data('overButton')){
          $this.addClass('hide-popover');
          $this.popover('hide');              
        }
      }
      
      $('#btnPopover').on('mouseenter', function(evt){
        $(this).data('overButton', true);
      });
      $('#btnPopover').on('mouseleave', function(evt){
        var $btn = $(this);
        $btn.data('overButton', false);
        
        setTimeout(function() {$btn.closePopover();}, 200);
        
      });
      $('#btnPopover').on('shown.bs.popover', function () {
        var $btn = $(this);
        $('.popover-content').on('mouseenter', function (evt){
          $btn.data('overPopover', true);
        });
        $('.popover-content').on('mouseleave', function (evt){
          $btn.data('overPopover', false);
          
          setTimeout(function() {$btn.closePopover();}, 200);
        });
      });
    });
	</script>
	<script type="text/javascript">
	function logout() {
		location.href ="User?command=logout";
	}
	function mypage() {
		location.href ="User?command=mypage";
	}
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
				<td>태그</td>
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
		 			<input type="button" id="cancel" class="btn btn-default btn-wide" onclick="cancel(<%=seq %>)" value="취소">
		 			
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