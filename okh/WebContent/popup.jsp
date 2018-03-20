<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<title>Insert title here</title>
	<style type="text/css">
	img{
	display: inline-block;
	}
	</style>
</head>
<body>
	<p style="text-align: center"><img alt="" src="image/khlogo.png"></p>
	<img alt="" src="image/pop1.PNG" id="popimg">
	<p><input type="checkbox">오늘 하루 이창안보기 <span id="popcancel" style="cursor: pointer">X</span></p>
	
	<script type="text/javascript">
	var timeid;
	timeid=setTimeout("changepop2()",3000);
	function changepop1() {
		$("#popimg").attr('src','image/pop1.PNG');
		clearTimeout(timeid);
		 timeid=setTimeout("changepop2()",3000);
	}
	function changepop2() {
		$("#popimg").attr('src','image/pop2.PNG');
		 clearTimeout(timeid);
		 timeid=setTimeout("changepop3()",3000);
	}
	function changepop3() {
		$("#popimg").attr('src','image/pop3.PNG');
		clearTimeout(timeid);
		 timeid=setTimeout("changepop1()",3000);
	}
	
	$("#popcancel").click(function () {
		widow.close();
	});
	</script>
	
</body>
</html>