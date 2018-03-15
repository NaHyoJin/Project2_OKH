<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 

<script type="text/javascript">
        function setParentText(){
             opener.document.getElementById("pdate").value = document.getElementById("cdate").value
             window.close();
        }
</script>
   
   



 
<title>Insert title here</title>
</head>
<body>
<input type="text" id="cdate">
<input type="button" value="선택" onclick="setParentText()">


<script type="text/javascript">
$(function() {
    $( "#cdate" ).datepicker({
    	 dateFormat: "yymmdd"
    });
});
</script>
</body>
</html>