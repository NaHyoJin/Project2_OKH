package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.CacheHttpServlet;

@WebServlet("/ChatListsController")
public class ChatListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String fromid = request.getParameter("fromid");
		String toid = request.getParameter("toid");
		String listtype = request.getParameter("listtype");
		
		if(fromid == null || fromid.equals("") || toid == null || toid.equals("") ||
				listtype == null || listtype.equals("")) {
			response.getWriter().write("");
		}else if(listtype.equals("ten")) response.getWriter().write(getTen(URLDecoder.decode(fromid, "UTF-8"), URLDecoder.decode(toid, "UTF-8")));
		else {
			try {
				response.getWriter().write(getId(URLDecoder.decode(fromid, "UTF-8"), URLDecoder.decode(toid, "UTF-8"), listtype));
			}catch(Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	public String getTen(String fromid, String toid) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		IChatService chatService = ChatService.getInstance();
		ArrayList<ChatDto> chatList = chatService.getChatListByRecent(fromid, toid, 100);
		System.out.println("chatList----------------------------------------------" + chatList);
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatcontent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChattime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getSeq() + "\"}");
		chatService.readChat(fromid, toid);
		return result.toString();
	}
	
	public String getId(String fromid, String toid, String seq) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		IChatService chatService = new ChatService();
		ArrayList<ChatDto> chatList = chatService.getChatListById(fromid, toid, seq);
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatcontent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChattime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getSeq() + "\"}");
		chatService.readChat(fromid, toid);
		return result.toString();
	}

}
