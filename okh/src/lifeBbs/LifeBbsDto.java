package lifeBbs;

import java.io.Serializable;

/*
DROP TABLE LIFEBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_LIFEBBS;

CREATE TABLE LIFEBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	TAG VARCHAR2(200),
	FILENAME VARCHAR2(50),
	UP NUMBER(8) NOT NULL,
	UPID VARCHAR2(4000),
	DOWNID VARCHAR2(4000),
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	DOWNCOUNT NUMBER(8) NOT NULL,
	
	COUNTREPLY NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_LIFEBBS
START WITH 1 INCREMENT BY 1;

ALTER TABLE LIFEBBS
ADD CONSTRAINT FK_LIFEBBS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);
*/

public class LifeBbsDto implements Serializable {
	
	private int seq;
	private String id;
	
	private int ref;		// 그룹번호
	private int step;		// 열번호
	private int depth;		// 깊이
	
	private String title;
	private String content;
	private String tag;
	private String filename;
	private int up;			// 추천
	private String upid;
	private String downid;
	private String wdate;	// 작성일
	private int parent;		// 부모글
	
	private int del;		// 삭제여부 삭제X : 0 삭제O : 1
	private int readcount;
	private int downcount;

	private int countreply;
	
	public LifeBbsDto() {
	}

	public LifeBbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String tag,
			String filename, int up, String upid, String downid, String wdate, int parent, int del, int readcount,
			int downcount, int countreply) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
		this.up = up;
		this.upid = upid;
		this.downid = downid;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
		this.downcount = downcount;
		this.countreply = countreply;
	}

	public LifeBbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String tag,
			String filename, int up, String wdate, int parent, int del, int readcount, int downcount,
			int countreply) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
		this.up = up;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
		this.downcount = downcount;
		this.countreply = countreply;
	}

	public LifeBbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String tag,
			String filename, int up, String wdate, int parent, int del, int readcount, int downcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
		this.up = up;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
		this.downcount = downcount;
	}

	public LifeBbsDto(int seq, String title, String content, String tag, String filename) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
	}

	public LifeBbsDto(String id, String title, String content, String tag, String filename) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.tag = tag;
		this.filename = filename;
	}

	public LifeBbsDto(int up, String upid, String downid) {
		super();
		this.up = up;
		this.upid = upid;
		this.downid = downid;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
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

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public int getCountreply() {
		return countreply;
	}

	public void setCountreply(int countreply) {
		this.countreply = countreply;
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

	@Override
	public String toString() {
		return "LifeBbsDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth
				+ ", title=" + title + ", content=" + content + ", tag=" + tag + ", filename=" + filename + ", up=" + up
				+ ", upid=" + upid + ", downid=" + downid + ", wdate=" + wdate + ", parent=" + parent + ", del=" + del
				+ ", readcount=" + readcount + ", downcount=" + downcount + ", countreply=" + countreply + "]";
	}

	

}
