<%@page import="user.UserDto"%>
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

LifeBbsDto downBbs = dao.getupdownid(Integer.parseInt(seq));
LifeBbsDto upBbs = dao.getupdownid(Integer.parseInt(seq));

List<String> downSplit = new ArrayList<String>();
List<String> upSplit = new ArrayList<String>();
String downArraySplit[] = null;
String upArraySplit[] = null;
int up = downBbs.getUp();

if(downBbs.getDownid() != null && upBbs.getUpid() == null){
	System.out.println("di1");
	boolean findId = false;
	int findIndex = 0;
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < downSplit.size(); i++){
		if(downSplit.get(i).trim().equals(mem.getId().trim())){
			findId = true;
			findIndex = i;
			break;
		}
	}

	if(findId){
		System.out.println("di1-1");
		String downIdSplit[] = null;
		boolean findDownId = false;
		if(bbs.getDownid() != null && mem != null){
			downIdSplit = bbs.getDownid().split(",");
			for(int i = 0; i < downIdSplit.length; i++){
				if(downIdSplit[i].equals(mem.getId())){
					findDownId = true;
					out.println(findDownId);
					break;
				}
			}
		}
	}else{
		System.out.println("di1-2");
		String downIdSplit[] = null;
		boolean findDownId = false;
		if(bbs.getDownid() != null && mem != null){
			downIdSplit = bbs.getDownid().split(",");
			for(int i = 0; i < downIdSplit.length; i++){
				if(downIdSplit[i].equals(mem.getId())){
					findDownId = true;
					out.println(findDownId);
					break;
				}
			}
		}
	}

}else if(downBbs.getDownid() == null && upBbs.getUpid() != null){
	System.out.println("di2");
	boolean findId = false;
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
		if(upSplit.get(i).trim().equals(mem.getId().trim())){
			findId = true;
			break;
		}
	}

	if(findId){
		System.out.println("di2-1");
		out.println(true);
	}else{
		System.out.println("di2-2");
		String downIdSplit[] = null;
		boolean findDownId = false;
		if(bbs.getDownid() != null && mem != null){
			downIdSplit = bbs.getDownid().split(",");
			for(int i = 0; i < downIdSplit.length; i++){
				if(downIdSplit[i].equals(mem.getId())){
					findDownId = true;
					out.println(findDownId);
					break;
				}
			}
		}
	}
	
}else if(downBbs.getDownid() != null && upBbs.getUpid() != null){
	System.out.println("di3");
	int findId = 0;
	int findIndex = 0;
	downArraySplit = upBbs.getDownid().split(",");
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < downSplit.size(); i++){
		if(!(downSplit.get(i).trim().equals(mem.getId().trim())) && upSplit.get(i).trim().equals(mem.getId().trim())){
			findId = 1;
		}else if(downSplit.get(i).trim().equals(mem.getId().trim()) && !(upSplit.get(i).trim().equals(mem.getId().trim()))){
				findId = 2;
				findIndex = i;
		}else if(!(downSplit.get(i).trim().equals(mem.getId().trim())) && !(upSplit.get(i).trim().equals(mem.getId().trim()))){
			findId = 3;
		}
	}
	if(findId == 1){
		System.out.println("di3-1");
		out.println(true);
	}else if(findId == 2){
		System.out.println("di3-2");
		String downIdSplit[] = null;
		boolean findDownId = false;
		if(bbs.getDownid() != null && mem != null){
			downIdSplit = bbs.getDownid().split(",");
			for(int i = 0; i < downIdSplit.length; i++){
				if(downIdSplit[i].equals(mem.getId())){
					findDownId = true;
					out.println(findDownId);
					break;
				}
			}
		}
	}else if(findId ==3){
		System.out.println("di3-3");
		String downIdSplit[] = null;
		boolean findDownId = false;
		if(bbs.getDownid() != null && mem != null){
			downIdSplit = bbs.getDownid().split(",");
			for(int i = 0; i < downIdSplit.length; i++){
				if(downIdSplit[i].equals(mem.getId())){
					findDownId = true;
					out.println(findDownId);
					break;
				}
			}
		}
	}
}else{
	System.out.println("di4");
	String downIdSplit[] = null;
	boolean findDownId = false;
	if(bbs.getDownid() != null && mem != null){
		downIdSplit = bbs.getDownid().split(",");
		for(int i = 0; i < downIdSplit.length; i++){
			if(downIdSplit[i].equals(mem.getId())){
				findDownId = true;
				out.println(findDownId);
				break;
			}
		}
	}
}
%>