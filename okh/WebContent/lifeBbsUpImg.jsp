<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="user.UserDto"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>
<%
request.setCharacterEncoding("UTF-8");

String seq = request.getParameter("seq");

ILifeBbsDao dao = LifeBbsDao.getInstance();
LifeBbsDto bbs = dao.getDetailBbs(Integer.parseInt(seq.trim()));


IUserService service = UserService.getInstance();

LifeBbsDto upBbs = dao.getupdownid(Integer.parseInt(seq));
LifeBbsDto downBbs = dao.getupdownid(Integer.parseInt(seq));

List<String> upSplit = new ArrayList<String>();
List<String> downSplit = new ArrayList<String>();
String upArraySplit[] = null;
String downArraySplit[] = null;
int up = upBbs.getUp();

if(upBbs.getUpid() != null && downBbs.getDownid() == null){
	boolean findId = false;
	int findIndex = 0;
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
 		if(upSplit.get(i).equals(mem.getId())){
 			findId = true;
			findIndex = i;
			break;
		}
	}
	
	if(findId){
		String upIdSplit[] = null;
		boolean findUpId = false;
		if(bbs.getUpid() != null && mem != null){
			upIdSplit = bbs.getUpid().split(",");
			for(int i = 0; i < upIdSplit.length; i++){
				if(upIdSplit[i].equals(mem.getId())){
					findUpId = true;
					out.println(findUpId);
					break;
				}
			}
		}
	}else {
		String upIdSplit[] = null;
		boolean findUpId = false;
		if(bbs.getUpid() != null && mem != null){
			upIdSplit = bbs.getUpid().split(",");
			for(int i = 0; i < upIdSplit.length; i++){
				if(upIdSplit[i].equals(mem.getId())){
					findUpId = true;
					out.println(findUpId);
					break;
				}
			}
		}
	}
	
}else if(upBbs.getUpid() == null && downBbs.getDownid() != null){
	boolean findId = false;
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < downSplit.size(); i++){
		if(downSplit.get(i).equals(mem.getId())){
			findId = true;
			break;
		}
	}

	if(findId){
		out.println(true);
	}else{
		String upIdSplit[] = null;
		boolean findUpId = false;
		if(bbs.getUpid() != null && mem != null){
			upIdSplit = bbs.getUpid().split(",");
			for(int i = 0; i < upIdSplit.length; i++){
				if(upIdSplit[i].equals(mem.getId())){
					findUpId = true;
					out.println(findUpId);
					break;
				}
			}
		}
	}
	
}else if(upBbs.getUpid() != null && downBbs.getDownid() != null){
	int findId = 0;
	int findIndex = 0;
	upArraySplit = upBbs.getUpid().split(",");
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
		if(!(upSplit.get(i).equals(mem.getId())) && downSplit.get(i).equals(mem.getId())){
			findId = 1;
		}else if(upSplit.get(i).equals(mem.getId()) && !(downSplit.get(i).equals(mem.getId()))){
			findId = 2;
			findIndex = i;
		}else if(!(upSplit.get(i).equals(mem.getId())) && !(downSplit.get(i).equals(mem.getId()))){
			findId = 3;
		}
	}
	
	if(findId == 1){
		out.println(true);
	}else if(findId == 2){
		String upIdSplit[] = null;
		boolean findUpId = false;
		if(bbs.getUpid() != null && mem != null){
			upIdSplit = bbs.getUpid().split(",");
			for(int i = 0; i < upIdSplit.length; i++){
				if(upIdSplit[i].equals(mem.getId())){
					findUpId = true;
					out.println(findUpId);
					break;
				}
			}
		}
	}else if(findId == 3){
		String upIdSplit[] = null;
		boolean findUpId = false;
		if(bbs.getUpid() != null && mem != null){
			upIdSplit = bbs.getUpid().split(",");
			for(int i = 0; i < upIdSplit.length; i++){
				if(upIdSplit[i].equals(mem.getId())){
					findUpId = true;
					out.println(findUpId);
					break;
				}
			}
		}
	}
	
}else if(upBbs.getUpid() == null && downBbs.getDownid() == null){
	String upIdSplit[] = null;
	boolean findUpId = false;
	if(bbs.getUpid() != null && mem != null){
		upIdSplit = bbs.getUpid().split(",");
		for(int i = 0; i < upIdSplit.length; i++){
			if(upIdSplit[i].equals(mem.getId())){
				findUpId = true;
				out.println(findUpId);
				break;
			}
		}
	}
}
%>