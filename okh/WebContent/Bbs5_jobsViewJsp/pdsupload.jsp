<%@page import="jobs_BBS5.newbbs5HWCodingPDSVO"%>
<%@page import="jobs_BBS5.newbbs5HWCodingPDSService"%>
<%@page import="jobs_BBS5.newbbs5HWCodingPDSServiceImpl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pdsupload.jsp</title>
</head>
<body>
<%!
public String processUploadFile(FileItem fileItem, String dir, JspWriter out) 
		throws IOException{
	String filename = fileItem.getName();
	long sizeInBytes = fileItem.getSize();
	//업로드한파일이정상일때
	if(sizeInBytes>0){	//ex d:\\tmp\\abc.jpg(d:/tmp/abc.jpg) 
		int idx=filename.lastIndexOf("\\");		//마지막부터찾는거	파일명만가져오기
		if(idx==-1){
			idx=filename.lastIndexOf("/");
		}
		filename=filename.substring(idx+1);
		
		try{
			System.out.println("파일업로드 : " + filename);
		File uploadFile = new File(dir,filename);
		fileItem.write(uploadFile);	//실제업로드하는부분
		}catch(Exception e){}
	}
	return filename;
}
%>

<%
/*
업로드시주의사항	- 한글파일사용하려면 다른처리를해줘야함
			- 자료실에 올릴시에는 시간으로 파일명을하는경우가많다
			- 톰캣에 올릴때는 서버켰다껏다하면서 파일이사라질수있다.
*/ 

//지정폴더에저장
String fupload="E:\\tmp";	//물리적으로 삭제해야된다.

//톰캣에배포
//String fupload=application.getRealPath("/techupload");	//WebContent안의 폴더이름을정해준거다 여기에올라갈거다

System.out.println("파일업로드 : " + fupload);

String yourTempDirectory = fupload;
int yourMaxRequestSize = 1024 * 1024 * 1024;	//올릴수 있는 크기???
int yourMaxMemorySize = 100 * 1024;

//form field에있는 데이터(String)
String id = "";
//file data
String filename="";
String sseq="";
String update="";
String title="";
String content="";
String tagString="";
boolean isMultipart=ServletFileUpload.isMultipartContent(request);
if(isMultipart){
	////////////파일용
	
	//fileItem 오브젝트를 생성하는 클래스
	DiskFileItemFactory factory = new DiskFileItemFactory();
	factory.setSizeThreshold(yourMaxMemorySize);
	factory.setRepository(new File(yourTempDirectory));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setHeaderEncoding("utf-8");
	upload.setSizeMax(yourMaxRequestSize);	//파일업로드최대크기
	
	///////////////////////
	List<FileItem> items = upload.parseRequest(request);
	Iterator<FileItem> it=items.iterator();
	
	while(it.hasNext()){
		
		FileItem item = it.next();
		if(item.isFormField()){		//pdswrite에서받아올때 폼필드를받을때
			if(item.getFieldName().equals("id2")){	//id값이넘어온다
				id = item.getString("utf-8");
			}else if(item.getFieldName().equals("seq")){	//content값이넘어온다
				sseq = item.getString("utf-8");
			}else if(item.getFieldName().equals("update")){	//content값이넘어온다
				update=item.getString("utf-8");
			}else if(item.getFieldName().equals("title")){	//content값이넘어온다
				title=item.getString("utf-8");
			}else if(item.getFieldName().equals("tagString")){	//content값이넘어온다
				tagString=item.getString("utf-8");
			}else if(item.getFieldName().equals("content")){	//content값이넘어온다
				content=item.getString("utf-8");
			}
		}else{						//input의 file타입을받을때
			if(item.getFieldName().equals("fileload")){
				filename=processUploadFile(item, fupload, out);
			}
		System.out.println("filename : "+filename);
		}
		
	}
	
}else{
	//multipart type아님
	System.out.println("multipart type아님");
}
newbbs5HWCodingPDSServiceImpl pservice = newbbs5HWCodingPDSService.getInstance();
boolean isS;
int parent=0;
newbbs5HWCodingPDSVO dto1=null;
if(update.equals("update")){
	System.out.println("ghkdwn");
	int seq=Integer.parseInt(sseq);
	isS=pservice.pdsupdate(seq, filename);
}else{
	parent=pservice.getSeq();
	isS=pservice.writePds(new newbbs5HWCodingPDSVO(id,filename,(parent+1)));
	System.out.println(id+"222222"+filename+parent);
}
if(isS){
	System.out.println("성공");
	System.out.println(title+content+tagString);
	dto1 = new newbbs5HWCodingPDSVO(id,filename,(parent+1));
	request.setAttribute("pdsdto11", dto1);
	pageContext.forward("pdsChild.jsp?command11=addafter");
%>
<%
}else{
	System.out.println("실패");
	pageContext.forward("pdsChild.jsp?command11=faill");
%>

<%
}
%>

</body>
</html>