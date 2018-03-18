package techreplebbs;

import java.io.Serializable;

/*
DROP TABLE TECHREPBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_TECHREPBBS;

CREATE TABLE TECHREPBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,

	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE SEQ_TECHREPBBS
START WITH 1 INCREMENT BY 1;

ALTER TABLE TECHREPBBS
ADD CONSTRAINT FK_TECHREPBBS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

*/

public class TechRepbbsDto implements Serializable {

	private int seq;
	private String id;
	
	private String content;
	private String wdate;
	private int parent;	// 부모글
	
	private int del;	// 삭제
	public TechRepbbsDto() {}
	
	public TechRepbbsDto(int seq, String id, String content, String wdate, int parent, int del) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
	}

	public TechRepbbsDto(String id, String content,int parent) {
		super();
		this.id = id;
		this.content = content;
		this.parent = parent;
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

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	
}





