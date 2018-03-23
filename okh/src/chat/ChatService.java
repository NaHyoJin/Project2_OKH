package chat;

import java.util.ArrayList;

public class ChatService implements IChatService {
	
	private static ChatService chatService = new ChatService();
	private static IChatDao chatdao = new ChatDao();
	
	public static ChatService getInstance() {
		return chatService;
	}

	@Override
	public ArrayList<ChatDto> getChatListById(String fromid, String toid, String seq) {
		return chatdao.getChatListById(fromid, toid, seq);
	}

	@Override
	public ArrayList<ChatDto> getChatListByRecent(String fromid, String toid, int number) {
		return chatdao.getChatListByRecent(fromid, toid, number);
	}

	@Override
	public int submit(String fromid, String toid, String chatcontent) {
		return chatdao.submit(fromid, toid, chatcontent);
	}

	@Override
	public boolean readChat(String fromid, String toid) {
		return chatdao.readChat(fromid, toid);
	}

	@Override
	public int getAllUnreadChat(String userID) {
		return chatdao.getAllUnreadChat(userID);
	}

	@Override
	public ArrayList<ChatDto> getBox(String userID) {
		return chatdao.getBox(userID);
	}

}
