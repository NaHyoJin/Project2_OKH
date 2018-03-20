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
	
<!-- 쿠키용 -->
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">

function setCookie(name, value, expiredays) {
var today = new Date();
    today.setTime(today.getTime() + expiredays);

    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';'
}

function closePop() {        
if(document.forms[0].todayPop.checked)                
setCookie('okhpop', 'rangs', 1*300*1000);
self.close();
}
</script>
	
</head>
<body>
	<p style="text-align: center"><img alt="" src="image/khlogo.png"></p>
	<img alt="" style="cursor: pointer" src="image/pop1.PNG" id="popimg" onclick="javascript:void(window.open('http://www.iei.or.kr/main/main.kh?src=overture&kw=003DFA&gclid=CjwKCAjw4sLVBRAlEiwASblR-zr9uAqeH584n01fZZpTL1CxpR9BFmzG-2O5rAaYAsMYQ4sxsmoythoCuvMQAvD_BwE', '_blank'))">
<form>
<input type="checkbox" name="todayPop" onClick="closePop()">
오늘 하루 그만보기
</form>
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
	function nosee(elem) {
		if (elem.checked==true) {	//팝업안보겠다	쿠키생성
			total=total+arr[n];
		}
		else{						//팝업보겠다	쿠키제거
			total=total-arr[n];
		}
	}
	</script>
	
</body>
</html>