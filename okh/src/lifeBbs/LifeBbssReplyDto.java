package lifeBbs;

import java.io.Serializable;
/*
DROP TABLE LIFEBBSREPLY
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_LIFEBBSREPLY;

CREATE TABLE LIFEBBSREPLY(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	CONTENT VARCHAR2(4000) NOT NULL,
	UP NUMBER(8) NOT NULL,
	DOWN NUMBER(8) NOT NULL,
	UPID VARCHAR2(4000),
	DOWNID VARCHAR2(4000),
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	BBSSEQ NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_LIFEBBSREPLY
START WITH 1 INCREMENT BY 1;

ALTER TABLE LIFEBBSREPLY
ADD CONSTRAINT FK_LIFEBBSREPLY_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

ALTER TABLE LIFEBBSREPLY
ADD CONSTRAINT FK_LIFEBBSREPLY_BBSSEQ FOREIGN KEY(BBSSEQ)
REFERENCES LIFEBBS(SEQ);
*/

public class LifeBbssReplyDto {
	
	private int seq;
	private String id;
	
	private int ref;
	private int step;
	private int depth;
	
	private String content;
	private int up;
	private int down;
	private String upid;
	private String downid;
	private String wdate;
	private int parent;
	
	private int del;
	private int bbsseq;

	public LifeBbssReplyDto(int seq, String id, int ref, int step, int depth, String content, int up, int down,
			String upid, String downid, String wdate, int parent, int del, int bbsseq) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.content = content;
		this.up = up;
		this.down = down;
		this.upid = upid;
		this.downid = downid;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.bbsseq = bbsseq;
	}

	public LifeBbssReplyDto(int seq, String id, String content, int up, int down, String upid, String downid,
			String wdate, int del, int bbsseq) {
		super();
		this.seq = seq;
		this.id = id;
		this.content = content;
		this.up = up;
		this.down = down;
		this.upid = upid;
		this.downid = downid;
		this.wdate = wdate;
		this.del = del;
		this.bbsseq = bbsseq;
	}

	public LifeBbssReplyDto(String id, String content, int bbsseq) {
		super();
		this.id = id;
		this.content = content;
		this.bbsseq = bbsseq;
	}

	public LifeBbssReplyDto(int seq, String content) {
		super();
		this.seq = seq;
		this.content = content;
	}

	public LifeBbssReplyDto(int del, int bbsseq) {
		super();
		this.del = del;
		this.bbsseq = bbsseq;
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

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public String getUpid() {
		return upid;
	}

	public void setUpid(String upid) {
		this.upid = upid;
	}

	public String getDownid() {
		return downid;
	}

	public void setDownid(String downid) {
		this.downid = downid;
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

	public int getBbsseq() {
		return bbsseq;
	}

	public void setBbsseq(int bbsseq) {
		this.bbsseq = bbsseq;
	}

	@Override
	public String toString() {
		return "LifeBbssReplyDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth
				+ ", content=" + content + ", up=" + up + ", down=" + down + ", upid=" + upid + ", downid=" + downid
				+ ", wdate=" + wdate + ", parent=" + parent + ", del=" + del + ", bbsseq=" + bbsseq + "]";
	}

}
