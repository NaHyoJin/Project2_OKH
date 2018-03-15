package techreplebbs;

import java.io.Serializable;

/*
DROP TABLE TECHREPBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_TECHREPBBS;

CREATE TABLE TECHREPBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,

	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	LIKECOUNT NUMBER(8)
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
	
	private int ref;	// 그룹번호
	private int step;	// 열번호
	private int depth;	// 깊이
	
	private String content;
	private String wdate;
	private int parent;	// 부모글
	
	private int del;	// 삭제
	private int likecount;
	public TechRepbbsDto() {}

	public TechRepbbsDto(int seq, String id, int ref, int step, int depth, String content, String wdate, int parent,
			int del,int likecount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.likecount = likecount;
	}

	public TechRepbbsDto(String id, String content,int parent) {
		super();
		this.id = id;
		this.content = content;
		this.parent = parent;
	}
	
	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
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





