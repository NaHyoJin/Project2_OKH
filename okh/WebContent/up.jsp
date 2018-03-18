
<%@page import="user.UserService"%>
<%@page import="user.IUserService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="lifeBbs.LifeBbsDto"%>
<%@page import="lifeBbs.LifeBbsDao"%>
<%@page import="lifeBbs.ILifeBbsDao"%>
<%
request.setCharacterEncoding("UTF-8");

String seq = request.getParameter("seq");
String id = request.getParameter("id");


System.out.println("seq in updown : " + seq);
System.out.println("upid in updown : " + id);

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
	upArraySplit = upBbs.getUpid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
		if(!(upSplit.get(i).equals(id))){
			// score +3
			int score = service.getScore(id);
			score += 3;
			boolean SS = service.updateScore(id, score);
			
			up++;
			String addupid = upBbs.getUpid() + "," + id;
			dao.updateupid(Integer.parseInt(seq), up, addupid);
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(upBbs.getUp());
		}else if(upSplit.get(i).equals(id)){
			// score -3
			int score = service.getScore(id);
			score -= 3;
			boolean SS = service.updateScore(id, score);
			
			up--;
			upSplit.remove(i);
			String addupid = null;
			for(int j = 0; j < upSplit.size(); j++){
				addupid += upSplit.get(i).toString();
			}
			dao.updateupid(Integer.parseInt(seq), up, addupid);
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(upBbs.getUp());
		}
	}
}else if(upBbs.getUpid() == null && downBbs.getDownid() != null){
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < downSplit.size(); i++){
		if(downSplit.get(i).equals(id)){
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(downBbs.getUp());
		}else if(!(downSplit.get(i).equals(id))){
			// score +3
			int score = service.getScore(id);
			score += 3;
			boolean SS = service.updateScore(id, score);
			
			up++;
			String addDownId = downBbs.getDownid() + "," + id;
			dao.updatedownid(Integer.parseInt(seq), up, addDownId);
			downBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(downBbs.getUp());
		}
	}
}else if(upBbs.getUpid() != null && downBbs.getDownid() != null){
	upArraySplit = upBbs.getUpid().split(",");
	downArraySplit = upBbs.getDownid().split(",");
	for(int i = 0; i < upArraySplit.length; i++){
		upSplit.add(upArraySplit[i]);
	}
	for(int i = 0; i < downArraySplit.length; i++){
		downSplit.add(downArraySplit[i]);
	}
	
	for(int i = 0; i < upSplit.size(); i++){
		if(!(upSplit.get(i).equals(id)) && downSplit.get(i).equals(id)){
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(downBbs.getUp());
		}else if(upSplit.get(i).equals(id) && !(downSplit.get(i).equals(id))){
			// score -3
			int score = service.getScore(id);
			score -= 3;
			boolean SS = service.updateScore(id, score);
			
			up--;
			upSplit.remove(i);
			String addupid = null;
			for(int j = 0; j < upSplit.size(); j++){
				addupid += upSplit.get(i).toString();
			}
			dao.updateupid(Integer.parseInt(seq), up, addupid);
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(upBbs.getUp());
		}else if(!(upSplit.get(i).equals(id)) && !(downSplit.get(i).equals(id))){
			// score +3
			int score = service.getScore(id);
			score += 3;
			boolean SS = service.updateScore(id, score);
			
			up++;
			String addupid = upBbs.getUpid() + "," + id;
			dao.updateupid(Integer.parseInt(seq), up, addupid);
			upBbs = dao.getupdownid(Integer.parseInt(seq));
			out.println(upBbs.getUp());
		}
	}
}else if(upBbs.getUpid() == null && downBbs.getDownid() == null){
	// score +3
	int score = service.getScore(id);
	score += 3;
	boolean SS = service.updateScore(id, score);
	
	up++;
	dao.updateupid(Integer.parseInt(seq), up, id);
	upBbs = dao.getupdownid(Integer.parseInt(seq));
	out.println(upBbs.getUp());
}
%>