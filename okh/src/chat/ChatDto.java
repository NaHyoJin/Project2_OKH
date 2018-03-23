package chat;

import java.io.Serializable;

/*
DROP TABLE CHAT
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_CHAT;

CREATE TABLE CHAT(
	SEQ NUMBER(10) PRIMARY KEY,
	FROMID VARCHAR2(20),
	TOID VARCHAR2(20),
	CHATCONTENT VARCHAR2(100),
	CHATTIME DATE,
	CHATREAD NUMBER(1)
);

CREATE SEQUENCE SEQ_CHAT
START WITH 1 INCREMENT BY 1;
*/

public class ChatDto implements Serializable {
	
	private int seq;
	private String fromid;
	private String toid;
	private String chatcontent;
	private String chattime;
	private int chatread;
	
	public ChatDto() {
	}
	
	public ChatDto(int seq, String fromid, String toid, String chatcontent, String chattime, int chatread) {
		super();
		this.seq = seq;
		this.fromid = fromid;
		this.toid = toid;
		this.chatcontent = chatcontent;
		this.chattime = chattime;
		this.chatread = chatread;
	}

	public ChatDto(int seq, String fromid, String toid, String chatcontent, String chattime) {
		super();
		this.seq = seq;
		this.fromid = fromid;
		this.toid = toid;
		this.chatcontent = chatcontent;
		this.chattime = chattime;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public String getChatcontent() {
		return chatcontent;
	}
	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}
	public String getChattime() {
		return chattime;
	}
	public void setChattime(String chattime) {
		this.chattime = chattime;
	}

	public int getChatread() {
		return chatread;
	}

	@Override
	public String toString() {
		return "ChatDto [seq=" + seq + ", fromid=" + fromid + ", toid=" + toid + ", chatcontent=" + chatcontent
				+ ", chattime=" + chattime + ", chatread=" + chatread + "]";
	}

	public void setChatread(int chatread) {
		this.chatread = chatread;
	}
	
	

}
