package techbbs;

import java.io.Serializable;
/*
INSERT INTO TECHBBS 
(SEQ, ID, TITLE,TAGNAME, CONTENT, WDATE, DEL, READCOUNT, LIKECOUNT,COMMENTCOUNT, SCRAPCOUNT) 
VALUES(SEQ_TECHBBS.NEXTVAL, 'aa', 'aa','aa','aa',SYSDATE,0,0,0,0,0 );

DROP TABLE TECHBBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_TECHBBS;

CREATE TABLE TECHBBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	TAGNAME VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	LIKECOUNT NUMBER(8) NOT NULL,
	LIKEID VARCHAR2(1000),
	DISLIKEID VARCHAR2(1000),
	COMMENTCOUNT NUMBER(8) NOT NULL,
	SCRAPCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_TECHBBS
START WITH 1 INCREMENT BY 1;

ALTER TABLE TECHBBS
ADD CONSTRAINT FK_TECHBBS_ID FOREIGN KEY(ID)
REFERENCES OKHMEM(ID);

SELECT B.SEQ,B.ID, B.TITLE,B.TAGNAME,B.CONTENT,B.WDATE, 
B.READCOUNT,B.LIKECOUNT,B.COMMENTCOUNT,B.POINT,B.SCRAPCOUNT,P.SEQ pdsseq,P.FILENAME,P.PARENT 
FROM TECHBBS B,TECH_PDS P  
WHERE B.ID=P.ID AND B.SEQ=1 AND B.SEQ=P.PARENT; 

SELECT SEQ,ID, TITLE,TAGNAME,CONTENT,WDATE,DEL,  
READCOUNT,LIKECOUNT,COMMENTCOUNT,POINT,SCRAPCOUNT 
FROM TECHBBS 
WHERE SEQ=1
 */
public class TechbbsDto implements Serializable {
	private int seq;
	private String id;
	
	private String title;
	private String tagname;
	private String content;
	private String wdate;
	
	private int del;	// 삭제
	private int readcount;
	private int likecount;
	private String likeid;
	private String dislikeid;
	private int commentcount;
	private int scrapcount;
	
	private String filename;
	private int parent;
	private int pdsseq;
	private int pdsys;
	
	private int likeidyn;
	private int dislikeidyn;
	
	
	public TechbbsDto(int likeidyn, int dislikeidyn) {
		super();
		this.likeidyn = likeidyn;
		this.dislikeidyn = dislikeidyn;
	}

	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int likeidyn) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.likeidyn = likeidyn;
	}
	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate,
			int readcount, int likecount, String likeid, String dislikeid, int dislikeidyn) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.dislikeidyn = dislikeidyn;
	}
	public int getLikeidyn() {
		return likeidyn;
	}

	public void setLikeidyn(int likeidyn) {
		this.likeidyn = likeidyn;
	}

	public int getDislikeidyn() {
		return dislikeidyn;
	}

	public void setDislikeidyn(int dislikeidyn) {
		this.dislikeidyn = dislikeidyn;
	}

	public TechbbsDto(int seq, String likeid, String dislikeid) {
		super();
		this.seq = seq;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
	}

	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int readcount,
			int likecount, String likeid, String dislikeid, int commentcount, int scrapcount, String filename,
			int parent, int pdsseq, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int commentcount, int scrapcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
	}

	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int likecount, String likeid, String dislikeid, int commentcount, int scrapcount,
			String filename, int parent, int pdsseq, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.likecount = likecount;
		this.likeid = likeid;
		this.dislikeid = dislikeid;
		this.commentcount = commentcount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	//조인으로얻어온값들
	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, 
			int readcount, int commentcount, int likecount, int scrapcount, String filename, int parent,
			int pdsseq,int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
		this.filename = filename;
		this.parent = parent;
		this.pdsseq = pdsseq;
		this.pdsys = pdsys;
	}

	//자료유무판단
	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int commentcount, int likecount, int scrapcount, int pdsys) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
		this.pdsys = pdsys;
	}


	public int getPdsys() {
		return pdsys;
	}


	public void setPdsys(int pdsys) {
		this.pdsys = pdsys;
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


	public int getPdsseq() {
		return pdsseq;
	}


	public void setPdsseq(int pdsseq) {
		this.pdsseq = pdsseq;
	}




	//게시판dto
	public TechbbsDto(int seq, String id, String title, String tagname, String content, String wdate, int del,
			int readcount, int commentcount, int likecount, int scrapcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.commentcount = commentcount;
		this.likecount = likecount;
		this.scrapcount = scrapcount;
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

	//글쓰기할때dto
	public TechbbsDto(String id, String title, String tagname, String content) {
		super();
		this.id = id;
		this.title = title;
		this.tagname = tagname;
		this.content = content;
	}


	public int getCommentcount() {
		return commentcount;
	}


	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}


	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
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
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
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
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getScrapcount() {
		return scrapcount;
	}
	public void setScrapcount(int scrapcount) {
		this.scrapcount = scrapcount;
	}
	
	
}
