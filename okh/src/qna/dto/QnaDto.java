package qna.dto;

import java.io.Serializable;

/*

DROP TABLE QNA
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_QNA;

CREATE TABLE QNA(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	TAG VARCHAR2(200) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	
	FAVOR NUMBER(1) NOT NULL,
	LVPOINT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_QNA
START WITH 1 INCREMENT BY 1;

ALTER TABLE QNA
ADD CONSTRAINT FK_QNA_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);



*/

public class QnaDto implements Serializable{

	private int seq;
	private String id;
	private int ref;		// 그룹번호
	private int step;		// 열번호
	private int depth;		// 깊이
	private String title;
	private String content;
	private String wdate;
	private String tag;



	private int parent;		// 부모글
	private int del;
	private int readcount;
	private int favor;		// 찬성,반대
	private int lvpoint;	// 활동 포인트 글+5, 댓글+2
	
	public QnaDto() {}
	
	

	public QnaDto(int seq, String id, int ref, int step, int depth, String title, String content, String wdate,
			String tag, int parent, int del, int readcount, int favor, int lvpoint) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.tag = tag;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
		this.favor = favor;
		this.lvpoint = lvpoint;
	}



	public QnaDto(String id, String title, String content, String tag) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
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


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	public int getFavor() {
		return favor;
	}


	public void setFavor(int favor) {
		this.favor = favor;
	}


	public int getLvpoint() {
		return lvpoint;
	}


	public void setLvpoint(int lvpoint) {
		this.lvpoint = lvpoint;
	}
	
	
	
	
	
	
	
	
	
	
}
