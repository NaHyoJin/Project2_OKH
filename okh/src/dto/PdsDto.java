package dto;

import java.io.Serializable;
/*
DROP TABLE TECH_PDS
CASCADE CONSTRAINT;

DROP SEQUENCE SEQ_TECH_PDS;

CREATE TABLE TECH_PDS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	FILENAME VARCHAR2(50) NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_TECH_PDS
START WITH 1 INCREMENT BY 1; 

ALTER TABLE TECH_PDS 
ADD CONSTRAINT FK_PDS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
 */
public class PdsDto implements Serializable {
	private int seq;
	private String id;
	private String filename;
	private int parent;
	private String regdate;	//올린날짜
	
	public PdsDto() {
	}
	
	

	public PdsDto(int seq, String id, String filename, int parent, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.filename = filename;
		this.parent = parent;
		this.regdate = regdate;
	}





	public PdsDto(String id, String filename, int parent) {
		super();
		this.id = id;
		this.filename = filename;
		this.parent = parent;
	}

	
	public int getParent() {
		return parent;
	}



	public void setParent(int parent) {
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	
}
