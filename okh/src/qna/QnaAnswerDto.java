package qna;

import java.io.Serializable;
/*
DROP TABLE QNAANSWER
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_QNAANSWER;

CREATE TABLE QNAANSWER(
	SEQ NUMBER(8) PRIMARY KEY,	
	ID VARCHAR2(50) NOT NULL,
	
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	DEL NUMBER(1) NOT NULL	
);

CREATE SEQUENCE SEQ_QNAANSWER
START WITH 1 INCREMENT BY 1;

ALTER TABLE QNAANSWER
ADD CONSTRAINT FK_QNAANSWER_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

ALTER TABLE QNAANSWER
ADD CONSTRAINT FK_QNAANSWERSEQ_ID FOREIGN KEY(parent)
REFERENCES QNA(SEQ);

*/

public class QnaAnswerDto implements Serializable {
		
	private int seq;	
	private String id;	
	private String content;
	private String wdate;
	private int parent;	
	private int del;
	
	
	public QnaAnswerDto() {}


	public QnaAnswerDto(int seq, String id, String content, String wdate, int parent, int del) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
	}


	public QnaAnswerDto(String id, String content, int parent) {
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


	@Override
	public String toString() {
		return "QnaAnswerDto [seq=" + seq + ", id=" + id + ", content=" + content + ", wdate=" + wdate + ", parent="
				+ parent + ", del=" + del + "]";
	}

	
	
}








