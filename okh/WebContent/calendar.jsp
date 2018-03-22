<%@page import="user.UserDto"%>
<%@page import="studysrc.iCalendar"%>
<%@page import="user.UserDao"%>
<%@page import="java.util.Calendar"%>
<%@page import="studysrc.CalendarDao"%>
<%@page import="studysrc.CalendarDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calendar</title>
<style type="text/css">
  body {
    margin: 40px 10px;
    padding: 0;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    font-size: 14px;
  }
  tr{
  	
  }

</style>
</head>
<body>

<!-- 로그인 세션 -->
	<%
	Object ologin = session.getAttribute("login");
	UserDto mem = (UserDto)ologin;
	%>
<!-- 메뉴 -->
	<div class="menu">
		<%
		if(ologin == null){
		%>
		<input type="button" class="login" id="login">
		<input type="button" class="account" id="account">
		<%
		}else{
		%>
		<div class="actionlogin">
			<span><%=mem.getId() %></span>
			<img class="settingbtn" alt="" src="image/mainsetting.PNG" style="cursor: pointer" id="btnPopover">
			<img class="alarmbtn" alt="" src="image/alarm.PNG" style="cursor: pointer" id="btnPopover">	
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

<%!
	public boolean nvl(String msg){
	return msg == null || msg.trim().equals("")?true:false;
}

// 날짜를 클릭하면 그날의 일정을 모두 보이게 하는 calllist.jsp 로 이동시키는 함수
	public String calllist(int year, int month, int day){
		String s = "";
		
		
		s += String.format("%2d",day);	//1 2 ~ 9 10
		
		return s;
	}

//일정 기입하기위해서 pen이미지를 클릭하면, calwrite.jsp 로 이동
/* public String showPen(int year, int month, int day){
	String s = "";
	String url = "calwrite.jsp";
	String image = "<img src='image/pen.gif'>";
	
	s = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", url, year, month, day,image);
	
	return s;
	
} */
// 1글자를 2글자로 1 > 01
public String two(String msg){
	return msg.trim().length()<2?"0"+msg:msg.trim();
}
// 내용이 길면 도트3개로 처리 내용 + ...
public String dot3(String msg){
	String s = "";
	if(msg.length() >= 5){
		s = msg.substring(0, 5);
		s += "...";
		
	}else{
		s = msg.trim();
	}
	return s;
}

// 각 날짜별로 테이블을 생성하는 함수
public String makeTable(int year, int month, int day, List<CalendarDto> list){
	String s = "";
	String dates = (year+"")+two(month+"")+two(day+"");// 20180306
	s += "<table>";
	s += "<col width='98'>";
	
	for(CalendarDto dto : list){
		if(dto.getRdate().substring(0,8).equals(dates)){
			s += "<tr bgcolor='yellow'>";
			s += "<td>";
			s += "<a href='CommunityControl?command=detail&seq="+dto.getChild()+"&likeid="+dto.getId()+"'>";
			s += "<font style='font-size:8; color:red'>";
			s += "#"+dto.getChild();
			s += "</font>";
			s += "</a>";
			s += "</td>";
			s += "</tr>";
		}
	}
	s += "</table>";
	return s;
}
%>
<div align="center">
<h1>스케쥴</h1>
<%
	Calendar cal = Calendar.getInstance();
cal.set(Calendar.DATE, 1);
String syear = request.getParameter("year");
String smonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);
if(nvl(syear) == false){
	year = Integer.parseInt(syear);
}

int month = cal.get(Calendar.MONTH)+1;
if(!nvl(smonth)){
	month = Integer.parseInt(smonth);
	
}

if(month <1){
	month = 12;
	year--;
}

if(month>12){
	month = 1;
	year++;
}
cal.set(year, month-1, 1); 	//연 월 일 셋팅 완료

// 요일 1~7 일요일 1 토요일 7

int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

// << 연도
String pp = String.format("<a href='%s?year=%d&month=%d'><img src='image/left.gif'></a>",
							"calendar.jsp",year-1,month);
// < 월
String p = String.format("<a href='%s?year=%d&month=%d'><img src='image/prec.gif'></a>",
							"calendar.jsp",year,month-1);
// > 월
String n = String.format("<a href='%s?year=%d&month=%d'><img src='image/next.gif'></a>",
							"calendar.jsp",year,month+1);
// >> 연도
String nn = String.format("<a href='%s?year=%d&month=%d'><img src='image/last.gif'></a>",
							"calendar.jsp",year+1,month);

UserDto user = (UserDto)session.getAttribute("login");
iCalendar dao = CalendarDao.getInstance();

List<CalendarDto> list = dao.getCalendarList(user.getId(), year+two(month+""));

%>

<table border="1">
	<col width="100"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100">
	<tr height="80" bgcolor="gray">
		<td colspan="7" align="center">
			<%=pp %>&nbsp;&nbsp;<%=p %>&nbsp;
			<font color="black" style="font-size: 50">
				<%=String.format("%d년&nbsp;&nbsp;%s월", year,month) %>
			</font>
		&nbsp;<%=n %>&nbsp;&nbsp; <%=nn %>
		</td>
	</tr> 
	
	<tr height="80">
		<td align="center"> 일 </td>
		<td align="center"> 월 </td>
		<td align="center"> 화 </td>
		<td align="center"> 수 </td>
		<td align="center"> 목 </td>
		<td align="center"> 금 </td>
		<td align="center"> 토 </td>
	</tr>
	<tr height="80" align="left" valign="top">
	<%
	//위쪽의 빈칸
		for(int i=1;i<dayOfWeek;i++){
	%>
				<td bgcolor="#c6c6c6">&nbsp;</td>
	<% 
		}
	
	//실제날짜
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	for(int i = 1;i <= lastDay;i++){
	%>
		<td>
			<%=calllist(year, month, i)%> &nbsp; <%=makeTable(year, month, i, list) %>
		</td>
	<%
		if((i+dayOfWeek-1)%7==0 && i != lastDay){
			%>
				</tr><tr height="80"  align="left" valign="top">
			<%
		}
	}
	
	// 밑칸
	for(int i=0;i<(7-(dayOfWeek + lastDay - 1)%7)%7; i++){
		%>
		
		<td bgcolor="#c6c6c6">&nbsp;</td>
		<%
	}
	
	
	
	%>
	</tr>
</table>
</div>






</body>
</html>