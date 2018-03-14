package jobs_BBS5;

import java.io.Serializable;

/////////////////////이게 자료실 게시판 자체다. 다 따로따로 DB에 테이블이 존재.

/*
DROP TABLE BbsMaterialsBeanDtoVOTable
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BbsMaterialsBeanDtoVOTable;

CREATE TABLE BbsMaterialsBeanDtoVOTable(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_BbsMaterialsBeanDtoVOTable
START WITH 1 INCREMENT BY 1;

ALTER TABLE BbsMaterialsBeanDtoVOTable
ADD CONSTRAINT FK_MaterialsBeanDtoVOTable_ID FOREIGN KEY(ID)
REFERENCES okhmem(ID);

*/

//자료실 게시판 3개를 테이블 다 따로따로 만들어보자.
public class BbsMaterialsBeanDtoVOTable implements Serializable {

	private int seq;
	private String id;
	
	private int ref;	// 그룹번호
	private int step;	// 열번호
	private int depth;	// 깊이
	
	private String title;
	private String content;
	private String wdate;
	private int parent;	// 부모글
	
	private int del;	// 삭제
	private int readcount;//조회수
	
	
	public BbsMaterialsBeanDtoVOTable() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "BbsMaterialsBeanDtoVOTable [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth="
				+ depth + ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", parent=" + parent
				+ ", del=" + del + ", readcount=" + readcount + "]";
	}

	
	public BbsMaterialsBeanDtoVOTable(int seq, String id, int ref, int step, int depth, String title, String content,
			String wdate, int parent, int del, int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
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
}
