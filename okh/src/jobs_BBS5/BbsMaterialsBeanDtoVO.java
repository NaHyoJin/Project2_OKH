package jobs_BBS5;

import java.io.Serializable;

///////////////////////이게 PDS다.

/*
	DROP TABLE BbsMaterialsBeanDtoVO
	CASCADE CONSTRAINT;
	
	DROP SEQUENCE SEQ_BbsMaterialsBeanDtoVO;
	
	CREATE TABLE BbsMaterialsBeanDtoVO(
		SEQ NUMBER(8) PRIMARY KEY,
		ID VARCHAR2(50) NOT NULL,
		TITLE VARCHAR2(200) NOT NULL,
		CONTENT VARCHAR2(4000) NOT NULL,
		FILENAME VARCHAR2(50) NOT NULL,
		READCOUNT NUMBER(8) NOT NULL,
		DOWNCOUNT NUMBER(8) NOT NULL,
		REGDATE DATE NOT NULL	--등록일.
	);
	
	CREATE SEQUENCE SEQ_BbsMaterialsBeanDtoVO
	START WITH 1 INCREMENT BY 1;
	
	ALTER TABLE BbsMaterialsBeanDtoVO
	ADD CONSTRAINT FK_BbsMaterialsBeanDtoVO_ID FOREIGN KEY(ID)
	REFERENCES okhmem(ID);
*/
public class BbsMaterialsBeanDtoVO implements Serializable {//자료실 bean 테이블 부분.

	private int seq;
	private String id;
	private String title;
	private String content;
	private String filename;
	private int readcount;
	private int downcount;
	private String regdate;
	
	
	public BbsMaterialsBeanDtoVO() {
		// TODO Auto-generated constructor stub
	}
	
	public BbsMaterialsBeanDtoVO(int seq, String id, String title, String content, String filename, int readcount,
			int downcount, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.regdate = regdate;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BbsMaterialsBeanDtoVO [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", filename=" + filename + ", readcount=" + readcount + ", downcount=" + downcount + ", regdate="
				+ regdate + "]";
	}
	
	
	
}
