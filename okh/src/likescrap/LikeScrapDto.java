package likescrap;

import java.io.Serializable;
/*

DROP TABLE LIKESCRAP
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_LIKESCRAP;

CREATE TABLE LIKESCRAP(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	WHATBBS VARCHAR2(50) NOT NULL,			
	LIKEID VARCHAR2(200),
	DISLIKEID VARCHAR2(200),
	SCRAPID VARCHAR2(200),
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_LIKESCRAP
START WITH 1 INCREMENT BY 1;

ALTER TABLE LIKESCRAP
ADD CONSTRAINT FK_LIKESCRAP_PARENT FOREIGN KEY(PARENT)
REFERENCES TECHBBS(SEQ);
ALTER TABLE LIKESCRAP
ADD CONSTRAINT FK_LIKESCRAP_PARENT FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
 */
public class LikeScrapDto implements Serializable {
	private int seq;	
	private String whatbbs;
	private String likeid;
	private String dislikeid;
	private String scrapid;
	private String wdate;
	private int parent;
	public LikeScrapDto(int seq, String whatbbs, String likeid, String dislikeid, String scrapid, String wdate,
			int parent) {
		super();
		this.seq = seq;
		this.whatbbs = whatbbs;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.scrapid = scrapid;
		this.wdate = wdate;
		this.parent = parent;
	}
	
	//추가할때
	public LikeScrapDto(String whatbbs, String likeid, String dislikeid, String scrapid, int parent) {
		super();
		this.whatbbs = whatbbs;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.scrapid = scrapid;
		this.parent = parent;
	}
	

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWhatbbs() {
		return whatbbs;
	}
	public void setWhatbbs(String whatbbs) {
		this.whatbbs = whatbbs;
	}
	public String getLikeid() {
		return likeid;
	}
	public void setLikeid(String likeid) {
		this.likeid = likeid;
	}
	public String getDislikeid() {
		return dislikeid;
	}
	public void setDislikeid(String dislikeid) {
		this.dislikeid = dislikeid;
	}
	public String getScrapid() {
		return scrapid;
	}
	public void setScrapid(String scrapid) {
		this.scrapid = scrapid;
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
	
	
	
	
	
}
