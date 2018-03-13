package dto;

import java.io.Serializable;
/*
INSERT INTO TECHBBS 
(SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, READCOUNT, LIKECOUNT,COMMENTCOUNT, SCRAPCOUNT) 
VALUES(SEQ_TECHBBS.NEXTVAL, 'aa', 'aa','aa','aa',SYSDATE,0,0,0,0,0 );

DROP TABLE TECHBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_TECHBBS;

CREATE TABLE TECHBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	TAGNAME VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	LIKECOUNT NUMBER(8) NOT NULL,
	COMMENTCOUNT NUMBER(8) NOT NULL,
	SCRAPCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_TECHBBS
START WITH 1 INCREMENT BY 1;

ALTER TABLE TECHBBS
ADD CONSTRAINT FK_TECHBBS_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
 */
public class TechbbsDto implements Serializable {
	private int seq;
	private String id;
	
	private String title;
	private String tagname;
	private String content;
	private String wdate;
	
	private int del;	// 삭제
	private int readcount;
	private int commentcount;
	private int likecount;
	private int scrapcount;
	

	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int commentcount, int likecount, int scrapcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
	}
	
	
	public TechbbsDto(String id, String title, String tagname, String content) {
		super();
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
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
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getScrapcount() {
		return scrapcount;
	}
	public void setScrapcount(int scrapcount) {
		this.scrapcount = scrapcount;
	}
	@Override
	public String toString() {
		return "TechBbsDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", wdate="
				+ wdate + ", del=" + del + ", readcount=" + readcount + ", likecount=" + likecount + ", scrapcount="
				+ scrapcount + "]";
	}
	
	
}
