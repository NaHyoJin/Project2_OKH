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
   
   
<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../../tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" type="text/css">
<script src="../../tagsinput/js/bootstrap-tagsinput.min.js"></script>
   



 
<title>Insert title here</title>
</head>
<body>
<script>
    $('#tagPlaces').tagsinput({
        allowDuplicates: true
    });
</script>
<input type="text" id="cdate">
<input type="button" value="선택" onclick="setParentText()">

<div class="form-group">
        <label for="tagPlaces" class="col-sm-2 control-label">Tag Places</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" data-role="tagsinput" id="tagPlaces">
        </div>
</div>
<script type="text/javascript">
$(function() {
    $( "#cdate" ).datepicker({
    	 dateFormat: "yymmdd"
    });
});
</script>
</body>
</html>