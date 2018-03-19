package jobs_BBS5;

import java.io.Serializable;


/*
DROP TABLE newbbs5HWCodingPDSVO
CASCADE CONSTRAINT;

DROP SEQUENCE SEQ_newbbs5HWCodingPDSVO;

CREATE TABLE newbbs5HWCodingPDSVO(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	FILENAME VARCHAR2(50) NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_newbbs5HWCodingPDSVO
START WITH 1 INCREMENT BY 1; 

ALTER TABLE newbbs5HWCodingPDSVO
ADD CONSTRAINT FK_newbbs5HWCodingPDSVO_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

--아래 코드는 임시 코드
ALTER TABLE newbbs5HWCodingPDSVO
ADD CONSTRAINT FK_TSEQ_ID FOREIGN KEY(PARENT)
REFERENCES newbbs5HWCodingVO(SEQ);


SELECT B.SEQ,B.ID, B.TITLE,B.TAGNAME,B.CONTENT,B.WDATE,
		B.READCOUNT,B.LIKECOUNT,B.COMMENTCOUNT,B.SCRAPCOUNT,P.SEQ pdsseq,P.FILENAME,P.PARENT
		FROM newbbs5HWCodingVO B, newbbs5HWCodingPDSVO P 
		WHERE B.SEQ = P.PARENT AND B.SEQ = 37;
 */

//HW 자료실 부분.
public class newbbs5HWCodingPDSVO implements Serializable {

	private int seq;
	private String id;
	private String filename;
	private int parent;
	private String regdate;	//올린날짜
	
	
	public newbbs5HWCodingPDSVO() {
	}	

	public newbbs5HWCodingPDSVO(int seq, String id, String filename, int parent, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.filename = filename;
		this.parent = parent;
		this.regdate = regdate;
	}





	public newbbs5HWCodingPDSVO(String id, String filename, int parent) {
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
