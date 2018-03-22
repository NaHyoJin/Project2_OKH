package qna;

import java.io.Serializable;

/*
DROP TABLE QNA_PDS
CASCADE CONSTRAINT;

DROP SEQUENCE SEQ_QNA_PDS;

CREATE TABLE QNA_PDS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	FILENAME VARCHAR2(50) NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_QNA_PDS
START WITH 1 INCREMENT BY 1; 

ALTER TABLE QNA_PDS 
ADD CONSTRAINT FK_QPDS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
ALTER TABLE TECH_PDS 
ADD CONSTRAINT FK_QSEQ_ID FOREIGN KEY(PARENT)
REFERENCES QNA(SEQ);

*/

public class QnaPdsDto implements Serializable {
	private int seq;
	private String id;
	private String filename;
	private int parent;
	private String regdate;	//올린날짜
	
	public QnaPdsDto() {}

	public QnaPdsDto(int seq, String id, String filename, int parent, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.filename = filename;
		this.parent = parent;
		this.regdate = regdate;
	}

	public QnaPdsDto(String id, String filename, int parent) {
		super();
		this.id = id;
		this.filename = filename;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "QnaPdsDto [seq=" + seq + ", id=" + id + ", filename=" + filename + ", parent=" + parent + ", regdate="
				+ regdate + "]";
	}
	
	
	
	
	
	
}
