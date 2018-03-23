package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChatController")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String fromid = request.getParameter("fromid");
		String toid = request.getParameter("toid");
		String chatcontent = request.getParameter("chatcontent");
		String listtype = request.getParameter("listtype");
		String userID = request.getParameter("userID");
		
		String command = request.getParameter("command");
		
		IChatService service = ChatService.getInstance();
		
		if(command.equals("chat")) {
			if(fromid == null || fromid.equals("") || toid == null || toid.equals("") ||
					   chatcontent == null || chatcontent.equals("")) {
						response.getWriter().write("0");
			}else {
				fromid = URLDecoder.decode(fromid, "UTF-8");
				toid = URLDecoder.decode(toid, "UTF-8");
				chatcontent = URLDecoder.decode(chatcontent, "UTF-8");
				response.getWriter().write(service.submit(fromid, toid, chatcontent) + "");
			}
		}else if(command.equals("list")) {
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
		}else if(command.equals("unread")) {
			if(userID == null || userID.equals("")) {
				response.getWriter().write("0");
			}else {
				userID = URLDecoder.decode(userID, "UTF-8");
				response.getWriter().write(service.getAllUnreadChat(userID) + "");
			}
		}else if(command.equals("box")) {
			if(userID == null || userID.equals("")) {
				response.getWriter().write("");
			}else {
				try {
					userID = URLDecoder.decode(userID, "UTF-8");
					response.getWriter().write(getBox(userID));
				}catch(Exception e){
					response.getWriter().write("");
				}
			}
		}
		
		
	}
	
	public String getBox(String userID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		IChatService chatService = new ChatService();
		ArrayList<ChatDto> chatList = chatService.getBox(userID);
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToid() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatcontent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChattime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getSeq() + "\"}");
		return result.toString();
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
