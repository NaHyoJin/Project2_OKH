package studysrc;

import java.io.Serializable;

/*
	
DROP TABLE COMBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_COMBBS;

CREATE TABLE COMBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	COMMENTCOUNT NUMBER(8) NOT NULL,
	TAGNAME VARCHAR2(50) NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	JOINERCOUNT NUMBER(8),
	JOINDATE VARCHAR2 (50) NOT NULL,
	LIKEINNER VARCHAR2(50)
);

CREATE SEQUENCE SEQ_COMBBS
START WITH 1 INCREMENT BY 1;

ALTER TABLE COMBBS
ADD CONSTRAINT FK_COMBBS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
 	
 */
public class CombbsDto implements Serializable{

	
	private int seq;		//시퀀스
	private String id;		// 아이디
	private String title;	//제목
	private String content;	//내용
	private String wdate;	//작성일
	private int del;	//삭제 0,1
	private int readcount;	//조회수
	private int commentcount;		//댓글수 
	private String tagname;	//지역테그
	private int parent;		//부모글
	private int joinercount;	// 좋아요
	private String joindate;	// 모임날
	private String likeinner;	// 좋아요 누른사람
	
	public CombbsDto() {}
	public CombbsDto(int seq, String id, String title, String content, String wdate, int del, int readcount,
			int commentcount, String tagname, int parent, int joinercount, String joindate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.tagname = tagname;
		this.parent = parent;
		this.joinercount = joinercount;
		this.joindate = joindate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getJoinercount() {
		return joinercount;
	}
	public void setJoinercount(int joinercount) {
		this.joinercount = joinercount;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public CombbsDto(String id, String title, String content, String tagname, String joindate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tagname = tagname;
		this.joindate = joindate;
	}
	
	
	
	
	
	
	
	
}
	
