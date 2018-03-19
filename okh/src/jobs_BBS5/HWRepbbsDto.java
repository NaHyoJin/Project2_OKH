package jobs_BBS5;

import java.io.Serializable;

/*
DROP TABLE HWRepbbsDto
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_HWRepbbsDto;

CREATE TABLE HWRepbbsDto(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,

	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL
);

CREATE SEQUENCE SEQ_HWRepbbsDto
START WITH 1 INCREMENT BY 1;

ALTER TABLE HWRepbbsDto
ADD CONSTRAINT FK_HWRepbbsDto_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

*/

public class HWRepbbsDto implements Serializable {

	private int seq;
	private String id;
	
	private String content;
	private String wdate;
	private int parent;	// 부모글
	
	private int del;	// 삭제
	public HWRepbbsDto() {}
	
	public HWRepbbsDto(int seq, String id, String content, String wdate, int parent, int del) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
	}

	public HWRepbbsDto(String id, String content,int parent) {
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





