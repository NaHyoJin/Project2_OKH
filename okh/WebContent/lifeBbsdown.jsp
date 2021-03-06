
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

LifeBbsDto downBbs = dao.getupdownid(Integer.parseInt(seq));
LifeBbsDto upBbs = dao.getupdownid(Integer.parseInt(seq));

List<String> downSplit = new ArrayList<String>();
List<String> upSplit = new ArrayList<String>();
String downArraySplit[] = null;
String upArraySplit[] = null;
int up = downBbs.getUp();

if(downBbs.getDownid() != null && upBbs.getUpid() == null){
	System.out.println("d1");
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
		}
	}

	if(findId){
		System.out.println("d1-1");
		// score +3
		int score = service.getScore(id);
		score += 3;
		boolean SS = service.updateScore(id, score);
		
		up++;
		downSplit.remove(findIndex);
		while (downSplit.remove(null));
		String addDownId = " ";
		for(int j = 0; j < downSplit.size(); j++){
			addDownId += downSplit.get(j).toString();
		}
		dao.updatedownid(Integer.parseInt(seq), up, addDownId.trim());
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else{
		System.out.println("d1-2");
		// score -3
		int score = service.getScore(id);
		score -= 3;
		boolean SS = service.updateScore(id, score);
		
		up--;
		String addDownId = downBbs.getDownid() + "," + mem.getId();
		dao.updatedownid(Integer.parseInt(seq), up, addDownId);
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}

}else if(downBbs.getDownid() == null && upBbs.getUpid() != null){
	System.out.println("d2");
	boolean findId = false;
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
		if(upSplit.get(i).trim().equals(mem.getId().trim())){
			findId = true;
		}
	}

	if(findId){
		System.out.println("d2-1");
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else{
		System.out.println("d2-2");
		// score -3
		int score = service.getScore(id);
		score -= 3;
		boolean SS = service.updateScore(id, score);
		
		up--;
		String addDownId = downBbs.getDownid() + "," + mem.getId();
		dao.updatedownid(Integer.parseInt(seq), up, addDownId);
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}
	
}else if(downBbs.getDownid() != null && upBbs.getUpid() != null){
	System.out.println("d3");
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
		System.out.println("d3-1");
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else if(findId == 2){
		System.out.println("d3-2");
		// score +3
		int score = service.getScore(id);
		score += 3;
		boolean SS = service.updateScore(id, score);
		
		up++;
		downSplit.remove(findIndex);
		while (downSplit.remove(null));
		String addDownId = "))))))))))))))))))))))))))))";
		for(int j = 0; j < downSplit.size(); j++){
			addDownId += downSplit.get(j).toString();
		}
		dao.updatedownid(Integer.parseInt(seq), up, addDownId.trim());
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}else if(findId ==3){
		System.out.println("d3-3");
		// score -3
		int score = service.getScore(id);
		score -= 3;
		boolean SS = service.updateScore(id, score);
		
		up--;
		String addDownId = downBbs.getDownid() + "," + mem.getId();
		dao.updatedownid(Integer.parseInt(seq), up, addDownId);
		downBbs = dao.getupdownid(Integer.parseInt(seq));
		out.println(downBbs.getUp());
	}
}else{
	System.out.println("d4");
	// score -3
	int score = service.getScore(id);
	score -= 3;
	boolean SS = service.updateScore(id, score);
	
	up--;
	dao.updatedownid(Integer.parseInt(seq), up, mem.getId());
	downBbs = dao.getupdownid(Integer.parseInt(seq));
	out.println(downBbs.getUp());
}
%>