<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
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

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<link rel="stylesheet" type="text/css" href="_techwrite.css?ver=1.58">
<link rel="stylesheet" type="text/css" href="_main.css?ver=1.33">
<!-- 폰트  -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<script type="text/javascript">
    
        var openWin;
    
        function openChild()
        {
            // window.name = "부모창 이름"; 
            window.name = "parentForm";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("pdsChild.jsp",
                    "childForm", "location=0, width=570, height=350, resizable = no, scrollbars = no");    
        }
        
        function gotobbs() {
			location.href="TechbbsController?command=techbbs";
		}
 
   </script>
</head>
<body bgcolor="#fcfbfb">
<script type="text/javascript">
	 function write1() {
			document.getElementById('pdsjsp').submit();
		}
	 </script>
<script type="text/javascript">
history.pushState(null, document.title, location.href); window.addEventListener('popstate', function(event) { history.pushState(null, document.title, location.href); });
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

$('input[type="text"]').keydown(function() {
    if (event.keyCode === 13) {
        event.preventDefault();
    }
});
$('#tagString').keydown(function() {
    if (event.keyCode === 13) {
    	eventonblur();
    }
});
function checkSpace(str) { if(str.search(/\s/) != -1) { return true; } else { return false; } };

function doSelect(elem) {
	var deletspan=$(elem).attr('name');
	$("#"+deletspan).remove();
	$(elem).remove();
}

$("#pdsup").click(function() {
	document.getElementById('pdscontrol').submit();
});

</script>
<%
Object ologin = session.getAttribute("login");
UserDto mem = null;
mem = (UserDto)ologin;
PdsDto pdsdto=null;
List<PdsDto> pdslist=null;
IUserService service = UserService.getInstance();
String yn="";
String profile = null;
int mainscore=0;
String maingetprofile="";

if(ologin != null){
	profile = service.getProfile(mem.getId());
	mainscore=service.getScore(mem.getId());
	maingetprofile=service.getProfile(mem.getId());
}
String userID = null;
   if(session.getAttribute("userID") != null){
      userID = (String)session.getAttribute("userID");
   }
%>


	<!-- 인클루드 부분 -->
	<div class="menu">
			<%
if(ologin == null){	//로그인안한상태
	yn="no";
	%>
	<input type="button" class="homebtn" onclick="location.href='index.jsp'">
	<input type="button" class="login" id="login">
	<input type="button" class="account" id="account">

		<%
}else{
	yn="yes";
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
		$(function() {//좌측 메뉴바 누르는 곳.
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
				if(<%=yn.equals("yes")%>){
					location.href = "CommunityControl?command=list";
		
				}
				else{
					location.href = "User?command=guest";
				}
				
			});

		});
	</script>
	<div class="titlediv"><h2>새글쓰기</h2><br>
	</div>
<div class="wrap">
	
	
	 <form action="TechbbsController" method="post" id="pdsjsp">
	 <div class="myinfo">
	 	
	 	<p class="myinfo_icon" style="margin-bottom: 3px;">
      <a onclick="upmydetail()" style="cursor: pointer">
      <img src="<%=maingetprofile %>" class="media-object img-circle" style="max-width: 50px; float:left; max-height: 50px; margin: 0 auto;">
      </a>
      <span class="detailid">
      <a onclick="upmydetail()" style="cursor: pointer; margin-left: 20px;"><%=mem.getId() %></a>
      </span>
</p>
<span class="" style="display: inline-block;"><img src="image/actionpoint.PNG" class="pointimg"></span>
      <span style="display: inline-block;"><%=mainscore%></span>
		 <input type="hidden" name="id" value="<%=mem.getId()%>">
		 <input type="hidden" name="command" value="techwriteAf">
	 </div>
	 
	 <div class="writearea">
	 	<input type="text" name="title" value="" placeholder="제목을 입력해 주세요." class="form-control" id="title" /><br>
	 	 <input type="text" name="tagString" value="" placeholder="Tags," class="form-control" id="tagString" onblur="eventonblur()" /><br>
	 	<div id="tagdiv">
	 		</div>
	 	<textarea name="content" id="summernote"></textarea><br>
		 <input type="button" class="btn btn-default btn-wide" onclick="gotobbs();" value="취소">
	<button id="write" style="float: right" class="btn btn-success btn-wide" onclick="write1();">글추가</button>
		</div>
	
	 </form>
	 <div class="height"></div>
	 
</div>

<div class="upload">
	  <input type="button" class="btn btn-primary" value="첨부파일선택" onclick="openChild()"><br>
	  <input type="text" value="첨부된 파일 " readonly="readonly" class="form-control">
	 <textarea cols="5" rows="10" id="pInput" readonly="readonly" class="form-control"></textarea>
	
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
$("#addya").val(aa+" - "+"<%=pdslist.get(0).getFilename()%>"+" ");	
</script>   
<%
}else if(pdslist.size()>=1&&dto11!=null&&pdslist!=null){
	 
	 for(int i=0;i<pdslist.size();i++){
		 PdsDto dto22=pdslist.get(i);
		 System.out.println("황준현22"+dto22.getFilename());
%>
<script type="text/javascript">
   

var aa=document.getElementById("addya").value;
$("#addya").val(aa+" - "+"<%=pdslist.get(i).getFilename()%>"+" ");
</script>  
<%
}
}
%> 
<script type="text/javascript">

$('input[type="text"]').keydown(function() {
    if (event.keyCode === 13) {
        event.preventDefault();
    }
});
$('#tagString').keydown(function() {
    if (event.keyCode === 13) {
    	eventonblur();
    }
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
    if(obj==""){
    	return;
    }
	$("#tagString").val("");						//input tag text field비워주기
	var span = document.createElement("span")		//input 밑에 span(id=입력한이름)태그생성
	var jbBtnText = document.createTextNode(" "+ obj+" " );
	var inputNode = createInputElement('hidden','tagnames',obj);
	span.style.background = "#5bc1de";
	span.style.display = "inline-block";
	span.style.border=" 3px solid #fff";
	span.id = obj;
	var button = document.createElement("img")	//span태그옆에 취소버튼(name=cancel)
	var BtnText = document.createTextNode( 'X' );
	button.name=obj;
	button.src="image/x.PNG"
		button.style.cursor="pointer";
	button.onclick = function(){
	doSelect(this);
	}
	button.style.background = "#5bc1de";
	button.style.color = "#fff";
	span.appendChild( jbBtnText );
	span.appendChild( inputNode );
	span.appendChild( button );
	button.appendChild( BtnText );
	$("#tagdiv").append(span);						
	
  }


</script>  
<script>
      $(function() {
         // initialize popover with dynamic content
         $('#btnPopover').popover({
            placement: 'right',
            container: 'body',
            html: true,
            trigger: 'hover',
            content: '<button onclick="logout()" type="button" class="btn btn-default popover-dismiss">logout</button><button onclick="upmydetail()" type="button" class="btn btn-default popover-dismiss">MY페이지</button>'
         });
         // prevent popover from being hidden on mouseout.
         // only dismiss when explicity clicked (e.g. has .hide-popover)
         $('#btnPopover').on('hide.bs.popover', function(evt) {
            if(!$(evt.target).hasClass('hide-popover')) {
               evt.preventDefault();
               evt.stopPropagation();
               evt.cancelBubble = true;
            }
         });
         // reset helper class when dismissed
         $('#btnPopover').on('hidden.bs.popover', function(evt) {
            $(this).removeClass('hide-popover');
         });
         $('body').on('click', '.popover-dismiss', function() {
            // add helper class to force dismissal
            $('#btnPopover').addClass('hide-popover');
            // call method to hide popover
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
          
          //set flags when mouse enters the button or the popover.
          //When the mouse leaves unset immediately, wait a second (to allow the mouse to enter again or enter the other) and then test to see if the mouse is no longer over either. If not, close popover.
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
	function upmydetail() {
		location.href ="User?command=mypage";
	}
	</script>
</body>
</html>