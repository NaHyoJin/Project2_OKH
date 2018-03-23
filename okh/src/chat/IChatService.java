package chat;

import java.util.ArrayList;

public interface IChatService {
	
	public ArrayList<ChatDto> getChatListById(String fromid, String toid, String seq);
	public ArrayList<ChatDto> getChatListByRecent(String fromid, String toid, int number);
	public int submit(String fromid, String toid, String chatcontent);
	public boolean readChat(String fromid, String toid);
	public int getAllUnreadChat(String userID);
	public ArrayList<ChatDto> getBox(String userID);

}
