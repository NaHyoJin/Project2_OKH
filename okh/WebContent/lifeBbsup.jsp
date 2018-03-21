
<%@page import="user.UserDto"%>
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<!-- login session -->
<%
Object ologin = session.getAttribute("login");
UserDto mem = (UserDto)ologin;
%>
<%
request.setCharacterEncoding("UTF-8");

String seq = request.getParameter("seq");
String id = request.getParameter("id");


System.out.println("seq in updown : " + seq);
System.out.println("upid in updown : " + id);
System.out.println("mem.getId() in updown : " + mem.getId());

ILifeBbsDao dao = LifeBbsDao.getInstance();
IUserService service = UserService.getInstance();

LifeBbsDto upBbs = dao.getupdownid(Integer.parseInt(seq));
LifeBbsDto downBbs = dao.getupdownid(Integer.parseInt(seq));

List<String> upSplit = new ArrayList<String>();
List<String> downSplit = new ArrayList<String>();
String upArraySplit[] = null;
String downArraySplit[] = null;
int up = upBbs.getUp();

if(upBbs.getUpid() != null && downBbs.getDownid() == null){
	System.out.println("1");
	boolean findId = false;
	int findIndex = 0;
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
 		if(upSplit.get(i).trim().equals(mem.getId().trim())){
 			findId = true;
			findIndex = i;
			break;
		}
	}
	
	if(findId){
		System.out.println("1-1");
		// score -3
		int score = service.getScore(id);
		score -= 3;
		boolean SS = service.updateScore(id, score);
		
		up--;
		upSplit.remove(findIndex);
		String addupid = " ";
		for(int j = 0; j < upSplit.size(); j++){
			addupid += upSplit.get(j).toString();
		}
		dao.updateupid(Integer.parseInt(seq), up, addupid.trim());
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(upBbs.getUp());
	}else {
		System.out.println("1-2");
		// score +3
		int score = service.getScore(id);
		score += 3;
		boolean SS = service.updateScore(id, score);
		
		up++;
		String addupid = upBbs.getUpid() + "," + mem.getId();
		dao.updateupid(Integer.parseInt(seq), up, addupid);
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(upBbs.getUp());
	}
	
}else if(upBbs.getUpid() == null && downBbs.getDownid() != null){
	System.out.println("2");
	boolean findId = false;
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < downSplit.size(); i++){
		if(downSplit.get(i).trim().equals(mem.getId().trim())){
			findId = true;
			break;
		}
	}

	if(findId){
		System.out.println("2-1");
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else{
		System.out.println("2-2");
		// score +3
		int score = service.getScore(id);
		score += 3;
		boolean SS = service.updateScore(id, score);
		
		up++;
		String addDownId = downBbs.getDownid() + "," + mem.getId();
		dao.updateupid(Integer.parseInt(seq), up, addDownId);
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}
	
}else if(upBbs.getUpid() != null && downBbs.getDownid() != null){
	System.out.println("3");
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
		if(!(upSplit.get(i).trim().equals(mem.getId().trim())) && downSplit.get(i).trim().equals(mem.getId().trim())){
			findId = 1;
		}else if(upSplit.get(i).trim().equals(mem.getId().trim())){
			if(!(downSplit.get(i).trim().equals(mem.getId().trim()))){
				findId = 2;
				findIndex = i;
		}
		}else if(!(upSplit.get(i).trim().equals(mem.getId())) && !(downSplit.get(i).trim().equals(mem.getId().trim()))){
			findId = 3;
		}
	}
	
	if(findId == 1){
		System.out.println("3-1");
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else if(findId == 2){
		System.out.println("3-2");
		// score -3
		int score = service.getScore(id);
		score -= 3;
		boolean SS = service.updateScore(id, score);
		
		up--;
		upSplit.remove(findIndex);
		while (upSplit.remove(null));
		String addupid = " ";
		for(int j = 0; j < upSplit.size(); j++){
			if(upSplit.get(j).toString() != null){
				addupid += upSplit.get(j).toString();
			}
		}
		dao.updateupid(Integer.parseInt(seq), up, addupid.trim());
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(upBbs.getUp());
	}else if(findId == 3){
		System.out.println("3-3");
		// score +3
		int score = service.getScore(id);
		score += 3;
		boolean SS = service.updateScore(id, score);
		
		up++;
		String addupid = upBbs.getUpid() + "," + mem.getId();
		dao.updateupid(Integer.parseInt(seq), up, addupid);
		upBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(upBbs.getUp());
	}
	
}else if(upBbs.getUpid() == null && downBbs.getDownid() == null){
	System.out.println("4");
	// score +3
	int score = service.getScore(id);
	score += 3;
	boolean SS = service.updateScore(id, score);
	
	up++;
	dao.updateupid(Integer.parseInt(seq), up, mem.getId());
	upBbs = dao.getupdownid(Integer.parseInt(seq));
	out.println(upBbs.getUp());
}
%>