<%@page import="java.util.List"%>
<%@page import="techpds.PdsDto"%>
<%@page import="techpds.PdsService"%>
<%@page import="techpds.PdsServiceImpl"%>
<%@page import="user.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="_write.css?ver=1.40">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	 <script type="text/javascript">
	 function middle() {
		 var obj=$(".fileload").val();
		if(obj==null){
			return;
		}
		document.getElementById('pdscontrol').submit();	
	
	}
	 function gotop() {
		 
		 opener.document.getElementById("pInput").value = document.getElementById("cInput").value;
		 window.close();
		}
	 
	 </script>
</head>
<body>

<%
UserDto mem = (UserDto)session.getAttribute("login");
PdsServiceImpl pservice=PdsService.getInstance();
PdsDto pdsdto=null;
List<PdsDto> pdslist=null;
%>
    
 
	<form action="pdsupload.jsp" method="post" enctype="multipart/form-data" id="pdscontrol">
	 	<input type="file" name="fileload" style="width: 400px" class="fileload">
	 	  <input type="hidden" name="id2" value="<%=mem.getId()%>">
	 	   <input type="button" class="btns" value="파일올리기" onclick="middle()">	
	 </form>
	 <input type="text" value="첨부된 파일 " readonly="readonly" class="form-control">
	  <textarea cols="5" rows="7" id="cInput" readonly="readonly" class="form-control"></textarea><br><br>
	  <div id="pdsfiles"></div>
	 <input type="button" value="첨부하기" onclick="gotop()">


<%
PdsDto dto11= (PdsDto)request.getAttribute("pdsdto11");
String command11=request.getParameter("command11");
if(command11==null){
	
}else{
	
}
if(command11!=null&&command11.equals("faill")){
%>
<script type="text/javascript">
alert("파일을 선택해주세요");
</script>
<%
}else{
	
}
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
var aa=document.getElementById("cInput").value+" - ";
$("#cInput").val(aa+"<%=pdslist.get(0).getFilename()%>\n");
</script>   
<%
}else if(pdslist.size()>=1&&dto11!=null&&pdslist!=null){
	 
	 for(int i=0;i<pdslist.size();i++){
		 PdsDto dto22=pdslist.get(i);
		 System.out.println("황준현22"+dto22.getFilename());
%>
<script type="text/javascript">
var aa=document.getElementById("cInput").value+" - ";
$("#cInput").val(aa+"<%=pdslist.get(i).getFilename()%>\n");
</script>  
<%
}
}
%> 
</body>
</html>