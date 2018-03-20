<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="user.IUserDao"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>lifeBbsUpload</title>
</head>
<body>

<%!
public String processUploadFile(FileItem fileItem, String dir, JspWriter out) throws IOException{
	String fileName = fileItem.getName();
	long sizeInBytes = fileItem.getSize();
	
	// 업로드한 파일이 정상일 경우
	if(sizeInBytes > 0){						// d:\\tmp\\abc.jpg  == d:/tmp/abc.jpg
		int idx = fileName.lastIndexOf("\\");
		
		if(idx == -1){
			idx = fileName.lastIndexOf("/");
		}
		fileName = fileName.substring(idx + 1);
		
		try{
		File uploadFile = new File(dir, fileName);
		fileItem.write(uploadFile);				// 실제 업로드 하는 부분
		}catch(Exception e){}
	}
	
	return fileName;
}
%>

<%

String fupload = application.getRealPath("/upload");

System.out.println("파일업로드 : " + fupload);

String yourTempDirectory = fupload;

int yourMaxRequestSize = 100 * 1024 * 1024;			// 1M
int yourMaxMemorySize = 100 * 1024;

// form field에 있는 데이터(String)
String id = "";
String title = "";
String content = "";
String tagBf = "";
String tag = "";

// file data
String filename = "";
String filenames = "";

boolean isMultipart = ServletFileUpload.isMultipartContent(request);

if(isMultipart){
	////////////////////// file
	
	// FileItem 오브젝트를 생성하는 클래스
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	factory.setSizeThreshold(yourMaxMemorySize);
	factory.setRepository(new File(yourTempDirectory));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(yourMaxRequestSize);							// 파일 업로드 최대 크기
	upload.setHeaderEncoding("UTF-8");
	
	///////////////////////////
	
	List<FileItem> items = upload.parseRequest(request);
	System.out.println("items in upload : " + items);
	
	Iterator<FileItem> it = items.iterator();
	System.out.println("it in upload : " + it);
	
	while(it.hasNext()){
		FileItem item = it.next();
		item.getString("UTF-8");
		if(item.isFormField()){
			if(item.getFieldName().equals("id")){
				id = item.getString("UTF-8");
			}else if(item.getFieldName().equals("title")){
				title = item.getString("UTF-8");
			}else if(item.getFieldName().equals("content")){
				content = item.getString("UTF-8");
			}else if(item.getFieldName().equals("tag")){
				tagBf = item.getString("UTF-8");
				tag = "사는얘기," + tagBf;
			}
		}else{														// file load
			if(item.getFieldName().equals("fileload")){
				filename = processUploadFile(item, fupload, out);
				if(filenames.equals("")){
					filenames += filename;
				}else{
					filenames += "," + filename;
				}
			}else if(item.getFieldName().equals("fileload1")){
				filename = processUploadFile(item, fupload, out);
				if(filenames.equals("")){
					filenames += filename;
				}else{
					filenames += "," + filename;
				}
			}else if(item.getFieldName().equals("fileload2")){
				filename = processUploadFile(item, fupload, out);
				if(filenames.equals("")){
					filenames += filename;
				}else{
					filenames += "," + filename;
				}
			}else if(item.getFieldName().equals("fileload3")){
				filename = processUploadFile(item, fupload, out);
				if(filenames.equals("")){
					filenames += filename;
				}else{
					filenames += "," + filename;
				}
			}else if(item.getFieldName().equals("fileload4")){
				filename = processUploadFile(item, fupload, out);
				if(filenames.equals("")){
					filenames += filename;
				}else{
					filenames += "," + filename;
				}
			}
			System.out.println("filename : " + filename);
			System.out.println("filenames : " + filenames);
		}
	}
}else{
	// multipart type 아님
}

ILifeBbsDao dao = LifeBbsDao.getInstance();
IUserService service = UserService.getInstance();

boolean isS = dao.writeBbs(new LifeBbsDto(id, title, content, tag, filenames));

if(isS){
	// score +5
	int score = service.getScore(id);
	score += 5;
	boolean SS = service.updateScore(id, score);
	if(SS){
		%>
		<script type="text/javascript">
		location.href = "lifeBbsList.jsp";
		</script>
		<%
	}
}else{
%>
	<script type="text/javascript">
	request.getSession().setAttribute("messageType", "오류 메시지");
	request.getSession().setAttribute("messageContent", "새 글 쓰기 오류.");
	response.sendRedirect("lifeBbsList.jsp");
	return;
	</script>
<%
}
%>

</body>
</html>